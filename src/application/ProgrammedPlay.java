package application;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import domine.Hall;
import domine.Play;

public class ProgrammedPlay extends ProgrammedEvent {
	private final Play play;
	private ArrayList<Hall> halls;
	private ArrayList<Date> dates;
	
	public ProgrammedPlay(Play p) {
		super();
		this.play = p;
		this.halls = new ArrayList<>();
		this.dates = new ArrayList<>();
	}
	
	
	public Play getPlay() {
		return play;
	}


	public ArrayList<Hall> getHalls() {
		return halls;
	}


	public ArrayList<Date> getDates() {
		return dates;
	}


	public boolean program(Collection<Hall> hall) throws Exception {
		//programming the first day of the play
		Date firstDay = programEachWeekendHasPlay(hall);
		
		if(firstDay == null) {
			//we didn't find an empty weekend
			firstDay = programInNearestDate(hall, this.play.getStartDate());
		}
		if(firstDay != null) {
			//programming the rest of the days
			
			for(int i = 1; i < this.play.getNbDays(); i++) {
				Date currentDate = programInNearestDate(hall, firstDay);
				
				if(currentDate == null) {
					return false;
				}
			}
		}
		
		return this.dates.size() == this.play.getNbDays();
	}
	
	
	public Date programEachWeekendHasPlay(Collection<Hall> halls) throws Exception {
		
		Iterator<Hall> it =  halls.iterator();
		
		while(it.hasNext()) {
			Hall hall = it.next();
			
			if(hall.hasEmptySlots()) {
				Date weekend = hall.getEmptyWeekend();
				if(weekend != null){
					//we found a free weekend
					if(hall.addEvent(play, weekend)) {
						this.dates.add(weekend);
						this.halls.add(hall);
						return weekend;
					}
				}
			}
		}
		return null;
	}
	
	public Date programInNearestDate(Collection<Hall> halls, Date d) throws Exception {
		
		Iterator<Hall> it = halls.iterator();
		Hall resHall = null;
		Date resDate = null;
		long diff = Integer.MAX_VALUE;
		
		while(it.hasNext()) {
			Hall hall = it.next();
			
			Date currentDate = hall.getNearestValidDate(d);
			
    		long diffInMillies = Math.abs(currentDate.getTime() - d.getTime());
    	    long currentDiff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
    	    
    	    if(currentDiff < diff) {
    	    	diff = currentDiff;
    	    	resDate = currentDate;
    	    	resHall = hall;
    	    }
		}
		
		if(resDate != null) {
			if(resHall.addEvent(play, resDate)) {
				this.dates.add(resDate);
				this.halls.add(resHall);
				return resDate;
			}
		}
		return null;
	}
	
	
	

}
