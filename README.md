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

>Java Util Function Package

>Functions

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

>Chaining Functions
```
 Function<Integer, Integer> addBy1AndThenMultiplyBy10 =
                incrementByOneFunction.andThen(multiplyBy10Function);
        System.out.println(addBy1AndThenMultiplyBy10.apply(4));
 ```
 Where functions are:
 ```
 static Function<Integer, Integer> incrementByOneFunction =
            number -> number + 1;

 static Function<Integer, Integer> multiplyBy10Function = number -> number * 10;
 ```
 
 >BiFunction: take 2 inputs and produce one output
 
BiFunction, takes 2 inputs, its the same as method incrementByOneAndMultiply:
```
    static BiFunction<Integer, Integer, Integer> incrementByOneAndMultiplyBiFunction =
            (numberToIncrementByOne, numberToMultiplyBy)
                    -> (numberToIncrementByOne + 1) * numberToMultiplyBy;
```
**After the lambda symbol, -> ,its de same as the method incrementByOneAndMultiply return**              
   Methods works as the BiFunction incrementByOneAndMultiplyBiFunction:
   
   ```
    static int incrementByOneAndMultiply(int number, int numToMultiplyBy) {
        return (number + 1) * numToMultiplyBy;
    }
```

Using a normal java function:
```
        System.out.println(
                incrementByOneAndMultiply(4, 100)
        );
```

Using a BiFunction:
```
        System.out.println(
                incrementByOneAndMultiplyBiFunction.apply(4, 100)
        );
```
 
 >Consumer
 Accepts a single input argument and returns **NO RESULT**
  Interface Consumer<T> 
  Type Parameters: T - the type of the input to the operation
  Normal function of java:
  
```
    static void greetCustomer(Customer customer) {
        System.out.println("Hello " + customer.customerName +
                ", thanks for registering phone number "
                + customer.customerPhoneNumber);
    }
```
Consumer is as function greetCustomer(a void function),
 but using Functional interface:
 ```
    static Consumer<Customer> greetCustomerConsumer = customer ->
            System.out.println("Hello " + customer.customerName +
                    ", thanks for registering phone number "
                    + customer.customerPhoneNumber);
```
**T, is the type of input, Customer**
**customer, is the actual name of the Customer input**
**void, is ther return of function Consumer**

>Biconsumer
BiConsumer<T,U> , Represents an operation that accepts two input arguments 
and returns no result.
Normal function at java, 2 inputs no output (void):

```
    static void greetCustomerV2(Customer customer, boolean showPhoneNumber) {
        System.out.println("Hello " + customer.customerName +
                ", thanks for registering phone number "
                + (showPhoneNumber ? customer.customerPhoneNumber : "*********"));
    }
```
Now the functional way, BiConsumer, de functional way of function greetCustomerV2:
```
    static BiConsumer<Customer, Boolean> greetCustomerConsumerV2 = (customer, showPhoneNumber) ->
            System.out.println("Hello " + customer.customerName +
                    ", thanks for registering phone number "
                    + (showPhoneNumber ? customer.customerPhoneNumber : "*********"));
```

>Predicate<T>, Represents a predicate (boolean-valued function) of one argument.
A normal java function:

```
    static boolean isPhoneNumberValid(String phoneNumber) {
        return phoneNumber.startsWith("07") && phoneNumber.length() == 11;
    }
     System.out.println("Without predicate");
        System.out.println(isPhoneNumberValid("07000000000"));
```

Functional way, using Predicate:
```
    static Predicate<String> isPhoneNumberValidPredicate = phoneNumber ->
            phoneNumber.startsWith("07") && phoneNumber.length() == 11;

    static Predicate<String> containsNumber3 = phoneNumber ->
            phoneNumber.contains("3");
            
 System.out.println("With predicate");
        System.out.println(isPhoneNumberValidPredicate.test("07000000000"));
        System.out.println(isPhoneNumberValidPredicate.test("0700000000"));
```
Combining two predicates:
```
System.out.println(
                "Is phone number valid and contains number 3 = " +
                isPhoneNumberValidPredicate.and(containsNumber3).test("09009877300")
        );
```

>Supplier, Supplier<T> Represents a supplier of results.

Function of java, with no input!!!:
```
    static String getDBConnectionUrl() {

        return "jdbc://localhost:5432/users";
    }
```

Functional way, using Supplier, without input, () is void!!
Return a String, a list of type string:
```
    static Supplier<List<String>> getDBConnectionUrlsSupplier = ()
            -> List.of(
            "jdbc://localhost:5432/users",
            "jdbc://localhost:5432/customer");

}
```
Now we call the functions:
```
System.out.println(getDBConnectionUrl()); System.out.println(getDBConnectionUrlsSupplier.get());
```
