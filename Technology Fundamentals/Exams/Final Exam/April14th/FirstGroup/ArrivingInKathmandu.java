package April14th.FirstGroup;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArrivingInKathmandu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String message = sc.nextLine();
//        String regex = "^(?<peak>[A-Za-z\\d!@#$?]+)=(?<length>\\d+)<<(?<geohashcode>.+)$";
        String regex = "^(?<peak>[A-Za-z\\d!@#$?]+)=(?<length>\\d+)<<(?<geohashcode>.*)$";
        Pattern pattern = Pattern.compile(regex);

        while (!"Last note".equals(message)) {
            Matcher matcher = pattern.matcher(message);
            if (matcher.find()) {
                StringBuilder sb = new StringBuilder();
                sb = sb.append(matcher.group("peak"));
                int length = Integer.parseInt(matcher.group("length"));
                String geoHashCode = matcher.group("geohashcode");
                int count = 0;
                for (int i = 0; i < geoHashCode.length(); i++) {
                    if(geoHashCode.charAt(i) != ' '){
                        count++;
                    }
                }
                if (length != count) {
                    System.out.println("Nothing found!");
                } else {
                    String charsToRemove = "0123456789!@#$?";
                    for (int i = 0; i < sb.length(); i++) {
                        if(charsToRemove.contains("" + sb.charAt(i))){
                            sb.replace(i,i+1, "");
                            i--;
                        }
                    }
                    System.out.printf("Coordinates found! %s -> %s%n", sb, geoHashCode);
                }
            } else {
                System.out.println("Nothing found!");
            }
            message = sc.nextLine();
        }
    }
}
