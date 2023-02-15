# Omega Point Code Assignment


## Dependencies
- Java 19.

## Compile
```
$ cd omega-point-code-assignment
$ javac *.java
```
**But why isn't there a build script?** For this size of a project (smol) I chose to 
focus on making the object oriented part as good as I could, since that seemed to be most important. 
Since there are no dependencies, using Gradle or Maven felt a bit overkill.
Also, for it to make sense, I would also need to rewrite my make shift unit tests to real unit tests and I did not have time for that.

## Run
The program is run with `java Main` from command line, while in the `omega-point-code-assignment` folder.

### Test example numbers
With no arguments, the program tests example numbers from all three classes of numbers 
and prints if they're valid or not and list failing tests if invalid.
```
$ java Main
> Testing VALID personnummer:
> 900118+9811 is a valid personnummer
> 201701102384 is a valid personnummer
> ...
> Testing INVALID organisationsnummer:
> 17556614-3185 is not a valid organisationsnummer
> 1 out of 4 ValidityChecks failed:
> YearDigitCheck: Year digit is not 16.
```
### Test one number
Specific numbers can be tested by passing them as agruments to the program. The type of number must be passed as an argument (`p`, `o` or `s`). 
```
$ java Main 900118+9811 p
> 900118+9811 is a valid personnummer

$ java Main 556614-3185 o 
> 556614-3185 is a valid organisationsnummer

$ java Main s 190910799824
> 190910799824 is a valid samordningsnummer

$ java Main 17556614-3185 o
> 17556614-3185 is not a valid organisationsnummer
```

If you want to see what tests failed, pass the argument `v` or `verbose`.
```
$ java Main 17556614-3185 o v
> 17556614-3185 is not a valid organisationsnummer
> 1 out of 4 ValidityChecks failed:
> YearDigitCheck: Year digit is not 16.
```
