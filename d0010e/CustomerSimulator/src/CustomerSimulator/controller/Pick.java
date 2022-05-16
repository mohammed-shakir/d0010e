package CustomerSimulator.controller;

import CustomerSimulator.models.Customer;
import CustomerSimulator.models.Store;

public class Pick extends Event {
	private double timeStarted;
	private Store store;
	private Customer customer;
	private EventQueue events;
	private int registry;
	public Pick(Store s, EventQueue q, Customer c, double t, double ts) {
		this.store = s;
		this.events = q;
		this.customer = c;
		this.timeStarted = t;
	}
	public void run() {
		this.registry = store.toQueue(customer.getID());
		double newTime = this.timeStarted + this.store.getNextPayTime();
		double payNextTime = this.store.getNextPayTime();
		store.getQueue(registry).setQueueTime(payNextTime);
		if(this.store.getQueue(registry).getPayQueueSize() > 1) {
			newTime = store.getQueue(registry).getTotalTime() + payNextTime;
		}
		store.setEventName("Pick");
		store.setCurrentTime(this.timeStarted);
        this.store.setFreeTime();
		store.update();
		events.addEvent(new Pay(this.store, this.events, this.customer, newTime, registry, payNextTime));
	}
	public double getTime() {
		return this.timeStarted;
	}
	
	public Customer getCustomer() {
		return this.customer;
	}
}