package SetsAndMaps;

import java.util.LinkedHashSet;
import java.util.Scanner;

public class SetsOfElements {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] data = sc.nextLine().split("\\s+");
        int n = Integer.parseInt(data[0]);
        int m = Integer.parseInt(data[1]);

        LinkedHashSet<String> set1 = new LinkedHashSet<>();
        LinkedHashSet<String> set2 = new LinkedHashSet<>();

        for (int i = 0; i < n; i++) {
            String number = sc.nextLine();
            set1.add(number);
        }

        for (int i = 0; i < m; i++) {
            String number = sc.nextLine();
            set2.add(number);
        }

        if(set1.size() > set2.size()) {
            while(!set2.isEmpty()) {
                String iter = set2.iterator().next();
                set2.remove(iter);
                if(set1.contains(iter)){
                    System.out.print(iter + " ");
                }
            }

        } else  {
            while(!set1.isEmpty()) {
                String iter = set1.iterator().next();
                set1.remove(iter);
                if(set2.contains(iter)){
                    System.out.print(iter + " ");
                }
            }
        }
    }
}
