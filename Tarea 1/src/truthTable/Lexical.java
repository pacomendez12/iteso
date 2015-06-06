/**
 * This is an absolutely very simple Lexical class
 * */
package truthTable;

public class Lexical {
	private String mInput;
	private int mPointer;
	private char [] mInputChar;
	
	Lexical() {
		this("");	
	}
	
	Lexical(String input) {
		setInput(input);
		mPointer = 0;
	}
	
	
	public void setInput(String input) {
		mInput = input;
		mInputChar = mInput.toCharArray();
	}
	
	public tokenLexPair nextTokenLexPair() {
		if (mPointer < mInput.length()) {
			if (mInputChar[mPointer] == ' ') {
				mPointer++;
				return nextTokenLexPair();
			} else {
				char [] tmpArray = new char[3];
				char c = mInputChar[mPointer];
				int lex = tokenLexPair.INVALID;
				int length = 1;
				String token;
				
				if ('-' == c) {
					/* I am not going to validate the input
					 * but this input is for an implication
					 * and this will be the complete simbol: -> 
					 * */
					tmpArray[0] = c;
					tmpArray[1] = mInputChar[mPointer + 1];
					mPointer += 2;
					length = 2;
				} else if ('<' == c) {
					/* This code is for a double implication and
					 * the shape of the simbol will be: <->*/
					tmpArray[0] = c;
					tmpArray[1] = mInputChar[mPointer + 1];
					tmpArray[2] = mInputChar[mPointer + 2];
					mPointer += 2;
					length = 3;
				} else {
					/* It's a symbol with only one element*/
					tmpArray[0] = c;
					mPointer++;
					length = 1;
				}
				
				token = new String(tmpArray, 0, length);
				lex = tokenLexPair.wichLexIs(token);
				
				return new tokenLexPair(token, lex) ;
			}
		}
		return null;
	}

}

class tokenLexPair {
	private String mToken;
	private int mLexema;
	
	/* Valid lexemas: */
	public static final int INVALID = 0;
	public static final int VARIABLE = 1;
	public static final int UNARY_CONNECTOR = 2;
	public static final int BINARY_CONNECTOR = 3;
	public static final int FIRST_PARENTHESES = 4;
	public static final int SECOND_PARENTHESES = 5;
	
	
	
	public tokenLexPair(String token, int lexema) {
		mToken = token;
		mLexema = lexema;
	}
	
	public String getToken() {
		return mToken;
	}
	
	public int getLexema() {
		return mLexema;
	}
	
	public static int wichLexIs(String token) {
		switch(token) {
		case "~": 
			return UNARY_CONNECTOR;
		case "&": case "|": case "->": case "<->": 
			return BINARY_CONNECTOR;
		case "(":
			return FIRST_PARENTHESES;
		case ")":
			return SECOND_PARENTHESES;
		default:
			return VARIABLE;
		}
	}
	
	
}
