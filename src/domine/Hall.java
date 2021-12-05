package domine;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

public class Hall { //represent "salles" // Aggregate
	
	private int capacity; //could be replaced by value object
	private String name; 	// naming the halls to easily identify them
	
	private HashMap<Date,TimeSlot> timeSlots;
	private HashMap<Date,Event> events;
	
	public Hall(int cap, String n){
		this.capacity = cap;
		this.name = n;
		this.timeSlots = new HashMap<>();
		this.events = new HashMap<>();
		
	}
	
	public int getCapacity(){
		return this.capacity;
	}
	
	public String getName() {
		return name;
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
	
	
	public boolean verifyConcertDate(Date d) {
		if(this.events.containsKey(d)){
			return false;
		}
		if(!this.timeSlots.containsKey(d)){
			return false;
		}

		return true;
	}
	
	public boolean verifyCapacity(Event ev) {
		return ev.getCapacity() <= this.capacity;
	}
	
	public Date getEmptyWeekend() {

        for (Entry<Date, TimeSlot> entry : timeSlots.entrySet()) {
        	
        	Date date = entry.getKey();
        	if(isDateWeekend(date) && verifyConcertDate(date)) {
        		return date;
        	}
        	
        }
        
        return null;
	}
	
	public static boolean isDateWeekend(Date d) {
		
	    Calendar c1 = Calendar.getInstance();
	    c1.setTime(d);

	    if ((c1.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) 
	            || (Calendar.DAY_OF_WEEK == Calendar.SUNDAY)) {
	    	return true;
	    	
	    }
	    
	    return false;
	}
	
	public boolean hasConcert() {
        for (Entry<Date, Event> entry : events.entrySet()) {
        	
        	Event event = entry.getValue();
        	
        	if(event instanceof Concert) {
        		return true;
        	}
        }
		
		return false;
	}
	
	public boolean hasEmptySlots() {
		
		return timeSlots.size() > events.size();
	}
	
	public Date getNearestValidDate(Date date) {
		
		Date res = null;
		long diff = Integer.MAX_VALUE;
        for (Entry<Date, TimeSlot> entry : timeSlots.entrySet()) {
        	
        	Date d = entry.getKey();
        	
        	if(this.verifyConcertDate(d)) {
        		long diffInMillies = Math.abs(d.getTime() - date.getTime());
        	    long currentDiff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        	    
        	    if(currentDiff < diff) {
        	    	diff = currentDiff;
        	    	res = d;
        	    }
        	}
        }		
		return res;
	}
	
	public boolean addEvent(Event ev, Date date)throws Exception{
		if(this.events.containsKey(date)){
			throw new Exception("this date is already alocated");
		}
		if(!this.timeSlots.containsKey(date)){
			throw new Exception("this date does not exist for this Hall");
		}
		if(ev.getCapacity() > this.capacity){
			throw new Exception("this Hall is not big enough (Event expectedCapacity > the Hall capacity)");
		}
		
		this.events.put(date, ev);
		return true;
	}
	
	
	
	

}
