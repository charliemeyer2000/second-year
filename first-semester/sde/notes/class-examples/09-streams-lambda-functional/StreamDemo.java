import java.util.ArrayList;
import java.util.List;

import static java.util.Comparator.comparing;

public class StreamDemo {
    public static void main(String[] args) {
        List<Student> slist = new ArrayList<>();
        slist.add(new Student("Aaron", "Aaronson", 3.5, Level.UG));
        slist.add(new Student("Betty", "Black", 2.7, Level.UG));
        slist.add(new Student("Chuck", "Carter", 3.8, Level.G));
        slist.add(new Student("Debby", "Davis", 3.8, Level.UG));
        slist.add(new Student("Eddie", "Edwards", 2.0, Level.NT));
        slist.add(new Student("Frank", "Frederick", 4.0, Level.UG));
        slist.add(new Student("Greta", "Green", 3.6, Level.G));
        slist.add(new Student("Harry", "Horton", 1.3, Level.UG));

        slist.stream()
                .filter(s -> s.getLevel() == Level.UG)
                .filter(s -> s.getGpa() >= 3.5)
                .sorted((o1, o2) -> Double.compare(o2.getGpa(), o1.getGpa()))
                .map(s -> String.format("%-20s - %1.2f",
                        s.getCombinedName(), s.getGpa()))
                .forEach(s -> System.out.println(s));


    }
}
