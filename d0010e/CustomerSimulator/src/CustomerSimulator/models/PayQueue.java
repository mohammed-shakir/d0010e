package CustomerSimulator.models;

import java.util.ArrayList;

public class PayQueue {
	private double totalCustomerQueueTime = 0.0;
	private ArrayList<Integer> payQueue = new ArrayList<>();
	private int registry;
	private double freeTime = 0.0;
	
	public PayQueue(int r) {
		this.registry = r;
	}
	
	public void toQueue(int id) {
    	this.payQueue.add(id);
    }
    
    public int getFirstPay() {
    	return this.payQueue.get(0);
    }
    
    public void setQueueTime(double nextPayTime) {
    	this.totalCustomerQueueTime +=nextPayTime;
    }
    
    public int getPayQueueSize() {
    	return this.payQueue.size();
    }
    
    public void removeFromQueue() {
    	payQueue.remove(0);
    }
    
    public double getTotalTime() {
    	return this.totalCustomerQueueTime;
    }
    
    public ArrayList<Integer> getQueue(){
    	return this.payQueue;
    }
    
    public void setFreeTime(double currentTime, double prevTime) {
    	if(this.payQueue.size() < 1) {
    		freeTime+=currentTime-prevTime;
    	}
    }
    
    public double getFreeTime() {
    	return freeTime;
    }
}