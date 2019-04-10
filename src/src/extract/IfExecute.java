package extract;
import java.util.*;

public class IfExecute {
	private Stack<Boolean> ifExecute=new Stack<>();
	private Boolean beforeExecute=true;
	
	public boolean peek(){
		if(ifExecute.isEmpty())
			return true;
		else
			return ifExecute.peek();
	}
	
	public void put(){
		if(ifExecute.isEmpty())
			beforeExecute=true;
		else
			beforeExecute=ifExecute.peek();
		if(!beforeExecute)
			ifExecute.push(false);
		else
			ifExecute.push(true);
	}
	
	public void set(Boolean value) {
		ifExecute.pop();
		if(beforeExecute)
			ifExecute.push(value);
		else
			ifExecute.push(false);
	}
	
	public void pop() {
		ifExecute.pop();
		if(ifExecute.isEmpty()){
			beforeExecute=true;
		}else{
			Boolean curOp=ifExecute.peek();
			ifExecute.pop();
			if(ifExecute.isEmpty())
				beforeExecute=true;
			else
				beforeExecute=ifExecute.peek();
			ifExecute.push(curOp);
		}
	}
}
