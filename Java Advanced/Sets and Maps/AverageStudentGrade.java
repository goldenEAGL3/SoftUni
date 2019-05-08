package SetsAndMaps;

import com.sun.source.tree.Tree;

import java.util.*;
import java.util.stream.Collectors;

public class AverageStudentGrade {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TreeMap<String, List<Double>> students = new TreeMap<>();

        int n = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < n; i++) {
            String[] data = sc.nextLine().split("\\s+");
            String name = data[0];
            double grade = Double.parseDouble(data[1]);
            if (!students.containsKey(name)) {
                students.put(name, new ArrayList<>());
                students.get(name).add(grade);
            } else {
                students.get(name).add(grade);
            }
        }

        for (Map.Entry<String, List<Double>> kvp : students.entrySet()) {
            System.out.printf("%s -> ", kvp.getKey());
            double average = 0;
            for (Double grade : kvp.getValue()) {
                System.out.printf("%.2f ", grade);
                average += grade;
            }
            average /= kvp.getValue().size();
            System.out.printf("(avg: %.2f)%n", average);

        }
    }
}
