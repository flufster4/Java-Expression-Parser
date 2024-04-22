# Variables and Variable Map
JEP allows for easy use and reuse of expression strings with variables. Variables can only be a single 
character (e.g. 'x' is allowed but 'pi' isn't), and have to be lowercase. They can be assigned using a builder like
API found on the `Expression` class. The methods of interest to do this are `Expression#variable` and `Expression#variableMap`.
The first function takes two parameters. The first parameter is of type `Character`, and it is the character to associate 
a value too. The second function is of type `Double`, and it is the value that is to be associated with the character.
The second function, `Expression#variableMap`, takes a `HashMap<Character, Double>`. The key of the HashMap is the variable
character, and the value is the variable's value. Variables support implied multiplication. Here are some examples:
```
Expression expression = new Expression("xy")
    .variable('x', 2d)
    .variable('y', 2d);    
System.out.println(expression.evaluate()); //Prints 4
```
```
HashMap<Character, Double> variables = new HashMap<>();
variables.put('a', 2d);
variables.put('b', 2d);

Expression expression = new Expression("(a^b+5)*2")
    .variableMap(variables);
System.out.println(expression.evaluate()); //Prints 18
```

# Functions
JEP provides both built-in functions and an API to create custom ones. For a built-in function reference, go [here](advancedops.md).
The custom function API is very similar to the variable one. The function API provides two methods, `Expression#function`
and `Expression#functionMap`. The first takes a `String` as its first parameter, and an `Expression` as the second. The
String is the identifier of the function, and is what will be used to call it. The name has to be fully capitalized and
can be as short or long as you want. The second parameter is an `Expression` object that will parse the function. The parameter 
of the function will be passed into the expression as the `x` variable. The second method, `Expression#functionsMap` is
almost identical to `Expression#variableMap`, except it takes a `HashMap<String, Expression>` as its input instead. Here
is an example:
```
Expression expression = new Expression("DOUBLE(x*ADDTEN(1))")
    .function("DOUBLE", new Expression("2x"))
    .function("ADDTEN", new Expression("10+x"))
    .variable('x', 2d);    
System.out.println(expression.evaluate()); //Prints 44
```