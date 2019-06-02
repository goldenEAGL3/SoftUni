package Classes.CompanyRoster;

import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numberOfPeople = Integer.parseInt(sc.nextLine());
        Map<String, List<Employee>> myEmployees = new HashMap<>();
        for (int i = 0; i < numberOfPeople; i++) {
            String[] data = sc.nextLine().split("\\s+");
            Employee newEmployee;
            String name = data[0];
            double salary = Double.parseDouble(data[1]);
            String position = data[2];
            String department = data[3];

            if (data.length == 6) {
                String email = data[4];
                int age = Integer.parseInt(data[5]);
                newEmployee = new Employee(name, salary, email, age);
            } else {
                newEmployee = new Employee(name, salary);
                if (data.length == 5) {
                    if (data[4].contains("@")) {
                        newEmployee.setEmail(data[4]);
                    } else {
                        newEmployee.setAge(Integer.parseInt(data[4]));
                    }
                }
            }
            myEmployees.putIfAbsent(department, new ArrayList<>());
            myEmployees.get(department).add(newEmployee);
        }

        myEmployees = myEmployees.entrySet().stream().sorted((department1, department2) -> {

            double averageFirstDepartment = department1.getValue().stream().mapToDouble(Employee::getSalary).average().getAsDouble();
            double averageSecDepartment = department2.getValue().stream().mapToDouble(Employee::getSalary).average().getAsDouble();
            return Double.compare(averageSecDepartment, averageFirstDepartment);

        }).limit(1).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> a, HashMap::new));

        myEmployees.forEach((key1, value1) -> {
            System.out.printf("Highest Average Salary: %s%n", key1);
            value1.stream()
                    .sorted((emp1, emp2) -> Double.compare(emp2.getSalary(), emp1.getSalary()))
                    .forEach(employee -> System.out.println(employee.getInfo()));

        });

    }
}
