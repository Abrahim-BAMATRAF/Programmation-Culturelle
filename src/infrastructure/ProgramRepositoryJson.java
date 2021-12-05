package infrastructure;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.*;

import application.Programmer;
import application.ProgrammerId;
import domine.ProgrammerRepository;

public class ProgramRepositoryJson implements ProgrammerRepository {
	
	
	public void save(Programmer prog) {
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		try {
			Writer writer = new FileWriter("data/"+prog.getIdString()+".json");
			gson.toJson(prog,writer);
			writer.close();
		} catch (JsonIOException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Programmer findProgrammerById(ProgrammerId programmerId) {

		Gson gson = new Gson();
		Reader reader;
		try {
			reader = Files.newBufferedReader(Paths.get("data"+programmerId+".json"));
			Programmer result = gson.fromJson(reader, Programmer.class);
			reader.close();
			return result;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	

}
