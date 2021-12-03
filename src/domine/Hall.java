package domine;

import java.util.Date;
import java.util.HashMap;

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
	
	public void addConcert(Concert con)throws Exception{
		if(this.events.containsKey(con.getDate())){
			throw new Exception("this date is already alocated");
		}
		if(!this.timeSlots.containsKey(con.getDate())){
			throw new Exception("this date does not exist for this Hall");
		}
		if(con.getCapacity() > this.capacity){
			throw new Exception("this Hall is not big enough (Event expectedCapacity > the Hall capacity)");
		}
		this.events.put(con.getDate(), con);
	}
	
	public void addPlay(Play pl)throws Exception{
		// verifying requirement 
		if(pl.getCapacity() > this.capacity){
			throw new Exception("this Hall is not big enough (Event expectedCapacity > the Hall capacity)");
		}
		
		
		Date startingDate = pl.getStartDate();
		Date endingDate = pl.getEndDate();
		
		while(startingDate.before(endingDate)) {
			
			if(this.events.containsKey(startingDate)){
				throw new Exception("this timeSlot is already alocated");
			}
			if(!this.timeSlots.containsKey(startingDate)){
				throw new Exception("this timeSlot does not exist for this Hall");
			}
			
			
			@SuppressWarnings("deprecation")
			Date tmp = new Date(startingDate.getYear(), startingDate.getMonth(), startingDate.getDay() +1);
			startingDate = tmp;
		}
		
		startingDate = pl.getStartDate();
		
		while(startingDate.before(endingDate)) {
			this.events.put(startingDate, pl);
			
			@SuppressWarnings("deprecation")
			Date tmp = new Date(startingDate.getYear(), startingDate.getMonth(), startingDate.getDay() +1);
			startingDate = tmp;
		}


	}


}
