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

### JSON Parsin'

### Database Concepts.

### SQL to be familiar with.

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



