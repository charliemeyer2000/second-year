package edu.virginia.cs.oo.inheritance.clock;

public class AlarmClock extends Clock {
	private int alarmHour, alarmMinute;

	public AlarmClock(String brandName,
					  int alarmHour, int alarmMinute) {
		super(brandName);
		this.alarmHour = alarmHour;
		this.alarmMinute = alarmMinute;
	}

	public AlarmClock(String brandName, int alarmHour, int alarmMinute,
						  int currentHour, int currentMinute, int currentSecond) {
		super(brandName, currentHour, currentMinute, currentSecond); //create the parent instance by passing relevant information.
		this.alarmHour = alarmHour;
		this.alarmMinute = alarmMinute;
	}
	

	public int getAlarmHour() {
		return alarmHour;
	}
	
	public int getAlarmMinute() {
		return alarmMinute;
	}

	public void setAlarmHour(int alarmHour) {
		this.alarmHour = alarmHour;
		checkAlarm();
	}
	
	public void setAlarmMinute(int alarmMinute) {
		this.alarmMinute = alarmMinute;
		checkAlarm();
	}


	public void update() {
		super.update();
		checkAlarm();
	}


	private void checkAlarm() {
		if (currentHour == alarmHour && currentMinute == alarmMinute) {
			System.out.println("BEEEP BEEP BEEEP BEEP BEEP");
		}
	}
	
	public static void main(String[] args) {
		AlarmClock ac = new AlarmClock("Cassio", 0, 6);
		System.out.println(ac);
		System.out.println(ac.getCurrentHour()); //can call parent class method
		System.out.println(ac.getAlarmHour()); //can call child class method
		Clock c = new AlarmClock("Digitech", 12, 00); //substitution principle
		System.out.println(c); //still prints like an alarm clock
		System.out.println(c.getCurrentHour()); //can call parent class method
		//System.out.println(c.getAlarmHour()); //CANNOT call child class method, because type is currently edu.virginia.cs.oo.inheritance.clock.Clock, not edu.virginia.cs.oo.inheritance.clock.AlarmClock
		System.out.println(((AlarmClock) c).getAlarmHour()); //can dynamically typecast and then call it
		
		//edu.virginia.cs.oo.inheritance.clock.AlarmClock c = new edu.virginia.cs.oo.inheritance.clock.Clock("I'm out of name ideas"); //does not compile, subsitution principle doesn't work that way.
	}
}
