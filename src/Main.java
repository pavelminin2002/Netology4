import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        Stream<Person> stream1 = persons.stream();
        Stream<Person> stream2 = persons.stream();
        Stream<Person> stream3 = persons.stream();
        System.out.println(stream1.filter(person -> person.getAge() < 18).count());
        List<Person> forArmy = stream2
                .filter(person -> person.getAge() >= 18 && person.getAge() <= 27 && person.getSex().equals(Sex.MAN))
                .collect(Collectors.toList());
        List<Person> forWork = stream3.filter(person -> {
            if (person.getAge() >= 18) {
                if (person.getSex().equals(Sex.MAN) && person.getAge() <= 65) {
                    return true;
                }
                return person.getSex().equals(Sex.WOMAN) && person.getAge() <= 60;
            } else {
                return false;
            }
        }).filter(person -> person.getEducation().equals(Education.HIGHER)).collect(Collectors.toList());
    }
}
