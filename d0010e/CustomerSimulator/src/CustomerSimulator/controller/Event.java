package CustomerSimulator.controller;

import CustomerSimulator.models.Customer;

public abstract class Event {
	abstract public void run();
	abstract public double getTime();
	abstract public Customer getCustomer();
}
