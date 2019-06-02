package Classes.CompanyRoster;

public class Employee {
    private String name;
    private double salary;


    private String email;
    private int age;
    private final static String unknownEmail = "n/a";
    private final static int unknownAge = -1;

    public Employee(String name, double salary, String email, int age) {
       this(name, salary);
        this.email = email;
        this.age = age;
    }

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
        this.email = Employee.unknownEmail;
        this.age = Employee.unknownAge;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return this.salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }


    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public String getInfo() {
        return String.format("%s %.2f %s %d", this.name, this.salary, this.email, this.age);
    }
}
