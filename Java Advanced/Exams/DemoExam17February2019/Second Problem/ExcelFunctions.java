package Exams.DemoExam17February2019;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;

public class ExcelFunctions {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<List<String>> table = new ArrayList<>();

        int n = Integer.parseInt(sc.nextLine());
        List<String> header = new ArrayList<>(Arrays.asList(sc.nextLine().split(",\\s+")));

        fillTable(sc, table, n);

        String[] command = sc.nextLine().split("\\s+");
        switch (command[0]) {
            case "hide":
                hidingColumn(command[1], table, header);
                break;

            case "sort":
                table = sortingColumn(command[1], table, header);
                break;

            case "filter":
                table = filterColumn(command[1], command[2], table, header);
                break;

        }

        System.out.println(String.join(" | ", header));
        table.forEach(list -> System.out.println(String.join(" | ", list)));

    }

    private static List<List<String>> filterColumn(String column, String parameter, List<List<String>> table, List<String> header) {
        int index = indexOfColumn(column, header);
        return table = table.stream().filter(list -> list.get(index).equals(parameter)).collect(Collectors.toList());

    }

    private static List<List<String>> sortingColumn(String column, List<List<String>> table, List<String> header) {
        int index = indexOfColumn(column, header);
        return table = table.stream().sorted(Comparator.comparing(list -> list.get(index))).collect(Collectors.toList());

    }

    private static void hidingColumn(String column, List<List<String>> table, List<String> header) {
        int index = indexOfColumn(column, header);
        header.remove(index);
        table.stream().map(list -> list.remove(index)).collect(toList());
    }

    private static void fillTable(Scanner sc, List<List<String>> table, int n) {
        for (int i = 1; i < n; i++) {
            List<String> newList =
                    new ArrayList<>
                            (Arrays.asList(sc.nextLine().split(",\\s+")));
            table.add(newList);
        }
    }

    private static int indexOfColumn(String column, List<String> header) {
        for (int i = 0; i < header.size(); i++) {
            if(column.equals(header.get(i))) {
                return i;
            }
        }
        return -1;
    }

}
