package CustomerSimulator.controller;

import CustomerSimulator.models.Customer;

import CustomerSimulator.models.Store;

public class Pay extends Event {
	private double timeStarted;
    private Store store;
    private Customer customer;
    private int registry;

    public Pay(Store s, EventQueue q, Customer c, double t, int r, double nt) {
        this.store = s;
        this.customer = c;
        this.timeStarted = t;
        this.registry = r;
    }
    public void run() {
    	this.store.getQueue(registry).removeFromQueue();
        this.store.setFreeTime();
        this.store.decreasePeopleInStore();
        this.store.setEventName("Pay");
        this.store.increasMoney();
        store.setCurrentTime(this.timeStarted);
        store.update();
    }
	public double getTime() {
		return this.timeStarted;
	}
	
	public Customer getCustomer() {
		return this.customer;
	}
}