import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.*;


public class RPNStack {

	public static void main(String[] args) throws FileNotFoundException, Exception{
		// TODO Auto-generated method stub
		Scanner in = new Scanner(openFile());

		ArrayList<Token> tokens = new ArrayList<Token>();
		Stack<Integer> stack = new Stack<>();

		int currentX, currentY; //expressao no formato x <operador> y
		char currentOperator;
		char currentSymbol;

		while (in.hasNext()) {//ler até não ter mais nada

			if(in.hasNextInt()) {//ler inteiros
				String numero = String.valueOf(in.nextInt()); 
				Token numToken = new Token(TokenType.NUM, numero);//tipo do token será um número
				tokens.add(numToken);

			}else {//ler símbolos
				currentSymbol = in.next().charAt(0);

				if (currentSymbol == '+') {	

					Token plusToken = new Token(TokenType.PLUS, String.valueOf(currentSymbol));//adição
					tokens.add(plusToken);

				}else if (currentSymbol == '-') {

					Token minusToken = new Token(TokenType.MINUS, String.valueOf(currentSymbol));//subtração
					tokens.add(minusToken);

				}else if (currentSymbol == '*') {

					Token starToken = new Token(TokenType.STAR, String.valueOf(currentSymbol));//multiplicação
					tokens.add(starToken);

				}else if  (currentSymbol == '/'){//divisão

					Token slashToken = new Token(TokenType.SLASH, String.valueOf(currentSymbol));
					tokens.add(slashToken);

				}else {
					throw new Exception("Error: Unexpected character: " + currentSymbol);
				}
			}
		}
		//a partir da lista de tokens, sera realizada a interpretacao das expressoes com uma pilha
		for (int i = 0; i < tokens.size(); i++) {//itera por todo o array de tokens formado antes

			Token currentToken = tokens.get(i);
			TokenType currentType = currentToken.type;
			System.out.println(currentToken.toString());

			if (currentType == TokenType.NUM) {
				int inteiro = Integer.parseInt(currentToken.lexeme);				
				stack.push(inteiro);

			}else {
				currentY = stack.pop();
				currentX = stack.pop();
				//faz cada operação e coloca na pilha o resultado para que as outras continuem sendo feitas
				if (currentType == TokenType.PLUS){
					stack.push(currentX + currentY);

				}else if (currentType == TokenType.MINUS){
					stack.push(currentX - currentY);

				}else if (currentType == TokenType.STAR){
					stack.push(currentX * currentY);

				}else if (currentType == TokenType.SLASH){
					stack.push(currentX / currentY);
				}
			}
		}

		System.out.println("\nSaida: " + stack.pop());
	}

	private static File openFile() {
		URL url = RPNStack.class.getClassLoader().getResource("Calc1.stk");
		assert url != null;
		return new File(url.getPath());
	}

}
