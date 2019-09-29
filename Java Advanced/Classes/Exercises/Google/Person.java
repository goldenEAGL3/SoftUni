package Classes.Google;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Person {
    private String name;
    private Car car =new Car();
    private Company Company = new Company();
    private List<Pokemon> pokemonList;
    private List<Parents> parentsList;
    private List<Children> childrenList;

    public Person(String name, Pokemon pokemon) {
        this(name);
        this.pokemonList.add(pokemon);
    }

    public Person(String name, Children children) {
        this(name);
        this.childrenList.add(children);
    }

    public Person(String name, Parents parents) {
        this(name);
        this.parentsList.add(parents);
    }

    public Person(String name, Car car) {
        this(name);
        this.car = car;
    }

    public Person(String name, Company company) {
        this(name);
        this.Company = company;
    }

    public Person(String name) {
        this.name = name;
        this.parentsList = new ArrayList<>();
        this.childrenList = new ArrayList<>();
        this.pokemonList = new ArrayList<>();

    }

    public String getName() {
        return this.name;
    }

    public Car getCar() {
        return this.car;
    }

    public Classes.Google.Company getCompany() {
        return this.Company;
    }

    public List<Pokemon> getPokemonList() {
        return this.pokemonList;
    }

    public List<Parents> getParentsList() {
        return this.parentsList;
    }

    public List<Children> getChildrenList() {
        return this.childrenList;
    }

    public void getInfo() {
        System.out.println(this.getName());
        System.out.println("Company:");
        if (this.getCompany().getName() != null) {
            System.out.printf("%s %s %.2f%n", this.getCompany().getName(), this.getCompany().getDepartment(), this.getCompany().getSalary());
        }
        System.out.println("Car:");
        if (this.getCar().getModel() != null) {
            System.out.printf("%s %d%n", this.getCar().getModel(), this.getCar().getSpeed());
        }
        System.out.println("Pokemon:");
        if (this.getPokemonList() != null) {
            this.getPokemonList().forEach(pokemon -> System.out.printf("%s %s%n", pokemon.getName(), pokemon.getType()));

        }
        System.out.println("Parents:");
        if (this.getParentsList() != null) {
            this.getParentsList().forEach(parent -> System.out.printf("%s %s%n", parent.getName(), parent.getBirthday()));

        }

        System.out.println("Children:");
        if (this.getChildrenList() != null) {
            this.getChildrenList().forEach(children -> System.out.printf("%s %s%n", children.getName(), children.getBirthday()));

        }
    }
}
