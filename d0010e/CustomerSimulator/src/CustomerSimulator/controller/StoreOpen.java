package CustomerSimulator.controller;

import CustomerSimulator.models.Customer;
import CustomerSimulator.models.Store;

public class StoreOpen extends Event {
	private Store store;
	private double arrivedTime = 0;
	private EventQueue queue;
	
	public StoreOpen(Store s, EventQueue q) {
		this.store = s;
		this.queue = q;
	}
	
	public void run() {
		store.setEventName("Open");
		store.update();
		while(arrivedTime < store.getMaxTime()) {
			double nextArrived = store.getNextArrived();
			arrivedTime += nextArrived;
			if(arrivedTime < store.getMaxTime()) {
				queue.addEvent(new Arrive(this.store, this.queue, arrivedTime, nextArrived));
			}
		}
	}

	public double getTime() {
		return 0;
	}
	public Customer getCustomer() {
		return null;
	}
}