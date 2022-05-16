package CustomerSimulator;


import CustomerSimulator.controller.EventQueue;
import CustomerSimulator.controller.StoreClose;
import CustomerSimulator.controller.StoreOpen;
import CustomerSimulator.models.Store;

public class Simulator {
	public void run(double timeOpen, int registersOpen, int maxCustomers, double lambda, double pickMin, double pickMax, double payMin, double payMax, long seed) {
		double[] pickMinMax = new double[2];
		double[] payMinMax = new double[2];
		pickMinMax[0] = pickMin; 
		pickMinMax[1] = pickMax;
		payMinMax[0] = payMin; 
		payMinMax[1] = payMax;
		EventQueue queue = new EventQueue();
		Store store = new Store(timeOpen, registersOpen, maxCustomers, lambda, pickMinMax, payMinMax, seed, queue);
		store.update();
		queue.addEvent(new StoreOpen(store, queue));
		queue.addEvent(new StoreClose(store, timeOpen));
		while(store.getStatus() || queue.getSize() != 0) {
			queue.getFirst().run();
			queue.removeEvent();
		}
		store.update();
	}
}
