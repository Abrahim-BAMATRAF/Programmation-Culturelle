package application;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import domine.Concert;
import domine.Hall;

public class ProgrammedConcert {
	
	private final Concert concert;
	private Hall hall;
	private Date date;
	private boolean isProgramed;
	
	public ProgrammedConcert(Concert con) {
		this.concert = con;
		this.isProgramed = false;
	}
	
	public boolean program(Collection<Hall> halls) throws Exception {
		
		boolean prog = false;
		
		if(!prog) {
			prog = programEachHallHasAConcert(halls);
		}
		if(!prog) {
			prog = programEachWeekendHasConcert(halls);
		}
		if(!prog) {
			prog = programInNearestDate(halls);
		}
		
		this.isProgramed = prog;
		return prog;
	}
	
	public boolean programEachHallHasAConcert(Collection<Hall> halls) throws Exception {
		
		Iterator<Hall> it = halls.iterator();
				
		while(it.hasNext()) {
			Hall hall = it.next();
			
			if(hall.hasEmptySlots()) {
				if(!hall.hasConcert()) {
					Date weekend = hall.getEmptyWeekend();
					if(weekend != null){
						//we found a free weekend
						if(hall.addConcert(concert, date)) {
							this.hall = hall;
							return true;
						}
					}else {
						Date nearestDate = hall.getNearestValidDate(concert.getDate());
						if(hall.addConcert(concert, nearestDate)) {
							this.hall = hall;
							return true;
						}
					}
				}	
			}
		}
		return false;
	}
	
	public boolean programEachWeekendHasConcert(Collection<Hall> halls) throws Exception {
		
		Iterator<Hall> it = halls.iterator();
		
		while(it.hasNext()) {
			Hall hall = it.next();
			
			if(hall.hasEmptySlots()) {
				Date weekend = hall.getEmptyWeekend();
				if(weekend != null){
					//we found a free weekend
					if(hall.addConcert(concert, date)) {
						this.hall = hall;
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public boolean programInNearestDate(Collection<Hall> halls) throws Exception {
		
		Iterator<Hall> it = halls.iterator();
		Hall resHall = null;
		Date resDate = null;
		long diff = Integer.MAX_VALUE;
		
		while(it.hasNext()) {
			Hall hall = it.next();
			
			Date currentDate = hall.getNearestValidDate(this.concert.getDate());
			
    		long diffInMillies = Math.abs(currentDate.getTime() - this.concert.getDate().getTime());
    	    long currentDiff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
    	    
    	    if(currentDiff < diff) {
    	    	diff = currentDiff;
    	    	resDate = currentDate;
    	    	resHall = hall;
    	    }
		}
		
		if(resDate != null) {
			if(hall.addConcert(concert, resDate)) {
				this.hall = resHall;
				return true;
			}
		}
		return false;
	}
}
