package truthTable;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class atomicFormulas {

}

class formula {
	enum Type {VARIABLE, CONNECTOR};
	enum Connector {CONJUNCTION, DISJUNCTION, IMPLICATION, DOUBLE_IMPLICATION, NEGATION, INVALID};
	enum Position {LEFT, RIGHT};
	
	private formulaNode mRoot;
	private formulaNode mAuxNode;
	List<tokenLexPair> mList;
	
	
	formula() {
		mList = new LinkedList<tokenLexPair>();
	}
	
	
	
	public boolean createTree() {
		if (mList != null) {
			ListIterator<tokenLexPair> it = mList.listIterator();
			addNode(it);

			return true;
		} else {
			return false;
		}
	}
	
	private formulaNode addNode(ListIterator<tokenLexPair> it) {
		if (!it.hasNext()) {
			return null;
		} else {
			tokenLexPair tlp = it.next();
			formulaNode tmp;
			if (tlp.getLexema() == tokenLexPair.VARIABLE) {
				tmp = new formulaNode(tlp.getToken());
				mAuxNode = tmp;
				addNode(it);
				
			} else if (tlp.getLexema() == tokenLexPair.UNARY_CONNECTOR || 
					tlp.getLexema() == tokenLexPair.BINARY_CONNECTOR) {
				String token;
				token = tlp.getToken();
				if (token == "~") {
					
				} else {
					switch (tlp.getToken()) {
					case "|":
						tmp = new formulaNode(Connector.NEGATION);
						break;
					case "&":
						tmp = new formulaNode(Connector.NEGATION);
						break;
					case "->":
						tmp = new formulaNode(Connector.NEGATION);
						break;
					case "<->":
						tmp = new formulaNode(Connector.NEGATION);
						break;
					default:
						tmp = new formulaNode(Connector.INVALID);
						break;
					}
					
					tmp.setElement(Position.LEFT, mAuxNode);
					tlp = it.next();
					formulaNode tmp2 = addNode(it);
					tmp.setElement(Position.LEFT, tmp2);
					return tmp;
				}
				
			}
			
		}
		return null;
	}
	
	
	class formulaNode {
		public formulaNode left;
		public formulaNode right;
		private String var_elem;
		private Connector con_elem;
		private Type type;
		
		public formulaNode(String var) {
			var_elem = var;
			type = Type.VARIABLE;
		}
		
		public formulaNode(Connector con) {
			con_elem = con;
			type = Type.CONNECTOR;
		}
		
		public void setElement(Position pos, formulaNode val) {
			if (pos == Position.LEFT) {
				left = val;
				
			} else if (pos == Position.RIGHT) {
				left = val;
			}
		}
		
		public Type typeOfNode() {
			return type;
		}
		
		public String getVariable() {
			return var_elem;
		}
		
		public Connector getConnector() {
			return con_elem;
		}
	}
	
}