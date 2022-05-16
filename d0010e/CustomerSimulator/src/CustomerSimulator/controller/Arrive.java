package CustomerSimulator.controller;

import CustomerSimulator.models.Customer;
import CustomerSimulator.models.Store;

public class Arrive extends Event {
	private Store store;
	private Customer customer;
	private EventQueue events;
	private double timeStarted;

	public Arrive(Store s, EventQueue q, double t, double ts) {
		this.store = s;
		this.events = q;
		this.timeStarted = t;
	}
	public void run() {
		
		if (this.store.getPeopleInStore() < this.store.getMaxPeople()) {
            this.store.increasePeopleInStore();
            this.store.increaseTotAmountOfCustomers();
            this.customer = new Customer(this.store.getNewCustomerId(),this.store, this.store.getPeopleInStore() < this.store.getMaxPeople());
            store.addCustomer(customer);
            store.setEventName("Arrive");
            store.setCurrentTime(this.timeStarted);
            this.store.setFreeTime();
            store.update();
            double nextPickTime = this.store.getNextPickTime();
            events.addEvent(new Pick(this.store, this.events, this.customer, this.timeStarted+nextPickTime, nextPickTime));
        }
		else {
			this.store.increaseMissedCustomers();
		}
	}
	
	public double getTime() {
		return this.timeStarted;
	}
	
	public Customer getCustomer() {
		return this.customer;
	}
}