package April14th.SecondGroup;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TheIsleOfManTTRace {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String regex = "^([#$%&*])(?<name>[A-Za-z]+)\\1=(?<length>\\d+)!{2}(?<geoHashCode>.*)$";
        Pattern pattern = Pattern.compile(regex);
        boolean isFound = false;
        String input = sc.nextLine();
        while(true){
            Matcher matcher = pattern.matcher(input);
            if(matcher.find()) {
                StringBuilder sb = new StringBuilder();
                String name = matcher.group("name");
                int length = Integer.parseInt(matcher.group("length"));
                String geoHashCode = matcher.group("geoHashCode");

                if(length != geoHashCode.length()) {
                    System.out.println("Nothing found!");
                } else {
                    for (int i = 0; i < geoHashCode.length(); i++) {
                        int newChar = geoHashCode.charAt(i) + length;
                        sb.append((char)newChar);
                    }
                    System.out.printf("Coordinates found! %s -> %s%n", name, sb);
                    isFound = true;
                }
            } else {
                System.out.println("Nothing found!");
            }
            if(isFound) {
                break;
            }
            input = sc.nextLine();
        }
    }
}
