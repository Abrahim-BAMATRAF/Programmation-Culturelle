package infrastructure;

import java.util.HashMap;
import java.util.Map;

import application.Programmer;
import application.ProgrammerId;

public class ProgramRepositoryInMemory {
	
	private Map<ProgrammerId, Programmer> list = new HashMap<>();
	
	public void save(Programmer prog) {
		this.list.put(prog.getId(), prog);
	}
	
	public Programmer findProgrammerId(ProgrammerId id) {
		return this.list.get(id);
	}

}
