# Project 1: Deterministic Finite Automata

* Author: Cameron Quitugua, Hailey Whitaker
* Class: CS361 Section 001
* Semester: Spring 2025

## Overview

This program offers functionality for building a Deterministic Finite Automata (DFA).

## Reflection

When creating this project, it was crucial to have a good and solid understanding of what a DFA is, what it requires, and it's functionality. This was the easier part. The harder part was translating DFA functionality into code, especially figuring out what additional classes to implement. The project description was helpful in guiding our understanding and hinting towards what is best. Most of what was a struggle was the smaller problems, for example, one of our tests weren't passing because HashSet wasn't storing elements as exepcted so we switched to LinkedHashSet instead. Another thing that was a bit difficult was figuring out the best way to code the swap method without to much code overhead. 

- What worked well and what was a struggle?
    - We would say the part we struggled most with was adapting the functionality of DFA's to code.
    - We struggled a little with figuring a good Set and Map class to implement.
    - There were also some small bugs and things we overlooked that we eventually fixed while testing. 
- What concepts still aren't quite clear?
    - I would say that we have a good understanding of how a DFA works.
    - Some concepts that are super clear mainly revolve around adapting FA functionality to code. 
- What techniques did you use to make your code easy to debug and modify?
    - We utilized object-orientated concepts so that not all functionality are coded within a single class.
    - Spliting DFA functionality between DFAState and DFA made it easier to manage our state transitions.
    - We also used the debug tool to run through our program step-by-step.
- What would you change about your design process?
    - We believe that our design process was pretty adequate.
    - We probably should spend more time on having a fully fleshed out idea of what functionality goes where. 
- If you could go back in time, what would you tell yourself about doing this project?
    - We would tell ourselves to spend more time on planning out where we want specific functionality of the DFA to be housed (ie DFA or DFAState).

## Compiling and Using

To compile, run the following code.
```
$ javac -cp .:/usr/share/java/junit.jar ./test/dfa/DFATest.java
```

To run tests, run the following code.
```
$ java -cp .:/usr/share/java/junit.jar:/usr/share/java/hamcrest/core.jar org.junit.runner.JUnitCore test.dfa.DFATest
```

## Sources used

