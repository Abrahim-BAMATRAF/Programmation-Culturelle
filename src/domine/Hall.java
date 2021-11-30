package domine;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

public class Hall { //represent "salles" // Aggregate
	
	private int capacity; //could be replaced by value object
	
	private HashMap<Date,TimeSlot> timeSlots;
	private HashMap<Date,Event> events;
	
	public Hall(int cap){
		this.capacity = cap;
		this.timeSlots = new HashMap<>();
		this.events = new HashMap<>();
		
	}
	
	public int getCapacity(){
		return this.capacity;
	}
	
	public void addTimeSlot(TimeSlot slot)throws Exception{
		if(this.timeSlots.containsKey(slot.getDate())){
			throw new Exception("this date already has a timeSlot");
		}
		
		this.timeSlots.put(slot.getDate(), slot);
	}
	
	public TimeSlot getTimeSlot(Date d){
		return this.timeSlots.get(d);
	}
	
	public void addConcert(Concert con, TimeSlot slot)throws Exception{
		if(this.events.containsKey(slot.getDate())){
			throw new Exception("this timeSlot is already alocated");
		}
		if(!this.timeSlots.containsKey(slot.getDate())){
			throw new Exception("this timeSlot does not exist for this Hall");
		}
		if(con.getCapacity() > this.capacity){
			throw new Exception("this Hall is not big enough (Event expectedCapacity > the Hall capacity)");
		}
		this.events.put(slot.getDate(), con);
	}
	
	public void addPlay(Play pl,Collection<TimeSlot> slot)throws Exception{
		// verifying requirement 
		if(pl.getCapacity() > this.capacity){
			throw new Exception("this Hall is not big enough (Event expectedCapacity > the Hall capacity)");
		}
		
		Iterator<TimeSlot> it = slot.iterator();
		while(it.hasNext()){
			TimeSlot tmp = (TimeSlot) it.next();
			if(this.events.containsKey(tmp.getDate())){
				throw new Exception("this timeSlot is already alocated");
			}
			if(!this.timeSlots.containsKey(tmp.getDate())){
				throw new Exception("this timeSlot does not exist for this Hall");
			}	
		}
		
		//adding the Play
		it = slot.iterator();
		while(it.hasNext()){
			TimeSlot tmp = (TimeSlot) it.next();
			this.events.put(tmp.getDate(), pl);
		}
	}


}
