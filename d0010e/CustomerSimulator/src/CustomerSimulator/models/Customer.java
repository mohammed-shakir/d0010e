package CustomerSimulator.models;

public class Customer {
	private int ID;
	private double pickTime;
	private double payTime;
	
	public Customer(int ID, Store storeState, boolean check) {
        this.ID = ID;
        
        if (check) {
        	this.pickTime = storeState.getNextPickTime();
        	this.payTime = storeState.getNextPayTime();
        }
    }
	
	public int getID() {
		return this.ID;
	}
}
