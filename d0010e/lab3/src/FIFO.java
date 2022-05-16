import java.util.NoSuchElementException;
import java.util.ArrayList;

public class FIFO implements Queue{
	
	ArrayList<Object> lst = new ArrayList<Object>();
	int lstMaxSize = 0;

	public void add(Object arg0) {
		lst.add(arg0);
		lstMaxSize += 1;
	}

	public Object first() throws NoSuchElementException {
		if(lst.size() < 1) {
			throw new NoSuchElementException();
		}
		return lst.get(0);
	}

	public boolean isEmpty() {
		if (lst.isEmpty()) {
			return true;
		}
		else {
			return false;
		}
	}

	public void removeFirst() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		else {
			lst.remove(lst.get(0));
		}
	}

	public int size() {
		return lst.size();
	}
	
	public int maxSize() {
		return lstMaxSize;
	}
	
	public String toString() {
        String returnValue = "";
        for (int i = 0; i < lst.size(); i++) {
        	String newValue = "(" + String.valueOf(lst.get(i)) + ") ";
            returnValue = returnValue + newValue;
		}
        return ("Queue: " + returnValue);
	}
	
	public boolean equals(Object f) throws ClassCastException {
		if(f.getClass() != this.getClass()) {
			throw new ClassCastException();
		}
		
		if (((FIFO) f).lst.size() != this.lst.size()) {
			return false;
		}
		
		for (int i = 0; i < ((FIFO) f).lst.size(); i++) {
			if ((this.lst.get(i) != null && ((FIFO) f).lst.get(i) == null) || (this.lst.get(i) == null && ((FIFO) f).lst.get(i) != null)) {
				return false;
			}
			
			else if (this.lst.get(i) != null) {
				if(!this.lst.get(i).equals(((FIFO) f).lst.get(i))) {
					return false;
				}
			}
		}
		return true;
	}
}
