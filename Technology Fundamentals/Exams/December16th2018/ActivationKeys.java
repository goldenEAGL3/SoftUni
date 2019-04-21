package December20th2018;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ActivationKeys {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String regex = "(^|&)(?<symbols25>[\\dA-Za-z]{25})|(?<symbols16>[\\dA-Za-z]{16})(?:&|$)";

        String[] input = sc.nextLine().split("&");
        Pattern pattern = Pattern.compile(regex);
        List<StringBuilder> myList = new ArrayList<>();

        for (String s : input) {
            Matcher matcher = pattern.matcher(s);
            while (matcher.find()) {
                String key;
                if (matcher.group("symbols25") != null) {
                    key = matcher.group("symbols25").toUpperCase();
                } else {
                    key = matcher.group("symbols16").toUpperCase();
                }
                StringBuilder sb = new StringBuilder();
                int beginIndex = 0;
                int EndIndex = 0;
                int numGroups = 0;
                int lastGroup = 0;
                int increment = 0;

                if (key.length() == 16) {
                    EndIndex = 4;
                    numGroups = 4;
                    lastGroup = 3;
                    increment = 4;

                } else if (key.length() == 25) {
                    EndIndex = 5;
                    numGroups = 5;
                    lastGroup = 4;
                    increment = 5;
                }
                for (int i = 0; i < numGroups; i++) {
                    if (i != lastGroup) {
                        sb.append(key, beginIndex, EndIndex).append("-");
                        beginIndex += increment;
                        EndIndex += increment;
                    } else {
                        sb.append(key, beginIndex, EndIndex);
                    }
                }
                for (int i = 0; i < sb.length(); i++) {
                    char a = sb.charAt(i);
                    if (Character.isDigit(a)) {
                        int digit = Integer.parseInt("" + a);
                        digit = 9 - digit;
                        sb.replace(i, i + 1, "" + digit);
                    }
                }
                myList.add(sb);
            }
        }
        System.out.print(String.join(", ", myList));
    }
}
