# Java Expression Parser
A simple java library for parsing string mathematical expressions. 

# Download
Download the lastest release.

# Usage
Java Expression Parser (JEP) aims to provide speed and ease of use. Due to this, invokation of the library is incredably simple. All you need to do is instanciate the `Expression` class and pass your expression as the paramater. Then, to evaluate it, you simply call `Expression#evaluate`. For example,
```
Expression expression = new Expression("1+1");
Double result = expression.evaluate();
System.out.println(result);
```

## Operations
The current parser evaluates using order of operations. The current supported operations are:
* Addition (+)
* Subtraction (-)
* Multiplication (*)
* Division (/)
* Parentheses

 # Road Map
 In the future, I plan to add support for more advanced operations such as square root, sine, cosine, tangant, and other mathamatical functions. Variable support is also planned.
