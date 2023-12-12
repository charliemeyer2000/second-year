package behavioral.observer;

public class IntegerSubject extends Subject {
	private int state;
	
	public IntegerSubject(int state) {
		this.state = state;
	}
	
	public int getState() {
		return state;
	}
	
	public void setState(int state) {
		this.state = state;
		//After we make the change, notify everyone observing a change has occurred
		this.notifyObservers(this);
	}
}
