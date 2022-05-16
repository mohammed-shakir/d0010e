package CustomerSimulator.controller;

import java.util.ArrayList;

public class EventQueue {
	private ArrayList<Event> events = new ArrayList<Event>();
	
	public void addEvent(Event e) {
		ArrayList<Event> newEventList = new ArrayList<Event>();
	      
	      int counter = 0;
	      
	      for(int i = 0; i < events.size(); i++) 
	      {
	         if(events.get(i).getTime() <= e.getTime()) 
	         {
	            newEventList.add(events.get(i));
	            counter ++;
	         }
	      }
	      
	      newEventList.add(e);
	      
	      for(int i = counter; i < events.size(); i++) 
	      {
	         if(events.get(i).getTime() > e.getTime()) 
	         {
	            newEventList.add(events.get(i));
	         }
	      }
	      
	      events = newEventList;
	}
	
	public Event getFirst() {
		return this.events.get(0);
	}
	
	public void removeEvent() {
		this.events.remove(0);
	}
	
	public int getSize() {
		return events.size();
	}
}
