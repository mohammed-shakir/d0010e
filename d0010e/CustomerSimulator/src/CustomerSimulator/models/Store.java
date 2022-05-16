package CustomerSimulator.models;

import java.util.ArrayList;
import java.util.Observable;

import CustomerSimulator.controller.Event;
import CustomerSimulator.controller.EventQueue;
import CustomerSimulator.views.StoreView;
import random.ExponentialRandomStream;
import random.UniformRandomStream;

public class Store extends Observable{
	private boolean simRunning;
	private int maxPeople;
	private double timeOpen;
    private double lambda;
    private double[] pickMinMax;
    private double[] payMinMax;
    private long seed;
    private int peopleInStore = 0;
    private int totalAmountOfCustomers = 0;
    private int missedCustomers = 0;
    private ArrayList<Customer> CustomerID = new ArrayList<Customer>();
    private double currentTime = 0.0;
    private int totAmOfRegs;
    private ExponentialRandomStream ArrivalTime;
    private UniformRandomStream PickTime;
    private UniformRandomStream PayTime;
    private ArrayList<PayQueue> payQueue = new ArrayList<PayQueue>();
    private double[] cashierTotalTime;
    private int totPeopleQueue = 0;
    private String eventName = "";
    private EventQueue evQueue;
    private double cashierFreeTime = 0.0;
    private double freeTime = 0.0;
    private double prevTime;
    private int money = 0;
    private int max = 0;
	public Store(double timeOpen, int cashiers, int maxPeople, double lambda, double[] pickMinMax, double[] payMinMax, long seed, EventQueue evQueue) {
		this.totAmOfRegs = cashiers;
        this.maxPeople = maxPeople;
        this.lambda = lambda;
        this.pickMinMax = pickMinMax;
        this.payMinMax = payMinMax;
        this.seed = seed;
        this.ArrivalTime = new ExponentialRandomStream(lambda,seed);
        this.PickTime = new UniformRandomStream(pickMinMax[0], pickMinMax[1], seed);
        this.PayTime = new UniformRandomStream(payMinMax[0], payMinMax[1], seed);
        this.simRunning = true;
        this.timeOpen = timeOpen;
        for(int i = 0; i < totAmOfRegs; i++) {
        	payQueue.add(new PayQueue(i));
        }
        this.cashierTotalTime = new double[cashiers];
        this.evQueue = evQueue;
        addObserver(new StoreView());
	}
	
	public double getNextArrived() {
        return ArrivalTime.next();
    }
	
	public double getNextPickTime() {
        return PickTime.next();
    }

    public double getNextPayTime() {
        return PayTime.next();
    }
    public void closeStore() {
    	this.simRunning = false;
    }
    
    public double getMaxTime() {
    	return timeOpen;
    }
    
    public boolean getStatus() {
    	return this.simRunning;
    }
    
    public int getNewCustomerId() {
    	return this.CustomerID.size();
    }
    public void addCustomer(Customer c) {
    	this.CustomerID.add(c);
    }
    public int getPeopleInStore() {
        return peopleInStore;
    }

    public int getMaxPeople() {
        return this.maxPeople;
    }

    public void increasePeopleInStore() {
        peopleInStore++;
    }

    public void decreasePeopleInStore() {
        peopleInStore--;
    }
    
    public int toQueue(int id) {
    	int leastAmmountID = 0;
    	int leastAmmountQueue = 0;
    	for(int i = 0; i < payQueue.size(); i++) {
    		if(i == 0) {
    			leastAmmountID = i;
    			leastAmmountQueue = payQueue.get(i).getPayQueueSize();
    		}
    		else if(payQueue.get(i).getPayQueueSize() < leastAmmountQueue) {
    			leastAmmountID = i;
    			leastAmmountQueue = payQueue.get(i).getPayQueueSize();
    		}
    	}
    	if(payQueue.get(leastAmmountID).getPayQueueSize() > 0) {
    		this.totPeopleQueue++;
    	}
    	payQueue.get(leastAmmountID).toQueue(id);
    	return leastAmmountID;
    	
    }
    
    public PayQueue getQueue(int id) {
    	return payQueue.get(id);
    }
    
    public void increaseTotAmountOfCustomers() {
        totalAmountOfCustomers++;
    }
    
    public int getTotAmountOfCustomers() {
        return this.totalAmountOfCustomers;
    }

    public void increaseMissedCustomers() {
        missedCustomers++;
    }
    
    public int getTotmissedCustomers() {
        return missedCustomers;
    }
    

    public double setTotalTimeQueue() {
    	for(int i = 0; i < payQueue.size(); i++) {
    		this.cashierTotalTime[i] = payQueue.get(i).getTotalTime();    	
    	}

    	int sum = 0;
        for(double i: this.cashierTotalTime){
          sum += i;
        }
        return sum;
    }
    
    public double freeCashierTime() {
    	this.cashierFreeTime += (this.getFreeRegs()*this.currentTime);
    	return this.cashierFreeTime;
    }
    
    public void update() {
    	setChanged();
    	notifyObservers();
    }
    
    public int getAmOfRegs() {
    	return this.totAmOfRegs;
    }
    
    public double getLambda() {
    	return this.lambda;
    }
    
    public String getPickMinMax() {
    	return "["+this.pickMinMax[0]+", "+this.pickMinMax[1]+"]";
    }
    
    public String getPayMinMax() {
    	return "["+this.payMinMax[0]+", "+this.payMinMax[1]+"]";
    }
    
    public long getSeed() {
    	return this.seed;
    }
    
    public double getTime() {
    	return this.timeOpen;
    }
    
    public int TotPeopleInQueue() {
    	return this.totPeopleQueue;
    }
    
    public double TotPeopleInQueueTime() {
    	double totalQueueTime = 0.0;
    	for(int i = 0; i < this.payQueue.size(); i++) {
    		totalQueueTime += this.payQueue.get(i).getTotalTime();
    	}
    	return totalQueueTime;
    }
    
    public String getEventName() {
    	return eventName;
    }
    
    public void setEventName(String name) {
    	this.eventName = name;
    }
    public int getCurrentCustomerId() {
    	int id;
    	try {
    		id = this.evQueue.getFirst().getCustomer().getID();
    	}
    	catch(Exception error) {
    		id = -1;
    	}
    	return id;
    }
    
    public int getFreeRegs() {
    	int ammountFree = 0;
    	for(int i = 0; i < this.payQueue.size(); i++) {
    		if(this.payQueue.get(i).getPayQueueSize() < 1) {
    			ammountFree++;
    		}
    	}
    	return ammountFree;
    }
    
    public int getPeopleInLineTotal() {
    	int inQueue = 0;
    	for(int i = 0; i < this.payQueue.size(); i++) {
    		inQueue += this.payQueue.get(i).getPayQueueSize();
    	}
    	return inQueue;
    }
    
    public double getInLineTime() {
    	double lineTime = 0;
    	for(int i = 0; i < this.payQueue.size(); i++) {
    		lineTime += this.payQueue.get(i).getTotalTime();
    	}
    	return lineTime;
    }
    
    public int getQueueSize() {
    	int size = 0;
    	for(int i = 0; i < this.payQueue.size(); i++) {
    		size += this.payQueue.get(i).getPayQueueSize();
    	}
    	return size;
    }
    
    public String getQueue() {
    	String returnedString = "";
    	for(int i = 0; i < this.payQueue.size(); i++) {
    		returnedString += "Queue "+i+" : " + this.payQueue.get(i).getQueue().toString() + ", ";
    	}
    	returnedString = returnedString.substring(0, returnedString.length() - 2);
    	return returnedString;
    }
    
    public double getCurrentTime() {
    	return this.currentTime;
    }
    
    public void setCurrentTime(double time) {
    	this.prevTime = this.currentTime;
    	this.currentTime = time;
    }
    
    public void setFreeTime() {
    	for(int i = 0; i < this.payQueue.size(); i++) {
    		this.payQueue.get(i).setFreeTime(this.currentTime, this.prevTime);
    	}
    	
    }
    
    public double getFreeTime() {
    	double freeTime = 0;
    	for(int i = 0; i < this.payQueue.size(); i++) {
    		freeTime += this.payQueue.get(i).getFreeTime();
    	}
    	return freeTime;
    }
    
    public int getMoney() {
    	return money;
    }
    
    public void increasMoney() {
    	money++;
    }
    
    public EventQueue getEvQueue(){
    	return this.evQueue;
    }
    
    public int getPayQueueMaxSize() {
    	int inQueue = 0;
    	for(int i = 0; i < this.payQueue.size(); i++) {
    		inQueue += this.payQueue.get(i).getPayQueueSize();
    	}
    	if (inQueue > max) {
    		max = inQueue;
    	}
    	return max;
    }
}