package SetsAndMaps;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PopulationControl {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LinkedHashMap<String, LinkedHashMap<String, Long>> populationControl = new LinkedHashMap<>();

        String input = sc.nextLine();
        while(!"report".equals(input)) {
            String[] data = input.split("\\|");
            String city = data[0];
            String country = data[1];
            long population = Integer.parseInt(data[2]);

            populationControl.putIfAbsent(country, new LinkedHashMap<>());
            populationControl.get(country).put(city, population);

            input = sc.nextLine();
        }
        populationControl = populationControl
                .entrySet()
                .stream()
                .sorted((country1, country2) -> {
                    long populationCountry1 = 0;
                    long populationCountry2 = 0;
                    for (Map.Entry<String, Long> kvp : country1.getValue().entrySet()) {
                        populationCountry1 += kvp.getValue();
                    }

                    for (Map.Entry<String, Long> kvp : country2.getValue().entrySet()) {
                        populationCountry2 += kvp.getValue();
                    }
                    return Long.compare(populationCountry2, populationCountry1);
                }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a,b) -> a, LinkedHashMap::new));

        for (Map.Entry<String, LinkedHashMap<String, Long>> outerLoop : populationControl.entrySet()) {

            Map<String, Long> helpMap = new LinkedHashMap<>();

            helpMap = outerLoop.getValue()
                    .entrySet()
                    .stream()
                    .sorted((city1, city2) -> Long.compare(city2.getValue(), city1.getValue()))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a,b) -> a, LinkedHashMap::new));
            long totalPopulation = 0;
            for (Map.Entry<String, Long> innerLoop : helpMap.entrySet()) {
                totalPopulation += innerLoop.getValue();
            }
            System.out.printf("%s (total population: %d)%n", outerLoop.getKey(), totalPopulation);
            for (Map.Entry<String, Long> innerLoop : helpMap.entrySet()) {
                System.out.printf("=>%s: %d%n", innerLoop.getKey(), innerLoop.getValue());
            }
        }
    }
}
