# java-functional-programming


[youtube amigosCode](https://www.youtube.com/watch?v=VRpHdSFWGPs&amp;ab_channel=Amigoscode)

>Bootstrapping Application
1st, at intellij:

Start new Project at Intellij- New Project From VCS
		Url: "https://github.com/amigoscode/java-functional-programming.git"
		
2nd, create, same name project at our gitHub

3rd, locally, at our project directory:

**which git remote?**
```
git remote -v   
origin	https://github.com/amigoscode/java-functional-programming.git (fetch)
origin	https://github.com/amigoscode/java-functional-programming.git (push)
```
**remove origin!!**
```
git remote -v   
origin	https://github.com/amigoscode/java-functional-programming.git (fetch)
origin	https://github.com/amigoscode/java-functional-programming.git (push)
```
**add our new origin, to our github**
```
git remote add origin git@github.com:bullitjose/java-functional-programming.git
```
**add changes at local to origin, at github**
```
git push -u origin master

```
**download changes at github to local**
```
git pull origin master
```

> Imperative Approach

[Java util funciton](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/function/package-summary.html)

The most we use:

- Function<T,R> :
Represents a function that accepts one argument and produces a result.

- Predicate<T>:
Represents a predicate (boolean-valued function) of one argument.

- Consumer<T>
Represents an operation that accepts a single input argument and returns no result.


> Declarative Approach

- Function<T,R> :
Represents a function that accepts one argument and produces a result. 

** Function takes 1 argument and produces 1 result **

Function<T,R>, T is the type of 1 argument of input, and R the type or de 1 result
at imperative:
```
static int incrementByOne(int number) {
        return number + 1;
    }
```
at declarative:
```
static Function<Integer, Integer> incrementByOneFunction =
            number -> number + 1;
```
**At functional style, Function<T,R>:**
-  T= Integer is the type of input paramether at incrementByOne method
- R=type of the output paramether ant incrementByOne method
- number is the variable name's at the method incrementByOne.

To use a Function (apply):
```
int increment2 = incrementByOneFunction.apply(1);
```
Functions static methods:
- apply, R apply(T t)
- andThen, default <V> Function<T,V> andThen(Function<? super R,? extends V> after)
Returns a composed function that first applies the before function to its input, and then applies this function to the result. If evaluation of either function throws an exception, it is relayed to the caller of the composed function.
- compose, default <V> Function<V,R> compose(Function<? super V,? extends T> before)
Returns a composed function that first applies the before function to its input, and then applies this function to the result. If evaluation of either function throws an exception, it is relayed to the caller of the composed function