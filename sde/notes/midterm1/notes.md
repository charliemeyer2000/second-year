# Notes for Midterm 1

This is a study guide for the first SDE midterm (note that this is condensed, as the first SDE midterm was cancelled, so it contains content from both).

## Midterm Review Video

### Streams

```java
public class StreamDemo() {
    public static void main(String[] args) {
        List<Student> slist = new ArrayList<>();
        sList.add(new Student("Aaron", "Aaronson", 3.5, Level.UG))
        // adding more students.
    }

    var optionalStudent = sList.stream()
        .filter(student -> student.getLevel() == Level.UG)
        .filter(student -> student.getGpa() >= 3.5)
        .max((s1, s2) -> Double.compare(s1.getGpa(), s2.getGpa()));
    
    optionalStudent.ifPresent(System.out::println);
    /** CAN ALSO BE WRITTEN AS
     * optionalStudent.ifPresent(student -> System.out.println(student));
     * 
     */

    // another example using map and forEach:
    var optionalStudent = sList.stream()
        .filter(student -> student.getLevel() == Level.UG)
        .filter(student -> student.getGpa() >= 3.5)
        .sorted(Comparator.comparing(student -> student.getGpa())) // same as sorted(Student::getGpa) or sorted((s1, s2) -> Double.compare(s1.getGpa(), s2.getGpa()))
        .map(student -> getDeansListFormat(student))
        .forEach(student -> System.out.println(student));


    private static String getDeansListFormat(Student student) {
        return String.format("%-20s - %1.2f", student.getCombinedName(), student.getGpa());
        // left aligned, width 20 for getCombinedName()
        // right aligned, min width is 1, decimals displayed is 2. 
    }
    
}
```

### Multi-Threading (Functional Programming)

```java
sList.parallelStream()
    .filter(student -> student.getLevel() == Level.UG)
    .filter(student -> student.getGpa() >= 3.5)
    .sorted((s1, s2) -> Double.compare(s1.getGpa(), s2.getGpa()))
    .map(Student::getDeansListFormat)
    .forEach(System.out::println);

/**
 * this prints out of order, even though its sorted. When you introduce parallelism, you can't predict order of things (i.e. race conditions). You can do .forEachOrdered(System.out::println) instead to ensure order. 
 * However, this produces a bottleneck and which kind of defeats the purpose of parallelism. 
 * 
 * Streams work on copies of immutable data. Optimizations are baked in. 
```

### Design Principles

- **Decomposition**: breaking up code into modules. However, you can over-decompose your code. The goal is to have modules that each module has a tight set of behaviors. 
- **Cohesion**: how related are two things. 
    * we want tight _intradependency_ - things within a module should be tightly related to the same fields, methods, ideas, etc.
    * 
- **Coupling**: things are tightly coupled when a change to one module necessitates the change to another module.
    * Data coupling - _best form of coupling_ - you pass data to a function, the function passes data to you.
    * Best way to reduce coupling is to design code around interfaces. This is why we design modules with `public` functions and `private` data. 
    






