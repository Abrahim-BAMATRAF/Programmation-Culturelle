package domine;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Play extends Event {
	private final Date startDate; // the date we want the event to start in, not necessarily the actual date of the event
	private final Date endDate;
	private final String title;
	
	public Play(Date start, Date end, String t, int cap){
		super(cap);
		this.startDate = start;
		this.endDate = end;
		this.title = t;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public String getTitle() {
		return title;
	}
	
	
	public int getNbDays() {
		
		long diffInMillies = Math.abs(endDate.getTime() - startDate.getTime());
	    long currentDiff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

		return (int)currentDiff + 1;
	}
	

}
