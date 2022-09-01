import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Stack;

public class RPNStack {

	public static void main(String[] args) throws FileNotFoundException, Exception{
		// TODO Auto-generated method stub
		Scanner in = new Scanner(openFile());

		ArrayList<String> entrada = new ArrayList<String>();
		Stack<Integer> stack = new Stack<>();
		
		while(in.hasNext()){
			String linha = in.nextLine();
			entrada.add(linha);
		}

		Integer saida = 0;
		Iterator<String> iterator = entrada.iterator();

		for (int i = 0; i < entrada.size(); i++) {//itera por todo o array de entrada formado antes
			
			String operador = iterator.next();
			
			if(stack.size()<=1){

				saida = Integer.parseInt(operador);

			} else{

				Integer num2 = stack.pop();
				Integer num1 = stack.pop();
	
				if(operador.equals("+")){
					saida = num1 + num2;
				} else if (operador.equals("-")){
					saida = num1 - num2;
				}else if (operador.equals("/")){
					saida = num1 / num2;
				}else if (operador.equals("*")){
					saida = num1 * num2;
				}
			}
			
			stack.add(saida);
		}

		System.out.println("\nThe final answer is " + stack.pop());
	}

	private static File openFile() {
		URL url = RPNStack.class.getClassLoader().getResource("Calc1.stk");
		assert url != null;
		return new File(url.getPath());
	}

}
