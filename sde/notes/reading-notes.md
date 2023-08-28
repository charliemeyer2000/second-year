---
title: Reading Notes
author: Charlie Meyer
date: Depends
---

# Reading Notes

## Reading 03 - Version Control

### Git Basics



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




####

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





