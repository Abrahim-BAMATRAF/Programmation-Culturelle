package domine;

import java.util.Date;

public class Play extends Event {
	private final Date startDate;
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
	

}
