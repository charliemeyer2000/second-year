# All Quizzes from SDE

## Quiz 2

1. _Ad hoc solutions are defined as_:
    * A single-purpose solution built without concern for long-term evolution or changes.
1. Which software failures were avoidable?
    * All software failures are avoidable, but it takes work and planning, and attention to detail as software evolves.
1. Which of the following measures the software's internal quality? 
    * Maintainability
1. Which of the following describes the purpose of the JDK?
    * Compiles `.java` files into `.class` Java byte code files
1. Purpose of JVM? 
    * Acts like a computer, handles interactions with the underlying system and hardware
1. Primary benefit of the JRE?
    * Allows portability, as the JRE has a standardized interface that hides details of underlying hardware.
1. Correct way to run a `.jar` fiile? 
    * `java -jar myProgram.jar`
1. What does `git add MyClass.java` do? 
    * Stages any current changes to MyClass.java so that those changes are committed to the local repo
1. `git commit` vs. `git push`
    * Commit stores changes in local repo, push sends local to remote
1. Centralized vs. Distributed Repo?
    * Centralized - all commits from working copy go directly to remote repo, whereas distributed everyone has a local copy that acts as an intermediate step b/w working copy and remote repo. 

## Quiz 3

1. What does gradle do for you? 
    * Download/install external deps used in project
    * Run test suite whenever you build your code
    * Generate build files (.class, .jar)
1. Main purpose of `gradlew`
    * Allows you to use gradle commands via gradlew without having to manually install gradle.
1. what is a fat-jar
    * All external deps are included in the jar.
1. Verification vs. Validation:
    * **Verification** - relates to whether the software matches the internal specification
    * **Validation** - whether the software ultimately satisfies the customer's needs and helps solve problems.
1. Purpose of testing? 
    * Find defects.
1. Correctly order scope of internal testing from _smallest to largest_.
    * Unit, integration, system.
1. Describe `assertEquals(expected, actual)`. 
    * If the two values are not equal, the test fails. If they are, the test does not necessarily pass.
1. If a test is unsound, then the test passing or failing...
    * Doesn't tell us whether a defect exists.
1. What is an equivalence case example in unit testing?
    * Typical, ordinary case like 5/6 for isOdd(). 

## Quiz 4

1. In Java, what is the difference between a _checked_ and _unchecked_ exception? 
    * Java syntactically forces you to handle checked exceptions or use throws to declare the function is capable of throwing a checked exception. UNchecked exceptions have no requirement.
1. Which of the following is most likely a lambda body to use in a comparator? 
    * `(a, b) -> a.getID() - b.getID()`
1. When testing exception cases with `assertThrows` what is best practice?
    * We need to check the state of the object before/after assertThrows to ensure the post-conditions of the exception are as intended.
1. Why should we use Exceptions rather than error codes (like returning -1).
    * Error codes can be mistaken for legit output. 
1. Refactoring is the process of making changes to the ___ without changing the __. The goal is to improve code quality.
    * code, behavior.
1. given `student -> student.getID()`, which of the following java operation would be used?
    * `.map()`
1. When should use try-catch for unchecked exceptions? 
    * YOu should only use a try-catch when you can meaningfully handle an unchecked exception. Otherwise, let it be thrown.
1. Source code _analyzability_ and _understandability_. What is relationship? 
    * Readability is necessary, but insufficient for understandability. 
1. Which of the following internal software quality metrics are affected by source code quality?
    * (all) - analyzability, changeability, testability, maintainability.
1. Which of the following stream operations uses a predicate? 
    * `.filter()`

## Quiz 5

1. Java interfaces:
    * cannot be directly instantiated
    * cannot have implementations that alter the implementing object's state
    * Cannot have fields
1. Java abstract classes:
    * cannot be directly instantiated
1. What are abstract methods in an abstract class? 
    * Methods that must be implemented by a child class. 
1. What is the **Single Responsibility Principle**
    * A class should have only one reason to change
1. **Cohesiveness**?
    * The extent to which elements within the same module are **intra**dependent with other elements in the same module.
1. What is **coupling**? 
    * The extent to which elements within the same module are **intra**dependent with other modules.
1. Key insight about **abstraction**? 
    * Our design should hide implementation details, such as that use of a module only requires understanding its interface, not behavior.
1. What type of coupling comes from boolean arguments?
    * Control coupling.

## Quiz 6

1. Big-bang integration? 
    * Simply integrate everything at once, and do not ingrate modules in any planned or particular order.
1. Which of the following best and most completely describes what a mock is? 
    * A mock class is used to replace dependencies of another class by mocking the dependencies' heavier, using stubs (hard-coded returns). 
1. Which of the following best describes the purpose of this line? `when(mockFoodAllergyList.contains("Nuts")).thenReturn(true);`
    * When we call `.contains("Nuts")` we get "true."
1. Assume we are mocking a class called `myData` and want to test that `.addData(String data)` is called with the argument "test data." Write it.
    * `verify(myMockedData).addData("test data")`
1. A UML class diagram is used by whom? 
    * Developers (internal).
1. If a class **aggregates** one instance of class B, what summarizes the relationship? 
    * Class a **has-a** class B.
1. In architecture, we talk about components. what is a relationship b/w components and modules? 
    * Components refer to major elements in the system, which contain several modules. 

## Quiz 7

1. When talking abt. architecture that has components for Presentation/Business Logic/Data, what kind of architecture is that closes to? 
    * Layered architecture.
1. What best describes business logic? 
    * Enforce the real-world constraints within our software system.
1. Using the same JSON as Question 5, assume we store this as a JSONObject variable in Java called "professor". Which of the following code snippets will get the value of the String that contains the name of CS 1110 ("Introduction to Computer Programming")
    * `String s = professor.getJSONArray("classes_taught").getJSONObject(0).getString("name")`.
1. What are correct statements abt sqlite vs postgres/mysql.
    * Sqlite uses file, others use external servers.
    * Sqlite query syntax can be slightly different.
    * Sqlite doesn't support user accounts within the database (per se).
1. In a SQL table, what is a record? 
    * A single row of data.
1. What is the purpose of primary key? 
    * Unique ID for each record in a table.

## Quiz 8

1. What constraints must a primary key follow? 
    * Unique, not null, but not auto increment.
1. Get all the records from the table PETS.
    * `SELECT * FROM PETS`
1. Get only the age of the pet named stewart.
    * `SELECT Age from PETS where PetName = 'Stewart`
1. What is the purpose of a foreign key? 
    * Act as a reference to a record in another table.
1. What is 1NF? 
    * each cell in a table can only hold a single value. 
1. What is 2NF? 
    * Requirement that all tables have a single column primary key (no multi-column primary keys). 
1. What design pattern separates how to create an object vs. how to use an objecT? 
    * Creation.
1. Using a comparator in `Collections.sort(myComparator)` where the specific instance of Comparator is most closely associated with what? 
    * Strategy
1. What is true about design patterns? 
    * Help communication/understandability becaus they define a common language. 

## Quiz 9

1. What is the purpose of an ORM? 
    * Simplify process of storing/retrieving object instances of classes as records in datable tables w/o the client needing to directly write SQL code. 
1. When using Hibernate, say we had an instance variable named stewart of the class Pet. What would be the correct way to use a Hibernate Session, called session, to store that object in the Database?
    * `session.persist(stewart)`
1. Get a Pet with id of 2 from Hibernate session object?
    * `Pet pet = session.get(Pet.class, 2)`
1. Design pattern convert interface of some existing class into another interface for compatibility
    * adapter. 
1. Consider the following problem: "I want several classes in my software to be able to write information to the same file. However, I want to ensure that I don't try to open that file when it is already opened, (since only one FileWriter for a given file can be active at a time) nor do I want to overwrite content written by other classes."
    * Singleton
1. Which design pattern is most likely being used in the code below?
    * ```java
        Window w = new Window();
        w = new VerticalScroll(w);
        w = new HorizontalScroll(w);```
    * Decorator
1. What is something where you create an object using various public methods rather than one large constructor? 
    * Builder.
1. Pattern vs. Framework:
    * Pattern - general idea of how to solve a design problem. (not tied to code implementation)
    * Framework - library of reusable code to build sogtware with.
1. JavaFX scene, stage, pane.
    * Window is a Stage, on the stage is a scene, containing one or more panes that contain one or more controls/widgets.
1. Which type of JavaFX control to use to type information.
    * TextBox.


## Quiz 10

1. Premature optimization is
    * The root of all evil.
1. What is the purpose of the order of operations in a boolean expression in terms of code optimization? 
    * Put the time/compute-intensive operation as the second-if. 
1. Memoization is a trade off, where we trade increased __ for ___.
    * memory usage for execution time.
1. What is wrong about OO languages?
    * Garbage-collected OO langues tend to be faster and more memory efficient than non-garbage collected languages because the user doesn't have to manually worry about freeing memory manually.
1. Purpose of a profiler? 
    * Shows after execution time spent on each portion of executed code.
1. What part is the "V" in MVC for a JavaFX program.
    * FXML files.
1. In an FXML file, what connects a node to a variable in a controller?
    * `fx:id`


 