# Advanced Operations/Functions
For a custom function guide, refer to [here](variablefunctiondoc.md).
Java expression parser supports mathematical functions and other "advanced" operations. To use them is quite simple,
just include the name of the function in your expression. Please note that **all function names must be in all caps**.
All functions can have their input be either in parentheses (``SIN(29+1)``), or without (``SIN30``). 

## Trigonometry
All trigonometrical function inputs are in radians, and so is the output. The current supported trigonometrical function are:
- SIN and ARCSIN
- COS and ARCCOS
- TAN and ARCTAN

## Misc Functions
The current supported misc functions are:
- SQRT (square root)
- EXP (e^x)
- LOG (natural log)
- LOG10 (base-10 log)
- FACT (factorial)

# Examples
Here is a list of examples and their output:
```
SIN(30) = 0.5
COS(60) = 0.5
TAN(45) = 1
ARCSIN(0.5) = 30
ARCCOS(0.866) = 30
ARCTAN(1) = 45
SQRT(9) = 3
```