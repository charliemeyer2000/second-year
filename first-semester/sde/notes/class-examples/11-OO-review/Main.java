package edu.virginia.cs.oo.inheritance.clock;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Clock> clocks = new ArrayList<>();
        clocks.add(new Clock("casio"));
        clocks.add(new AlarmClock("timex", 7, 30));
    }

    public static void clockExample() {
        Clock clock = new Clock("Cassio");
        AlarmClock timex = new AlarmClock("Timex", 7, 30);
        System.out.println(clock.getCurrentHour());
        System.out.println(timex.getCurrentHour());
        System.out.println(timex.getAlarmHour());
    }

    public static void alarmClock() {
        AlarmClock timex = new AlarmClock("Timex", 7, 30);
    }
}
