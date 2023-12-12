package edu.virginia.cs.oo.inheritance.clock;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Clock {
	protected String brandName;
	protected int currentHour, currentMinute, currentSecond;
	
	public Clock(String brandName) {
		this.brandName = brandName;
		update();
	}
	
	public Clock(String brandName, int currentHour, int currentMinute, int currentSecond) {
		this.brandName = brandName;
		this.currentHour = currentHour;
		this.currentMinute = currentMinute;
		this.currentSecond = currentSecond;
	}
	
	public void update() {
		LocalDateTime dateTime = LocalDateTime.now();
		DateTimeFormatter f = DateTimeFormatter.ofPattern("HH:mm:ss");
		String s = dateTime.format(f);
		currentHour = Integer.parseInt(s.substring(0, 2));
		currentMinute = Integer.parseInt(s.substring(3, 5));
		currentSecond = Integer.parseInt(s.substring(6, 8));
	}
	
	public int getCurrentHour() {
		return currentHour;
	}
	
	public int getCurrentMinute() {
		return currentMinute;
	}
	
	public int getCurrentSecond() {
		return currentSecond;
	}
	
	public void setCurrentHour(int currentHour) {
		this.currentHour = currentHour;
	}
	
	public void setCurrentMinute(int currentMinute) {
		this.currentMinute = currentMinute;
	}
	
	public void setCurrentSecond(int currentSecond) {
		this.currentSecond = currentSecond;
	}
	
	public String toString() {
		return brandName + " brand clock - " + getTimeAsString();
	}
	
	protected String getTimeAsString() {
		return currentHour + ":" + currentMinute + ":" + currentSecond + " ";
	}
	
	public static void main(String[] args) {
		Clock c = new Clock("Cassio");
		System.out.println(c);
		System.out.println(c.getCurrentHour());
		// System.out.println(c.getAlarmHour()); //Not an alarm clock, so I can't call edu.virginia.cs.oo.inheritance.clock.AlarmClock method
	}
}
