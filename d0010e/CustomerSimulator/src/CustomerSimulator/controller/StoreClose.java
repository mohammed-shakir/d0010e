package CustomerSimulator.controller;

import CustomerSimulator.models.Customer;
import CustomerSimulator.models.PayQueue;
import CustomerSimulator.models.Store;

public class StoreClose extends Event {
	private Store store;
	private double timeStarted;
	
	public StoreClose(Store s, double t) {
		this.store = s;
		this.timeStarted = t;
	}

	public void run() {
		store.setCurrentTime(this.timeStarted);
		store.setEventName("Close");
		store.update();
		this.store.closeStore();
	}

	public double getTime() {
		return this.timeStarted;
	}
	public Customer getCustomer() {
		return null;
	}
}