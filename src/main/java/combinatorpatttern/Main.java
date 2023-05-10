package combinatorpatttern;

import java.time.LocalDate;

import static combinatorpatttern.CustomerRegistrationValidator.*;

public class Main {

    public static void main(String[] args) {
        Customer customer = new Customer(
                "Alice",
                "alice@gmail.com",
                "+0898787879878",
                LocalDate.of(2015, 1,1)
        );

        Customer customer1 = new Customer(
                "Alice",
                "alice@gmail.com",
                "0898787879878",
                LocalDate.of(2000, 1,1)
        );
        //System.out.println(new CustomerValidatorService().isValid(customer1));

        // if valid customer, we can store customer in db

        // Using combinator pattern to know what is wrong
        //with our validation, combinator patter allows
        //change function together so take the function as
        //arguments and returns new functions
        CustomerRegistrationValidator.isEmailValid()
                .and(CustomerRegistrationValidator.isPhoneNumberValid())
                .and(isAnAdult())


        ValidationResult result = isEmailValid()
                .and(isPhoneNumberValid())
                .and(isAnAdult())
                .apply(customer1);

        System.out.println(result);

        if (result != ValidationResult.SUCCESS) {
            throw new IllegalStateException(result.name());
        }

    }
}
