package domine;

import java.time.LocalTime;
import java.util.Date;

public final class TimeSlot { //Value Object
	
	private Date date;	 	//to represent date without the time
	private LocalTime time; 		//to represent time without the date

	public TimeSlot(Date d, LocalTime t) {
		this.date = d;
		this.time = t;
		
	}
	
	public Date getDate(){
		return this.date;
	}
	
	public LocalTime getTime(){
		return this.time;
	}
	
	

}
