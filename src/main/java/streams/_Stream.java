package streams;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

import static streams._Stream.Gender.*;

public class _Stream {

    public static void main(String[] args) {

        List<Person> people = List.of(
                new Person("John", MALE),
                new Person("Maria", FEMALE),
                new Person("Aisha", FEMALE),
                new Person("Alex", MALE),
                new Person("Alice", FEMALE),
                new Person("Bob", PREFER_NOT_TO_SAY)
        );

        //Functional way with stream
        people.stream()
                //Perform a transformation on that list,
                //convert every person into a gender
                .map(person -> person.gender)
                //We have a list full of genders
                //now we collect to a set, deleting duplicates
                .collect(Collectors.toSet())
                .forEach(System.out ::println);

        //Get the name
        people.stream()
                        .map(person -> person.name)
                                .collect(Collectors.toSet())
                                        .forEach(System.out ::println);

        //Get the length of every name, java Function
        Function<Person, String> personStringFunction=person -> person.name;
        ToIntFunction<String> length=String::length;
        IntConsumer println= System.out::println;
        people.stream()
                .map(personStringFunction)
                        .mapToInt(length)
                                .forEach(println);
        System.out.println("\n Functional way ");
        //Get the length of every name, functional way
        people.stream()
                .map(person -> person.name)
                .mapToInt(String::length)
                .forEach(System.out::println);


        //There is only females?
        Predicate<Person> personPredicate = person -> FEMALE.equals(person.gender);
        boolean containsOnlyFemales= people.stream()
                .allMatch(personPredicate);
        System.out.println(containsOnlyFemales);
    }

    static class Person {
        private final String name;
        private final Gender gender;

        Person(String name, Gender gender) {
            this.name = name;
            this.gender = gender;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", gender=" + gender +
                    '}';
        }
    }

    enum Gender {
        MALE, FEMALE, PREFER_NOT_TO_SAY
    }
}
