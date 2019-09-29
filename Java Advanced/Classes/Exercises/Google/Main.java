package Classes.Google;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();
        List<Person> allPersons = new ArrayList<>();
        while (!"End".equals(input)) {
            String[] data = input.split("\\s+");

            switch (data[1]) {
                case "company":
                    if (personExists(allPersons, data[0])) {
                        int indexOfPerson = indexOfExistingPerson(allPersons, data[0]);
                        allPersons.get(indexOfPerson).getCompany().setName(data[2]);
                        allPersons.get(indexOfPerson).getCompany().setDepartment(data[3]);
                        allPersons.get(indexOfPerson).getCompany().setSalary(Double.parseDouble(data[4]));
                    } else {
                        Company newCompany = new Company(data[2], data[3], Double.parseDouble(data[4]));
                        Person newPerson = new Person(data[0], newCompany);
                        allPersons.add(newPerson);
                    }
                    break;

                case "car":
                    if (personExists(allPersons, data[0])) {
                        int indexOfPerson = indexOfExistingPerson(allPersons, data[0]);
                        allPersons.get(indexOfPerson).getCar().setModel(data[2]);
                        allPersons.get(indexOfPerson).getCar().setSpeed(Integer.parseInt(data[3]));

                    } else {
                        Car newCar = new Car(data[2], Integer.parseInt(data[3]));
                        Person newPerson = new Person(data[0], newCar);
                        allPersons.add(newPerson);
                    }
                    break;

                case "pokemon":
                    Pokemon newPokemon = new Pokemon(data[2], data[3]);
                    if (personExists(allPersons, data[0])) {
                        int indexOfPerson = indexOfExistingPerson(allPersons, data[0]);
                        allPersons.get(indexOfPerson).getPokemonList().add(newPokemon);
                    } else {
                        Person newPerson = new Person(data[0], newPokemon);
                        allPersons.add(newPerson);
                    }
                    break;

                case "parents":
                    Parents newParent = new Parents(data[2], data[3]);
                    if (personExists(allPersons, data[0])) {
                        int indexOfPerson = indexOfExistingPerson(allPersons, data[0]);
                        allPersons.get(indexOfPerson).getParentsList().add(newParent);
                    } else {
                        Person newPerson = new Person(data[0], newParent);
                        allPersons.add(newPerson);
                    }
                    break;

                case "children":
                    Children newChild = new Children(data[2], data[3]);
                    if (personExists(allPersons, data[0])) {
                        int indexOfPerson = indexOfExistingPerson(allPersons, data[0]);
                        allPersons.get(indexOfPerson).getChildrenList().add(newChild);
                    } else {
                        Person newPerson = new Person(data[0], newChild);
                        allPersons.add(newPerson);
                    }
                    break;
            }
            input = sc.nextLine();
        }
        String nameOfPerson = sc.nextLine();

        allPersons.stream()
                .filter(person -> person.getName().equals(nameOfPerson))
                .forEach(Person::getInfo);

    }

    private static boolean personExists(List<Person> myList, String name) {
        for (Person person : myList) {
            if (name.equals(person.getName())) {
                return true;
            }
        }
        return false;
    }

    private static int indexOfExistingPerson(List<Person> myList, String name) {
        for (int i = 0; i < myList.size(); i++) {
            if (name.equals(myList.get(i).getName())) {
                return i;
            }
        }
        return -1;
    }

}
