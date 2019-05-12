package SetsAndMaps;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SerbianUnleashed {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String regex = "(?<singer>[a-zA-Z\\s]+)\\s@(?<venue>[a-zA-Z\\s]+)\\s(?<ticketPrice>\\d+)\\s(?<ticketCount>\\d+)$";
        Pattern pattern = Pattern.compile(regex);
        LinkedHashMap<String, Map<String, Integer>> singersAndVenues = new LinkedHashMap<>();
        String input = sc.nextLine();

        while (!"End".equals(input)) {
            Matcher matcher = pattern.matcher(input);
            if (matcher.find()) {
                String singer = matcher.group("singer");
                String venue = matcher.group("venue");
                int ticketPrice = Integer.parseInt(matcher.group("ticketPrice"));
                int ticketCount = Integer.parseInt(matcher.group("ticketCount"));

                singersAndVenues.putIfAbsent(venue, new LinkedHashMap<>());
                singersAndVenues.get(venue).putIfAbsent(singer, 0);
                singersAndVenues.get(venue).put(singer, singersAndVenues.get(venue).get(singer) + ticketCount * ticketPrice);
            }

            input = sc.nextLine();
        }



        for (Map.Entry<String, Map<String, Integer>> outerLoop : singersAndVenues.entrySet()) {
            System.out.printf("%s%n", outerLoop.getKey());
            outerLoop.getValue()
                    .entrySet()
                    .stream()
                    .sorted((singer1, singer2) -> Integer.compare(singer2.getValue(), singer1.getValue()))
                    .forEach(singer -> {
                System.out.printf("#  %s -> %d%n", singer.getKey(), singer.getValue() );
            });
        }
    }
}
