---
title: Lecture 1 
author: Charlie Meyer
date: August 22, 2023
---

# Lecture 3 ([slides](slides/03%20Version%20Control%20+%20Git.pdf))



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

##





