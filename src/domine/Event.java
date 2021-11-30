package domine;

public abstract class Event {
	private int expectedCapacity;
	
	public Event(int cap){
		this.expectedCapacity = cap;
	}
	
	public int getCapacity(){
		return this.expectedCapacity;
	}

}
