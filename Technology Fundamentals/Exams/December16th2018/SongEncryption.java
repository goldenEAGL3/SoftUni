package December16th;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SongEncryption {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();
        String regex = "^(?<artist>[A-Z][a-z\\s']+):[A-Z\\s]+$";
        Pattern pattern = Pattern.compile(regex);

        while (!"end".equals(input)) {
            Matcher matcher = pattern.matcher(input);
            if (matcher.find()) {

                String artist = matcher.group("artist");
                int key = artist.length();
                StringBuilder sb = new StringBuilder();

                for (int i = 0; i < input.length(); i++) {
                    char a = input.charAt(i);
                    if (a == 39 || a == ' ') {
                        sb.append(a);
                        continue;
                    }
                    if (a == ':') {
                        sb.append('@');
                        continue;
                    }

                    if (a + key > 'z') {
                        int diff = a + key - 'z';
                        a = (char) (96 + diff);
                        sb.append(a);
                    } else if (a + key > 'Z' && Character.isUpperCase(a)) {
                        int diff = a + key - 'Z';
                        a = (char) (64 + diff);
                        sb.append(a);
                    } else {
                        sb.append((char) (a + key));
                    }
                }
                System.out.printf("Successful encryption: %s%n", sb.toString());


            } else {
                System.out.println("Invalid input!");
            }


            input = sc.nextLine();
        }
    }
}
