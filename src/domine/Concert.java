package domine;

import java.util.Date;

public class Concert extends Event {
	private final Date date;
	private final String presenter; //could be replaced by value object
	

	public Concert(int cap, Date d, String name) {
		super(cap);
		this.date = d;
		this.presenter = name;
	}

}
