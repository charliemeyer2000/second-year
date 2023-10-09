---
title: Reading Notes
author: Charlie Meyer
date: Depends
---

# Reading Notes

## Reading 14 - [Architecture](https://docs.google.com/document/d/1J1mWwgpGpzc9m8bDhD7-d_JsXe_x6NeAqvrn8FHH-No/edit)

### Architecture

With architecture, we want to think of **components**. Components describe the major sub-systems in our software system. We can think of components as:
- groups of modules that handle the same major feature or feature-set
- independent programs 
- data sources
- external internet connections 

Architectural Concerns:
1. **boundaries** - interacting with systems that you have no control over. This can include structure of a JSON object, the life of an api key, rate limiting, etc. Avoiding this can be doing things like hiding interactions behind abstraction
1. **separation of concerns** - Making each component to handle one major concern. This is like making a database for storing persistent data, we want a GUI to exist separate from that database, etc. 
1. **long-term maintainability** - architecture's organization should be clear to understand. Architectural designs are the hardest to change and if we don't make smart design decisions in architecture, entropy can build up quickly.

### Architectural Patterns

A pattern is an established idea of how to solve design problems. Note the difference between a pattern and a framework. A framework is existing code/structure you use to build a program. 

Common architectural patterns:
1. A big ball of mud - no connection whatsoever
1. Layered architecture - each component interacts with the components in the layer above/below it. 
1. Networked architectures:
    * Client-server architecture - two separate components communicating with one another
    * Peer-to-peer - no centralized resource. Clients communicate with each other over the network, with no one user being considered the "main" user. 
1. Monolithic architecture - we deploy an application as a single application. 
1. Microservice architecture - Building applications as microservices - each microservice typically creates an API that exposes a set of features/operations that meet needs. They can be deployed on a different server and can interact with things. 

### Three-Layer Architecture

Notable example of layered architecture. In the example of handling a website that does course registration, we'd consider thee things: **presentation**, **business logic**, and **data layer**. 


## Reading 13 - [Decomposition](https://docs.google.com/document/d/1s4NEoJlDCtIZwzbZJRy99cfvqmT4DA7QQmmHH0c2KDA/edit?usp=sharing)

### UML Class Diagrams

Class diagrams communicate design plan and structure. They are not **commandments** but general guidelines to aid in communication. 

![uml diagram](images/uml-diagram.png)

Here's a way we can describe a class:

![class description](images/class-description.png)

- Class Name at the top. Can be indicated as `<<abstract>>` or `<<interface>>` when they are those. 
- Fields: listed `[name]:[type]`
    * Doesn't include aggregation (i.e. a list of a class, for example).
- Methods: listed as `[name]:[return type]`
    * Common Practice:
        - `+`: `public` items (methods/constructors)
        - `-`: `private` items (generally fields)
        - `#`: `protected` items


Relationship diagrams:

![relationship diagrams](images/relationship-diagrams.png)

1. Dependency - weakest form of coupling, where one class uses another but does not "contain" another. Arrow points towards class being used.
1. Aggregation/composition - when one class "possesses" another, specifically as a field. This contains a diamond on the side of the **owner**, stating that this is the class that has the field containing the other class.
1. Bidirectional Association - both classes meaningfully are aware of one another through mutual aggregation. 
1. Multiplicity: See [reading](https://sde-coursepack.github.io/modules/design/UML-Class-Diagrams/) for more intricate diagram explanations and drawings.
    * **one to one**: ex: every student has one transcript, every transcript is associated w/one student.
    * **one to many or many to one**: Each student has multiple assignments, but each individual assignment is only associated w/one student
    * **many to many**: every student is on several class rosters, each class roster has multiple students. 
1. Realization/Generalization: refers to inheritance through implementing `interfaces` or extended other `class`es. Arrow head points to the class being implemented/extended. 






## Reading 12 - [Design Principles](https://docs.google.com/document/d/11pQgeiM8cOTca0hev_KhGGUB3qDyUzRKajsStCyXYdY/edit?usp=sharing)

### Design

Good design - to allow maintenance so that **the product operates the same as it did before**. Software is expected to evolve to meet new needs. **good design** slows the growth of software entropy. 

### Design Concepts

1. Modularity - break up our problem into little problems. Each unit of code is called a _module_. We think of modules as classes in java. Even the ideas of using helper functions (the process of _decomposition_) within a class is a form of using modularity. We want each module to be functionally independent, i.e. each module can perform its purpose with minimal interactions with the rest of the system. The "gold standard" for functional independence is when two modules only interact through passing arguments/receiving return values via public functions. Interdependence between modules is called **coupling**, we want to minimize coupling.
1. Abstraction - **abstraction** is the process of hiding alll unnecessary details and exposing only required details. This means we hide implementation details behind a minimal interface. We want to **encapsulate** some behavior or data. 
1. Information hiding - mechanism by which we create a limitied interface to achieve abstraction. This is why we used `private` fields even if we have public getters/setters. 

### Modularity

1. **Functional Decomposition** - We decompose our highest-level module into lower-level modules and decompose sub-modules into modules that can't be decomposed further. The book talks about an example of using the Huntington method for apportionment to elaborate on this idea. However, most software doesn't run this way. Our application may be persistent or be asynchronous. Furthermore, this creates high-level functions that are incredibly dependent on each low-level function. If implementation details change, that could cascade upwards significantly. 
1. **Class Decomposition**
    * Cohesiveness refers to how well one class sticks to doing one thing well.
        * WORST COHESION: Loose cohesion, avoided at all costs:
            * Coincidental cohesion - Elements in a module are combined by coincidence (ex having one giant `Main` class).
            * Logical cohesion - elements are grouped into a module because they have the same general activity/similar interfaces. 
            * Temporal cohesion - items are grouped because they occur at around the same time but are otherwise unrelated. 
        * DECENT: Functional cohesion, acceptable:
            * Procedural cohesion - a module with only procedural coupling is supporting unrelated activities in which control passes from one activity to the next, ensuring they execute in a specific order.
            * Communicational cohesion - Elements are grouped together in a module simply because they perform actions on the same data for either input or output. 
        * BEST:
            * Sequential cohesion - Elements are grouped because they describe a single procedure. However, it stipulates that one element's output are directly used by the next element's inputs. 
            * Functional Cohesion - if a module describes a single, well-supported function, it is functionally cohesive.

### Functional Independence

Coupling - the degree to which two modules interact with another:
    * **cohesion** - extent to which elements _in the same module_ are dependent on one another
    * **coupling** - extent to which elements _in interacting modules_ are dependent on each other 

Types of Coupling:
1. Worst:
    * Content coupling - when one class modifies the inner state of a class it depends on 
    * Common Coupling - when two classes share the same global data (i.e. a global variable)
    * Control Coupling - when one class passes a control flag to another class (generally a boolean). 
1. Mid: 
    * Stamp Coupling - when a module passes a data structure to another module, when the entire data structure is not needed.
1. Good: 
    * Data Coupling - all communication between modules is done via passing the minimum amount of data as arguments and returning exactly the data needed. 


### Abstraction

The idea is that "If module A has an instance of/uses Module B, and module B has an instance of/uses module C, module A should never directly interact with module C." This can be done with information hiding:

* Private fields - we can use private fields to hide implementation details. This prevents illegal `set` calls.

### Design Principles

* KISS - Keep it simple, stupid - the idea that you should keep your code simple. This means that you should avoid over-engineering your code.
* DRY Principle - Don't repeat yourself - the idea that you should avoid repeating code. If you find yourself repeating code, you should abstract it into a function/class or implement an interface. However, don't be too DRY - you should only abstract code if it makes sense to do so.
* YAGNI - You ain't gonna need it - you only add features to your code when required. This is because you don't want to over-engineer your code.

### SOLID

Solid is an aconym for five OO-Principles: 

1. S: **single responsibility principle**:
    * Each software module should have one, and only one, reason to change. 
1. O: **open-closed principle**:
    * Software entities should be open for extension, but closed for modification.
1. L: **Liskov substitution principle**:
    * All subclasses should be substitutable for their base classes.
1. I: **interface segregation principle**:
    * Clients should not be forced to depend on methods they do not use.
1. D: **dependency inversion principle**:
    * Depend on abstractions, not concretions.




## Reading 11 -[Exam Review](https://docs.google.com/document/d/1oaJvF_s2TRh2MD-yfbJQRZSF6h2DoU5cTfNdb8pFdbQ/edit?usp=sharing)

### Software Engineering

* Ad-hoc development - Ad hoc solutions are a **one time solution to a problem**. They are not meant to be maintained, and are not expected to be used by other people. They do not work for long-term software projects since they are non-scalable solutions to a problem.
* Software Complexity -
    - **Essential** difficulties are intrinsic to developing software.
    - **Accidental** difficulties come from a particular implementation of software.
    - The "hard part" about writing software is getting over the essential difficulties while minimizing the accidental difficulties.
* Java - 
    * Compiling - the process of converting source code into machine code.
    * Interpreting - the process of executing machine code.
    * Basic Java CLI args:
        * `Java` - the command to run the Java Virtual Machine (JVM)
        * `java -jar` - the command to run a Java program from a jar file
        * `javac` - the command to compile a Java program
    * How java works - JDK, JRE, JVM, JIT. 
        * JDK - Java development kit, the thing that complies your code into a `.class` file. This is a file that contains instructions for the JVM.
        * JRE - The Java Runtime Environment that contains the JVM. This is what runs your `.class` file.
        * JVM - Each JRE contains a JVM. This is the thing that actually runs your code.This is an interpreter that interprets your `.class` files and talks directly with the CPU and memory
        * JIT - Just In Time Compiler - Part of the JVM, part that can compile JVM Byte code into machine code
    * Benefits of the approach to using the JDK, JRE, JVM, JIT -> platform independence. Write once, run anywhere (aka write once, debug everywhere :grin:)
    * Packages
        * These are external libraries that you can use in your code. You can import them using `import` statements. This includes built-in packages from the JDK, and external packages from the internet. Internal packages include `java.util`, `java.io`, `java.lang`, etc. external packages can be included through your `build.gradle` file and copied from the maven repository.
    * Command Line Arguments
        * You can pass in command line arguments into your Java programs to change the behavior of your program. These are passed in as an array of strings in the `main` method. (hence `public static void main(String[] args)`)
* Software Construction:
    * Git Version control:
        * Distributed repositories:
            * This means that every developer has a copy of the repository on their local machine. This allows developers to work on their own copy of the repository without affecting the main repository. This also allows developers to work on their code without an internet connection. 
        * Commands to know:
            * `add` - adds files to the staging area (select all files with the `.`)
            * `commit` - commits the files in the staging area to the local repository. Add a message with the `-m` flag.
            * `push` - pushes the files in the local repository to the remote repository.
            * `pull` - pulls the files from the remote repository to the local repository.
            * `branch` - creates a new branch. Use the `-b` flag to create a new branch and switch to it.
                * You can also list branches by just doing `git branch`
            * `switch` - switches to a different branch. Use the `-c` flag to create a new branch and switch to it. This switches to local branches. To check out to remote branches, use `git checkout -b [branch name] origin/[branch name]`
            * `checkout` - checks out a branch. Use the `-b` flag to create a new branch and switch to it. This switches to local branches. To check out to remote branches, use `git checkout -b [branch name] origin/[branch name]`
            * `merge` - merges a branch into the current branch.
        * Conflict resolution - git doesn't allow you to merge shit if there's a conflict. You gotta fix that shit yourself. You could get something that looks like this:
        ```java
        <<<<<<<<HEAD (Current Change)
            // code
        ========
            // code
        >>>>>>>> Test (incoming change)
        ```
        * Branching:
            * using branches is important, you generally have a flow where each person creates a branch per each feature. Merging effectively and safely includes merging main into the feature branch, then creating a PR against main and merging. Solve the conflicts in your FEATURE branch, not the `main` branch. 
    * Gradle:
        * Gradle is a build tool that allows you to build and test Java programs easily along with managing dependencies. It has a general layout that has an `src` folder with `src/main/java` and `src/main/test` for both running and testing, `gradle/wrapper` to contain gradle's wrapper, `build` that contains all generated files including `.class` and `.jar` files, and at the top level `build.gradle`, `settings.gradle`, and `gradlew`/`gradlew.bat`. `build.gradle` is especially important since it contains the build script that tells gradle how to build your project. You can specify testing with Junit, you can tell it to build a jar file with archiveBaseName, you can tell it to build a fat jar with shadowJar, etc.
        * Gradle Wrapper - this is a script that allows you to run gradle without having to install gradle on your computer. This is useful if you don't have permissions to install gradle on your computer. You can run it with `./gradlew` or `./gradlew.bat` on Windows. Make sure to make it executable with `chmod +x gradlew` on mac/linux. 
    * `./gradlew build` - this builds your project. It runs the `build` task in your `build.gradle` file. This compiles your code and runs your tests.
    * `./gradlew test` - this runs your tests. It runs the `test` task in your `build.gradle` file. This compiles your code and runs your tests.
    * Fat-jar - a fat jar is a jar file that contains all of the dependencies of your project. This is useful for running your project on a computer that doesn't have gradle installed. 
* Testing:
    * Purpose of testing - to find defects in your code.
    * Test Scenarios:
        - inputs: testing arguments and state
        - output: expected vs actual output. Also testing changes to state and return values. 
    * Writing Junit:
        * `@Test` - this is an annotation that tells Junit that this is a test method. This is a method that tests a method in your code.
        * `@BeforeEach` - this is an annotation that tells Junit to run this method before each test. This is useful for setting up your tests.
        * Assertions:
            - `assertEquals` - checks if two values are equal. For primatives, use the version that takes in two arguments. For doubles, use the version that takes in three arguments and a delta (delta is a third arg). For objects, use the version that takes in three arguments and a comparator.
            - `assertTrue/assertFalse` - checks if a boolean value is true/false.
            - `assertThrows` - checks if a method throws an exception. Takes in a class and a lambda function.
        * Types of Testing Scenarios:
            - Equivalence - testing normal inputs/outputs
            - Boundary - testing a value at the boundary of a range (fencepost cases)
            - Exception - testing if a method throws an exception
            - Robustness - cases that are **syntactically valid** but **semantically meaningless**. 
                * For example, if you have a method that takes in a number, you can test if it throws an exception when you pass in a string.
        * Testing Plans:
            - Black Box Testing: testing the functionality of a method without knowing the implementation. Basically, we write tests for the public interface of a class. You should have equivalence partitioning so that you can test inputs/outputs based on "groups."
            - White Box Testing - Also known as "glass box testing," **white-box testing** selects tests while considering the existing implementation of the code being tested. THis is different from black-box testing because we are considering the implementation, not just the interface.
                * Statement - we consider what % of statements (code) has been covered by our tests
                * Branch - we consider what % of branches (control flow) has been covered by our tests. 
                * Conditional - catch every true/false case and other conditional cases.
        * Test-Driven Development - TDD is a software development process that relies on the repetition of a very short development cycle: first the developer writes an (initially failing) automated test case that defines a desired improvement or new function, then produces the minimum amount of code to pass that test, and finally refactors the new code to acceptable standards.
            - Red - write a test that fails
            - Green - write code that passes the test
            - Refactor - refactor your code to make it better
* Defensive Programming
    * Throwing Exceptions - throw when you want to communicate that something went wrong. This crashes the program INTENTIONALLY and should provide a meaningful error message.
    * Handling Exceptions - you can catch exceptions and either decide to throw or continue based upon the exception.
        * Handling "checked" exceptions - these are exceptions that are checked at compile time. You can either catch them or throw them. This includes things like `IOException` and `SQLException` for when you are using a BufferedReader or a database.
    * Custom Exceptions: you can create your own exceptions by extending the `Exception` class. You can then throw these exceptions and catch them.
    * Assertions - these are used to check for errors in your code. They are used to check for **programmer errors**. They are not used to check for user errors. They are used to check for things that should never happen. 
        * `assert` - this is a statement that checks if a boolean value is true. If it is, it does nothing. If it is false, it throws an `AssertionError` with a message. 
        * `assertThat` - this is a method that checks if a value meets a condition. If it does, it does nothing. If it doesn't, it throws an `AssertionError` with a message. 
        * `assumeTrue` - this is a method that checks if a boolean value is true. If it is, it does nothing. If it isn't, it throws an `AssumptionViolatedException` with a message. 
        * `assumeThat` - this is a method that checks if a value meets a condition. If it does, it does nothing. If it doesn't, it throws an `AssumptionViolatedException` with a message. 
        * `fail` - this is a method that throws an `AssertionError` with a message.
    * AssertionError:
        - `-ea` java argument - this is a java argument that allows you to enable assertions. This is useful for testing.
        * `assert` keyword - this is a keyword that allows you to check for errors in your code. This is useful for checking for programmer errors.
* Code Quality:
    - Analyzability:
        - Readability - to what extend we can read and understand the code's syntax
        - Understandability - To what extend we read and understand the code's semantics. You can't have understandability without readability.
    - Code smells: A **code smell** refers to problems within the design and structure of the underlying code in software. Code smells aren't bugs/defects, but problems with _internal quality_ like manageability, readability, and maintainability, etc. This can be something like long functions, long parameters to a function, having boolean parameters in a function, etc.
    - Refactoring techniques:
        * rename identifiers to be more descriptive
        * Introduce constants instead of "magic numbers"
        * Abstract conditional logic - if you have a lot of conditionals, you can abstract them into a function.
        * replace `null` with optional - if you have a function that returns null, you can replace it with an optional. This is useful for when you have a function that returns null and you have to check for null every time you call it.
        * Extract method - if you have a long method, you can extract parts of it into a separate method.
        * Extract class - if you have a class that does too much, you can extract parts of it into a separate class.
        * Preserve Whole Object - if you have a lot of parameters, you can pass in an object that contains all of the parameters.
    - DRY vs. WET
        * Don't repeat yourself - this is a principle that says that you shouldn't repeat code. If you have a lot of repeated code, you should abstract it into a function.
        * WET - code that doesn't adhere to DRY - "write everything twice." 
        * Code comments:
            - comments should only be used when the code is unclear. If the code is unclear, you should refactor it to make it more clear. If you can understand what the code does by simply reading it, you don't need a comment.
            * Documentation tells about what things do, not how they do it.
        * Technical debt - this is the idea that you should clean up your code after you write it. If you don't, you accrue technical debt. This is bad because it makes your code harder to maintain.
* Functional Programming:
    * Lambda functions - these are functions that are unnamed and are used on the fly. They are used in functional programming to pass functions as arguments to other functions. They are useful for when you want to pass a function as an argument to another function.
    * Functional Interface:
        - Comparator: used for sorted() and sort()
        - Consumer: used for forEach()
        - Predicate: used for filter()
        - Function - used for map()
        - Executable - used for assertThrows()
    * Streams:
        - Making streams from lists (`.stream()`) and files (`Files.lines()`) and sets (`.stream()`) and maps (`.entrySet().stream()`)
        - `stream()` vs. `parallelStream()` - parallel streams run in parallel, which is useful for large data sets. However, this breaks the order of printing, so you have to use `forEachOrdered()` instead of `forEach()`.
    * Intermediate Operations:
        * Sorted - sorts the stream
        * map - transforms elements
        * filter - filters elements
        * limit - limits the number of elements
        * peek - allows you to look at the elements in the stream
        * distinct - outputs only unique elements
    * Terminal Operations:
        * forEach - performs an action on each element
        * count - returns the number of elements in the stream
        * toList - collects the elements into a list. Equivalent to `Collectors.toList()`
        * reduce - reduces the elements in the stream to a single value. (count stuff).
    



- **JDK**: When you compile a Java file, the JDK compiles your code producting a `.class` file. This is a _bytecode_ file, which is a file that contains instructions for the Java Virtual Machine (JVM).

Here's a more accurate image of how it works:

![Image](https://sde-coursepack.github.io/modules/java/images/3/compiler3.png)

- **JRE**: This is because the `.class` files don't run on any hardware, they run in a virtual Java Runtime Environment (JRE). Note that this is different from the JDK, which is used for _compiling_, not running. When you download the JDK, it will include a compatible JRE, but you can download a JRE to run files without downloading the JDK.

- **JVM**: Each JRE contains a Java Virtual Machine (JVM). This interacts directly with the CPU, memory, disk, and monitor. Technically, the JVM is an _interpreter_ that interprets `.class` byte code compiled by the JDK, passed to the JVM by the JRE. 
    - Note that Kotlin and Groovy also use the JVM, so you can use them in Java projects.

To help you understand how these all fit together, think of the JRE as a waiter, and the JVM as a chef, where a `.class` file is an order that talks to the JRE who then asks the JVM to execute the order.

- **JIT**: The just-in-time compiler is a part of the JVM, specifically the part that can compile the JVM byte code instructions into machine code instructions for underlying hardware. 




## Reading 10 - Polymorphism ([readings](https://docs.google.com/document/d/17QR_K96ZTnKMctqIKXgDJMgx5PHvWavGm9fkvGRrXuE/edit))

### Benefits of Polymorphism

#### Interface

- interface is a description of the **behaviors** of a class. An example of an interface is the `Comparator<E>` interface in Java which is one method that requires us to define a `int compare(E e1, E e2)` method.
- The **concept of an interface** is different. The interface is both the syntax and the intended _abstract behavior_. (i.e. for `Comparator<E>`, how things should be sorted).

#### Abstraction

- Consider this abstract class:

```java
public abstract class StateSupplier {
    public abstract List<State> getStates();
}
```

This describes a behavior - get states. This doesn't describe the behavior of _how_ to get states, but just that we should get states. You can then `extend` this class and implement the `getStates()` method.

Consider this **abstraction**:

```java
public abstract class ApportionmentMethod {
    public abstract Apportionment getApportionment(List<State> stateList, int representatives);
}
```

We can now implement the Hamilton/Vinton method:

```java
public class HamiltonApportionmentMethod extends ApportionmentMethod {
    public Apportionment getApportionment(List<State> stateList, int representatives) {
        Apportionment apportionment = new Apportionment();
        int totalPopulation = getTotalPopulation(stateList);
        ...//implementation continues
    }
    public void executeApportionment() {
        StateSupplier stateSupplier = getStateSupplier();
        List<State> = stateSupplier.getStates();
        ApportionmentMethod method = getApportionmentMethod();
        Apportionment apportionment = method.getApportionment();
    }

    private ApportionmentMethod getApportionmentMethod() {
        return new HamiltonApportionmentMethod();
        // if we wanted to change to the HuntingtonHill method, we can just change this to return a new HuntingtonHillApportionmentMethod()
    }
}
```

If we wanted to change this at runtime, perhaps during a GUI, we can do something like this:

```java
    private ApportionmentMethod getApportionmentMethod() {
        if (isHamiltonApportionment()) {
            return new HamiltonApportionmentMethod();
        } else {
            return new HuntingtonHillApportionmentMethod();
        }
    }
```

#### Dependency

When we used polymorphism, we pass control to the function. This allows us to write a function instead of a `class` for each `subclass`. 

#### Dependency Inversion

Dependency inversion is the idea that we should depend on abstractions, not concretions. This means that we should depend on interfaces, not classes. Thus, a class never has to know about the implementation of another class, only the interface.

- We pass control to the function: the calling function stops executing and waits until the called function stops executing.
- We call a function indirectly - classes are not dependent on each other, but on interfaces.

### Design

- Software changes
    - We want to make sure that our software is easy to change. If we remove a part of our software, we want to make sure that we don't have to change a lot of other parts of our software/break all of it. 
    - Software is expected to evolve to meet new needs. 
- Entropy: effort measured in developer-hours to maintain software
- Over-design: working too hard on a system or over-designing it when not needed. 
- Agile Design: design that is flexible and can be changed easily. This allows us to refactor design elements later as our needs change.

### Class Relationships/Dependency

#### Depends on (uses)

A class depends on another class when it uses methods or functions from that class. 

```java
public class Course {
    Professor professor;
    List<Student> students;
    
    ...
    
    public EmailResponseCode emailAllStudents(String subject, String message) {
        EmailBuilder emailBuilder = new EmailBuilder();
        emailBuilder.setSender(professor.getEmailAddress())
                .setSubject(subject)
                .setMessage(message);
        for (var student : students) {
            var studentEmailAddress = student.getEmailAddress();
            emailBuilder.addBccRecipient(studentEmailAddress);
        }
        Email email = emailBuilder.get();
        return email.send();
    }
}
```

Notice how `Course` and `EmailResponse` are loosely coupled - `Course` doesn't know anything about `EmailResponse` except that it can send an email. 

#### Aggregation, Composition

Aggregation, "A aggregates B", refers to an instance of a class is a "has-a" relationship. For example, a `Course` has a `Professor` and a `List<Student>`.

Composition, "A is composed of B," refers to a relationship where one or more instances of B are intrinsically part of another class. 

Thik of this as "aggregating" clothes vs. being "composed of" body parts. 

Entertain this example:

```java
public class MailingList {
    private String name;
    private String listServAddress;
    private MailingListHistory history;
    private Set<User> subscribers;
    
    public MailingList(String name, String listServAddress) {
        this.name = name;
        this.listServAddress = listServAddress;
        this.history = new MailingListHistory();
        this.subscribers = new HashSet<>();
    }
    
    //getters here
    
    public boolean addSubscriber(User newSubscriber) {
        return subscribers.add(newSubscriber);
    }
    
    public boolean removeSubscriber(User subscriber) {
        return subscribers.remove(subscriber);
    }
    
    public void sendEmail(Email email) {
        for(User user : subscribers) {
            user.sendEmail(email);
        }
        history.add(email);
    }
}
```

This class `MailingList` aggregates `Users`, but the `MailingList` is composed of a `MailingListHistory`. 

#### Association

- General definition: any interacting b/w classes
- Specific definition: two classes connected in a way that interaction may be needed both ways. 

#### Abstract Class Coupling

Abstract classes typically cause more coupling than interfaces since they:
- have their own fields
- own implemented methods (to use or override)
- own constructors (which children must invoke)

#### Generalization

Generalization encapsulates an "is-a" relationship. For example, a `Student` is a `Person`. This is the tightest form of coupling. 

### Flaws of Inheritance

Abstraction is good if you find yourself repeating al ot of code (i.e. just make an abstract class.) When dealing with inheritance structures, because of tight coupling b/w classes, changes produce _substantial_ changes. Perhaps even an abstract class is too much, just do an intercace. But ultimately, it can be better to leave classes separate than force abstraction upon them, but when abstraction is desireable, using a minimal interface to describe the behavior is the best way to go.

## Reading O9 - Functional Programming, Streams ([readings](https://docs.google.com/document/d/1jVMEZecfmRBgytroPihewTan3589N2Vu2B6qxdn_DWY/edit?usp=drive_link))

### Functional Programming

Functional interfaces are interfaces used to define a **single abstract method** (SAM). This includes:
* Comparator: `int compare(T o1, T o2)`
* Runnable: `void run()`
* Callable: `V call()`
* ActionListener: `void actionPerformed(ActionEvent e)`
* Consumer: `void accept(T t)`
* Predicate: `boolean test(T t)`
* Supplier: `T get()`

#### Anonymous Classes

We can supply a comparator to a `.sort()` method directly. For example:

```java
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("Will");
        names.add("Charlie");
        names.add("John");
        names.add("Bob");
        names.add("Alice");
        names.add("Zach");

        names.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        System.out.println(names);
    }
```

#### Lambda Functions

Rather than doing anonymous classes, we can use unnamed functions called lambda functions that are only the fly, used at _runtime_. 

```java
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("Will");
        names.add("Charlie");
        names.add("John");
        names.add("Bob");
        names.add("Alice");
        names.add("Zach");

        names.sort((String o1, String o2) -> {
            return o1.compareTo(o2);
        });

        System.out.println(names);
    }
```

We've also seen this in things for JUnit testing like `assertThrows`, for example - we can pass in a lambda function to test if an exception is thrown. This is becauase `assertThrows` takes in a `Executable` object, which is a functional interface. This method takes in nothing and returns nothing. 

```java
@Test 

public void testWithdrawInsufficientFunds() {
    BankAccount account = new Account(500);

    assertThrows(InsufficientFundsException.class, () -> account.withdraw(700));
}
```

#### Functional Interfaces to Know

1. Comparator:
    * Method - `public int compare (E e1, E e2)`
    * Use - used in sorting lists (collections.ort) and defining tree sets.
1. Executable: 
    * method: `public void execute()`
    * use - use in assertThrows, dynamicTest, and assumingThat. 
1. Runnable
    * method: `public void run`
    * use: describe a procedure to be executed (typically by a thread)
1. Predicate
    * method: `public boolean test(T t)`
    * use - for checking if a value meets a condition
1. Consumer
    * Method: `public void accept (E e)`
    * use: take in some value and doing something with it, but don't return it. (specifically `forEach`)
1. Supplier:
    * method: `public T get()`
    * use: get results from some source and do something with it It doen't take in any input. An example is `() -> (int) (Math.random() * 6) + 1`
1. Function<E, R>
    * method: `public R apply(E e)`
    * pass some instance of T as an argument, return an instance of R. used in the `map` function in streams. Includes something like `x -> x.toString().toUpperCase()`
1. ActionListener:
    * Method: `public void actionPerformed(ActionEvent e)`
    * used in a GUI, such as `e -> handleButtonPress()`

### Java Streams

a **stream** is like an assembly line - it's a sequence of operations that are performed in order. Take, for example, we can do fancy things with streams to reduce code bloat. For example, we can do:

```java
public int getTotalPopulation(List<State> stateList) {
    return stateList.stream()
        .mapToInt(State::getPopulation)
        .sum();
}
```

and the list goes on. Check out the examples [here](https://sde-coursepack.github.io/modules/refactoring/Java-Streams/)

#### How does it Work?

1. Beginning `.stream()`
    * Starts with a collection that allows us to perorm intermediate operations on and one terminal operation on.
1. Intermediate Operations
    * sorted
        * method: `sorted(Comparator<E> comparator)`
        * example: `.sorted((a, b) -> a.size() - b.size())`
    * filter - outputs only elements that meet a condition
        * method: `filter(Predicate<E> predicate)`
        * example: `.filter(e -> e.size() > 5)`
    * limit - outputs only first `n` elements
        * method: `limit(long n)`
        * example: `.limit(5)`
    * map - transforms elements
        * method: `map(Function<E, R> function)`
        * example: `.map(e -> e.size())`
    * distinct - outputs only unique elements
        * method: `distinct()`
        * example: `.distinct()`
    * peek - allows you to look at the elements in the stream
        * method: `peek(Consumer<E> consumer)`
        * example: `.peek(e -> System.out.println(e))`
    * flatmap - flattens a stream of streams into a single stream
        * method: `flatMap(Function<E, Stream<R>> function)`
        * example: `.flatMap(e -> e.stream())`
1. Terminal Operations
    * forEach - performs an action on each element
        * method: `forEach(Consumer<E> consumer)`
        * example: `.forEach(e -> System.out.println(e))`
    * count - returns the number of elements in the stream
        * method: `count()`
        * example: `.count()`
    * collect - collects the elements into a collection
        * method: `collect(Collector<E, ?, R> collector)`
        * example: `.collect(Collectors.toList())`
    * average, sum, min, max - returns the average, sum, min, or max of the elements in the stream
        * method: `average()`, `sum()`, `min()`, `max()`
        * example: `.average()`
    * reduce - reduces the elements in the stream to a single value
        * method: `reduce(R identity, BinaryOperator<R> accumulator)`
        * example: `.reduce(0, (a, b) -> a + b)`

#### Parallel Streams

We can use `.parallelStream()` to run operations in parallel. This is useful for large data sets.

If you notice that printing parallel streams breaks the printing in order, do something like this:

```java
    public void printRepresentationAlpabeticalOrder(Representation representation) {
        Representation.getStateList().parallelStream()
        .sorted((s1, s2) -> s1.getName().compareTo(s2.getName()))
        .map(state -> state.getName() + representation.getRepresentativesForState(state))
        .forEachOrdered(string -> System.out.println(string)); // note forEachOrdered
    }
```


#### Files

We can use `Files.lines()` and `BufferedReader.lines()` as a `Stream<String>` for File reading.





## Reading 08 - Code Quality, Refactoring, Method Extraction ([readings](https://docs.google.com/document/d/1V2Nf22FrBFdPgAxJ2IqzUHad3nYL2jRL1mJ_F-OHSJw/edit))

### Analyzability

Analyzability breaks down into two parts:
* Readability - to what extend we can read and understand the code's syntax
* Understandability - To what extend we read and understand the code's semantics.

#### Readability

- Spacing: 
    * indentation, line breaks, and other things make code more readable without any syntactic changes.
- Identifier Names
    * Variable names should be made up of human-readable and human-pronounceable words.
    * Variable names should be distinct from each other that they can be told apart from each other at a glance.

#### Understandability

Understandability is the measure of the extend to which the _semantics_ or meaning/intent, of the code can be understood. Readability is a _necessary but insufficient condition_ for **understandability**. That is, our code **cannot** be understandable if it isn't readable. 

- Names that communicate intent: add spacing in names
- Use enums or final constants for "magic numbers" (random indices, etc).
- Encapsulation:
    * Hide implementation details.
    * As long as the _interface_ of a function remains unchanged, classes will be unaffected if we change the inner workings of a function/that class.

#### Conclusions

1. Analyzability is vital and helps us understnad code while allowing it to be reusable and maintainable.
1. Readability is a part of analyzability that relates to being able to correctly interpret the syntax of the software.
1. Understadability is the part of analyzability that relates to understand the sematics, or the _high level_ meaning of the code.

### Code Quality/"Good Code"

#### Software Entropy

this means that as we add more features to our software, it becomes more complex and harder to maintain.

The problem is that you sometimes don't spend time cleaning-up after you finish a task. By not doing this, you accrue **technical debt** which then accrues interest. As our software builds technical debt, it gets harder and harder to edit. 

The best time to improve your code is after you write it. The second best time is now. The worst time is "later," as "later equals never." 

Note that you always read more than you write code. Thus, there is no trade-off of quality vs speed in software development. Writing clean code pays off the very next time you have to re-read your code. 

### Code Smells

A **code smell** refers to problems within the design and structure of the underlying code in software. Code smells aren't bugs/defects, but problems with _internal quality_ like manageability, readability, and maintainability, etc. 

Common Mistakes:
1. Large/Long methods - Functions should be doing one thing and one thing only. If they are long with multiple loops/conditionals, the function is probably doing several things. 
1. Large Classes
    * Don't put everything inside of Main.java. 
    * A class can be hard to understand if it has too many fields that have not much to do with each other, the class has too many methods that do wildly different things, and the class has _thousands_ of lines of code
1. Long Parameter List - can lose argument order for functions with many inputs. It's easier to make multiple monadic calls. 
    * Monadic - 1 argument function
    * Dyadic - 2 argument function
    * Triadic - 3 argument function
1. Boolean parameters - if you have a boolean parameter, you should probably have two separate functions.
    * You can also replace booleans with polymorphism - you can have two different classes that extend the same class, and then you can call the same method on both classes and they will do different things. So, for example yo could have an `InterestExemptStudent` and a `Student` rather than a boolean `isInterestExempt`.
1. Primitive Obsessions - A lot of fields in a class can create a lot of bloat, especially if you're already writing 2 methods (a getter and a setter) for each field. 
1. Duplicate Code - if you are copying and pasting code, you should probably make a function. We want to be **DRY** (Don't Repeat Yourself) rather than **WET** (Write Everything Twice).
1. Message Chains - if you have a chain of method calls like `a.getB().getC().getD()`, you can break that up into multiple lines. It's okay, less lines of code doesn't always mean it's better.

## Refactoring

* **refactoring** is the process of making changes to the code to improve the **internal** software quality without making changes to the functionality of the software. 

### Code Quality

* No one writes easily understandable/changeable code the first time. 
* Refactoring code ensures that we don't change functionality but internal maintainability. 
* Tests are the scaffolding that allow us to remodel our code. 

Some ways to improve quality:
1. Extract constnats - use constant values rather than "magic numbers" 
1. Renaming identifiers - variables/functions/constants can  be renamed. Don't use find-and-replace, because you might change something you didn't want to.
1. Renaming classes
1. Moving classes between packages - can click and drag in the project explorer. Make sure to move your `[ClassName]Test.java` file accordingly, too.
1. Change signature - 
1. Replace Array with Object - if you have an array of values, you can make a class that holds those values and then make an array of that class.
1. Inline variable - if you have something that is a boolean value, don't return true/false. just return the boolean value.
1. Abstract conditional logic - if you have a bunch of conditionals, you can make a function that returns a boolean value and then use that function in your conditionals.
1. Replace error code with exception - rather than returning -1, throw an exception.
1. Replace exception with optionals - the Java `Optional<T>` class is useful for describing a value that may or may not be present. Optionals allow us to communicate syntactically that a method may either return a value or not.
1. Replace null with optionals - if you have a null value, you can use optionals instead.

## Extract Method

### Breaking up Big Methods

Functions do one thing and one thing only!

### Well-Written Prose

Well-written prose is easy to read and understand. It has a clear structure, and each paragraph has a clear purpose.

## Duplicate Code and Clean Test

### Clean Testing

Here's a Library class:

```java
public class Library {
    public static final int MAX_BOOKS_PER_PATRON = 3;

    private Map<Book, Integer> bookCopies;
    private List<Patron> patrons;

    public Library(Map<Book, Integer> bookCopies, List<Patron> patrons) {
        this.bookCopies = bookCopies;
        this.patrons = patrons;
    }

    public int getNumCopies(Book b) { ... }

    public void addBooks(Book b, int copies) { ... }

    public void checkOut(Patron p, Book b) { ... }
```

and here is a LibraryTest:

```java
public class LibraryTest {
    @Test
    public void addBooksNewBooksTest() {
        Map<Book, Integer> testBookCopies = new HashMap<>();
        List<Patron> testPatronList = new ArrayList<>();
        Library testLibrary = new Library(testBookCopies, patronList);
        Book gardensOfTheMoon = new Book(1,
                "Gardens Of The Moon: Book 1 of Malazan Book of the Fallen",
                "Steven Erikson");

        testLibrary.addBooks(gardensOfTheMoon, 2);

        assertTrue(testBookCopies.containsKey(gardensOfTheMoon), "Test book not added to Map");
        assertEquals(2, testBookCopies.get(gardensOfTheMoon), "Incorrect number of copies after add");
    }

    @Test
    public void addBooksExistingBooksTest() {
        Map<Book, Integer> testBookCopies = new HashMap<>();
        List<Patron> testPatronList = new ArrayList<>();
        Book gardensOfTheMoon = new Book(1,
                "Gardens Of The Moon: Book 1 of Malazan Book of the Fallen",
                "Steven Erikson");
        Library testLibrary = new Library(testBookCopies, patronList);
        testBookCopies.put(gardensOfTheMoon, 2);

        testLibrary.addBooks(gardensOfTheMoon, 2);
        assertTrue(testBookCopies.containsKey(gardensOfTheMoon), "Test book no longer in Map");
        assertEquals(4, testBookCopies.get(gardensOfTheMoon), "Incorrect number of copies after add");
    }
}
```

This is not good.

### @BeforeEach test setup

We can re-initialize each value at the start of every test using JUnit's @BeforeEach. This tells JUnit to run this function **before each test**. We need to do this because we want to make sure every test run is independent; that is, no single test affects any other. This is b/c we want our tests to fail on their **own** conditions. 

Here's a setup:

```java
    @BeforeEach
    public void setupDefaultTestObjects() {
        testBookCopies = new HashMap<>();
        testPatronList = new ArrayList<>();
        testLibrary = new Library(testBookCopies, testPatronList);

        gardensOfTheMoon = new Book(1,"Gardens Of The Moon: Book 1 of Malazan Book of the Fallen", "Steven Erikson");

        testCheckOutList = new ArrayList<>();
        testPatron = new Patron(12, "John", "Smith", patronCheckedOut);
    }

```
and now we can rewrite our test to:

```java
@Test
    public void addBooksNewBooksTest() {
        testLibrary.addBooks(gardensOfTheMoon, 2);

        assertTrue(testBookCopies.containsKey(gardensOfTheMoon), "Test book not added to Map");
        assertEquals(2, testBookCopies.get(gardensOfTheMoon), "Incorrect number of copies added");
    }
```

#### Custom Assertions

We can make our own assertions by refactoring a test, for example:

```java
    @Test
    public void addBooksNewBooksTest() {
        testLibrary.addBooks(gardensOfTheMoon, 2); // add copies of a new book

        assertLibraryHasNCopiesOfBook();
    }
    // notice no @Test tag
    private void assertLibraryHasNCopiesOfBook() {
        assertTrue(testBookCopies.containsKey(gardensOfTheMoon), "Test book not added to Map");
        assertEquals(2, testBookCopies.get(gardensOfTheMoon), "Incorrect number of copies added");
    }
```



## Reading 07 - Defensive Programming ([reading](https://sde-coursepack.github.io/modules/refactoring/Defensive-Programming/))

### Defensive Programming

Defensiveness considers things that could fail to no fault of the developer: 
1. The user may not include any command line arguments
1. The user may link to an invalid file
1. The user may have errors in their input file
1. The user may have the file open in excel, which may cause errors

With respect to these eros, the users should never see a programmer error, and should be informed when they make a user error with a clear description of what went wrong.

#### Client Classes

We have to consider our _clients_ and can make a _client class_ that uses our code, and ensures that (1) the client uses the class as intended (2) they will be made aware if they aren't using it properly (3) our class cannot be incorrectly used by the client to create an erroneous state. 

#### Rollback and Throw

If an erroneous state is detected, we can _rollback_ any changes the method made _before_ throwing an exception. 

#### Assert

the `assert` keyword throws an assertionError if the statement is false. This is useful for checking preconditions and postconditions. Note that you must allow AssertionErrors by adding the `-ea` flag to your run-configs. 

### Exceptions Best Practices

Throw exceptions when the return value would not make sense given the inputs. For example, you'd throw an exception in an `isPrime()` function if the user passes in a 0/negative value. However, if you have a `try/catch` block, don't throw exceptions if yuo're just trying to return a `true/false` output. 

You'd use try-catch blocks if you want to allow the user to continue using the program even if there is an error.

#### Exceptions you Can't handle

There are instances for a function where you don't even need to catch the exception. For example, if you wanted to execute a `reverseString()` method, there's no need to catch a nullPointerException because you can't do anything about it.

#### Exception Types

1. Checked Exceptions - exceptions that Java forces you to either handle or throw. 
1. Unchecked Exceptions - exceptions that Java does not force you to handle or throw.

#### CustomExceptions

Writing custom exceptions is a way to **communicate intent** as to why an exception occurred. 

### Testing With Exceptions

We can use `assertThrows` to test that an exception is thrown.

```java
    @Test
    void testGetThrowsException() {
        MySortedList myList = new MySortedList();
        assertThrows(IndexOutOfBoundsException.class,
                () -> myList.get(0));
    }
```

#### Rolling Back

We must make sure that when exceptions are thrown, we do not create any unintended side effects. 












## Reading 06 - Testing([reading](https://sde-coursepack.github.io/modules/testing/Test-Plans/))

### Test Plans

A colleciton of tests is a **test plan** and is necessary to ensure that you are testing all the features of your software. There is _no perfect number_ of tests to write, but we want to use a systematic approach to write _enough_ tests to ensure that our software is working as expected.

#### Types of Tests

Consider the class `MySortedList` with functions `get(int index)`, `add(int value)`, `contains(Object target)`. 

1. Equivalence Tests - shows how the software behaves under normal, predictable conditions
1. Boundary - shows how the software behaves at the boundaries of the specification
1. Exception - test cases that cannot be meaningfully executed correctly ands should throw exceptions.
1. Robustness - cases that are **syntactically valid** but **semantically meaningless**. 

#### How many do i need? 

* We want to test our code thoroughly enough so that we are justifiably confident in its correctness.
* We don't want to **test for the sake of testing**, because that is a waste of time and resources.

We can't have a perfect test plan, but we can modify it as we go to catch all the things we encounter as we keep working. 

### Black Box Testing

Black box testing is an idea where we test what scenarios to write according to the specification of the modules we are testing (a function). We are testing the _interface_, not the _implementation_. 

Testing Strategies:
1. Equivalence Partitioning - partitioning breaks up something big into something small. For our case, this is breaking up our _equivalence cases_ into groups that largely behave the same. 
    * For example, if we wanted to test the `Math.abs()` function we could partition the inputs into 3 groups:
        1. Positive numbers
        1. Negative numbers
1. Boundary Testing - in our example with `Math.abs()`, this would be testing boundary cases like "0".

### Test Driven Development

Test Driven Development (TDD) is a development process where you write tests before you write the code. 

Process:
1. Write a stub - a stub is a function that does nothing but return a value. 
1. Write your first test!

With TDD, you are supposed to write **one test** at a time, and for each test write just enough code to make the last test pass. 

### White Box Testing

Also known as "glass box testing," **white-box testing** selects tests while considering the existing implementation of the code being tested. THis is different from black-box testing because we are considering the implementation, not just the interface.

With white box testing, we want to consider code _coverage_, that is what percentage of _statements_ have been covered by our tests. 

#### Branch Coverage

Branch coverage is a type of white-box testing that considers the _control flow_ of the code. This means that we want to test all possible paths through the code. So for example, for every `if` statement, test the conditions `true` and `false`, and for every loop we test `enters loop` and `does not enter loop`.

#### Conditional Coverage

While we want to catch every true/false case, we also need to consider cases like `if (a > b && b > 0)`, we need to consider every possible case. 

#### Path Coverage

Path coverage is interested in what percentage of possible paths through our code we have taken. 

### TDD Workflow







## Reading 05 - Testing ([reading](https://sde-coursepack.github.io/modules/testing/V-and-V/))

### V&V - Verification and Validation

V&V allows you know when you are done and how you know when that time comes. 

Verification - "does our code meet our specification?"

Validation - "is the customer satisfied with our product?"

#### Differences

When we discus _verification_, we are focused on the **internal** perspective as developers. This is code quality, ensuring the code does what's expected, etc. On the other hand, _validation_ is primarily external and asks if this is what the customer wanted. 
* **Verification**: Did you build the thing right?
* **Validation**: Did you build the right thing?

#### Defects, Failures, Mistakes, Errors

1. Defect - existing problem according to the software specification in the product that hasn't been discovered yet.
1. Failure - the inability of the software system to perform its function according to the specification.
1. Mistake - human error that causes something incorrect, like a bug or misunderstood customer need.
1. Error - the difference between the current state and the correct state. 

![Feedback Cycle](images/feedback-cycle.png)

### Testing

**Testing** is executing all or part of a program in a controlled manner to find **defects**.

#### Testing Scenario

A testing scenario is where you create a controlled environment with defined inputs and expected behaviors. A **test passes** when the expected results and actual results match.  The purpose of testing is **TO FIND DEFECTS**.

#### Main Method Testing

For now, we write tests in the main method. Consider:

```java
    public static int max(int a, int b, int c) {
        if (a > b) {
            if (a > c) return a;
            else return c;
        } else {
            if (b > c) return b;
            else return a; // should be return c
        }
    }
```

This function takes in 3 integers, and returns the largest one. 

Also, consider this max function:


```java
public static int max(int a, int b) {
    return (a + b + Math.abs(a-b)) / 2;
}
```

Everything works fine until we reach `Integer.MAX_VALUE` in java, for example, we are trying to do something with the largest number `int x = 2147483647;`. This is where our error happens - if our number is more than half the maximum integer, we cannot represent the number doubled. 

A quick fix is to use `longs` instead of `ints`.

It's important to remmebr why we test - to find defects. Remember - **defects are already there**, and if we don't test properly we won't find the defects.


### Types of Testing

#### Unit Testing

**unit testing** is testing individual modules (typically, individual functions). This allows us to catch "silly bugs" immediately. This is great because it can isolate the bug to a method and reduces the amount of code we need to search through.

#### Integration Testing

Where unit testing is used to verify individual modules are operating well by themselves, **integration testing** is when modules are combined. 

For example, if we are testing a method in a class that requires another class to function, we aren't just testing one class, but that class **within the larger operation that comines the class with another class**.

#### System Testing

System testing involves testing the entire system from the user perspective. They generally follow a script to use a feature/set of features within an application. If a defect is found first within **system testing**, this means that there was a lack of thoroughness in unit/integration testing. 

#### Regression Testing

**Regression testing** is when you re-run tests that have already passed to ensure that they still pass. This is because you could introduce a new bug that breaks a previously working feature. Gradle actually performs integration testing for us using `gradlew test` and `gradlew build`.

#### CI

CI is the practice in Software Dev where your code is merged into/added to a "protected" branch, run against a testing suite, and if it passes, it is merged into the protected branch. This is done automatically, and is a great way to ensure that your code is always passing tests.

GitHub offers GitHub Actions. 

#### External Testing (Customers)

1. Internal Testing - testing by the developers
1. Alpha testing - testing carried out internally by the development organization. 
1. Beta testing - testing carried out by the customer in a live environment that is closely monitored. 
1. Acceptance testing - testing with the purpose of **validation** 

### JUnit 5

Testing framework integrated through `gradle`. 

#### WHy not Main method testing?

1. Testing in `main` we have to check whether a test passes or fails by reading the output. 
1. The `main` method of a class may have responsibilities, like executing the software. We want to isolate testing our software from executing our software. 
1. If we want to run multiple testes on multiple classes at once, interpreting results can be a pain.

#### Test Class

With JUnit, we organize our unit-test classes into classes that are **parallel** with the class being tested.

For example, We usually make a `MainFunctionsTest` class. When we are testing, gradle will four our `main` and `test` folders together so that the test classes can access the classes in `main`. But when executing the software, gradle only uses the `main` folder and not anything in test. 

#### Test Methods

A test class contains methods. A test fails if it crashes or throws an `AssertionError`. a test passes when it executes completely w/o throwing an AssertionError. 

```java
package edu.virginia.cs.testingintro;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class MaxFunctionsTest {
    @Test
    @DisplayName("Testing 3-argument max with Descending order arguments: 3, 2, 1")
    public void testMaxDescendingArguments() {
        // input
        int a = 3, b = 2, c = 1;
        //expected output
        int expected = 3;
        //actual output
        int actual = MaxFunctions.max(a, b, c);
        //assertion
        assertEquals(expected, actual);
    }
}
```

@Test? 

This is an identifier for a test method. All test methods include an @Test tag which must meet the specifications of:
1. Must be `public` and not `static`
1. Must return `void`
1. must take in no arguments

#### Assertions

Assertions are the way we check if a test passes or fails. `assertEquals` used above is an assertion. Note that this has an arugment order of `expected` and `actual`.

`assertEquals` with objects uses the `equals` method to check if the objects are equal.

So consider this example where they are equal:

```java
    @Test
    public void stringTest() {
        String a="Will ";
        a += "McBurney";
        String b="Will McBurney";
        assertEquals(a,b);
    }
```

but in this example, since the literaly memory addresses are not the same, these two arrays wil not assert equal:

```java
    @Test
    public void arrayTestFail() {
        String[] a = {"Will", "McBurney"};
        String[] b = new String[2];
        b[0] = "Will";
        b[1] = "McBurney";
        assertEquals(a, b);
    }
    // note that you can use "assertArrayEquals" instead to compare the CONTENTS of the arrays, rather than memory addresses
```

#### AssertEquals with doubles and Doubles.

When considering the impresicion of flaoting point numbers, we have to be careful when testing floats, like `double` or `Double`, or `float`. Thus, we can do `assertEquals(double expected, double actual, double tolerance)`.

#### Boolean Assertion

1. `assertTrue(value)` - passes if true, error if false
1. `assertFalse(value)` - passes if false, error if true

#### Null Assertion

1. `assertNull(value)` - throws assertionError if x **is not null**.
1. `assertNotNull(value)` - throws assertionError if x **is null**.

#### Exception Assertion

`assertThrows` is used when we expect the code we are using to throw an exception. Consider this code:

```
    @Test
    void initiallyEmptyGetThrowsExceptions() {
        MySortedList myList = new MySortedList();
        assertThrows(IndexOutOfBoundsException.class,
                () -> myList.get(0));
    }
```

#### TestingDocumentation

@DisplayName - we can give a display name to a test. This is useful for when we have multiple tests that are similar, but we want to differentiate them. This identifies what function/module was tested, what inputs/outputs, and what a failure indicates. 

Assert fail messages - you can pass an optional message for an assertion failure. 
* `assertEquals(int expected, int actual, String message)`
* `assertEquals(double expected, double actual, double tolerance, String message)`

### Testing w/Objects

We can write tests with objects to check for **state changes**:

```java
    @Test
    void testAlreadyChangedSetNumberChanged() {
        //Setup and configure test object
        NumberChanges nc = new NumberChanges(7, 4);
        
        //run operation to be tested
        nc.setNumber(13);
        
        //Check to ensure object behaved as specified
        assertEquals(13, nc.getNumber(), "Number did not change to 13 correctly");
        assertEquals(5, nc.getTimesChanged(), "Number of times change did not correctly increment");
    }
```

#### Best Practice

1. One Operation per test - just becuase you are testing more things in one test doesn't make it better. 
1. Sound tests - one that correctly testes against the specification. if a test is _unsound_, it is incorrect and acts as misinformation.
    * False positive: a test fails, indicating there's a defect, when there's no defect actually
    * False negative: a test passes, indicating there's no defect, when there is a defect.
1. One assertion per test (not very important to follow, but you can if you want.)
1. Call the tested operation **once**
1. @BeforeEach tag - this is a method that is run before each test. This is useful for when you want to reset the state of an object before each test.

#### What methods to test? 

We want to test all `public` and `protected` methods. We do not test `private` methods because they are implementation details and can change. We want to test how the **object behaves**, not how it's _implemented_. 

#### Protected Fields

IF we want to test some field, we can make it `protected` instead of `private` so that classes in the same package can treat the variable/method as though it were public. 



## Reading 04 - Build Tools ([reading](https://sde-coursepack.github.io/modules/construction/Build-Tools/))

### Build Tools

When we want to import a library, we can simply write something like `org.json` to then parse JSON. BUt this is not a built-in java library. Thus, we can instead use build tools to do this for us to manage external libraries, building the `.jar` files, testing, and more. The 4 most common tools for Java building:

1. **make**: Part of the GNU project that creates a `makefile` that specifies how to build a project.
1. **ant**: Apache Software Foundation built ant that used XML configuration files to define the build process. Its biggest weakness was not having any way to manage dependencies like external libraries.
1. **maven**: also used XML like ant, but added dependency management by adding a `pom.xml` file.
1. **gradle**: Same features as Maven but better and shorter syntax, and allows you to run the build script without downloading the gradle software itself. 

### Gradle (follow along with [this code](https://github.com/sde-coursepack/NBAExcelTeams))

If you want to simply clone this repo and run it `git clone https://github.com/sde-coursepack/NBAExcelTeams.git`, you'll notice you can't. This is because it has many dependencies! Rather than that, you can just run `cd NBATeams` and `gradlew build` to download deps, optimize build process, compile code, run the testing suite, and output the `.jar` file for you.

once finished, gradle will product the `.jar` file in the `build/libs` folder. You can then run it with `java -jar NBA-Excel-1.0.jar`

#### Gradle Project Structure

The root directory is the projects root folder.

1. `src` folder - contains all source code both for running (`src/main/java`) and for testing (`src/test/java`).
1. `gradle/wrapper` - folder called `gradle` that contains `wrapper`
1. `build` - contains all generated files when building the project including `.class`, `.jar`, and test results.
1. gradle files - `build.gradle`, `settings.gradle`, `gradlew` and `gradlew.bat` are needed files for gradle to run. Most importantly, `build.gradle` is as script to build the project according to `settings.gradle`.

#### build.gradle

This is the heart of the gradle build. It uses Groovy and includes plugins, repositories, dependencies, and tasks.

1. Repositories - tells gradle that you want to download all references from that repository.
1. Dependencies - lists all external libraries. 
1. Test - we just use `useJUnitPlatform()` so leave it as is. You can see an outputted html file in `build/reports/tests/test/index.html` that shows the results of the tests.
1. jar - tells gradle how to build our output jar file.
    * archiveBaseName - output name of the jar file. It automatically appends version number
    * group - the group name of the project. This is used for dependency management.
    * duplicatesStrategy - leave it as "exclude."
    * manigest - define the properties of our jar manifest. When you are releasing a runnable jar, you must include the `attributes "Main-class"` item. 
    * fat-jar: contains **not only my code** but all the code of your dependencies. 

#### Gradle BUild

This tells gradle to execute the build script. this is **not** the same as `gradlew build`. 

#### Gradle Wrapper

If you don't have perms to run `gradlew build` you can just do `chmod +x gradlew`. This does the same as `gradle build`, but note that it does not require you to have gradle installed on your computer. 

### Example With POI

APache POI is used to read formats like word, excel, etc. We will be writing information about NBA teams onto an excel spreadsheet. 

To add poi with gralde, we can go to the Apache PO-OOXML library [here](https://mvnrepository.com/artifact/org.apache.poi/poi-ooxml). We then copy the text that says `implementation group: 'org.apache.poi', name: 'poi-ooxml', version: '5.2.2'` and paste it into our build.gradle file inside of the dependencies closure. We also need to add log4j: `implementation group: 'org.apache.logging.log4j', name: 'log4j-core', version: '2.18.0'` since it's used by `poi-ooxml` at runtime. Note that we can specify that `log4j` is `runtimeOnly` in our deps, since we are not using it in our code so we don't need to load the library. If you're ever unsure though, just leave your deps as `implementation`. 

here are our deps:
```
dependencies {
    implementation group: 'org.apache.poi', name: 'poi-ooxml', version: '5.2.2'
    runtimeOnly group: 'org.apache.logging.log4j', name: 'log4j-core', version: '2.18.0'

    testImplementation 'org.junit.jupiter:junit-jupiter-api:5.9.0'
    testRuntimeOnly 'org.junit.jupiter:junit-jupiter-engine:5.9.0'
}
```

You'll also notice that JUnit libraries are `testImplementation` and `testRuntimeOnly`, this is because we are separating the testing and running processes. 

... skipping stuff that you should read but i don't need to write down ...

#### Enumerated Types

An enumerated type is a type that has a fixed set of values. For example, we can have a `public enum Conference { EAST, WEST }` and then we can use `Conference.EAST` and `Conference.WEST` as values.

#### Java Streams and Lambda Bodies

Java streams are a way to iterate through a collection of objects. For example, we can do:

```java
List<String> names = new ArrayList<>();
names.add("Will");
names.add("Charlie");
names.add("John");
names.add("Jane");
names.stream().forEach(name -> System.out.println(name));
// javascript better.
```

## Reading 03 - Version Control

### Git Basics

![Git Cheat Sheet](images/GitCheatSheet.png)

### Version Control

A version control system (VCS) is a system that tracks changes to files in a project over time. There are many reasons to use VCS besides just collaboration:
- Safety: If you mess up, you can quickly revert to a previous version.
- Debugging: In large software systems, you can run into problems with **stability**, that is, changes in one location of code can negatively impact other parts of the code. With VCS, you can quickly revert to a previous version to see what changed, and with test-driven development, you can quickly see what broke.
- Collaboration: Allows multiple branches and merging of code. 
- Conflict Detection: If two people are working on the same file, you can see what changes were made and merge them together. It will **not** resolve conflicts for you, but it will tell you where the conflicts are and **forces** you to take manual effort to resolve them.
- Deployment: You can deploy your code to a server, and if you mess up, you can quickly revert to a previous version. This can be done quickly using a CI/CD pipeline, like GitHub Actions. 
- Regression Testing: a VCS system can be set up to reject pushes and merges to important branches like `main` or if a push causes a test to fail to not merge that code.

#### Centralized Repository

Older VCS systems used a centralized repository:

![Centralized Repo](images/centralized-repo.png)

IN this system, the remote repo is where all the shared versions of the software are stored, and when they want to record their work they have to **commit** their changes to the remote repo. When they want to get work from the repo, they **update**.

#### CVS

Concurrent Version Systems (cvs) was an early form of a centralized repo that had branching alongside committing and updating. This had some flaws because it tracked version control separately for each file, meaning commits of multiple files had different numbers. Furthermore, it would reject a commit with a conflict. And if you committed multiple files and only one file had a conflict, every other file would go through! Weird. 

#### SVN

Apache Subversion `svn`, is a more popular centralized-repo VCS system. It had a single commit number for all commits and accepted either all files or no files. 

#### Distributed Version Control Repo 

![Distributed Repo](images/distributed-repo.png)

this is a system that has a local repo and a remote repo. The local repo is where you do all your work, and the remote repo is where you push your changes.

Generic operations are:
- Commit: store your current changes in the local repo
- Update: get any changes to your local repo
- Push: send the state of your local repo to the remote repo
- Pull 0 get any new changes from the remote repo

#### `git`

`git` is a distributed VCS system. It is the most popular VCS system, and is used by GitHub, GitLab, and BitBucket.

This system separates committing from pushing, resulting in a "committing early and often" mentality. Furthermore, they also support a staging area. 

#### `git` and GitHub

GitHub didn't invent `git`, but it hosts remote repositories so you don't have to create your own. It also supports CI things and other features. It also supports PR's and can be helpful for the job search.

#### .gitignore

`.gitignore` is a file that tells `git` to ignore certain files. For example, if you have a `.class` file, you don't want to commit that to the repo, so you can add it to the `.gitignore` file.

```
# Gradle related folders
build/
.gradle/

# Local settings files
gradle-app.setting
.settings/
.classpath
.idea/**/workspace.xml


# Ignore java compiled files
*.jar
*.class

# Don't ignore gradle wrapper jar
!gradle-wrapper.jar
```

I will not be going over how to create a repository and alla that because we know this. 

### Git Branches

When we create a git repo, the default branch is `main` or `master`, but we want to avoid working directly to `main`. 

IntelliJ has a GUI for branching. 

You can view all branches with `git branch` and create a new branch with `git branch my_new_branch`

Checkout means "I want to switch to this branch." You can checkout to branches with `git checkout branch_to_switch_to`. 

You can merge your work with the `merge` command. It is best practice to **first merge from development into our feature branch**, resolve any conflicts _within our branch_, only the emerging our feature branch to development. 

### Repo Operations

- `add`: adding new changes. To add all changes, do `git add .`. 
- `commit`: Commits require a message, so you can do `git commit -m "your message here"`.
    - You cannot commit individual files, because commit automatically stores *all staged* changes. So your message describes all staged changes. 
- `push`: Pushes all commits in your current branch since the last push to the remote repo. **Remember to pull before you push**.
- `pull`: gets the most recent changes from the remote repo for your current branch. If you get an error when you try to pull, this means that you have changes that have not been committed. 
    - You can stash changes for changes you don't want to commit but also don't want to save:
        * `git stash`
        * `git pull`
        * `git stash pop`

Each commit has a hash, and is identified by the first 7 hexadecimal characters that identifies the commit. 

Conflicts can occur when you commit to a branch in the remote repository taht someone else has pushed to since your last pull. Here's an example conflict 

```
public class HelloWho {
    public static void main(String[] args) {
<<<<<<< HEAD
        if (args.length == 0) {
            String who = args[0];
            System.out.println("Hello, " + who);
        } else {
=======
        try {
            String who = args[0];
            System.out.println("Hello, " + who);
        } catch (ArrayIndexOutOfBoundsException e) {
>>>>>>> 7bce35c8b1e70f4b3daf478d772546d554f07e96
            System.out.println("Error: No command line arguments");
        }
    }
}
```

1. Between `<<<<<<< HEAD` and `=========` is person 1's code
1. Between `========` and `>>>>>>>>> + hash` is the incompatible changes on the remote repo. 

We can resolve the conflict by **picking which implementation we want**. 

If you get stuck, you can run `git reset --hard`, which resets your repo to the state of the previous commit. If you don't want to lose the changes, you can stash them or copy them into a temp file. 

A nuclear option is also to delete your _local_ repo and re-cloning.

### Best Practices

1. Note that .gitignore is **not retroactive**, if you need to remove files from a repo while keeping the local file, you can do `git rm [file-name] --cached`. the `--cached` file deletes the file from **both the repo and your working copy**. 
1. Always pull before you start working. 
1. Always pull before pushing and merging.
1. Merge into the more specialized branch first:
    * Commit your changes to the feature branch,
    * check out to main,
    * pull main
    * checkout to feature branch
    * `git merge main` into your feature branch
    * resolve any conflicts, commit, and push
    * Checkout main
    * `git merge featureBranch`
1. Always push when you stop working
1. Commit early and often
1. Never commit new code directly to main/master
1. Once a feature is completed, delete the branch
1. Make good commit messages.  




## Reading 02 - Required

### [How Java Works](https://sde-coursepack.github.io/modules/java/How-Java-Works/)

Code is instructions that computers follow. For computers, code must translate into instructions that are unambiguous, syntactically correct, and logically possible. 

Code is compiled into something that the computer can understand.

#### Compiling in C

Before talking about Java, here's how C works - 
```c
#include <stdio.h>
int main() {
    printf("Hello, World!");
    return 0;
}
```

The compiler takes the code and translates it into machine code, which is then run by the computer. We don't have to worry about the readability of the machine code, because it's not meant to be read by humans. We only worry about that it can be compiled and un-compiled. 

#### Interpreters

Some languages are _interpreted_ rather than compiled, like python. This means that the code is read line by line and executed. This means that you don't have a static compiled file. 

Note that you do not have to "install" c to run it like Python, since it is compiled.

#### How Java Works

- **JDK**: When you compile a Java file, the JDK compiles your code producting a `.class` file. This is a _bytecode_ file, which is a file that contains instructions for the Java Virtual Machine (JVM).

Here's a more accurate image of how it works:

![Image](https://sde-coursepack.github.io/modules/java/images/3/compiler3.png)

- **JRE**: This is because the `.class` files don't run on any hardware, they run in a virtual Java Runtime Environment (JRE). Note that this is different from the JDK, which is used for _compiling_, not running. When you download the JDK, it will include a compatible JRE, but you can download a JRE to run files without downloading the JDK.

- **JVM**: Each JRE contains a Java Virtual Machine (JVM). This interacts directly with the CPU, memory, disk, and monitor. Technically, the JVM is an _interpreter_ that interprets `.class` byte code compiled by the JDK, passed to the JVM by the JRE. 
    - Note that Kotlin and Groovy also use the JVM, so you can use them in Java projects.

To help you understand how these all fit together, think of the JRE as a waiter, and the JVM as a chef, where a `.class` file is an order that talks to the JRE who then asks the JVM to execute the order.

- **JIT**: The just-in-time compiler is a part of the JVM, specifically the part that can compile the JVM byte code instructions into machine code instructions for underlying hardware. 

#### Takeaways

- Compiling is taking a source doe and producing byte code to be run
- Interpreting is similar to compiling but is done dynamically at runtime
- Java uses a JDK for compiling in bytecode that runs on a virtual machine
- the JRE runs Java programs via a JVM
- the JVM interprets Java bytecode into machine instructions 
- the JIT is used to compile java bytecode into machine instructions at runtime.

### [Terminal](https://sde-coursepack.github.io/modules/java/Terminal-and-Java/)

- `ls` - long listing - list files in a dir
- `cd` - change directory

#### Java Commands

- `java --version` - check java version
- `javac` - compile java code
    - note that we won't have to do this in class, we will put our `.class` files in a `build` or `bin` or `out` folder. We use `gradle` to compile our code.
- `java example.Main` - run the `Main` class in the `example` package

```
package example;

import java.util.*

public class Main {
    public static void main (String[] args) {
        ...
    }
}
```

Effectively what this means is "in the package example, run the class Main."

#### .Jar Files

A `.jar` file is a Java Archive file, which is a compressed file that contains all the `.class` files for a program. For example from a "java" folder that contains "example" where "example" has al of our .java and .class files, we can run 

- `jar cf MyJar.jar example/*.class`

And then to run it:

- `jar cfe MyJar.jar example.Main example/*.class` - this specifies to run the Main class in the example package with the example/*.class files

In the end, we will be using `gradle` to compile our code, so we won't have to worry about this.

### [Packages](https://sde-coursepack.github.io/modules/java/Packages/)

To import basic packages, you can do:

```
import java.util.*;
import java.io.*;
```

Note that it's generally fine to just do the `*` selector for packages, but only if there's not conflicting packages. For example, if you're importing all of `java.util` and `java.awt`, and there's a `List` class in both, you'll have to specify which one you want to use.

Packages are important if you don't want to use the default namespace so you can share your code. If you want to use some package in a particular `.jar` file, you can import the relevant packages. 

A general rule of thumb for package names is "reverse internet domain" - so instead of `cs.virginia.edu`, do `edu.virginia.cs`.

Also, you can quickly add libraries by getting copies of the `.jar` file and copying and pasting it into the project folder and add it as a library. 

### [Command Line Arguments](https://sde-coursepack.github.io/modules/java/Command-Line-Arguments/)

When we are writing `public static void main(String[] args)`, we are saying that we are passing in an array of strings as arguments. For example, when we do:

- `jar cfe MyJar.jar example.Main example/*.class`

That is converted into a String[].

So if we want to run a program with arguments, we can do:

- `java myPackage.MyClass arg1 arg2 arg3 "this is arg4"`


Note that you can also perform using numeric arguments using Integer.parseInt, if you wanted to do something like:

`java java edu.virginia.cs.commandLine.HelloNTimes 5`

#### Optional Arguments

You can add optional arguments, in a command for example that calculates isLeapYear either in the Gregorian or the Julian calendar:

- `java edu.virginia.cs.commandline.isLeapYear 1900`
- `java edu.virginia.cs.commandline.IsLeapYear 1900 --julian` OR `java edu.virginia.cs.commandline.IsLeapYear 1900 -j`


You can check for these flags like:


```
    public static void main(String[] args){
        List<String> argList= Arrays.asList(args);
        boolean isJulian=checkForJulianFlag(argList);
        ...
    }
    private static boolean checkForJulianFlag(List<String> argList) {
        return argList.contains("-j") || argList.contains("--julian");
    }
```

Note the use of Arrays.asList(args). Working with raw arrays is annoying, so we can use:

```
List<String> argsList = Arrays.toList(args);
```

## Reading 01 - Optional

### Abstract Classes

TODO

### Prereq Knowledge

[Example Code to Review](https://github.com/sde-coursepack/java-prerequisite/tree/main/src/main/java/example)

- Control flow: You should be comfortable with if, else, and switch, along with for and while loops.

```
public String getDayName(int dayIndex){
    switch(day) {
        case 0:
            return "Sunday";
        case 1:
            return "Monday";
        ...
        case 6:
            return "Saturday";
        default:
            throw new IllegalDayException();
    }
}
```

- Functions: you should understand return types, local and global scope, return value, side effects. 

- Classes: You should be comfortable with classes. Using pre-defined classes like `String` and `ArrayList`, writing your own classes, methods and fields, constructors, and the `static` keyword. Also class field and method visibility with `public` and `private` keywords. 

- Object Orientation: Familiar with `interface` and `implements`. This includes understanding Java Collections interfaces like `List`, `Set`, `Map`, `Comparable<E>`, and `Comparator<E>`.

- Exception Handling

## Reading - 01

1. [Software Scale](https://sde-coursepack.github.io/modules/intro/scale/)
1. [Software Failures](https://sde-coursepack.github.io/modules/intro/failures/)
1. [Software Complexity](https://sde-coursepack.github.io/modules/intro/complexity/)
    1. [No silver bullet](http://worrydream.com/refs/Brooks-NoSilverBullet.pdf)
1. [Improving](https://sde-coursepack.github.io/modules/intro/Improving/)
1. [Software Quality](https://sde-coursepack.github.io/modules/intro/Software-Quality/)
    - [External Quality](https://sde-coursepack.github.io/modules/intro/External-Quality/)
    - [Internal Quality](https://sde-coursepack.github.io/modules/intro/Internal-Quality/)

Optional Readings

1. [Why Java?](https://sde-coursepack.github.io/modules/java/Why-Java/)
1. [Prerequisite Knowledge](https://sde-coursepack.github.io/modules/java/Prerequisite-Knowledge/)
1. [IntelliJ](https://sde-coursepack.github.io/modules/java/IntelliJ/)
1. [Java New Features](https://sde-coursepack.github.io/modules/java/Java-New-Features/)

OOP Review

1. [Refresher](https://sde-coursepack.github.io/modules/objects/OO-Refresher/)
1. [Abstract Classes](https://sde-coursepack.github.io/modules/objects/Abstract-Classes/)

### Software Scale (Scaling up Software)

#### Writing Code vs Solving Problems

- Ego padding

#### Large Scale Systems

_enterprise software_ are larger software systems managed by teams of programmers over a long period of time, and must respond to customer needs or environmental changes. 

Brook's Law states that "adding manpower to a late software project makes it later." This means that it takes time for skilled programmers to be productive in a system. 

Note that doubling the size of a team more than quadruples the lines of communication - increasing quadratically is why we don't bother with $n^2$. 

#### Ad Hoc Solutions

Ad hoc solutions are a **one time solution to a problem**. They are not meant to be maintained, and are not expected to be used by other people. 

#### Increasing Problem Scale

ad-hoc problem solving is **fundamentally different** from building larger, sustainable, systematic solutions to problems. They are also not built by one programmer in an afternoon, but industrial software is built incrementally over time and are maintained _long after release_.

#### Goals of the Course

- How to share code and break up work - collaboration
- how to know if we're on the right track - testing
- how to know if our code is good - refactoring
- is our software receptive to change - design
- what are some established techniques we can use to ensure maintainability - design patterns
- how can we interact with the software? - gui
- where is our data coming from/going - databases

### Software Failures

#### How bad is it? 

Only **31% of software projects are successful** (depending on whether you trust the CHAOS report). 

#### Examples of software failures

1. Therac 25: A bug in the machine resulted in radiation overdoses to patients.
1. Mars Climate Orbiter: A unit conversion error resulted in the loss of the orbiter.
1. Facebook, 2021: Facebook's DNS server went down, resulting in a global outage of Facebook, Instagram, and WhatsApp.


#### Why does Software Fail?

A key aspect to safety is redundancy. In software, for example, if you delete some code, the odds that the entire program still runs is slim to none. So what we need to do instead is tools, techniques, and systems in place to prevent, detect, and fix things _before_ they become problems.

#### Learning from Failure

Failure is both necessary to learn but also should be avoided!

### Software Complexity

#### Hidden Complexity

Software failures occur when software is "essentially complex," i.e. simple sites like Google have monstrous code bases. 

#### Why do we write software? 

To solve problems!! 

#### Software Crisis

Moore's Law describes the increase in complexity of computer hardware over time. And while there's a ceiling, it's still increasing and is meteoric. However, a lot of the software that used the hardwarae didn't. 

- [No Silver Bullet](http://worrydream.com/refs/Brooks-NoSilverBullet.pdf) - essay by Fred Brooks addressing this issue. 


### No Silver Bullet (First 4 Pages)

#### Abstract

There is no single development, in either technology or management technique, which by itself promises even one order-of-magnitude improvement within a decade in productivity, in reliability, in simplicity.

#### Introduction

The idea of most software projects is to be the perfect solution to the problem it intends to solve, but then it fails to do so - missed schedules, blown budgets, flawed products. And therein lies the necessity for a **silver bullet**, a single development that will make software costs drop by an order of magnitude like hardware. 

#### Does it have to be Hard - Essential Difficulties

Software progress is much slower than hardware. It is due to many difficulties, starting with the essence. 

Software requires a communication of concepts, data sets, relations among items, and other things. The errors do not come about from the syntax errors, but the conceptual errors in systems. Thus, here are four essential difficulties:

1. Complexity
    - Software systems have significantly more complexity than hardware, and scaling up them is not just as simple as simply "adding more" to it, packing more transistors in a chip.
    - Many problems surrounding software complexity surround its nonlinear nature.
1. Conformity
    - Many times a lot of complexity is caused by conformity, where the engineer is forced to conform to the system, rather than the system conforming to the engineer.
1. Changeability
    - Software is constantly subject to changes or pressures for change. This is because the software mainly embodies a function, and the function is the part that is subject to change. 
    - software is in a cultural matrix of applications, users, laws, and machine vehicles.
1. Invisibility
    - Software is _invisible and unvisualizable_. There are no geometric representations of software (not true anymore). 

### Essential and Accidental Difficulties

Many difficulties from software is both **essential** and **accidental**. 

- **Essential** difficulties are intrinsic to developing software.
- **Accidental** difficulties come from a particular implementation of software.

Brooks argues that there are essential difficulties that will always exist, but we can use our technological and methodological advances to reduce the accidental difficulties.

### How to Improve

#### Don't Panic

This class is about practice. 

#### In this Course

You will be exposed to various tools and techniques for sde:

- Version control systems
- Testing practices 
- Design principles that allow a system to evolve over time with less effort and more stability
- Communicating design decisions
- Writing and refactoring code in a re-useable and testable manner.
- Building a GUI
- Connecting a db 

#### Do i really need to...

Most situations you may find yourself in include finding a bug, changing code, praying it fixes it, be disappointed, and repeat. This is _super inefficient_. Building test-driven code will make you more efficient.

#### Learning is Hard

Learning a new skill is hard and takes time, and also takes a lot of un-learning.

#### You won't be good at first. 

Developing real software doesn't come with a set list of an ide, instructions, rules, and a checklist. You don't have _training wheels_. This means that when a customer asks for what they want:

> "I would like software that does [vague description of something the customer does already without software]

You will have to figure out what they want, and how to build it.

#### Summing it Up

You will struggle when presented with new info or a challenge. 

### Software Quality

You can see software as good from both the perspective of the _customer_ but also the _developer_. Software quality is incredibly important to allow for the software to be used, maintained, and extended.

#### ISO 9126

The International Organization of Standardization (ISO) has a standard for the evaluation of software quality, breaking it down into _internal_ and _external_ measures of quality. 

### External Quality

External quality is the quality of the software as perceived by the customer/stakeholder. 

#### Stakeholders

Stakeholders are anyone affected by the software, not just th euser - anyone who "holds a stake" in the usage of the software. With this in mind, there are various _external_ quality measures:

- **Functionality**: Is the software functionally complete? Does it have all the features customers expect? Is it secure? 
- **Reliability**: What is the capability of the software to maintain performance under certain conditions over a period of time? 
- **Usability**: How much effort is needed for a customer to use the software? 
- **Efficiency**: When operating, what resources are used, to what extent, by the software? 
- **Portability**: How well can the software be moved from one environment to another?

### Internal Quality

Internal quality is the quality of the software as perceived by the developer. Quality is **Maintainability**, that is, the internal quality of the software.

#### Software Maintenance

Software maintenance relates to initial development, bug fixes, new features, changing features, etc. It can be broken into:

- **Analyzability**: To what extend can the software construction be understood?
- **Changeability**: How easy is it to change the software?
- **Stability**: How does changes in one part of a software affect others? 
- **Reusability**: The extend to which part of the construction can be reused.
- **Testability**: The extent to which software can be tested to find faults.

#### Improving

To improve our internal quality we will be using version control, modularity, OOP, and JUnit/TDD to ensure that our software is maintainable.

#### Software Entropy

Entropy is the idea that in our Universe energy is a closed system. In software, entropy is the idea that software will degrade over time.





