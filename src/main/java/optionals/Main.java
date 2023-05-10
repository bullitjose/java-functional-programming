package optionals;

import java.util.Optional;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {

        Optional<Object> empty=Optional.empty();
        System.out.println("empty is empty");
        System.out.println(empty.isPresent());//false
        System.out.println(empty.isEmpty());//true

        Optional<String> empty1 = Optional.of("hello");
        System.out.println("empty is = hello");
        System.out.println(empty1.isPresent());//true
        System.out.println(empty1.isEmpty());//false

        //Valor per defecte, si el valor de hello no està present
        Optional<String> hello=Optional.empty();
        System.out.println("hello is empty, però té valor per defecte (world)");
        System.out.println(empty.isPresent());//false
        System.out.println(empty.isEmpty());//true
        //assignar valor per defecte
        String orElse=hello.orElse("world");
        System.out.println(orElse);

        //Valor per defecte, si el valor de hello no esta present
        //Map, per transformar valor a majuscules (functional way)
        System.out.println("hello en minuscules, però té valor per defecte (world)");
        Optional<String> hello1=Optional.ofNullable("hello");
        System.out.println(empty.isPresent());//false
        System.out.println(empty.isEmpty());//true
        //assignar valor per defecte
        String orElse1=hello1
                .map(String::toUpperCase)//pas a majuscules
                .orElse("world");
        System.out.println(orElse1);

        //Valor per defecte, si el valor de hello no esta present
        //valor per defecte assignat a .orElseGet(()->{//logic..}
        //Map, per transformar valor a majuscules (functional way)
        System.out.println("hello en minuscules, té valor per defecte (world)");
        Optional<String> hello2=Optional.ofNullable("hello");
        System.out.println(empty.isPresent());//false
        System.out.println(empty.isEmpty());//true
        //assignar valor per defecte
        String orElse2=hello2
                .map(String::toUpperCase)//pas a majuscules
                .orElseGet(()->{
                    return "world";
                });
        System.out.println(orElse2);

        //metode ifPresent
        //Valor per defecte, si el valor de hello no esta present
        System.out.println("\nnull, no hi ha valor, NO imprimeix valor!!");
        Optional<String> hello21=Optional.ofNullable(null);
        System.out.println(hello21.isPresent());
        System.out.println(hello21.isEmpty());
        System.out.println("imprimir valor= ");
        hello21.ifPresent(word->{
                    System.out.println("valor = "+word);
                });
        System.out.println("\nara afegim un valor (hello)");
        hello21= Optional.ofNullable("hello");
        hello21.ifPresent(word->{
            System.out.println("valor = "+word);
        });

        //metode ifPresentOrElse, per afegir valor per defecte en cas de null
        System.out.println("\nara afegim null, veure metode ifPresentOrElse,imprimeix paraula per defecte ");
        hello21= Optional.ofNullable(null);
        hello21.ifPresentOrElse(System.out::println ,()-> System.out.println("world"));
        System.out.println("\nara afegim valor pepe, veure metode ifPresentOrElse,imprimeix valor ");
        hello21= Optional.ofNullable("pepe");
        hello21.ifPresentOrElse(System.out::println ,()-> System.out.println("world"));

/* Comentat per no llençar excepció
        //Llençar excepció
        //Valor per defecte, si el valor de hello no esta present
        //metode orEsleThrow(IllegalStateException::new);Get(()->{//logic..}
        //Map, per transformar valor a majuscules (functional way)
        System.out.println("\nnull, no hi ha valor, llença EXCEPTION!!");
        Optional<String> hello3=Optional.ofNullable(null);
        System.out.println(empty.isPresent());
        System.out.println(empty.isEmpty());
        //assignar valor per defecte
        String orElse3=hello3
                .map(String::toUpperCase)//pas a majuscules
                .orElseThrow(IllegalStateException::new);
        System.out.println(orElse3);
*/

        Optional.ofNullable("Hello")
                        .ifPresent(value2->{
                            //logic
                            System.out.println(value2);
                        });
        //value different null
        Optional.ofNullable("john@gmail.com")
                        .ifPresent(email->System.out.println("Sending email to "+email));
        //value null
        Optional.ofNullable(null)
                .ifPresent(email->System.out.println("Sending email to "+email));
        //value null, print "cannot send email"
        Optional.ofNullable(null)
                .ifPresentOrElse(
                        email -> System.out.println("Sending email to " + email),
                        () -> System.out.println("Cannot send email"));

        Supplier<IllegalStateException> exception = () -> new IllegalStateException("exception");
        Object value1=Optional.ofNullable("Hello")
                .orElseThrow(exception);
        System.out.println(value1);


        //Optional with Class Person
        System.out.println("\nOptional with class Person, email not null");
        Person person = new Person("james","JAMESs@gmail.com");
        System.out.println(person
                .getEmail()
                .map(String::toLowerCase));

        //Salta excepcio si email es null
        /*
        //Exception in thread "main" java.lang.NullPointerException
        Person person2 = new Person("james",null);
        System.out.println(person2.getEmail().toLowerCase());
        */
        //Solució modificar el getEmail que retorni Optional

        //Optional with Class Person and optional al getEmail
        System.out.println("\nOptional with class Person, email is null");
        Person person3= new Person("james",null);
        String email=person3
                .getEmail()
                .map(String::toLowerCase)
                .orElse("email not provided");

        System.out.println(email);
    }
    static class Person{
        private final String name;
        private final String email;
         Person (String name, String email){
             this.name=name;
             this.email=email;

         }

        public String getName() {
            return name;
        }

        public Optional<String> getEmail() {
            //return email; //no controla null!!
            return Optional.ofNullable(email);
        }
    }
}
