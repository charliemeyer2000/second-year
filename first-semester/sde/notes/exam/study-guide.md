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
    * and, or, not, others.
        * And/or allows you to chain booleans in a query - `SELECT * from table_name where [condition1] and [condition2]` or `SELECT * from table_name where [condition1] or [condition2]`.
        * Other queries include using comparisons like `SELECT * from COMPANY WHERE AGE >= 25`
    * `like` for String comparators - the LIKE operator is used to match text values against a pattern using wildcards. You can use two wildcards, but you only need to know the % sign. 
        * `...WHERE SALARY LIKE 200%` - any values that start with 200
        * `...WHERE SALARY LIKE %200%` - any values that have 200 in any position.
        * `...WHERE SALARY LIKE %200` - any values that end with 200.
* Joining Stuff - these are queries where you can get data from a table and also other tables that are connected by foreign keys. Here are some examples of joining:
    * Joining the courses and lecturers tables:
    * `Select * from Courses join Lecturers on Courses.Lecturer = Lecturers.Id`
        * the "on" is the condition says to only join rows where Courses.Lecturer = Lecturers.id
    * `Select Courses.Subject, Courses.Number, Courses.Section, Lecturers.FirstName, Lecturers.LastName from Courses join Lecturers on Courses.Lecturer = Lecturers.Id`
    * `select StudentNumber from coursesTaken join courses on coursesTaken.CRN = courses.CRN where courses.CRN = 123456`
    * `select LastName, FirstName from Students where StudentNumber in (select StudentNumber from coursesTaken join courses on coursesTaken.CRN = courses.CRN where courses.CRN = 12345)`
* Update
    * an example of an update query is something that could look like `update Courses set Course=3130 where Course = 3100`
* Delete
    * To delete everything, do `delete from Courses`. 
    * most likely, you'd want to delete something specific from a table, so you should almost always add a `where` clause or you will entirely drop everything.
* Begin, commit, and rollback. 
    * SQlite 3 uses an "auto commit" by default and thus all changes are permanent. To avoid, this:
        1. Begin a transaction: `begin transaction;`
        1. Add your queries.
        1. `commit transaction;` to create a safe point to which you can return to with `rollback` in case you make any mistakes. 

### JDBC

* Connecting and setting up a database connection to a sqlite database using JDBC:
    * Need to add this to the build.gradle's dependencies:
        * ```java
            implementation group: 'org.xerial', name: 'sqlite-jdbc', version: '3.43.2.1'
            implementation group: 'org.slf4j', name: 'slf4j-simple', version: '2.0.9'```
    * Connection string - `connection = DriverManager.getConnection("jdbc:sqlite:database_filename.sqlite")`. This creates and opens our connection. Use `connection.close()` to close it.
    * `connection.createStatement().execute("PRAGMA foreign_keys = ON")` - allows foreign keys
    * `connection.setAutoCommit(false)` - turns off auto commit.
* Using the Connection:
    * Use `connection.commit()` and `connection.rollback()` to commit changes to the database since you've connected, or rollback to rollback to the last commit.
    * use `connection.disconnect()` to disconnect.
* Statements - the general order is `var statement = connection.prepareStatement(sqlQueryString)`.
    * These are sql queries, but when adding in your arguments, you add them in using .`set[Attribute]` corresponding to the 1-indexed `?`:
        * ```java
            String sql = "SELECT * from BUILDINGS WHERE ID = ? AND NAME = ?";
            var statement = connection.prepareStatement(sql);
            statement.setInt(1, 1099);
            statement.setString(2, "Rice Hall");
            ResultSet results = statement.executeQuery();```
    * To then get values from a resultset, you can do something like:
    * ```java
        var id = resultSet.getInt("ID");
        var isActive = resultSet.getBoolean("isActive");
        var longName = resultSet.getString("LongName");
        // dot dot dot```
    * Also you have to notice that there's a difference between `executeQuery()` and `executeUpdate()`. ExecuteQuery returns a ResultSet, whereas executeUpdate returns an int (either (1) the row count for SQL Data Manipulation Language (DML) statements or (2) 0 for SQL statements that return nothing).
    * Either ensure that you are explicitly stating `statement.close()` at the end and have your method throw an `SQLException` or wrap your creating statement in `try(var statement =) catch.`

### Hibernate & ORMs

* The ORM (Object Relational Model) goes from Object to Record/row in a database and back. This allows you to remove a lot of the boilerplate JDBC code that you'll find yourself repeating. 
* Hibernate Config:
    * You must create a `hibernate.cfg.xml` stored in `src/main/resources` file that creates the configuration for your file. There is a lot of boilerplate but here is the main breakdown:
        * `<property name="connection.url">jdbc:sqlite:my_db.sqlite3</property>` - path to your db so hibernate knows where it is.
        * `<mapping class="path.to.some.project.MyClass />` - all of the classes hibernate should use. 
    * For each class mapping (entity), you must provide a zero-argument constructor and default getters and setters for hibernate to work properly. 
* Common Hibernate Things:
    * `@Entity` - defines an entity.
    * `@Table(name="table_name")` - this is underneath the entity and specifies the table name associated with this entity.
    * `@Column(name="column_name")` - for each `private` field, provide a column name for that field within the table.
    * Primary Key Column should have all of these attributes:
        * ```java
            @Id
            @GeneratedValue(strategy = GenerationType.AUTO)
            @Column(name="ID")
            private int id;
            ``` 
    * Join Columns:
        * `@ManyToOne` - Many records in the current entity can correspond to one entity mentioned . An example is having like:
            * ```java
                @ManyToOne
                @JoinColumn(name="Course_ID", referencedColumnName="ID")
                private Course course;
                ```
        * `@OneToMany` - one record in the first entity can correspond to many records in the second entity (i.e. one Course can have many reviews. )
* Working with `session` methods to add/remove/fetch data:
    * `session.persist(object)`.
    * `session.remove(object)`.
    * Transactions:
        * `var transaction = session.beginTransaction()`
        * `transaction.commit()` or `session.getTransaction().commit()`
        * `transaction.rollback()` or `session.getTransaction().rollback()`
    * Querying:
        * Getting an object - `var object = session.get(ClassName.class, id)`
        * Getting Results:
            * `query.getResultList()` or `query.getSingleResult()`
        * queries - `var query = session.createQuery(hqlString, ClassName.class)`
            * HQL Can look like: `select e from States where e.name = :name` and then you do `query.setParameter("name", "Arkansas")`.
            * Ensure that you are using the **CLASS NAMES**, not the table names. 

### Design Patterns

* Design Patterns - best practice for creation of objects or projects for a specific purpose. 
    * Advantages - Can be beneficial if they solve a design problem you are trying to solve. They tend to improve the internal quality of software. 
    * Disadvantages - can lead to over-design for small systems that do not require them. 
* **Creational Patterns** - handle object creation/instantiation and _hide_ or _limit_ the constructor usage. 
    * **Singleton** - ensures that only one instance of a class can exist at a time. That instance can be shared by multiple modules. Common usage in logging, ui settings, and service locators. 
    * **Simple Factory** - class that chooses which concrete implementation of some abstract class to produce. 
    * **Abstract Factory** - returns abstract types. 
    * **Builder** - build complicated objects over multiple steps, aka doing a ton of `.setAttribute()` calls on an empty constructor. 
* **Structural Patterns** - way to structure modules to interact. Decouples an _abstraction_ from its _implementation_.
    * **Adapter** - allow classes to adhere to a ne interface without changing the implementation. Helpful when adapting legacy code to work with new stuff. 
    * **Decorator** - lets you attach new behaviors by attaching them inside of special wrapper objects that contain the behaviors. 
    * **Bridge** - split a large class/set of closely related classes into separate hierarchies which can be developed independently. This basically focuses on _object composition_ over inheritance, where you can compose objects using hierarchies rather than having each class have all of its state and behaviors. 
    * **Facade** - hide complex interactions behind a simple interface. 
* **Behavioral** - give a way to manifest flexible behavior
    * **Iterator** - allow oyu to visit all elements of collections at once. They provide functional independence and information hiding. 
    * **Strategy** - allows you to define a family of algorithms, put each of them into a separate class, and make their objects interchangeable. 
        * An example of this is breaking down something that creates a route into separate strategies.
    * **Observer** - allows you to define a subscription mechanism to notify multiple objects about any events that happen to an object they're observing.

### JavaFX

* High-level application setup. 
    * You highest-level class must extend `Application`, whose main method looks like `psvn(String[] args) { launch(args) }`
    * Have a start method that looks like:
    * ```java
        public void start(Stage stage) throws Exception {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            Scene scene = new Scene(loader.load());
            var controller = (MySceneController) loader.getController();

            controller.setPrimaryStage(stage);

            stage.setTitle("Course Reviews Application");
            stage.setScene(scene);
            stage.show();
        }```
    * Ensure that each FXML file is attached to a controller class - the `AnchorPane` has a property `fx:controller` that you specify the controller class to.
    * GUI Elements:
        * Stage - the overall window
        * Scene - a particular representation of the application.
        * Pane - a scene contains one or more panes, each pane containing one or more widgets.
        * Widgets: labels, buttons, textfield/textarea.
    * Setting handlers both in code and in FXML for Buttons
        * _FXML_: Each button will have an `fx:id` associated with a button in the controller class. Each button can have a `onAction` property to which you can set a handler, for example `onAction=#handleClick`.
        * _Code_: in your `initialize()` method, for each fxml element that you've defined, you can do something like `myButton.setOnAction(event -> { // code })`
    * Labels - 
        * _FXML_: attach the `fx:id` to a label. you can then supply `.setText()` or `.setVisible()` to change those corresponding properties.
        * _CODE_: same deal.
    * TextField/TextArea - 
        * `onKeyPressed` - allows you to filter by the key searched to then do text entry:
        * ```java
            @FXML
            // for an "on key pressed" 
            protected void onTextEntryEnter(KeyEvent event) {
                if (event.getCode().equals(KeyCode.ENTER)) {
                    try {
                        entryTextArea.setText(entryTextArea.getText().strip());
                        errorLabel.setText("");

                    } catch (NumberFormatException e) {
                        errorLabel.setText("Enter a valid int");
                    }
                }
            }
            ```
        * `typedEvent` - WTF

    * Common Things for a controller class
        * ensure that you connect your controller class with your `fx:controller="path.to.controller.class.MyController`.
        * Contains private instance variables corresponding to the attributes in the FXML file. 
        * A controller should only handle actions from the view, updating the view, and call functions in the model when needed. _No meaningful business logic should be handled in the controller class._
* MVC - Model View Controller:
    * Model - handles interactions with data storage
    * View - defines what is displayed to the user
    * Controller - receives requests (request to change information, request to get some information). 
    * MVC is a form of TLA, where View = presentation layer, model = data layer. 
    * MVC Design Benefits:
        * Modularity - cohesion for separating requests, data, and user output
        * Modifiable - easy to add more pages, model over time.

### Optimization

* Premature optimization (is the root of all evil): 
    * Bad becuase programmers think about the speed of noncritical parts of their program. We should forget about small efficiencies, about 97% of the time. 
* Critical Section - section that takes the most compute time and is difficult. Should be optimized. This is generally the code that is executed the most.
* profilers tell you how long certain parts of your code take to execute to determine what takes the longest. 
* Optimization Patterns:
    * Lazy Evaluation - only evaluate something when you need to, rather than every time.
    * Lazy Initialization - initialize object only when you need to (i.e. when it's not null), since memory allocation is a trade off. 
    * Memoization - a trade off of more memory space for improved time performance later. You make ure to only "invalidate" the existing stored memo when the inputs to that calculation change. 
    * Short-circuiting - evaluate less-time intensive things in a conditional first. 

## Midterm

### Software Engineering

* **Ad-hoc development** - solutions that are a one-time solution to a problem. They are not maintainable. 
* _Software Complexity_
    * Accidental Complexity - complexity from circumstance
    * Essential Complexity - complexity that is intrinsic to the development process.
* **Entropy** - amount of effort needed to make a software change. 
* Software Quality:
    * **External** - refers to quality of software from the perspective of the stakeholders (anyone affected by the software, not just the users. This include users, developers, investors). 
        * Functionality - does it do everything it was supposed to
        * Reliable - performing under conditions
        * Useable - is there a lot of effort necessary to use resources to use the product, and to what extent?
        * Portability - can it be transferred from env to env? 
    * **Internal** 
        * Analyzability - to what extent can the software be understood?
        * Stability - how changes in one place affect another
        * Changeability - effort required to change the software
        * reusability - how parts can be reused
        * testability - how software can be tested to find defects. 

### Java

* Compiling v. Interpreting:
    * Compiling is when you take your source code through a compiler and turn it into an executable. 
    * Interpreting is when a translator translates your program into machine instructions as you come to it. This means you don't have a statically compiled file.
* How Java Works:
    * **JDK** - compiler that produces a `.class` file.
    * **JRE** - environment used to run java programs
    * **JVM** - resource that acts like a separate program and interacts with the inner hardware. Technically it is an interpreter, as it interprets java `.class` byte code compiled by the JDK, given to the JVM by the JRE
    * **JIT** - part of the JVM that compiles byte code instructions into machine code instructions.
* Java Command Line Basics:
    * `java` - specifies to use `java` stuff. 
    * `java -jar` - specifies to run a java .`jar` file. 
    * `javac` - compiles a java file into a `.class` file.
    * `java example.Main` - this runs the class `Main` within the package `example`.
* file types:
    * `.java` - what you code in
    * `.class` - the compiled java code
    * `.jar` a bundle of all classes into a single file so you can run it all at once. For example, we can create a jar file manually using `jar cf MyJar.jar example/*.class` and run the file using `java -jar MyJar.jar`.

### Software Construction

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
    * **Gradle** - allows us to create a jar file that also includes external dependencies and generate a `.jar` effectively.
        * **build.gradle** - domain specific language in groovy that specifies build conditions:
            * Plugins - tells gradle we're using java
            * repositories - we just use `mavenCentral()` to download deps.
            * dependencies - tells gradle the external deps we're using
            * test - tells us how to test, aka `useJUnitPlatform()`
            * jar - tells how to build and output the `.jar` file:
                * `archiveBaseName` - output jar name
                * `group` - used if we want to deploy our file on Maven.
                * `duplicatesStrategy` - just leave it as `duplicatesStrategy.EXCLUDE`
                * `manifest` - include `attributes "Main-class"`
                * from - include:
                    * `configurations.runtimeClasspath.collect { it.isDirectory() ? it :zipTree(it)}`.
        * `gradlew buld` and `gradlew test` to build and test, also know how gradlew works and what it does. 

### Testing

* **testing** - the purpose of testing is to _find defects_. 
    * **defect** - an excisting problem that has not been discovered.
    * **failure** - inability to perform function according to specification
    * **mistake** - human error producing something incorrect (a bug in the code)
    * **error** - difference between current state and correct state
* V&V - 
    * **Verification** - focusing on the _internal_ specification, that is how well the developers met the internal specs. 
    * **Validation** - what the consumer thinks about the product. Did you build the right thing? 
* Sound Testing
    * A test is sound when it correctly tests against the specification
    * A test is unsound when it does't (misinformation)
* Test Scenarios - creating a controlled scenario for intended behaviors to test intended state. 
    * State testing - testing whether the intended behaviors for setters create the intended state for an object. 
* `@Test` - identifies a test method. this tells JUnit that this is a test. Test methods must be:
    * `public` and NOT STATIC.
    * return `void`
    * take in no arguments.
* `@BeforeEach` and `@AfterEach` - methods that run before and after each test. These are effectively "setup" and "teardown" methods. 
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
* Testing Scenarios:
    * **Equivalence** - normal testing scenarios (non-boundary)
    * **Boundary** - fencepost cases, like calling something on an empty list. 
    * **Robustness** - tests that are valid but useless (like calling .contains(String s) on a list you know contains all integers).
* Test Plans:
    * **White box testing** - select testing cases while considering the implementation.
        * Branch coverage - ensures that testing covers both true and false conditions for an inf statement and also escaping the while loop.
        * Conditional Coverage - multipole options for conditionals.
        * Statement coverage - what % of statements are covered.
        * Path coverage - interested in what paths your code can take.
    * **Black box testing** - testing based on interface, not implementation
        * **exhaustive testing** - not possible/feasible
        * **random testing** - not really useful
        * **equivalence partitioning** - breaking our equivalence tests into groups that act the same. 

### Defensive Programming

* **Defensive programming** - ensure code cannot be used incorrectly. Throwing exceptions when something that shouldn't happen is happening, but catching exceptions that are fine and move in.
    * **checked exceptions** - exceptions that java forces you to either handle or throw. 
    * Making your own exceptions - specific exceptions that offer unique information to the programmer/user:
        * ```java
            public class MyCustomException extends IOException {
                public MyCustomException(String message) {
                    super(String.format("some custom message %s", message));
                }
            }
            ```
    * AssertionErrors - you can add `assert([some boolean expression])` to ensure that if the statement is false, an AssertionError is thrown. These cannot be caught. You need to add `-ea` to your VM arguments for that to work. 

### Code Quality

* Code Quality:
    * readability - to what extend we can read and understand the code's syntax
    * Understandability - to what extend we can read and understand the code's semantics.
    * **Readability is a necessary but insufficient condition for understandability**. 
    * Analyzability - both readability and understandability. 
* Code Smells:
    * Long methods - functions should clearly only do one thing.
    * Large classes - do one giant `Main.java` is bad.
    * Long parameter list - functions should take in one parameter (ideally). 
    * Boolean parameters - bad.
    * too many primitives - you can extract a class with a ton of primitives into another class.
    * Message chains - split code with lots of construction/methods into multiple lines.
* Refactoring Techniques - **refactoring** is the process of making internal changes to code to improve _internal software quality_ without making changes to the functionality of the software. 
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
* DRY vs. WET
    * DRY - Don't reapeat yourself - extract method, etc.
    * WET - Write Everything Twice. Dry-ness requires more complex design patterns and could ultimately lead to unnecessary complexity. 
* Comments vs. Documentation:
    * Comments shouldn't be used to make sense of a class or method, they should only be used when a method needs explanation. Code should be understandable to the point that it can read like prose, and doesn't need comments.
    * Documentation tells you what things do, not how they do it. 

### Functional Programming

* Creating a stream:
    * List -> `.stream()`
    * Set -> `.keySet()`
    * Map -> `.entrySet()`
        * can do stuff like:
        * ```java
            myHashMap.entrySet().stream().forEach(entry -> {
                String key = entry.getKey();
                String value = entry.getValue();

                System.out.println(String.format("Key: %s, value: %s", key, value));
            })
            ```
* Intermediate Operations:
    * `.sorted` - such as `.sorted((a, b) -> a.getSize() - b.getSize())`. You can also pass in a custom comparator instead, like `.sorted(Comparator.comparing(x -> x.getName()))`. 
    * `.filter` - such as `.filter(a -> a.getSize() > 30)`. 
    * `.map` - such as `.map(x -> x.getName())`. 
    * `.distinct()` - remove all duplicate elements (using the element's default `.equals` method.).
    * `.peek` - can be used to print things or change state without changing the elements themselves. This can be done something like `.peek(thing -> System.out.println(thing))` or `.peek(thing -> thing.setName(thing.getName().toUpperCase()))`. 
    * `.limit()` - limit # of things, like `.limit(20)`. 
* Terminal Operations:
    * `.forEach()` - consumer must return void but do something (generally print).
    * `count()` - returns # of items left in stream
    * `.collect()` - collects, generally into a list. You can do something like `.collect(Collectors.toList())`. This returns an unmodifiable list. 
    * `.map()` - takes in two consumer lambdas, the key function and the value function. Something like `.map(x -> x, x -> x.getSize())`. 
    * `.reduce()` - example being `.reduce(0, (subtotal, statePopulation) -> subtotal + statePopulation)`. 

### Software Design

* Good vs. bad design can generally be sniffed out when it is understandable and also can be modified without much effort (in developer-hours) to meet changing demand.
* Design Concepts:
    * **Modularity** - each unit of code is a module, generally a class. A class should have only one reason to change (single responsibility principle).
    * **functional independence** - each module can perform its function with minimal interaction with the rest of the system.
    * **abstraction**:
        * designing for the interface, not the implementation. The goal of abstraction is to separate the interface from the implementation so that any change to the implementation doesn't change other modules. 
    * **information hiding** - create a limited interface to achieve abstraction. This is why we create public methods w/private fields. 
* Cohesion & Coupling:
    * **Cohesion** - how much a class adheres to one specific purpose
        * _Coincidental Cohesion_ - elements are jammed into one giant class/a giant "utils" file.
        * _Logical Cohesion_ - elements are grouped together because they do the same general thing but have vastly different implementations
        * _Temporal Cohesion_ - elements are grouped together because they do things that happen roughly at the same time.
        * _Procedural Cohesion_ - when things are grouped t/g b/c they describe a single procedure but the activities of each method are largely unrelated. 
        * _Communicational cohesion_ - Elements are grouped together in a module because they perform actions on the same data for either input/output. This is fine, even though the method implementations might be vastly different/performing different actions on the same data.
        * _Sequential Cohesion_ - things are grouped together because they outputs of one method are used as inputs to another
        * _Functional Cohesion_ - when a module supports a single, well-defined function.
    * **Coupling** - the extent to which interacting modules are (inter) dependent.
        * _Content Coupling_ - one class directly modifies state of another within a method.
        * _Common Coupling_ - public access to static variables.
        * _Control Coupling_ - passing a flag from one module to another in a control flow.
        * _Stamp Coupling_ passing data, but some data is unnecessary
        * _Data Coupling_ - communication b/w modules is done by passing the minimum amount of information necessary as arguments and returning exactly what is needed. 
    * Ideal:
        * **highly cohesive** - each module do one thing
        * **loosely coupled** - relationship b/w modules should be limited to just func calls.

### Polymorphism

* Generally know the ideas of interfaces and extending classes, what that means, the syntax, etc. 
* Benefit of polymorphism: dependency injection - passing modules as arguments allows us to ensure a function only deals with using the module's data, rather than both getting AND using its data. 

### Software Architecture

* Architecture - the way components are combined tother to make a system. 
    * Think of components are collections of modules, like an external program, a data source, an API, etc. 
* Common Patterns:
    * Client-Server: client (frontend) communicates with server (backend).
    * Three-Layer: 
        * Presentation - how users see and interact with our system.
        * Business Logic - enforcing real-world business roles on our system.
        * Data Layer - where is our data acquired from and stored? 
    * MVC




## Practice

### ToMap

```java
ArrayList<Cows> cowList = new ArrayList<Cow>(List.of(cow1, cow2));

Map<Cow, Integer> totalWeight = cowList.stream().toMap(e -> e, e -> e.getSize())
```


```java
var connection = DriverManager.getConnection("jdbc:sqlite:my_db.sqlite3");
PreparedStatement s = connection.prepareStatement("Select * from grandmas where age > 50");
ResultSet rs = s.executeQuery();
while (rs.next()) {
    System.out.println(rs.getString("Name"));
}
s.close();
connection.close();

```

```java
BufferedReader br = new BufferedReader(new FileReader("grandmas.json"));
StringBuilder sb = new StringBuilder();

var line = sb.readLine();
while (line) {
    sb.append(line).append("\n");
    line = sb.readLine();
}

JSONObject root = new JSONObject(sb.toString());
JSONarray gArray = root.getJSONArray("Grandmas");
var gList = new ArrayList<Grandma>();
for (int i = 0; i < gArray.length(); i++) {
    if (gArray.get(i).getInt("age") > 50) {
        gList.append(gArray.get(i));
    }
}

gList.stream().forEach(System.out::println)
```


```java
var root = new JSONObject();
var gArray = new JSONArray();
for (Grandma gma : gList) {
    var obj = new JSONObject();
    obj.put("name", gma.getName());
    obj.put("age", gma.getAge());
    gArray.append(obj);
}
root.put("Grandmas", gArray);
```

