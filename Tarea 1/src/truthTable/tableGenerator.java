package truthTable;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class tableGenerator {
	variablesManager vm;
	tableBase table;
	
	public tableGenerator() {
		vm = new variablesManager();
	}
	
	void createTable() {
		if (vm.quantity() < 1) {
			return;
		} else {
			table = new tableBase(vm);
		}
	}
}

class variablesManager {
	List<String> mVariables;
	
	public variablesManager() {
		mVariables = new LinkedList<String>();
	}
	
	public void addVariable(String var) {
		if (!mVariables.contains(var)) {
			mVariables.add(var);
		}
	}	
	
	public int quantity() {
		return mVariables.size();
	}
	
	public void printAllVariables() {
		ListIterator<String> it = mVariables.listIterator();
		System.out.println("All te variables:");
		while(it.hasNext()) {
			System.out.println(" - " + (String)it.next());
		}
	}
}


class tableBase {
	private boolean mTable[][];
	private variablesManager vm;
	
	public tableBase(variablesManager vm) {
		/* I'm creating a fixed table with the exact number of elements needed including result */
		this.vm = vm;
		mTable = new boolean[(int)Math.pow(2, vm.quantity())][vm.quantity() + 1];
	}
	
	public void setTableAt(int i, int j, boolean value) {
		mTable[i][j] = value;
	}
	
	public boolean getTableAt(int i, int j) {
		return mTable[i][j];
	}
	
}