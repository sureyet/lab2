package extract;

import java.util.HashMap;
import java.util.Map.Entry;

public class ValueStack {
	private HashMap<String, Integer> intStack = new HashMap<>();
	public int cur = 1;
	public int[] enemy = new int[1000];
	public int returnValue = -1;

	public HashMap<String, Integer> getIntStack() {
		return intStack;
	}

	public void setIntStack(HashMap<String, Integer> intStack) {
		this.intStack = intStack;
	}

	public int getInt(String key) {
		return intStack.get(key);
	}

	public void putInt(String key, int value) {
		intStack.put(key, value);
	}

	public void moveInt(String key) {
		intStack.remove(key);
	}

	public boolean containInt(String key) {
		return intStack.containsKey(key);
	}
	
	public void setEnemy(int value){
		enemy[cur]=value;
		cur++;
	}

	public void showValue() {
		for (Entry<String, Integer> entry : intStack.entrySet()) {
			System.out.println(entry.getKey() + ": " + String.valueOf(entry.getValue()));
		}
		System.out.println("Cur: "+String.valueOf(cur));
		System.out.println("return value: "+String.valueOf(returnValue));
		System.out.println();
	}
}
