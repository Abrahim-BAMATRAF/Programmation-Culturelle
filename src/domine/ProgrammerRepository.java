package domine;
import application.Programmer;
import application.ProgrammerId;


public interface ProgrammerRepository {
	
	public Programmer findProgrammerById(ProgrammerId programmerId);
	public void save(Programmer prog);

}
