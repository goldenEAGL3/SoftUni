package SetsAndMaps;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;

public class AcademyGraduation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TreeMap<String, Double> academyGraduation = new TreeMap<>();

        int n = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < n; i++) {
            String name = sc.nextLine();
            double grade = Arrays.stream(sc.nextLine()
                    .split("\\s+"))
                    .mapToDouble(Double::parseDouble)
                    .average()
                    .getAsDouble();

            academyGraduation.put(name, grade);

        }
        DecimalFormat df = new DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
        df.setMaximumFractionDigits(340); //340 = DecimalFormat.DOUBLE_FRACTION_DIGITS

        for (Map.Entry<String, Double> kvp : academyGraduation.entrySet()) {
            System.out.printf("%s is graduated with %s%n", kvp.getKey(), df.format(kvp.getValue()));

        }
    }
}
