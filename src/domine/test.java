package domine;

import java.time.LocalTime;
import java.util.Date;

import application.Programmer;
import infrastructure.ProgramRepositoryInMemory;
import infrastructure.ProgramRepositoryJson;

public class test {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		try {
			//creating Events
			Play p1 = new Play(new Date("2021/12/05"),new Date( "2021/12/07"), "Hamilton", 77);
			Concert c1 = new Concert(50, new Date("2021/12/08"), "Linkin Park");
			Concert c2 = new Concert(50, new Date("2021/12/09"), "Queen");
			
			//creating timeSlots
			TimeSlot t1 = new TimeSlot(new Date("2021/12/06"),  LocalTime.of(17, 30));
			TimeSlot t2 = new TimeSlot(new Date("2021/12/07"),  LocalTime.of(16, 30));
			TimeSlot t3 = new TimeSlot(new Date("2021/12/08"),  LocalTime.of(16, 30));
			TimeSlot t4 = new TimeSlot(new Date("2021/12/09"),  LocalTime.of(14, 30));
			TimeSlot t5 = new TimeSlot(new Date("2021/12/10"),  LocalTime.of(19, 30));
			TimeSlot t6 = new TimeSlot(new Date("2021/12/11"),  LocalTime.of(20, 30));
			TimeSlot t7 = new TimeSlot(new Date("2021/12/12"),  LocalTime.of(21, 30));
			
			//creating Halls
			Hall h1 = new Hall(60, "hall 1");
			Hall h2 = new Hall(100, "hall 1");
			Hall h3 = new Hall(100, "hall 1");
			
			//adding timeslots to halls
		
			h1.addTimeSlot(t1);
			h2.addTimeSlot(t2);
			h2.addTimeSlot(t3);
			h2.addTimeSlot(t4);
			h3.addTimeSlot(t5);
			h3.addTimeSlot(t6);
			h3.addTimeSlot(t7);
			
			// creating the programmer
			Programmer prog = new Programmer();
			
			prog.addHall(h1);
			prog.addHall(h2);
			prog.addHall(h3);
			
			prog.addEvent(p1);
			prog.addEvent(c1);
			prog.addEvent(c2);
			
			prog.program();
			prog.showProgram();
			
			ProgramRepositoryInMemory saver = new ProgramRepositoryInMemory();
			saver.save(prog);
			



		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
