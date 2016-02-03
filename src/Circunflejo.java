import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Circunflejo {
	private static int CIRCUNFLEJO = 94;
	
	public static void main(String args[]) throws IOException{
		Scanner scan = new Scanner(System.in);
		
		FileWriter fw = new FileWriter("salida.txt");
		BufferedWriter bw = new BufferedWriter(fw);
		PrintWriter salida = new PrintWriter(bw);
		
		while(scan.hasNext()){
			String nextLine = scan.nextLine();
			List<Integer> obtenerCodigos = obtenerCodigos(nextLine);
			for (int integer : obtenerCodigos) {
				salida.print(integer + ",");
			}
			salida.println();
		}
		salida.close();
	}
	
	private static boolean esCir(int a){
		return a == CIRCUNFLEJO;
	}
	
	private static boolean esASCIIHexadecimalLC(int a){
		return ((a >= 48 && a <= 57) || (a >= 97 && a <= 102));
	}	
	
	private static List<Integer> obtenerCodigos(String entrada){
		
		int tamEntrada = entrada.length();
		
		if(tamEntrada == 0)
			System.out.println("{}");
		
		if(tamEntrada == 1)
			System.out.println(entrada.charAt(0));
		
		char[] charArray = entrada.toCharArray();
		List<Integer> resp = new ArrayList<Integer>();
		
		for (int i = 0; i < tamEntrada; i++) {
			int intc1 = charArray[i];
			if(esCir(intc1) && (i+2) < tamEntrada){
				char c2 = charArray[i+2];
				int intc2 =  charArray[i+2];
				if((i+3) < tamEntrada && esASCIIHexadecimalLC(intc2) && esASCIIHexadecimalLC((int)charArray[i+3])){
					char c3 = charArray[i+3];
					String aux = Character.toString(c2) + Character.toString(c3) ;
					int hex = Integer.parseInt(aux, 16);
					if(esCir(hex)){
						charArray[i+3] = '^';
						i = i+2;
					}else{
						resp.add(hex);
						i = i+3;
					}
				}else if((i+2) < tamEntrada){
					if(intc2 <= 63){
						resp.add(intc2 + 64);
					}else{
						resp.add(intc2 - 64);
					}
					i = i+2;
				}
			}else{
				resp.add(intc1);
			}
				
		}
		return resp;
	}

}
/*
define("CIRCUNFLEJO",94);

$entrada = file_get_contents('entrada.txt');
$entradas = explode("\n",$entrada);

$j = count($entradas);
for($i = 0; $i < $j ; $i++){
	$entradas[$i] = substr($entradas[$i],1,strlen($entradas[$i]));
	$entradas[$i] = substr($entradas[$i],0,(strlen($entradas[$i])-1));
	$entradas[$i] = trim($entradas[$i]);
	
	$codigos = obtenerCodigos($entradas[$i]);
	echo "con la entrada: " . $entradas[$i] . "\n";
	print_r($codigos);
}


function obtenerCodigos($entrada){
	$tamanoCadena = mb_strlen($entrada);

	if($tamanoCadena == 0)
		die("{}\n");
	if($tamanoCadena == 1)
		die("{".ord(mb_substr($entrada, 0, 1))."}\n");

	$arregloChars = preg_split('//', $entrada, -1, PREG_SPLIT_NO_EMPTY);

	$a[0] = 0;
	$aux = "";
	$aux2 = 0;
	$aux3 = 0;


	for ($i = 0; $i < $tamanoCadena; $i++) {
		$char = $arregloChars[$i];
		$intChar = ord($char);
		if(esCircunflejo($intChar) && ($i+1) < $tamanoCadena){
			$char1 =  $arregloChars[$i+1];
			$intChar1 = ord($char1);
			if(esCircunflejo($intChar1) && ($i+2) < $tamanoCadena){
				$char2 =  $arregloChars[$i+2];
				$intChar2 = ord($char2);
				$char3 =  $arregloChars[$i+3];
				$intChar3 = ord($char3);
				if(($i+3) < $tamanoCadena && esASCIIHexadecimalLC($intChar2) && esASCIIHexadecimalLC($intChar3)) {
					$aux = $char2.$char3;
					$aux2 = hexdec($aux); 
					if(esCircunflejo($aux2)){
						$arregloChars[$i+3] = "^";
						$i = $i+2;
					}else{
						$a[$aux3] = $aux2;
						$aux3++;
						$i = $i +3;
					}
				}else if($i + 2 < $tamanoCadena){
					if($intChar2 <= 63){
						$a[$aux3] = $intChar2 + 64;
						$aux3++;

					}else{
						$a[$aux3] = $intChar2 - 64;
						$aux3++;
					}
					$i = $i+2;
				}			
			}else{
				$a[$aux3] = $intChar;
				$aux3++;			
			}
		}else{
			$a[$aux3] = $intChar;
			$aux3++;			
		}
	}
	return $a;
}

function esCircunflejo($int){
	return ($int == CIRCUNFLEJO);
}
function esASCIIHexadecimalLC($int){
	return (($int >= 48 && $int <= 57) || ($int >= 97 && $int <= 102));
}
?>*/