import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] numbers = sc.nextLine().split("\\s+");
        String[] urls = sc.nextLine().split("\\s+");

        Smartphone smartphone = new Smartphone(Arrays.asList(numbers), Arrays.asList(urls));

        System.out.print(smartphone.call());
        System.out.println(smartphone.browse());
    }
}
