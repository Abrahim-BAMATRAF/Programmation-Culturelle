package application;

public abstract class ProgrammedEvent {

	protected boolean isProgrammed;

	public ProgrammedEvent() {
		this.isProgrammed = false;
	}
	
	public boolean isProgrammed() {
		return this.isProgrammed;
	}

}