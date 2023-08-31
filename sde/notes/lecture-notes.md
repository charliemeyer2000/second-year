---
title: Lecture 1 
author: Charlie Meyer
date: August 22, 2023
---

# Lecture 4 - Build Tools ([slides](https://drive.google.com/file/d/1BDfFJ6DUbSitbJ-ttXCW3foW5hpuN0NK/view?usp=drive_link))

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





