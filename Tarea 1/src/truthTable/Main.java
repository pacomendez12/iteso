package truthTable;

import java.util.Scanner;

public class Main {
	
	public static void main(String args[]) {
		
		
		String input;
		if (args.length == 2) {
			input = args[1];
		} else {
			System.out.print("Ingresa la formula: ");
			@SuppressWarnings("resource")
			Scanner s = new Scanner(System.in);
			input = s.nextLine();
		}
		
		Lexical lex = new Lexical(input);
		tokenLexPair tlp;
		while((tlp = lex.nextTokenLexPair()) != null) {
			System.out.println("> " + tlp.getToken() + " del tipo " + tlp.getLexema());
			
		}
	}
}
