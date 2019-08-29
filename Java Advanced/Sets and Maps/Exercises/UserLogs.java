package SetsAndMaps;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserLogs {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String regexIP = "IP=(?<IP>[\\d\\.]+|[\\da-zA-Z:]+)\\s+";
        String regexUser = "user=(?<username>[\\da-zA-Z]+)$";
        Pattern patternIP = Pattern.compile(regexIP);
        Pattern patternUsername = Pattern.compile(regexUser);
        TreeMap<String, LinkedHashMap<String, Integer>> usernameIPaddress = new TreeMap<>();
        String input = sc.nextLine();
        while (!"end".equals(input)) {
            Matcher matcherIP = patternIP.matcher(input);
            Matcher matcherUsername = patternUsername.matcher(input);
            String addressIP = "";
            String username = "";
            if (matcherIP.find()) {
                addressIP = matcherIP.group("IP");
            }
            if (matcherUsername.find()) {
                username = matcherUsername.group("username");
            }

            usernameIPaddress.putIfAbsent(username, new LinkedHashMap<>());
            usernameIPaddress.get(username).putIfAbsent(addressIP, 0);
            usernameIPaddress.get(username).put(addressIP, usernameIPaddress.get(username).get(addressIP) + 1);

            input = sc.nextLine();
        }
        for (Map.Entry<String, LinkedHashMap<String, Integer>> outerLoop : usernameIPaddress.entrySet()) {
            System.out.printf("%s: %n", outerLoop.getKey());
            int count = 1;
            for (Map.Entry<String, Integer> innerLoop : outerLoop.getValue().entrySet()) {
                if(count == outerLoop.getValue().size()) {
                    System.out.printf("%s => %d.%n", innerLoop.getKey(), innerLoop.getValue());
                } else {
                    System.out.printf("%s => %d, ", innerLoop.getKey(), innerLoop.getValue());
                }
                count++;

            }
        }
    }
}
