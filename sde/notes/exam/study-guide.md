# SDE Exam Study Guide.

This class was lame asf, React better + ratio. I would much rather be building TextBuddy or LawGit rather than doing this, but here we are. Java! Woohoo 😀🔫

Anyways, all explanations for SDE from both midterm and final. Starting w/final, then also doing midterm.

## Final

### Integration Testing

* Integration vs. System and Unit testing.
    * Integration testing is used to test the combination of multiple modules together. 
    * Integration tests can also be automated, using mockito to isolate a specific module/modules to test so that external failures do not lead to false positives.
* Strategies for integration - these are various ways in which we can combine two or more modules together and test them to ensure correct behavior.
    * **Big-Bang**: A "no plan" integration in which the coder haphazardly integrates modules in whatever order you feel like. This leads to non-scalable solutions in the long-term, even if it works for ad-hoc solutions. 
    * **Top-Down**: This integration occurs when you _integrate the class on which nothing depends_, and then move downward. This heavily relies on mocking data to test. 
        * Advantages: with mockito, we can test, even if we have integrated dependencies, so long as we know their interface. Furthermore, we can make decisions about the high-level classes early before we integrate low-level classes. 
        * Disadvantages - Mocking user input can be hard. Interaction with real resources (data, databases, files, etc) can be hard to mock to test. Lower-level modules being implemented last means that they are tested in fewer integration tests. 
    * **Bottom-Up**: We integrate modules in the order of low-level to high-level. You begin by _integrating the class that depends on nothing_. Integrate each class after all of its dependencies have been integrated. 
        * Advantages - we can test our integration as we go and can locate faults quickly. 
        * Disadvantages - Generally the most critical modules of a program get tested/integrated last, thus possibly leading to having higher-level classes change to accommodate lower-level classes. 
    * **Sandwich**: Start with a **target layer** in the middle and integrate both up and down. 
        * Advantages - allows for scalability in larger applications. 
        * Disadvantages - large number of interdependencies in modules leads to picking a "target layer" being more difficult. 
* Drivers and Stubs
    * Test Doubles - classes that replace external dependency objects with replacement objects. This is like mockito, but shittier.
    * **Stubs** - an object that replaces an object with another object of the same interface, but the implementation is hard-coded to specifically return specific values. This has limitations, though, as you have to change your existing sub class every time you want to test a new method, and also have to make a new class for each test. 
    * **Drivers** - drivers act like a "main method" and drive the testing.

### Mockito

* You should be able to write mockito unit and integration tests on the exam. 
* Adding Mockito:
    * ```java
        testImplementation 'org.mockito:mockito-core:4.11.0'
        testImplementation 'org.mockito:mockito-junit-jupiter:4.11.0'```
    * Import using `import static org.mockito.Mockito.*;`
    * 
* Syntax to know:
    * `@Mock` and `mock(ClassName.class)`:
        * `@Mock` - this comes with MockitoExtensions. You must add `@ExtendWith(MockitoExtension.class)` before the class declaration to use `@Mock`. Using this decorator allows you to define mock objects just like any other test objects without having to explicitly mock them like `var thing = mock(ThingClass.class)`. 
    * `when-then` - this is a major operative thing that gives a static response when a method is called on a class. An example of this is `when(reader.getNBATeams()).then(List.of(LAKERS, CELTICS))`. This creates a stub inline.
        * An edge case is mocking lists. this means that we can even test the ADT `List` and keeping our test independent of the actual implementation of `List`. Make sure to add `@SuppressWarnings("unchecked")` at the top of the implementation of the mocking of a list, something like `List<Book> mockList = mock(List.class)`. 
    * `verify` - used to verify that a particular function with a particular argument was called on our object. This can come in multiple forms:
        * `verify(myObject).aMethod(anArgument)` - verifies that `myObject` called `aMethod()` with `anArgument`.
        * `verify(myObject, times(0)).aMethod(anArgument)` - verifies that `myObject` had called `aMethod(anArgument)` 0 times.
            * can also do `never()` instead of `times(0)`. 
        * `verifyNoMoreInteractions(anObject)` - ensures that no more methods (other than the ones you have called verify with) have been called. Don't use this, usually
        * Verify should be used to ensure the intended _destructive_ functions are called. Verify ensures that the _intended action is invoked_, but we don't check iff that interaction produces the correct outcome.
    * `lenient()` - a rule of mockito isd that you should only use `when-then` when that thing is actually being used. However, when the order of adding something (i.e. to a HashMap, for example) is not deterministic, you may have instances in which you have two `when-then`s but only one needs to be used. In this case, add `lenient()`. Thus, a test won't fail because of an unnecessary `when-then`. 

### DB Persistence

Persistence is teh idea that we want some information to be saved from one run to the next. This can be done through two main ways - _files_ and _databases_. Files include stuff like source code, documents files, executable programs, and media files. Databases are used to store data long term but abstract how the data is stored. They can either rbe local (sqlite) or use a server (Postgres, MySql, etc). 

Common File Formats:
* `.txt` - text
* `.csv` - comma separated values. 
* `.tsv` - tab separated values
* `json/xml/yml` - yummy.

### JSON Parsin'

* JSON Syntax and datatype. JSON is a structured data format that allows for nesting of data and human readability. The data types include:
    * Integers (whole numbers)
    * Floats (decimal numbers)
    * Strings 
    * booleans
    * lists
    * Other objects (maps)
* To use:
    * add to dependencies in build.gradle: `implementation 'org.json:json:20231013'`
* JSON to objects, objects to JSON:
    * ```java
        // taking a json file and parsing it using org.json
        String fileName = "json_files/simple.json";
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        StringBuilder sb = new StringBuilder();
        String line = br.readLine();
        while (line != null) {
            sb.append(line).append("\n");
            line = br.readLine();
        }
        String text = sb.toString();
        var root = new JSONObject(text);```
    * you can then get different values from the JSON like:
        * `var list = root.getJSONArray("listKey")`
        * `var object = root.getJSONObject("objectKey")`
        * `var string = root.getString("someStringKey")`
        * `var int = root.getInt("someIntKey")`
    * Furthermore, to create a JSON Object (like from a POJO)
        * If you have an array of basic datatypes, it's easy:
        * ```java
            var root = new JSONObject();
            root.append("key", value);
            root.append("key2", value2);```
        * Appending objects:
        * `root.append("objKey", someJSONObject)`
        * Arrays of stuff are more difficult. If you have an array of strings, you can just do `root.put("arrayKey", new JSONObject(new ArrayList<>(List.of("hello, "world"))))` or with a `ArrayList<String/boolean/Integer>`, anything in the collections framework. If you have a list of objects, you must iterate over that list, create the JSON, and put it.
        
### Database Concepts.

* Common terms:
    * Table - a table of data (generally an object)
    * Record - a row of data in a table
    * Primary Key - unique identifier for each record in a table. Can be specified by `INTEGER PRIMARY KEY` and sqlite handles the auto-increment.
    * Foreign Key - an identifier within a record of one table that maps to a primary key of data in another table.
    * Constraints:
        * Unique - must be unique. (however, you could have repeated null values).
        * Non-null - must be non-null
        * Check - can be used to test values whenever they are inserted/updated within a column. For example, you could add a `CHECK(length(phone) >= 10)` to add a check to ensure that all phones are both non-null and have a length of 10 or less.
        * Default - specifies a default value that will be added if that value is not specified in an insert. For example, we could have a column `Price INTEGER DEFAULT 100`, so if we do `INSERT INTO Products (product_id, product_name) VALUES(1, "Hello")` but don't specify price, the price will default to 100.
* SQLite vs. MySQL/Postgres
    * Sqlite uses a file, whereas Postgres/MySQL use a server. 
    * SQlite query syntax is slightly different.
    * Sqlite doesn't support user accounts within the database.


### SQL to be familiar with.

* Creating tables
    * ```sql
        create table if not exists Courses (
            Crn INTEGER PRIMARY KEY,
            Subject TEXT, 
            Number INTEGER,
            Section INTEGER,
            MeetingTime TEXT
        );``` 
* Foreign Keys - foreign keys connect multiple tables together.
    * For example, when creating something like this:
    * ```sql
        create table if not exists coursesTaken(
            CourseTakenID int NOT NULL AUTO_INCREMENT,
            StudentNumber int, 
            CRN int, 
            FOREIGN KEY (StudentNumber) references students(StudentNumber) ON DELETE CASCADE
            FOREIGN KEY (CRN) references courses (CRN) ON DELETE CASCADE
            PRIMARY KEY (CourseTakenID)
        )
        ```
    * Note that `ON DELETE CASCADE` ensures that if you delete a student with nunmber 4, it deletes all records in students where StudentNumber is 4 and all records in coursesTaken where StudentNumber is 4.
* Inserting into tables.
    * ```sql
        insert into Courses (CRN, Subject, Number, Section, MeetingTime)
        values (12345, "CS", 2501, 300, "TR 12:30 - 1:45");
        ```
    * Can also do something that doesn't supply the column names, just:
    * ```sql
        insert into Courses values (12345, "CS", 2501, 300, "TR 12:30 - 1:45");
        ```
    * Can do multiple inserts in one line, and note that also that CRN is an integer primary key and thus auto increments, we do not have to specify the Crn. 
    * ```sql
        insert into Courses (CRN, Subject, Number, Section, MeetingTime)
        values ("CS", 2501, 300, "TR 12:30 - 1:45"), 
        values("APMA", 2120, 001, "MWF 12:00 - 12:50");
        ```
* Selecting Stuff
    * `Select * from courses` - gets all data from courses table
    * `Select Subject, Number from courses` - gets just those specific columns from each row from the table.
    * Filtered Queries:
        * `Select * from courses where Subject = "CS"` - Only rows of data for CS
        * `Select * from courses where Subject = "CS" AND Number = 2501`. You get the idea.
* Joining Stuff - these are queries where you can get data from a table and also other tables that are connected by foreign keys. Here are some examples of joining:
    * Joining the courses and lecturers tables:
    * `Select * from Courses join Lecturers on Courses.Lecturer = Lecturers.Id`
        * the "on" is the condition says to only join rows where Courses.Lecturer = Lecturers.id
    * `Select Courses.Subject, Courses.Number, Courses.Section, Lecturers.FirstName, Lecturers.LastName from Courses join Lecturers on Courses.Lecturer = Lecturers.Id`
    * `select StudentNumber from coursesTaken join courses on coursesTaken.CRN = courses.CRN where courses.CRN = 123456`
    * `select LastName, FirstName from Students where StudentNumber in (select StudentNumber from coursesTaken join courses on coursesTaken.CRN = courses.CRN where courses.CRN = 12345)`

* Update
* Delete
* Begin, commit, and rollback. 

### JDBC

### Hibernate & ORMs

### Design Patterns

### JavaFX

### Optimization

## Midterm

### Software Engineering

### Java

### Software Construction

### Testing

### Defensive Programming

### Code Quality

### Functional Programming

### Software Design

### Polymorphism

### Software Architecture



