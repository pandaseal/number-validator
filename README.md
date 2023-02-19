# Number Validator - Code Assignment

This is my solution to a code assignment. The task was to write a program that tests the validity of Swedish personal numbers (social security numbers) and two other types of "governmental numbers" - organisationsnummer and samordningsnummer. The program is written in Java and structured in an Object Oriented manner. 

The numbers come to the program as strings and the program tests their validity according to the defined specifications for the number type. The program can also show the results of testing a bunch of example numbers that were provided in the instructions (none of them are real numbers).

## Dependencies
- Java 19

## Build and run
- The project uses Gradle for build and running (build with Gradle 8.0)
- The `gradlew` script ensures that the right version is used and because of it you don't need to have gradle installed to run the project. 
- To build and run the project, just use the following command, while in the project folder. 
- The program has two modes (see info below): 
  1) testing the example numbers (provided in instructions, hard coded)
  2) testing a single number passed as an argument

On Windows:
```
$ gradlew run
```
On Linux/Mac:
```
$ ./gradlew run
```

### Mode 1: Test example numbers
With no arguments, the program tests example numbers from all three classes of numbers 
and prints if they're valid or not and list failing tests if invalid.
```
$ ./gradlew run
> Testing VALID personnummer:
> 900118+9811 is a valid personnummer
> 201701102384 is a valid personnummer
> ...
> Testing INVALID organisationsnummer:
> 17556614-3185 is not a valid organisationsnummer
> 1 out of 4 ValidityChecks failed:
> YearDigitCheck: Year digit is not 16.
```
### Mode 2: Test one number
Specific numbers can be tested by passing them as agruments to the program. The type of number must be passed as an argument (`p`, `o` or `s`). 
```
$ ./gradlew run --args="900118+9811 p"
> 900118+9811 is a valid personnummer

$ ./gradlew run --args="556614-3185 o"
> 556614-3185 is a valid organisationsnummer

$ ./gradlew run --args="s 190910799824"
> 190910799824 is a valid samordningsnummer

$ ./gradlew run --args="17556614-3185 o"
> 17556614-3185 is not a valid organisationsnummer
```

If you want to see what tests failed, pass the argument `v` or `verbose`.
```
$ ./gradlew run --args="17556614-3185 o v"
> 17556614-3185 is not a valid organisationsnummer
> 1 out of 4 ValidityChecks failed:
> YearDigitCheck: Year digit is not 16.
```
