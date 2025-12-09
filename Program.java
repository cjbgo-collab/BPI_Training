package M2_Activity5;

public abstract class Program {
	 
	private String name;
	private boolean isRunning = false;
 
	public Program() {
 
	}
 
	public Program(String name) {
		this.name = name;
	}
 
	abstract void run();
 
	abstract void stop();
 
	public String getName() {
		return name;
	}
 
	public void setName(String name) { // <fill>
		this.name = name;
	}
 
	public boolean getIsRunning() { // <fill>
		return isRunning;
	}
 
	public void setIsRunning(boolean isRunning) { // <fill>
		this.isRunning = isRunning;
	}
 
}