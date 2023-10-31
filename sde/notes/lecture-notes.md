---
title: Lecture Notes
author: Charlie Meyer
date: August 22, 2023
---

## Lecture 19 - [Design Patterns](https://drive.google.com/file/d/1kDmVjqOUlI5cBmYZvvvsT6UIX4KqpCOl/view?usp=drive_link)

- [Readings](https://docs.google.com/document/d/1jKhBFyaQ6P58hgx3yssDkDdFhy95hcZkrQZg-OYrBD0/edit)

### Design Patterns

* Experience shows that many design patterns improve internal software quality
* Often best practices
* Not every pattern is suitable for every situation

### Iterators

* Iterators allow you to visit all elements of collections one at a time
* Java has a built in iterator pattern
* If you implement a collection, you have implemented an iterator

### Why use Iterators?

* key advantage is **functional independence** and **information hiding**. 
    * Don't have to know:
        * How a collection is structured
        * How the iterator works
    * All i know is that i can create an iterator to visit every element!
* If you implement an iterator, other programmers can use your code without having to understand it.

### Iterator Design Pattern

* Promotes information hiding and functional independence
* In a collection, iteratee through each element one by one without knowing it's internal structure:    

```java
Iterator.boolean hasNext() // returns true if there's a next element, otherwise false

Iterator.E getNext() // returns the next element, throws exception if there is no next. 

```

### Do i have to use design patterns? 

* Short answer is _no_:
    * Don't have to use them
    * Never force them!
* Can be beneficial to use **if they solve a design problem you are trying to solve**
    * People familiar with the pattern will better understand your code
    * Patterns we teach have been proven effective overtime at improving the internal quality of the software. 

### Groups of Design Patterns

1. Creational Patterns - handle object creation and instantiation
1. Structural Patterns - Bring existing objects together
1. Behavioral Patterns - give a way to manifest flexible behavior. 


### Creational Patterns

* Patterns that typically "hide" or **limit constructor usage**. 
* Objects may require a special process to create them correctly
    * Explaining a a complicated process can violate _information hiding_
    * This can lead to programmer's calling constructors in ways that they shouldn't. 

Example:

```java
public class Singleton {
    public static Singleton instance;

    private String attribute;

    private Singleton(String in) {
        instance = this;
        attribute = in;
    }

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton("You haven't set a string yet");
        }
        return instance;
    }
}
```

### Singleton Pattern

* Only once instance can exist at a time - instance can be shared by multiple modules without those 
modules being aware of each other
* Common uses:
    * Logging
    * Client side UI settings
    * Service locations
* Never use a singleton if you need multiple instances


### Factory Method Pattern

* Create a concrete factory class with a factory method
* Chooses which concrete implementation of some abstract class to produce. 

### Abstract Factory Pattern

* there's a factory interface various factories implement to then return various objects. 

### Builder Pattern

If you have an object that has a complex creation process (many objects, lots of configuration, etc). 
If you want to make the process to build the object correctly and easily, make a class whose 
job is to build a specific class. This allows you to define things _piecewise_, handling _default cases_
if not specified. Helps that the order of the arguments does not matter, meaning all that is required is the 
`buildObject()` method at the end. 

### Adapter/Facade Pattern

* **Adapter** - adapt an existing class/object to a new interface without changing the underlying class. 
    * useful to update interfaces while minimizing side effects/propagation of changes
* **Facade** - hide a complicated interface or a set of interfaces with a single interface 




## Lecture 18 - [Databases](https://drive.google.com/file/d/17DZMGbb2vtCheXsoUCseEcbG94EXZ-_C/view?usp=drive_link)

### Database Normal Forms

#### 1NF

#### 2NF

Divided the current students table into 2 tables. Single column, primary key. 

#### On Delete Cascade

Ensures that if the foreign key reference record is deleted, it also deletes any affiliated rows. 

#### SQLite Examples

* `select * from Courses where Subject="CS" and Number="3140"`
* `select * from Courses join Lecturers on Courses.Lecturer = Lecturers.Id`
    * Joins rows where Courses.Lecturer = Lecturers.Id. 
    * Necessary to include the `on` statement since if you didn't, you'd get every course joined with _every_ lecturer. Which isn't helpful!
* `drop table Courses;` - deletes the table "Courses"

#### Java JDBC with SQLite

* JDBC - org.xerial.sqlite-jdbc
* `implementation group: 'org.xerial', name: 'sqlite-jdbc', version: '3.39.40'`

#### Using JDBC in Java

* URL Example = `String url = "JDBC:sqlite:database_filename.sqlite3";`
* Where to store database:
    * We store the database in the root directory of the project, **Not in the resources folder**.
    * this is because stuff in the `/resources` folder should be **read only** but the database isn't!!
    * This is because we don't want to have that in the `.jar` file. 
* Two ways to set up a connection for SQLite:
    * Set up the database driver - `Class.forName("org.sqlite.JDBC");`
    * Create the connection object - `Connection con = DriverManager.getConnection("jdbc:sqlite:database_filename.sqlite3");`


## Lecture 17 - [SQLite](https://drive.google.com/file/d/1KElJQMXI-3I4gl--zpp9eiWAuJBP6dEt/view?usp=drive_link)

### Database Keywords

* Tables:
    * Tables list information (such as a spreadsheet)
    * Each row is a record
    * Each column is called an attribute
* PRIMARY_KEY:
    * Every table should have one or more primary key
    * A primary key has the following constraints:
        * Must be unique
        * Must be non-null
* SQLite data types:
    * Integer - integer, optionally `INT(5)` means "display at most 5 digits."
    * Real - floating point number. 
    * Text - A string of size "size" "String"
    * Blob - sequence of bytes (images, files, etc)

Example of creating a table:

```bash
create table if not exists Courses (
    crn INTEGER PRIMARY KEY,
    Subject Text,
    Number Integer, 
    Section Integer,
    MeetingTime Text,
);
```

adding data to table:
 
```bash
insert into Courses (CRN, Subject, Number, Section, MeetingTime) values
(12345, "CS", 3140, 2, "TT 2:00 - 3:15pm");
```

### SQLite

`sqlite3` is a command to interface with databases.

Making a database - `sqlite3 mydb.sqlite`
* Then you will be in sqlite, and you can do `create table People (Id INTEGER PRIMARY KEY, Name Text);`
* Open existing database - `sqlite3 existingFile.sqlite`
* Create new database - `sqlite3 nonExistingFile.sqlite`

SQLite Constraints:
* Tables and columns have constraints
* NOT NULL - Column cannot have a null value
* DEFAULT - Provides a default vlaue when user INSERT such that if no data is specified for the column, it adopts the default value
* UNIQUE - All non-null values must be unique. (but you can have repeated null values)
* CHECK - defines a boolean condition that must be true for a row to be added
* INTEGER PRIMARY KEY - only one primary key per table, is both UNIQUE and NOT NULL.

Additional commands:
* `.quit` – quit the sqlite command line (period required)
* `.open` - to open a database file
* `.tables` – list all tables in the database
* `.dump` – Create a sql script that recreates the databases state from scratch as a SQL script
* `.read` – Read in and run a sql script (such as a dump file)

Selecting, Reading, Updating

* `select * from Courses where Subject="CS" order by Number;`
* `select CRN from Courses where Subject="CS";`
* `update Courses set MeetingTime="TR 2:00 - 3:15 PM" where crn=12345;`

Not fucking up:
* `begin;` - sets a begin point (**transaction**)
    * `delete from Courses;`
    * `select * from Courses;` - nothing happens
    * `rollback;` - rolls back your deletion!

Database Normal Forms
* How can we create a table that shows which classes a student has credit for? 
* We should use **CRN** as the primary key, but this breaks a rule - _first normal form_ 
* _Second normal form_ - be in first normal form, and single column primary keys.




## Lecture 16 - [Data Persistence, XML, JSON](https://drive.google.com/file/d/1WUi6-g2QfM__LF9cTLGml63iB3a_r-Hr/view?usp=sharing)

What we want in a data format:
1. Flexibility - allow for optional fields and not even include a field
1. Communicability
    * state what fields a data element has
    * Clearly indicate how this element could be translated to an object
1. Ease of use
    * Make data consistent to parse
    * Make the data easy to read/write. 

Shit solution 1: XML
* It's like html but it's verbose and annoying. (LOL JAVA). 

Better Solution: JSON
* yummy yummy json! I love json. 
* JSONObject == HashMap, with **key of a string**, and the value can either be a `String`, `Integer`, `Boolean`, `Float`, JSONObject, or JSONArray.
* JSONArray == List

Working w/JSON example:

```java
public class Temp {
    public static void main(String[] args) {
        String jsonString = """
            {
                "fruit": "Apple",
                "size": "Large",
                "color": {
                    "red": 255,
                    "blue": 0
                    "green": 0
                }
            }
            """;
        JSONObject = new JSONObject(jsonString);
        // do parsing accordingly.
        System.out.println(jsonObject.getJSONObject("color").getInt("red"));

    }
}
```





## Lecture 15 - [Integration and Mockito](https://drive.google.com/file/d/10piRWCB-udehDSi9ija2gWRbZNX-KgCy/view?usp=drive_link)

* Integration example: Consider `main` with a `CSVStateReader`, `HamiltonStrategy`, and `Representation`. If you want to test the code after performing unit testing, you "stick them all together, test, and run." this is called **big bang integration** - combine however or all at once. 
* Alternatives:
    * **bottom up** 
        - start with modules on that _depend on nothing_
        - Once that's integrated, move _up_ and ensure integration works. 
        - Benefits:
            * Easier to race fault responsibility
            * "integrate as you build" - implement low-level features first
            * Each module is integration tested with working code
            * If low-level code is difficult, more likely defect-prone, test it first and often
        - Disadvantages:
            * More complex/data intensive
            * Thinking about abstract naturally is more top-down
            * High-level classes have bigger impact on design usability
            * virtually impossible to build prototypes efficiently. 
    * **top down**
        - start with modules on which _everything depends_
        - we use "mocking" so that we can "fake" behaviors of dependencies, allowing us to test top down. 

### Test Stubs

* drivers act like a main methods
* Stubs are typically hard-coded implementations that mimic (mock) the behaviors of dependency
* If you consider the example of a `CityTeamFinder` that finds the NBA teams associated with a city that depends on `NBATeamReader` that depends on `BallDontLieReader` that depends on the internet that depends on the Ball Dont Lie API, performing unit tests on `CityTeamFinder` could fail because some other dependency fails. 

### Mockito

**mockito** is a framework for mocking during testing. 
* we use it with junit during testing
* We can use it to implement stubs during development
* We would never want to ship mocked code

Dependency:
* `testImplementation group: 'org.mockito', name: 'mockito-core', version: '4.7.0'`

Import: 
* `import static org.mockito.Mockito.*;`

Much like Junit, it is in many other languages.


example of mockito:

```java
public class TeamCityFinderUnitTest {
    private CityTeamFinder testTeamFinder;
    private NBATeamReader mockTeamReader;

    private static NBATeam LAKERS = new NBATeam(1,"Lakers","Los Angelos","LAL", Conference.WESTERN, Division.PACIFIC);
    private static NBATeam CELTICS = new NBATeam(2, "Celtics", "Boston", "BOS", Conference.EASTERN, Division.ATLANTIC);

    @BeforeEach
    public void setup() {
        mockTeamReader = mock(NBATeamReader.class);
        testTeamFinder = new CityTeamFinder(mockTeamReader);
    }

    @Test
    public void testSingleCity() {
        when(mockTeamReader.getNBATeams()).thenReturn(List.of(LAKERS, CELTICS));
        Set<NBATeam> teamsInBoston = testTeamFinder.getTeamsFromLocations(List.of("Boston"));

        assertEquals(new HashSet<NBATeam>(Set.of(CELTICS)), teamsInBoston);
    }
}
```

When-Then:
* Example: `when(mockList.contains(mistborn)).thenReturn(false)`
* generally, this should be the first line of your test unless you have a very good reason
* In this example, mockLIst is mocking `List.class`. 
* This says, "when, during this test, someone calls "contains(mistborn)" on this mockList instance, return false. 


Mock Syntax - we can use `@Mock` in a mockito test to specify that something is a mock (unless specified) like this:

```java
@ExtendWith(MockitoExtension.class) {
class TranscriptTest {
    @Mock
    Map<Section, Grade> history;
    @Mock
    Course sde, algorithms;
    @Mock
    Section sectionAlgorithmsFail, sectionAlgorithmsPass, sectionSde;

    @Test
    void getGrade() {
        history = new HashMap<>(Map.of(sectionSde, Grade.A_PLUS));
        testTranscript = new Transcript(history);
        assertEquals(Grade.A_PLUS, testTranscript.getGrade(sectionSde));
    }

    @Test
    void add() {
        testTranscript = new Transcript(history); // by default, a mocked transcript
        testTranscript.add(sectionSde, Grade.A);

        verify(history).put(sectionSde, Grade.A);
    }

}


}

```




## Lecture 14 - [Architecture](https://drive.google.com/file/d/1WqcEMFLQf4EsPybUUsSDfYY38pez2JGE/view?usp=drive_link)

### Dependencies

* as software grows, it will be harder to make changes to existing systems


Levels of design:
- High-level design: (software architecture)
    * where does data come from
    * How is our system interacted with and monitored
    * What does our system need to communicate with externally?
- Low-level design
    * modularity
    * internal interfaces (module connections)
    * Detailed algorithms and data structures

### Software Architecture and High-Level Design

* Define a system in terms osf its components and interactions
* Examples of components:
    * independent programs - such as command line programs, pop-up applications, etc
    * Groups of modules/classes
    * Clients, servers
    * files, databases
* Examples of interactions
    * Procedure calls
    * Communication between components. 

### Bad Patterns

* _put it all in main_
    * bad!!
    * Complely unmanagable for larger systems
* _god object_ - one class that communicates with every object
* _big ball of mud_ - Many software projects that start with design end up a big ball of mud. This can be lack of restructuring, efficiency, and structural consistency. 
* _monolithic architecture_  - one main component that does everything
    * fine for small, simple programs. Not good for large, complex projects. This is because modules within the component are tightly coupled, and maintainability suffers because of that. 


### Architecture Patterns

* Patterns are a strategy for how to solve a common problem:
    * A pattern is not fully coded or implemented
    * A pattern is a generalized example that omits most implementation details

* Client-server:
    * Multiple client components interact with a shared service/server
    * Clients do not communicate directly - communication done with server
    * Clients can be thin/thick

### Layered Architecture

* System organized into hirearchially layers:
    * Each layer contains one or more components
    * Each laer provides a service to the layer above it
    * Each layer acts as a client to the layer below
* Common pattern, three-layer is very common.

### UI + Business Logic + Data




## Lecture 13 - Software Design Principles (again)

### Modularity & Cohesion

**Worst (ranked from worst-worst to worst-best)**
- Content - when one class modifies the inner state of a class it depends on directly. Occurs frequently with global variables. Example:

```java
public class Student {
    String name, email;
    int id;
    int notificationsReceived;
}

public class StudentNotifier() {
    public notifyStudent(String message, Student s) {
        Email notificationEmail = new Email(message, s.email);
        s.notificationsReceived++; // BAD BAD BAD BAD

    }
}
```
- Coincidental Cohesion - elements are combined by coincidence. This is like a `utils.java` file. 
- Logical Cohesion - elements are grouped into a module because they are described with the same general activity, have similar interfaces, or are otherwise categorized the same. There is no _intradependency_ .
- Temporal Cohesion - items are grouped together because they occur at the same time but are otherwise unrelated. 

**Ok**
- Procedural Cohesion - supporting unrelated activities in which control passes from one activity to the next. 
- Communicational - grouping because things perform actions on the same data/info
- Sequential - group modules into procedures, but it's like transitive in each "step." Each step fees the next step. Similar, but different to procedural cohesion
- Stamp Coupling - when a module passes a data structure to another module, when the entire data structure is not needed.

**Fine**
- Functional cohesion - a thing describes a single, well-supported function, i.e. something does one thing.

**Best**
- Data coupling - communication strictly between functions that hide implementation details with no side effects. 



## Lecture 12 - Software Design Principles ([slides](https://drive.google.com/file/d/1-0W7SZqZhdxWCDpNLKei6xR_tS5p3ceb/view?usp=drive_link))

### Internal Software Quality

* Maintainability:
    * This is the overarching theme/measure of internal quality which can be broken down into three components:
        * Analyzability: can be understood, measured, etc
        * Changeability: effort needed to make modifications
        * Stability: effect that a change in one part has on other parts of the system
        * Testability: ability to adequately test parts of code and detect defects. 

### Design Difficulties

Essential difficulties:
* Complexity - too complex for one person to understand
    * Software is not built on repeatable parts. Complexity is _inherent_ to software. 
* Conformity - interfaces with different users, systems, etc.
* Changeability - software is infinitely malleable
    * software is malleable. Rapidly changing technology creates desire for change. 
* Invisibility - difficult to diagram or visualize. 


### Design Concepts

* How should we organize our code? What code should be grouped, and what should be separated? 
    * Modularity
    * Functional independence
* How should we expose between modules? 
    * Abstraction
    * Information hiding

### Modularity

* A module is a unit of code. This includes a function/class/script. 
* Break programs into portion that solves a small problem. 
    * Benefit - each module is easier to specify, understand, design
    * Drawback - modules must communicate with each other.

A module should have only one reason to change. 

# Lecture 11 - OO Review ([slides](https://drive.google.com/file/d/19Y0BTlQbkFrFufTkLl52c7x9fAbH3Wh3/view?usp=drive_link))

### Homework Review 

- Dictionary is supposed to be such that words are stored in lower case. Must also have valid words.

### Polymorphism

### Object Instantiation

A class is a template, not a thing. Objects are _things_. We create objects by calling constructors: `Class myThing = new Class()`

#### Interfaces

Using **standardized pliugs** allows you to reuse power by any electrical device. A device implementing a standard North American plug, for example, is "implementing" the interface of a North American plug.

Java Interfaces:
* When declaring our class we say it `implements` an interface. For example:
    * `public class myClass impelements myInterface`
* This means that a class State is promising to implement specific behaviors. 


#### Syntax Refresher

See the syntax refresher [here](https://drive.google.com/file/d/19Y0BTlQbkFrFufTkLl52c7x9fAbH3Wh3/view?usp=drive_link?slide=id.p18)

#### Dependency Inversion

The purpose is is to separate the interface (the way it's used) and the implementation (the way it works). This dependency inversion occurs where the `main` method uses the interface, and the implementation implements the interface.

This is a way to do it with Dependency Inversion:

- Create a `StateSupplier` interface, then provide `CSVStateSupplier` and `JSONStateSupplier` implementations.
- Create an `ApportionmentMethod` interface, then provide `JeffersonMethod` and `HamiltonMethod` and `HuntingtonMethod`



# Lecture 9 - Functional Programming, Comparators, Lambda Bodies, Streams ([slides](https://drive.google.com/file/d/12NfJ82dAi6KNxdVDFyDVlZRO4x3SBReS/view?usp=drive_link))


## `compareTo()`

* TreeSet
    * Inherently sorted. They are sorted in order. As a result a TreeSet<String> will be in alphabetical order (with the exception of around case)
* Collections.sort(List<T> list)
    * Sort any list using the object's `compareTo()` method.
* To have a class use these features, it must implement comparable. For example, `public class Student implements Comparable<Student>`
* `compareTo()` returns an int. In the example of compare(a, b):
    * if A comes BEFORE B, we want to return a negative result
    * if A comes AFTER B, we want to return a positive result
    * if A and B are equal, we want to return 0

Defining compareTo override methods and also making comparators:

```java
private static final Comparator<Student> DEFAULT_STUDENT_COMPARATOR = Comparator.comparing(Student::getLastName).thenComparing (Student::getFirstName).thenComparing(Student::getLevel);

// syntax of (Student::getLastName) is the same as (student -> student.getLastName()). We can write it like that since we're not doing anything with the student object, we're just calling a method on it.

public int compareTo(Student student) {
    return DEFAULT_STUDENT_COMPARATOR.compare(this, student)
}
```

If you want to make an inline comparator, you could do something like 

```java
myList.sort(new Comparator<Student>() {
    public int compare(Student o1, Student o2) {
        return Double.compare(o1.getGpa(), o2.getGpa());
    }
});
```

### Functional Programming

* In functional programming we think of functions as "first class citizens"
    * Variables can be functions
    * Parameters can be functions

### Lambda

* "function" used without being defined in the context of a class
* Lambda function is an anonymous function

### Lambda Examples

* Details:
    * Parameter types may be inferred or states
    * Parenthesis may be omitted for a single inferred-type parameter
    * Lambda function **creates an object** to be used
* Examples:
    * (int x, int y) -> x + y
    * (x, y) -> x - y
    * c -> {int s = c.size; c.clear(); return s;}
        * takes in a collection, gets the size, clears it, returns the size.
    * () -> 42
        * takes in nothing, returns 42

### Why bother w/Lambda Bodies?

* Java doesn't have a function datatype . Named functions cannot be passed as arguments easily.
* Java creates function interfaces:
    * SAM - single abstract method interfaces examples:
        * Comparator
        * Runnable
        * Comparable
        * Callable
* Function Type - SAM 
    * Function object - Instance of SAM

```java
// we can do simple stuff like this!!

// sorting them in ascending order
Arrays.sort(words, (s1, s2) -> s1.length() - s2.length());

// you can also do (which does the same thing as above!):
Arrays.sort(words, Comparator.comparing(String::length));

// to do it reversed, you can do:
Arrays.sort(words, Comparator.comparing(String::length).reversed())

// to sort a string based on its own compareTo method, you can just do
Arrays.sort(words, Comparator.comparing(string -> string))

```

### Functional Interfaces (SAMS)

| Interface | Method | Concept | 
| --- | --- | --- |
| `Runnable` | `void run()` | ADo something |
| `Comparator<T>` | `int compare(T o1, T o2)` | Compare two objects |
| `ActionListener` | `void actionPerformed(ActionEvent e)` | Respond to an event (think GUI) | 
| `Callable<V>` | `V call()` | Return a value |
| `Consumer<T>` | `void accept(T t)` | Do something with "t" | 
| `Supplier<T>` | `T get()` | Give me something |
| `Function<T, R>` | `R apply(T t)` | Take t and give something |
| `Predicate<T>` | `boolean test(T t)` | Is t true? |

### Consumer Accept

Takes in some input and does something user defined with it. 

```java
String[] words = {"hi", "hello", "hey", "howdy"};
List<String> wordList = Arrays.asList(words);
wordList.forEach(word -> System.out.println(word));
// you can also do
wordList.forEach(System.out::println);
``` 

### Method Capture

* Capture an instance methods of a particular object
    * Syntax: `objectInstance::methodName`
    * Ex: `intList.forEach(System.out::println);`
* Capturing a static method:
    * Syntax: `ClassName::methodName`
    * Ex: `Arrays.sort(myIntegerArray, Integer::compare);`
* Capturing an instance method without capturing the object
    * Syntax: `ClassName::methodName`
    * Example: `Function<Object, String> stringConverter = Object::toString;`
* Capturing a constructor:
    * Syntax: `ClassName::methodName`
    * Example: `Supplier<List<String>> listFactory = ArrayList<String>::new;`


### Java Streams

* A stream is a sequence of elements from a source that supports aggregate operations
    * Source - collection, array, generator function, I/O channel, etc.
    * Aggregate operations - filter, map, reduce, find, match, sort, etc.
        * Filter - exclude items from a astream. Takes a predicate as its parameter and any thing that passes the predicate is included in the stream.
        * Map - transform an element in some way/extract information. Takes a function as oits parameter, output is the return type of that function
        * Limit - truncates a stream to no more than n elements
        * sorted - rearranges elements in a stream according to a comparator
        * distinct - removes duplicates from a stream
    * Terminal Operation
        * ForEach - consume each element applying a function parameter
        * Collect - reduce a stream to a collection
        * Count - return the number of elements in a stream
* Note when performing operations on the stream, you are not editing the original collection.
* Stream sources:
    * Call `stream()` or `parallelStream()` on any List or Set. 
        * For map, you can do `myMap.EntrySet().stream()`
    * From a file, call `.lines()`
* To use a stream:
    * get a a stream
    * Ue intermediate operations
    * Finally

```java
slist.stream()
    .filter(s -> s.getLevel() === level.UG)
    .filter(s -> s.getGpa() >= 3.5)
    .sorted(Comparator.comparing(Student::getGpa).reversed()) // can also do .sorted((s1, s2) -> s2.getGpa() - s1.getGpa())
    .forEach(System.out::println);
```






# Lecture 8 - Analyzability, Code Quality, Refactoring ([slides](https://drive.google.com/file/d/1rxvHVGJpBKdXy-nsj9RXuLrw0u1rNGkF/view?usp=drive_link))

## Analyzability

* Other people have to read your code:
    * Teammates, TAs, office hours, etc.
* You will read your code far more times than you will write it
    * You won't remember what you were thinking when you wrote it.

When do we need analyzability? 
* Vital for maintenance:
    * Debugging
    * Program comprehension
    * Concept location
* Every time you:
    * Bug fix
    * Improve/optimize
    * Add new features
* Whenever you do maintenance, you start by reading the code!

### Code Style

* Good style is important:
    * Using consistent style makes code easier to read
    * Repeating similar patterns means learning one part of a codebase prepares you for other parts
* Even a "bad" code style, if consistent, is better than no style at all
    * I'd rather be on a team that does something I don't like as a rule than a team with no rules
* However, no code style leads to some useless debates. 

### Readability vs. Understandability

* Readability: 
    * The ease that readers can identify and different tokens and their _syntactic_ meaning
    * Understands the mechanics of the code
* Understandability:
    * The ease with which a reader can identify the _semantic_ meaning of the code.
    * Understand why the code exists, what it does, etc. 

#### Readability

* Readability refers to how easy it is to derive the syntactic meaning from code
* What affects readability:
    * Whitespace usage
    * identifier length
    * Use of dictionary words
    * Variable between identifiers (i.e. appleCount vs. applesCount)


#### Understandability

* Readability is necessary, but _insufficient_ requirement for understandable code. 
    * Readable code is not necessarily understandable. 
* Understandability can be improved by:
    * Meaningful identifier names
    * comments and documentation can help but aren't a substitute for understandable code.
    * Adhere to naming conventions, i.e. `CONSTANT_VARIABLE` or `camelCase`. 

#### Conventions

* Avoid single-letter variable names
    * Only use stuff like i and j in a for loop, but nowhere else. 
* Variable names should be:
    * camelCase - never capitalize a variable, or it will look a class name
    * Exception - PROGRAM_CONSTANTS - all capitalized, underscores between words
* Focus on communicating **high level intent**
* Avoid jargon when possible, abbreviate
* Units in variable names - `distanceFeet` is better than `distance`

#### Informative Comments

* Comments shouldn't explain how code works, the code should explain itself. Comments should exist on code for stuff like regex or other things that cannot be easily understood.

#### Obstacles to Understandability

* Complexity:
    * Keep functions shorter by extracting sub-methods
        * decompose long functions into chains of shorter functions
    * try to make code readable rather than "clever." 
* Structural
    * the number of paths through code shouldn't be too large
        * Try to avoid nesting loops by using method extraction
        * When possible, limit nested if statements
* Number of identifiers
    * Too many identifiers can be confusing

#### Rules for Writing Functions

* Functions should be small
    * Functions are good at doing one thing, bad at doing multiple things.
* Functions should do one thing, well, and only!
* 


# Lecture 7 - Exceptions & Defensive Programming (no slides, see example code)

## Testing Types

Example: Consider a function called `nextDay` that takes in an `int year`, `int month` and `int day` and returns an `int[]` of the next day. 

1. Equivalence - "this is a usual test case. basic operations."
    * `nextDay(2023, 9, 12)` -> `[2023, 9, 13]`
1. Boundary - 
    * `nextDay(2023, 9, 30)` -> `[2023, 10, 1]`
    * Leap year
    * new year
1. Exception
    * `nextDay(2023, 9, 31)` -> `IllegalArgumentException`


### Exceptions

Exceptions mean "exceptional event." 
* That is, our program cannot handle a certain condition, and so an exception is thrown
* Exception must either be handled or the program crashes.

Ex:
* `Integer.parseInt("Five") -> NumberFormatException`

### Checked vs. Unchecked Exceptions

* Checked - compiler forces you to handle the exception. These exceptions happen when you're fetching data from external sources.
    * Ex: `FileNotFound`, `IOException`
    * Don't just make the method `throws Exception` - that's lazy and bad practice, and requires you to make the caller of that method handle the exception which could cascade into a lot of `throws Exception` statements for the caller if you're layers deep.
* Unchecked - compiler does not force you to handle the exception. 
    * Ex: `IllegalArgumentException`, `NullPointerException`








# Lecture 6 - Test Plans & Strategies ([slides](https://drive.google.com/file/d/1AdOHBPjC0IlxoXd5y1r9Hi6BG4gz4uDw/view?usp=drive_link))

## Announcements

HW1
* Part 1 - due monday 9/11
* Part 2 - release Tuesday, 9/12 morning, due 9/18. Same groups/repos for part 2
* Quiz 3  -due wednesday 9/13 covers content from lecture today/readings

## Test Plans

### Aside

Note that if you have multiple assert statements in a test, if the first one fails, it won't run the rest of the test. So that is why you can add the optional `message` string argument in `assertEquals` - to tell you which test failed if you have multiple assertions in a test. 

Consider the [Vote Tally](class-examples/06-testing-plans/VoteTally.java) class stored at `class-examples/06-testing-plans/VoteTally.java`. 

With the test for `addVotes_existingCandidates` in the accompanying test class, you can create a `protected` constructor in `VoteTally.java` which directly injects state into the field. Protected - public to everything inside of the same package, private otherwise. Thus, it's great for testing! That way we don't have to do multiple `testVoteTally.addVotes()` calls, but we can just do one. 

```java
    protected VoteTally(Map<String, Integer> candidateVotes) {
        this.candidateVotes = candidateVotes;
    }
```

So now our test can be:

```java
    @Test
    void addVotes_existingCandidates() {
        var testVoteTally = new VoteTally(new HashMap<>(
                Map.of("John Smith", 20, "Votey McVoteface", 10))); // make a new HashMap since Map.of is immutable

        // now you're only calling addVotes once! And the test is still the same!
        testVoteTally.addVotes(Map.of("John Smith", 10, "Jane Doe", 15));

        assertEquals(3, testVoteTally.getNumCandidates());
        assertTrue(testVoteTally.getCandidates().contains("John Smith"));
        assertTrue(testVoteTally.getCandidates().contains("Jane Doe"));
        assertTrue(testVoteTally.getCandidates().contains("Votey McVoteface"));
        assertEquals(30, testVoteTally.getVotesForCandidate("John Smith"));
        assertEquals(15, testVoteTally.getVotesForCandidate("Jane Doe"));
        assertEquals(10, testVoteTally.getVotesForCandidate("Votey McVoteface"));

    }
```

### Assert Functions

In addition to assertEquals, there are:
* `assertTrue` - pass iff is true
* `assertFalse` - pass iff is false
* `assertArrayEquals` - passes if arrays are same size and expected[i].equals(actual[i]) for all i
* `assertNull` - pass iff value is null
* `assertNotNull` - fail iff is not null
* `fail()` - automatically fails the test. 
* With object classes, assertEquals uses the object `.equals()` method. 

### AssertEquals with Doubles

Doubles in Java are imprecise. As such, assertEquals functions with java take in an additional "tolerance" argument. If the expected and actual values are within the tolerance it's considered true. This optional "tolerance" argument is also true in `assertArrayEquals` with a `double[]`.

### How many Tests is Enough? 

If you are testing something like "is _x_ a leap year on the gregorian calendar?" you can test every year from 1582 to 2400. However, this is not feasible for most programs.
* If we test 2024, do we need to test 2028? - **no**
* If we test 2100, do we need to test 2200? - **no**
* If we test 2000, do we need to test 2400? - **yes**

- Note this is "black box testing" - given an _interface_, we design test cases to test the _functionality_ of the interface.

### Test Plan Strategies

- A test plan is a set of tests to search for bugs
- TDD - is the process of writing tests before writing code. This relies on writing tests based on specification. **"Red, green, refactor"**
    * Red - write a test that fails
    * Green - write enough code to make the test pass
    * Refactor - clean up the code
    * Advantages:
        - Unit tests demonstrate clearly that a function behaves as intended
        - able to rapidly test bugs
        - encourages highly-cohesive, loosely-coupled code (good thing)
        - Demonstrated to significantly reduce debugging time. 

### Testing Strategies

1. Black Box Testing - selects tests considering the specification and interface of a module. 
    * Focus on the interface, not the implementation
    * NOt just _arguments_ and _return_ values, also _state_
1. White Box testing - selects tests using understanding structure and details of the code of a module
    * Focuses on implementation

### Code Coverage

1. Statement Coverage - what % of statements executed by test
1. Condition Coverage - Have all boolean variables/expressions been both true/false in at least one test? 
1. Branch Coverage - for every if, do we evaluate both enter and skip/else? For every switch, do we evaluate every case? For every loop, do we evaluate normal iteration, one pass, zero pass? 
1. Path Coverage - for every path through the code, do we have a test that executes that path?

We want to maximize code coverage. However, it is not feasible toe expect 100% converge across all measures. We can avoid things like trivial getters and setters that have no logic (aka auto-generated getters and setters).

### Code Refactoring Example

let's refactor this:

```java
	public double calculateBill(int coursesTaken) {
		double total = 0;
		if (coursesTaken < 3) {
			total = 8000 * coursesTaken;
		} else if (coursesTaken >= 3 && coursesTaken <= 6) {
			total = 6000 * coursesTaken;
		} else {
			total = 5500 * coursesTaken;
		}
		if (amountOverdue <= 2000 && isInterestExempt) {
			return total + amountOverdue;
		} else if (amountOverdue > 2000) {
			if (isInterestExempt) {
				return amountOverdue * 1.1 + total;
			} else {
				return (total + amountOverdue) * 1.1;
			}
		} else {
			return total + amountOverdue * 1.1;
		}
	}
```

* Let's turn the first `if` statement block into a new function called `getTotal(int coursesTaken)` (a static method not reliant on fields).
* Let's clean the second `if` statement block:

```java
var overdueBill = amountOverdue;
if (!interestExempt) {
    overdueBill *= 1.1;
}
return total + overdueBill

```

Now let's see this refactoring in action:

```java
public double calculateBill(int coursesTaken) {
    double total = getTotal(coursesTaken); // you can do this automatically by doing "refactor -> extract -> method" 
    if (amountOverdue > 2000) {
        total *= 1.1;
    }
    var overdueBill = amountOverdue;
    if (!interestExempt) {
        overdueBill *= 1.1;
    }
    return total + overdueBill;
}
```

So much more readable and understandable! And most importantly, **testable** and **debuggable**. 















# Lecture 5 - V&V and Testing ([slides](https://drive.google.com/file/d/17I6eKfAXFvfu7JEgxyhkwNywusNeMEX-/view?usp=drive_link))

## Verification and Validation

Verification:
- Evaluating a system or a component to determine whether products of a given development phase satisfy the conditions imposed at the start of the phase
    * "Did we build the thing right?"
- Validation: 
    * Evaluating a system or component during or at the end of the development process to determine whether it satisfies specified requirements. 

## Testing Software

- You will never be certain that your code is correct
- Even trivial software systems can have theoretical inputs, states, etc. 
    * Conventionally a finite number, but a finite number that is too big to practically constrain
- You cannot test all possible inputs, states, etc.

## Software Testing Cannot Prove Code Works

* Software testing - **_executing_ a piece of software with the intention of _finding_ defects/faults/bugs**.

## Software Testing

- The mindset of a tester should be to **find bugs**, not to prove the system works.
- Testing typically involves executing a portion of the program in a series of controlled state:
    - Expected output
    - Actual output
- Programs can pass verification but fail validation (can pass tests but it's not what the customer wants)

## Unit testing

```java
public static int max(int a, int b, int c) {
    if (a > b) {
        if (a > c) { return a; }
        else { return c; }
    } else {
        if (b > c) { return b; }
        else { return a; } // bug, this should be c
    }
}
```

```java
public static int max(int a, int b) {
    return (a + b + Math.abs(a - b)) / 2; // works, but integer overflow!
}
```

You cannot look at code and find bugs. No matter how hard, you will miss obvious bugs. Thus, we need help finding bugs. **Test** the code to find bugs!!

## Important Quote

- Program testing can effectively show the presence of bugs, but it is hopeless for showing their _absence_. 
- We test to _find_ bugs. Testing allows us to reduce (but not remove) _uncertainty_.

## Testing Scenarios

Has three things:
1. Input
    - function params
1. Expected output
    - what the function should return
1. Actual output
    - what the function actually returns

## Testing Errors

- Be careful constructing your expected output. If your expected output is incorrect, your test will be useles and misleading.
- Make sure tests are **sound** first!!

## Junit

- Create a class in the `test/java` folder, generally make the class name that you're testing `[x]Test`

```java
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MathFunctionsTest {

    @Test
    void max_3arg_descending() {
        // assertEquals(expected, actual)
        assertEquals(3, MathFunctions.max(3, 2, 1)); // this is a static function btw
    }

    @Test 
    void max_3arg_ascending() {
        assertEquals(3, MathFunctions.max(1, 2, 3));
    }

    @Test
    void max_3arg_middle() {
        assertEquals(3, MathFunctions.max(1, 3, 2));
    }

}

```

## Testing Classes with JUnit

Consider the class `NumberChanges`:

```java
/**
 * This class tracks a number as well as how many times that number has changed.
 */

public class NumberChanges {
    private int number;
    private int timesChanged;

    /**
     * Constructor where the initial value of the number is specified
     */
    public NumberChanges(int initialNumber) {
        this.number = initialNumber;
        this.timesChanged = 0;
    }

    /**
     * Constructor where the initial value of the number defaults to 0
     */
    public NumberChanges() {
        this(0);
    }

    /**
     * Returns the value of number
     */
    public int getNumber() {
        return number;
    }

    /**
     * Returns the number of times the number has been changed
     */
    public int getTimesChanged() {
        return timesChanged;
    }

    /**
     * Sets the value of number. If the value of newNumber value is different from before,
     * this increments the number of times the number has changed.
     */
    public void setNumber(int newNumber) {
        if (newNumber == number) {
            return;
        }
        number = newNumber;
        timesChanged++;
    }
}

```
Mow consider NumberChanges test!!
```java

import org.junit.jupiter.api.Test;

class NumberChangesTest {

    // constructor testing
    @Test
    void constructor() {
        var testNumberChanges = new NumberChanges(5);

        assertEquals(5, testNumberChanges.getNumber());
        assertEquals(0, testNumberChanges.getTimesChanged());
    }

    @Test
    void setNumber_differentNumber() {
        var testNumberChanges = new NumberChanges(5);

        testNumberChanges.setNumber(7);

        // now use getters to check the fields!
        assertEquals(7, testNumberChanges.getNumber());

        // check that timesChanged was incremented
        assertEquals(1, testNumberChanges.getTimesChanged());
    }

    @Test void setNumber_sameNumber() {
        var testNumberChanges = new NumberChanges(5);

        testNumberChanges.setNumber(5);

        // now use getters to check the fields!
        assertEquals(5, testNumberChanges.getNumber());

        // check that timesChanged was incremented
        assertEquals(0, testNumberChanges.getTimesChanged());
    }

}

```


# Lecture 4 - Build Tools ([slides](https://drive.google.com/file/d/1BDfFJ6DUbSitbJ-ttXCW3foW5hpuN0NK/view?usp=drive_link))

## Gradle

Think about everything you have to do to get to stat on HW1 w/o a build tool:
1. Everyone has to clone the repo
1. Everyone has to share the same version of Java
1. Everyone has to set up their IDE the same
1. Everyone has to use the same libraries

Gradle is a build tool and an automation tool that can:
* Automatically download all libraries including the correct version number
* Automate build process

### Gradle File Organization (using NBATeams Example):

This is how gradle expects the files to be organized! If you create a new project in IntelliJ, it does this, and so does GitHub classroom. All of your code is in `src`.
* The code that "runs" is within `main/java`
* the code in `test/java` is for testing

```
src
    main
        java
            edu.virginia.cs.somePackage
                Main.java
        resources
            teams.json
    test
        java
            edu.virginia.cs.somePackage
                MainTest.java
build 
    classes
    generated
    libs
        NBA-Excel-1.1.jar
    reports
        tests
            test
                classes
                css
                js
                packages
                index.html // open this to see test results
    test-results
    tmp
build.gradle // this is the build file
gradlew // leave this alone, it's a wrapper that runs gradle
.gitignore







```

#### Build.gradle

Follow along with this example [here](https://github.com/sde-coursepack/NBAExcelTeams.git) which is also in `class-examples/NBAExcelTeams`

Note that gradle will not re-compile if the `.class` files are already there. This is great for great for incremental development and very large projects - you only recompile things that were changed. 





```groovy
plugins {
    id 'java'
}

jar {
    // this is the base name of the jar file, so it will be NBA-Excel.jar. It will add the version number and the extension automatically
    archivesBaseName = "NBA-Excel"
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    manifest {
        // aka: "when i run the `.jar`, run this class"
        attributes "Main-class": "edu.virginia.cs.nbateams.Main"
    }

    from {
        configurations.runtimeClasspath.collect { it.isDirectory() ? it : zipTree(it) }
    }
}

group 'edu.virginia.cs.nbateams'
version '1.1'

repositories {
    mavenCentral()
}

// specifies dependencies that will be used in the project
dependencies {
    implementation group: 'org.json', name: 'json', version: '20220320'

    implementation group: 'org.apache.poi', name: 'poi-ooxml', version: '5.2.2'
    // used in runtime, not actually compiled. This is becuase POI uses log4j, which is a logging library that is used by POI but we won't use
    runtimeOnly group: 'org.apache.logging.log4j', name: 'log4j-core', version: '2.18.0'

    testImplementation group: 'org.mockito', name: 'mockito-core', version: '4.7.0'
    testImplementation 'org.junit.jupiter:junit-jupiter-api:5.9.0'
    testRuntimeOnly 'org.junit.jupiter:junit-jupiter-engine:5.9.0'
}

// If you want to test, you can do `./gradlew test`
test {
    useJUnitPlatform()
}
```

### When to use libraries

- If you need to do something complicated, like read/write excel files, use a library. 
- For example, if you want to read a `.xlxs` file, you can use the `apache-poi` library.

```java
public class Main {
    public static void main(String[] args) {
        var workbook = new XSSFWorkbook("filename.xlsx");
        var sheet = workbook.getSheetAt(0);
        var rowIterator = sheet.rowIterator();
        var catList = new ArrayList<Cat>();
        rowIterator.next(); // skip the header row
        while (rowIterator.hasNext()) {
            var row = rowIterator.next();
            var name = row.getCell(0).getStringCellValue();
            var age = (int) row.getCell(1).getNumericCellValue();
            var furPattern = row.getCell(2).getStringCellValue();
            var rating = row.getCell(3).getNumericCellValue();
            var newCat = new Cat(name, age, furPattern, rating);
            catList.add(newCat)
        }

        // cat comparator
        Collections.sort(catList, (a, b) -> Double.compare(b.getRating(), a.getRating()))
        // #javaScriptBetter lmao 

        // print catList
        catList.forEach(System.out::println); // ok this is kinda cool beuase in js you'd have to do catList.forEach(cat => console.log(cat)) which is still pretty clean lol
    }
}
```






# Lecture 3 - VCS - ([slides](slides/03%20Version%20Control%20+%20Git.pdf))

## Reminders

Homework 1 - Submit groups on Sept 1 at 11:59 PM, assignment 1 due September 8th at 11:59 PM

### Iterative vs. Incremental

Incremental - implementing "blocked" stages. This means that almost every stage of the project has been planned out. 
Iterative - Starting with an idea, and then building on it.

### Version Control

Version control tracks multiple file version, allowing multiple version of software to exist simultaneously and recovering old ones. 

It is now a mainstay in SDE, `git` being the most popular. 

### Git vs. SVN

For a centralized version control system like SVN, you have a central server that stores all the files. You can check out a copy of the files, make changes, and then commit them back to the server. The problem with this is that every commit will be seen by everyone, so it made people hesitant to commit

For `git`, each workstation has a local repository and a working copy that you can then commit/update to, and then pull/push to the remote repository. This supported the idea of "commit early and often" 

### Why VC? 

- Backup
- Bug Fixing: you can "backtrack" commits to find here the bug was introduced. 
- Collaboration: multiple devs can work together. Branching allows features to be added and developed independently. 
- Deployment: You can deploy different versions of the software to different servers, AKA CI/CD.
- Conflict: Humans decide how to resolve conflicts, not the computer.
- Regression testing: As you add tests for features you're adding, you can run them to make sure you didn't break anything.

### `git` cli

- `git init` - initialize a git repository
- `git add filename` - add files to the staging area
- `git commit -m "message"` - commit the files in the staging area to the _local_ repository. 
- `git push` - push the local repository to the remote repository
- `git branch branchname` - create a new branch
- `git switch branchname` - switch to a different branch
- `git checkout branchname` - switch to a different branch
    * you can also checkout to a specific commit with `git checkout commitid`
- `git merge branchname` - merge the branch into the current branch
- `git pull` - pull the remote repository to the local repository
- `git status` - show the status of the repository
- `git log` - show the commit history
- `git push -u origin branchname` - push the branch to the remote repository

Note that `HEAD` is the most recent commit. 



# Lecture 2 ([slides](slides/02%20Java%20Introduction%20Command%20Lines.pdf))

The hard part isn't coding:

- Understanding complicated, human-executed process. 
- Decompose it into individual steps. 
- Translate those steps into things a computer can do.

The entire point of design is taking a complicated general idea and turning it into specific, actionable, **repeatable** steps. 

## What will happen during your project

1. You will have a bug, and you don't know where it is.
1. You don't know how to fix it
1. YOu will go to TA office hours
1. You will go to prof office hours

This won't work becuase we can't understand the _scope_ of the code. It's too bug, complicated, and has several interacting parts. We cannot understand your entire _code base_ at one time. 

### Incrementalization

1. Being a good engineer doesn't mean:
    - Being a great programmer
    - knowing all the popular programming languages/frameworks/technologies
1. These are valuable but not the primary goals of SE:
    - You need to learn to use a hammer and a chisel to build a masterpiece sculpture
    - However, learning how to use a hammer and a chisel doesn't make you Michelangelo. 

### How Code Works

In C. you write a program in a `.c` file. You compile it with `gcc` and it produces an executable file. 

```bash
gcc -o hello hello.c
```

The problem is that it works on your operating system and hardware but not on others. This machine code is designed to interface with our hardware. 

### Interpretation vs Compilation

Interpretation generates machine code instructions during runtime. This means that you have to _install python_. However, you don't need to install `gcc` to run a compiled C program. 

### Java 


HelloWho.java

```java
public class HelloWho {
    public static void main(String[] args) {
        System.out.printf("Hello, %s", args[0]); // f string 
    }
}
```

You can compile this with `javac HelloWho.java` and run the `.class` file with `java HelloWho argument`.

- JRE: Java Runtime Environment: Abstract computing machine that is used to execute `.class` files.
- JVM: An implementation of a JRE, which interfaces with the underlying operating system, hardware, etc. 
- JIT: Just In Time Compiler: JIT is a part of the JVM, translates instruction sets of the JVM to instruction sets of the CPU. 

![Java Working](images/how-java-works.png)

See example in the August24 Project in IntelliJ. 




# Lecture 1

## Contact Info

Email: mcburney@virginia.edu - include [SDE] in the subject line

Office: 404 Rice Hall - OH Posted Soon

[Course Website](http://cs3140.com)

[Online Coursepack](http://sde-course.com/)

[Drive Link](https://drive.google.com/drive/folders/1rbif6FPelWVSoHCNLPmysHPuhl5iNlhs?usp=sharing)

## We will do

- SDE Practices:
    - Incremental development
    - Understandable code
- Design
    - Software that is responsive to change and 
    - resistant to entropy
- Front-to-back software with database backends and GUI frontend.





