# Java Expression Parser
A simple java library for parsing string mathematical expressions. 

# Download
[![](https://jitpack.io/v/flufster4/Java-Expression-Parser.svg)](https://jitpack.io/#flufster4/Java-Expression-Parser)

Download the lastest jar, or use a build system like maven or gradle.

## Maven
If you wish to use maven for your build system, simply add 
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

## Gradle
For gradle, add
```
dependencyResolutionManagement {
  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
		repositories {
			 mavenCentral()
			 maven { url 'https://jitpack.io' }
		}
}
```
to the end of your repositories, and then add the following dependency,
```
dependencies {
  implementation 'com.github.flufster4:Java-Expression-Parser:TAG'
}
```

# Usage
Java Expression Parser (JEP) aims to provide speed and ease of use. Due to this, invokation of the library is incredably simple. All you need to do is instanciate the `Expression` class and pass your expression as the paramater. Then, to evaluate it, you simply call `Expression#evaluate`. For example,
```
Expression expression = new Expression("1+1");
Double result = expression.evaluate();
System.out.println(result);
```

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