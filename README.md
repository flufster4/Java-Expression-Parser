# Java Expression Parser
[![](https://jitpack.io/v/flufster4/Java-Expression-Parser.svg)](https://jitpack.io/#flufster4/Java-Expression-Parser)

A simple java library for parsing string mathematical expressions. 

## Key Features
- All basic operations and parentheses supported
- Supports built in and custom mathematical functions
- Reusable expression string with a variable system
- Fast and efficient processor

# Download
For Maven or Gradle, please refer to [here](#maven-download).

# Usage
Java Expression Parser (JEP) aims to provide speed and ease of use. Due to this, invocation of the library is incredibly simple. 
All you need to do is instantiate the `Expression` class and pass your expression as the parameter. JEP also provides
a feature rich variable and function api that allows you to reuse expression strings. Here is an example implementation of
the Pythagoras Theory:
```
Expression expression = new Expression("SQRT(a^2+b^2)")
    .variable('a', 2d)
    .variable('b', 3d);
Double c = expression.evaluate();
System.out.println(result); //Prints 3.61
```
For more documentation regarding the variable and function system, please refer [here](doc/variablefunctiondoc.md)
## Operations
The current parser evaluates using order of operations (PEMDAS). The current supported operations are:
* Addition (+)
* Subtraction (-)
* Multiplication (*)
* Division (/)
* Exponents (^)
* Parentheses

### Advanced Operations/Functions
Documentation on function support can be found [here](doc/advancedops.md)

## Maven Download
If you wish to use maven for your build system, add
```
<repository>
  <id>jitpack.io</id>
  <url>https://jitpack.io</url>
</repository>
```
to your repositories and
```
<dependency>
  <groupId>com.github.flufster4</groupId>
  <artifactId>Java-Expression-Parser</artifactId>
  <version>TAG</version>
</dependency>
```
to your dependencies.

## Gradle Download
For Gradle, add
```
dependencyResolutionManagement {
  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
		repositories {
			 mavenCentral()
			 maven { url 'https://jitpack.io' }
		}
}
```
to the end of your repositories, and then add the following dependency:
```
dependencies {
  implementation 'com.github.flufster4:Java-Expression-Parser:TAG'
}
```