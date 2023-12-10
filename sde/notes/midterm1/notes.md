# Notes for Midterm 1

This is a study guide for the first SDE midterm (note that this is condensed, as the first SDE midterm was cancelled, so it contains content from both).

## Midterm Review Guide

* **software engineering**
    * **Ad-Hoc development** - ad-hoc solutions are solutions that is a _one-time_ solution to a problem. This does not create a maintainable, useable, or expandable solution.
    * **Software complexity**
        * Accidental Complexity - emerge because of circumstance. 
        * Essential complexity - difficulties that are intrinsic to the software development process.
    * **entropy** - how software's maintainability decreases. This is understood as the amount of effort needed to make a software change. 
    * **software quality**
        * **External Software Quality** - refers to the quality of the software from the perspective of the stakeholders:
            * _stakeholders_ are anyone affected by the software, not just the user. This includes both users, it, developers, etc. _functionality_ refers to whether the software is functionally complete - it does everything it is supposed to. Software is _reliable_ when it performs under certain conditions over a period of time. Software is _useable_ when there is not much effort needed for a consumer to use the software. _efficiency_ is what resources are being used to what extent. Finally, _portability_ is ow well it can be transferred from environment to environment. 
        * **internal software quality** - internal software quality, we are concerned with the quality from the perspective of the developers. 
            * **analyzability** - to what extent can the software be understood? 
            * **changeability** - the effort it requires to make changes to the software
            * **stability** - the extent to which changes in one place effect changes in the other
            * **reusability** - how parts can be reused. 
            * **testability** - how software can be tested to find faults/defects in software construction.
* Java Basics
    * Compiling vs. Interpreting:
        * **compiling** is when you take your source code file through a compiler program and run the executable file. 
        * **interpreting** (python, for example) is when a "translator" translates each line into machine instructions as you come to it. This means that you don't have a statically compiled file.
    * JHow java works:
        * **JDK** (Java Development Kit) - JDK is the compiler that produces a `.class` file. 
        * **JRE** (Java Runtime Environment) - is the environment that is used to _run_ java programs. 
        * **JVM** (Java Virtual Machine) - resource that acts like a "separate computer" that directly interacts with the actual underlying hardware. Technically the JVM is an _interpreter_ that interprets code at runtime. That code is not actually java source code, but `.class` byte code compiled by the JDK, given to the JVM by the JRE. 
        * **JIT** (Just In time compiler) - this is the specific part of the JVM that compiles byte code instructions into machine code instructions for underlying hardware. 
    * Why Java working this way is good
        * JRE can run any compatible byte code, not and does not require a JDK. This allows you to use other languages like Kotlin to also write `.class` files and run them in a JRE
     * Java Command Line Basics:
        * `java` - specifies to use `java` stuff. 
        * `java -jar` - specifies to run a java .`jar` file. 
        * `javac` - compiles a java file into a `.class` file.
        * `java example.Main` - this runs the class `Main` within the package `example`.
    * file types:
        * `.java` - what you code in
        * `.class` - the compiled java code
        * `.jar` a bundle of all classes into a single file so you can run it all at once. For example, we can create a jar file manually using `jar cf MyJar.jar example/*.class` and run the file using `java -jar MyJar.jar`.
* **Software Construction:**
    * types of repositories:
        * **centralized repository**: the remote repo is where all shared versions are stored. They commit and update. 
        * **distributed repository**: Every "workstation" has a local copy in addition to the remote repository. So you can commit to the local, then push to the remote. 
    * **git version control**:
        * Commands to know:
            * `add` - adds files to staging area
            * `commit` - commits files to your local repo
            * `push` - pushes local repo to the remote repo
            * `pull` - pulls remote repo into your local repo
            * `branch` - specifies branches. You can create branches off of code and then commit to those branches. 
            * `switch` - switch between branches
            * `checkout` - switch between two branches OR restore an old version of the repo/particular file (set working version to a previous version).
            * `merge` - merges two repos of separate branches. 
                * Make sure to first merge from development to feature branch, fix conflicts within your feature branch, then merge feature branch to development. 
    * **build tools** - allow us to create jar files that also include external dependencies and generate a `.jar` file efficiently. 
        * **gradle** - this is a build tool that downloads dependencies, compiles code, runs your test suite, and builds an output `.jar` file for you. 
        * **build.gradle** - domain specific language in groovy that specifies build conditions.
            * Plugins - tells gradle we're using java
            * repositories - use just `mavenCentral()` to download references for dependencies.
            * Dependencies - tells gradle the external libraries that you're using. 
            * test - tells how to test, generally `useJUnitPlatform()`
            * jar - tells how to build and output the `.jar` file. 
                * `archiveBaseName` - output jar file name
                * `group` - used if we want to deploy our file on Maven. 
                * `duplicatesStrategy` - just leave it as `duplicatesStrategy.EXCLUDE`
                * `manifest` - include `attributes "Main-class"`.
                * from - make sure to include: `    configurations.runtimeClasspath.collect { it.isDirectory() ? it : zipTree(it) }` to include all deps in jar file. 
        * Know `gradle build` and `gradle test` versus using gradle wrapper (i.e. you don't need gradle installed to run it) by doing just `./gradlew test` and `./gradlew build`.
* **testing**:
    * Purpose of testing - **to find defects**. 
        * **defect** - an existing problem that has not been discovered
        * **failure** - inability to perform function according to specification
        * **mistake** - human error that produces something incorrect, such as a bug in the code
        * **error** - diff b/w current state and correct state.
    * V&V
        * **Verification** - focusing on the _internal_ specification, that is how well the developers met the internal specs. Did ya build the thing right? 
        * **validation** - what the customer thinks about it. Did ya build the right thing? 
    * **sound testing**:
        * a test is sound when it correctly tests against the specification
        * A test is unsound when it doesn't - acts as misinformation.
    * **Test scenarios** - where you create a controlled scenario with defined inputs and expected behaviors. This is done by creating a test class for a class that tests the interface of the methods provided. 
        * **state testing** - tests whether the intended behaviors for setters create the intended state for an object. This is like if you have something like `nc.setNumber(13)` and you test whether `nc.getNumber() == 13`. You should only call the tested operation once and also don't test private methods. (a la test interface not implementation). 
        * Expected vs actual - expected is what you expect to happen from a test, actual is what actually happens.
    * `@Test` - identifies a test method. this tells JUnit that this is a test. Test methods must be:
        * `public` and NOT STATIC.
        * return `void`
        * take in no arguments. 
    * `@BeforeEach` and `@AfterEach` - methods that run before and after each test. These are effecitvely "setup" and "teardown" methods. 
    * Assertions:
        * AssertEquals:
            * asserts two things are equal. - `assertEquals(Object expected, Object actual,)`
                * Strings - uses their built in `.equals()` methods to compare.
                * Arrays - if you directly do `assertEquals(a, b)` where a and b are two arrays, they will only pass if a and b have the same memory address. Thus you can use `assertArrayEquals` instead. 
                * Doubles - for doubles with some error, you can do `assertEquals(a, b, delta)` where delta is some "lenience" factor that handles floating-point imprecision. 
                * Objects - just like strings uses their `.equals()` method. 
        * `assertTrue(boolean expression)`
        * `assertNull` and `assertNotNull` - takes in one thing and does what it says. 
        * `assertThrows(Exception.class, () -> thing())`
    * Types of Test Scenarios
        * **equivalence** - normal testing scenarios, with non-boundary inputs and outputs
        * **boundary** - fencepost case tests, like calling something on an empty list. Note that calling `.contains()` on a list for something that doesn't exist is most likely an equivalence case, whereas `.find()` on something that doesn't exist is a boundary case. 
        * **robustness** - tests that are _syntactically valid_ but _semantically meaningless_. 
    * Test Plans:
        * **white box testing** - select testing cases while considering the existing implementation of the code:
            * Branch coverage - ensures that testing considers both true and false conditions for if statements, and also considers skipping the while loop, one pass in a while loop, or multiple passes.
            * Conditional Coverage - considers multiple options for an if statement such as `if (a > b && b > 0)`. 
            * Statement coverage - measure of what % of statements have been covered by our tests
            * Path Coverage - interested in what paths your code can take through conditionals/whiles, etc. 
        * **black box testing** - testing based on the interface as specified by the method. Below are forms of black box testing. 
            * **exhaustive testing** - not really possible or feasible
            * **random testing** not really useful. 
            * **equivalence partitioning** - breaking our equivalence tests into "groups" that roughly act the same (i.e. positive and negative inputs for a `Math.abs()` test). We can then also test the boundary cases like 0. 
* **Defensive Programming** - the goal should be to ensure that your code cannot be used incorrectly. Because of this, you throw exceptions when something that shouldn't happen would be happening, but catch exceptions when it's fine (we can move forward). 
    * **checked exceptions** - these are exceptions that java forces you to either handle or throw. This includes when you are working with a BufferedReader, InputFileStream. Put it in a try-catch block rather than throwing that exception, as it'll then force the caller to handle that exception (it's changing the interface of the method!!)
    * Making your own exceptions - you can make your own exceptions that are more specific and give better user/developer experience. For example, you can do:
    ```java
    public class MyCustomException extends SomeValidException {
        public MyCustomException(String message) {
            super(String.format("some custom message, %s", message))
        }
    }
    ```
    * AssertionError:
        * **assert keyword** - it is of the form `assert([boolean-statement])`. IF the asserted statement is false, it throws an `AssertionError` which is not the same as an exception (it can't be caught). 
        * To enable AssertionErrors to be thrown, you need to add `-ea` to the VM arguments (not command line arguments!!).
* Code Quality:
    * **Analyzability** - "to what extent the code can be read and understood." This is a combination of both readability and understandability. Note that readability is necessary for understandability. 
        * **readability** - code is easy to read (i.e. spacing, indentation, using human readable and pronounceable words).
        * **understandability** - the extent to which the semantics, i.e. the meaning and intent, of the code can be understood. 
    * **Code Smells** - refers to problems within the design and structure of the underlying code in software. They are internal software quality issues (not explicitly bugs or defects). Below are examples of what causes code smells.
        * Long methods - functions should do one thing and should be very clear. 
        * Large classes - Do not do one giant `Main.java`. Also, classes shouldn't need like 20 fields, methods that do different things, or have too much code. 
        * Long parameter list - functions should, if possible, take in one parameter (**monadic**). 
        * Boolean parameters - bad
        * too many primitives - you can extract a class with a ton of primitives into classes. 
        * Message chains - if you are seeing something like `return new BufferedReader(new FileReader(new File("filename.csv"))).readLine().strip().split()` make it into a couple lines of code with new lines. 
    * **refactoring techniques** - refactoring is the process of making internal changes to the code to improve _internal software quality_ without making changes to the functionality of the software. 
        * **renaming identifiers** - Rename identifiers to be more understandable.
        * **introduce constant** - extract raw values into constants/enums. 
        * **renaming classes** - do it. Also use Refactor > Rename class in intellij
        * **changing signature** - same as before, changing order of parameters in a method, use Refactor > Change Signature
        * **abstract conditional logic** - we can refactor by encapsulating if-statements that use boolean logic into a separate function. 
        * **Invert boolean** - use Refactor > invert Boolean. This is helpful if you are doing something like:
        ```java
            public double getCharge(Date date) {
                if (isNotSummer(date)) {
                    return getWinterPrice();
                } else {
                    return getSummerPrice();
                }
            }
        ```
        and convert it to:
        ```java
            public double getCharge(Date date) {
                if (isSummer(date)) {
                    return getSummerPrice();
                } else {
                    return getWinterPrice();
                }
            }
        ```
        * **Replace ErrorCode with exception** - don't just return like -1 for something, use an exception. 
        * **replace null with optional** - avoid returning null so you don't have to catch exceptions for that:
        ```java
        // on the server
        public Optional<Student> getStudentByComputingID(String id) {
            for (Student student: studentLIst) {
                if (student.getComputingID.equals(id)) {
                    return Optional.of(student)
                }
            }
            return Optional.empty();
        }
        // on the client somewhere, forcing the client to decide what to do.
        Optional<Student> optionalStudent = getStudentByComputingId("ab12cd");
        if (optionalStudent.isPresent()) {
            // do something with the student
        } else {
            // no student with that studentId exists
        }
        ```
        * Extract method - use IntelliJ extract method to take out a chunk of code into a method. Allows a function to do one thing, and allows you to test implementations of things independently as well.
        * Extract class - **single responsibility principle** states that a class should only have one reason to change. The goal of a class is to hide implementation details. We can then extract an interface that describes the behavior with a standardized `interface`. 
        * **Preserve Whole Object** - hand in hand with avoiding primitive obsession, we can use a class so that code does not rely on how another class works. For example, we can do:
        ```java
        Building riceHall = buildingRepository.getBuilding("Rice");
        Coordinate riceCoordinates = riceHall.getCoordinates();
        distanceScreen.display(getDistanceFromLocationTo(riceCoordinates));
        ``` 
        without even having to know how the Coordinates work!
    * **DRY** vs. **WET**
        * **DRY (Don't Repeat Yourself)** - principle to extract method, class, function, or use `@BeforeEach` to avoid repeating yourself/.
        * **WET** - Code that doesn't adhere to DRY - aka. "Write Everything Twice." 
    * **Code Comments** - comments shouldn't be used to make sense of a class or method, they should only be used when the method needs explanation. Well written code should be understandable to the point that it can be read like prose, and doesn't need comments. 
        * Node that documentation is different from comments, documentation tells you what things do, not how they do it. 
* Functional Programming - review BELOW. This is more reviewed using practice than with actual just looking at it. However, here is what we will review from the guide.
    * Making Streams from:
        * List -> `.stream()`
        * Set -> `.keySet()`
        * Map -> `.entrySet()`
            * This allows you to access stuff like: `myHashMap.entrySet().stream().forEach(entry -> { String key = entry.getKey(); String value = entry.getValue(); System.out.println("Key: ", key, " Value: ", value)})`
    * Comparator.comparing - Allows you to put in a comparator with a `.sorted()`. So for example, you could do `.sorted(Comparator.comparing(State::getName))` to compare and then sort states by their natural order. If you are not operating on things that do not implement `Comparable` (i.e. `Integer`, `String`, `Double`, write your own inline comparator by doing `.sorted((a, b) -> a.getSize() - b.getSize())`)
    * Recall that when you use `.toList()` as a terminal operation you are return an _immutable_ list, and same with `.collect(Collectors.toSet())`.
    * Can use `.collect(Collectors.toMap(s -> s.getName(), s.getPopulation()))` to create a modifiable map. 
    * `reduce`: an example is `stateList.stream().map(s -> s.getPopulation()).reduce(0, (subtotal, statePopulation) -> subtotal + statePopulation)`
* **Software Design**
    * Good vs. Bad Design - you can tell something has good design when it is understandable and also can be modified without much effort (in developer-hours) to meet the changing needs. 
    * Design Concepts:
        * **modularity** - each unit of code is a _module_, generally think of each module being a class. Thus modularity is breaking up our problem into many sub-problems (modules) that can then be recombined to work together. 
            * Single responsibility principle - a class should only have one reason to change. 
        * **functional independence** - each module can perform its purpose with minimal interactions with the rest of the system. 
        * **abstraction** - hiding implementation details.
            * designing for the interface, not the implementation - the goal of abstraction is to separate the interface from the implementation so that any change to the implementation does not change other modules. 
        * **information hiding** - create a limited interface to achieve abstraction. This is why we create public methods to access private fields, so that the user **Must** use the interface to interact with the module.
    * Cohesion vs. Coupling: **cohesion** refers to how much each class adheres to one specific purpose. **coupling** refers to the extent to which interacting modules (inter) depend on each other. 
        * "highly cohesive" - good because we want each module to do one thing.  
        * "loosely coupled" - good because this makes the relationship between modules as simple as communicating with public function calls. 
    * Types of Cohesion:
        * WORST: Coincidental Cohesion - when elements are combined into a module with no real purpose. This includes a giant `Main` class or a `utils` file with no reasoning/cohesion.
        * WORST: Logical Cohesion - when elements are grouped together because they do the same "general thing" but have vastly different implementations.   
        * WORST: Temporal Cohesion - When things are grouped together because they happen roughly at the same time (i.e. initializing your GUI and initializing your Database).  
        * DECENT: Procedural Cohesion - When things are grouped together because they describe a single procedure but _the activities of each method are largely unrelated_.
        * DECENT: Communicational Cohesion - Elements are grouped together in a module because they perform actions on the same data for either input/output. This is fine, even though the method implementations might be vastly different/performing different actions on the same data. 
        * BEST: Sequential Cohesion - when things are grouped together because the outputs of one method are directly used as inputs of another to achieve a certain single procedure.
        * BEST: Functional Cohesion - when a module supports a single, well-supported function. (heyyyyyyyyy TypeScript!)
    * Forms of Coupling
        * WORST: Content Coupling - when one class directly modifies the state of another within a method. This means directly modifying private data of another class. YUCK.
        * WORST: Common Coupling - giving public access to static variables.
        * WORST: Control coupling - When you pass a flag (typically a boolean) from one module to another in a control flow. This can be somewhat unavoidable when you must look at a String/boolean (i.e. determining what the file type of a file is based on its filename, which in itself can be extracted to a factory class), but otherwise should be avoided. 
        * DECENT: Stamp Coupling - When passing data from one module/class to another, you pass extra unnecessary information that isn't used. 
        * BEST: Data Coupling - communication between modules is done by passing the minimum amount of information necessary as arguments and returning exactly what is needed. 
* Inheritance Flaws
    * "prefer aggregation over inheritance" - when dealing with inheritance, the tight coupling between classes produces substantial changes when the needs of the class change. 
* **dependency injection** - passing in modules as arguments allows us to ensure a function only deals with using the module's data, rather than both getting _and_ using its data. 
    * example of using dependency injection
* UML - see iPad drawings

### Streams 

A special section dedicated to streams (Just use JavaScript bro :shrug:) 

1. Beginning operation: `.stream`.
    * If you are working with a List, you start with `.stream()`
    * ... a Map, use `.entrySet()`
    * ... a set, use 
1. Intermediate operations:
    * `.sorted`, such as `.sorted((a, b) -> a.getSize() - b.getSize())`
        * Can also use stuff like `.sorted(Comparator.comparing(x -> x.getName())` since you are using String's default `.compareTo()` method (since `String` implements `Comparator`). YOu can also do stuff like `Double.compare()`
    * `.filter` - such as `.filter(a -> a.getSize() > 20)`
    * `.limit` - such as `.limit(20)`
    * `.map` - such as `.map(x -> x.getName())`. this could be any function though:
       ```java
       listOfThings.stream().map(thing -> {
        return thing.getFirstName() + " " + thing.getLastName();
       }).forEach(thing -> System.out.println(thing));
       ```
    * `.distinct` - such as `.distinct()` - to remove all duplicate elements (using the element's `.equals` method). 
    * `.peek` - can be used to like print or change state without changing the elements themselves. Such as `.peek(thing -> System.out.println(thing))` or `.peek(state -> setName(getName.toUpperCase()))`. Note that this changes state but not actually the list of elements itself.
1. Terminal Operations - 
    * `.forEach()` - the consumer must return void but do something (i.e. print). 
    * `.count()` - returns the number of items left in the stream
    * `collect()` - like `.collect(Collectors.toList())` - returns a `List<E>` which matches the state of the Stream at the end. This is an _unmodifiable_ list, but you can do just.
    ```java
    List<E> outputList = originalList.stream()
        .[intermediate operations]
        .collect(Collectors.toList());
    List<E> modifiable = new ArrayList<>(outputList);
    ```
    Note for `.collect()` you can also do stuff like `.toList()` to achieve the same affect.
    *  



        

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
    






