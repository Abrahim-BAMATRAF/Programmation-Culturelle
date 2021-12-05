package application;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import domine.Concert;
import domine.Event;
import domine.Hall;
import domine.Play;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Programmer {
	
	private final ProgrammerId id;	
	private HashSet<Event> events;
	private HashSet<Hall> halls;
	
	private HashSet<ProgrammedEvent> programmedEvent;  //the result of the process of programming the events
	
	public Programmer() {
		this.id = new ProgrammerId();
		this.events = new HashSet<>();
		this.halls = new HashSet<>();
		this.programmedEvent = new HashSet<>();
	}
	
	public void addEvent(Event ev) {
		this.events.add(ev);
	}
	
	public void addHall(Hall hall) {
		this.halls.add(hall);
	}
	
	public void program() throws Exception {
		//TODO if there is not enough timeSlots
		
		Iterator<Event> it = events.iterator();
		while(it.hasNext()) {
			Event tmpEvent = it.next();
			
			if(tmpEvent instanceof Play) {

				Play tmpPlay = (Play) tmpEvent;
				
				ProgrammedPlay progPlay = new ProgrammedPlay(tmpPlay);
				
				if(progPlay.program(this.halls)) {
					this.programmedEvent.add(progPlay);
					
				}
			}else if(tmpEvent instanceof Concert) {
				
				Concert tmpConcert = (Concert) tmpEvent;
				
				ProgrammedConcert progConcert = new ProgrammedConcert(tmpConcert);
				
				if(progConcert.program(halls)) {
					this.programmedEvent.add(progConcert);
				}
			}
		}
		
	}
	
	public void showProgram() {
		Iterator<ProgrammedEvent> it = programmedEvent.iterator();
		
		while(it.hasNext()) {
			System.out.println("-------------------------------------------------\n");
			ProgrammedEvent tmpEvent = it.next();
			
			if(tmpEvent instanceof ProgrammedPlay) {
				ProgrammedPlay tmpPlay = (ProgrammedPlay) tmpEvent;
				System.out.println("the play titled : " + tmpPlay.getPlay().getTitle() + "\n");
				
				for(int i =0; i < tmpPlay.getDates().size(); i++) {
					System.out.println("the showing number : " + i + "  will be in the hall named : " + tmpPlay.getHalls().get(i).getName() + 
							"  in the this date :  " + tmpPlay.getDates().get(i) + " \n" +
							"  at this time : " + tmpPlay.getHalls().get(i).getTimeSlot(tmpPlay.getDates().get(i)).getTime());
				}
			}else if(tmpEvent instanceof ProgrammedConcert) {
				ProgrammedConcert tmpConcert = (ProgrammedConcert) tmpEvent;
				
				System.out.println("the Concert will presentd by : " + tmpConcert.getConcert().getPresenter() + "\n");
				
				System.out.println("the concert will be in the hall named : " + tmpConcert.getHall().getName() + 
						"  in the this date :  " + tmpConcert.getDate() + " \n" + 
						" at this time : " + tmpConcert.getHall().getTimeSlot(tmpConcert.getDate()).getTime());
			}
		}
	}

	public ProgrammerId getId() {
		return id;
	}
	
	public String getIdString() {
		return id.getId();
	}
}
