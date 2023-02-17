# Number Validator - Code Assignment
This is my solution to a code assignment where the task was to, in Java, with an OOP approach, write a program that tests the validity of Swedish personnummer (social security numbers) and two other types of "governmental numbers" - organisationsnummer and samordningsnummer. The checks to be performed should extend a fictive class/interface ValidityCheck and a method that takes such checks should be implemented. The specifications of all three number types were provided. The program should take the numbers to be checked as strings. The project should also have a build script.

The program is adapted for demo purposes (see "Run" section) but the code could also be used as part of something bigger. The basic idea is: To validate a nummer, create a `Nummer` object of corresponding type and pass the number to be validated as a string to the constructor, then execute the object's method `checkValidity()`, which will update the `isValid` and `failingTests` fields of the Nummer object. These fields can then be accessed to make printouts/logging or whatever one needs them for. In `App.java`, a few such methods are implemented for the demo.

## Dependencies
- Java 19

## Build
- The project uses Gradle for build and running (build with Gradle 8.0)
- The `gradlew` script ensures that the right version is used and because of it you don't need to have gradle installed to run the project. 
- To build the project, just use the following command, while in the project folder. 

On Windows:
```
$ gradlew.bat build
```
On Linux/Mac:
```
$ ./gradlew build
```

## Run
The program has two modes: 
  1) testing example numbers - pass no arguments
  2) testing a single number - pass number to test as argument

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
Specific numbers can be tested by passing them as arguments to the program. The type of number must be passed as an argument (`p`, `o` or `s`). 
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

## Unit tests
The project is tested with JUnit Jupiter tests. To execute the tests run:
```
$ ./gradlew test
```
