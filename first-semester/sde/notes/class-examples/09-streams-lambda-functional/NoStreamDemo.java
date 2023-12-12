import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class NoStreamDemo {
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

        //get undergrads
        List<Student> undergrads = new ArrayList<>();
        for (Student s: slist) {
            if (s.getLevel().equals(Level.UG)) {
                undergrads.add(s);
            }
        }

        //get deansList
        List<Student> deansList = new ArrayList<>();
        for (Student student: undergrads) {
            if (student.getGpa() >= 3.5) {
                deansList.add(student);
            }
        }

        //sort deansList by GPA in descending order
        deansList.sort(new Comparator<Student>() {
            public int compare(Student o1, Student o2) {
                return Double.compare(o2.getGpa(), o1.getGpa());
            }
        });

        //get a List<String> to represent the deans list printout
        List<String> undergradNameAndGPAs = new ArrayList<>();
        for (Student student : deansList) {
            undergradNameAndGPAs.add(String.format("%-20s - %1.2f",
                    student.getCombinedName(), student.getGpa()));
        }

        for (String name : undergradNameAndGPAs) {
            System.out.println(name);
        }
    }
}
