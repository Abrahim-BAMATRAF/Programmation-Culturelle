package domine;

import java.util.Date;

public class Concert extends Event {
	private final Date date;		// the date we want the event to take place in, not necessarily the actual date of the event
	private final String presenter; //could be replaced by value object
	

	public Concert(int cap, Date d, String name) {
		super(cap);
		
		this.date = d;
		this.presenter = name;
	}


	public Date getDate() {
		return date;
	}


	public String getPresenter() {
		return presenter;
	}

}
