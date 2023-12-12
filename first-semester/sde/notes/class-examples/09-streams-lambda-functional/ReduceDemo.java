import java.util.ArrayList;
import java.util.List;

public class ReduceDemo {
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

        var output = slist.stream()
                .filter(student -> student.getLevel() == Level.UG)
                .map(student -> student.getLastName())
                .reduce("", (subString, element) -> subString + " " + element);
        System.out.println(output);
    }
}
