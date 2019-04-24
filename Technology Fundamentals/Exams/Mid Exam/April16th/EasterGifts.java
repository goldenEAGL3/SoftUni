package MidExam.April16th;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class EasterGifts {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        List<String> myGifts = Arrays.stream(sc.nextLine()
                .split("\\s"))
                .collect(Collectors.toList());

        String input = sc.nextLine();

        while (!"No Money".equals(input)) {
            String[] data = input.split("\\s+");
            String command = data[0];
            String gift = data[1];
            switch (command) {
                case "OutOfStock":
                    if (myGifts.contains(gift)) {
                        int index = myGifts.indexOf(gift);
                        while (index != -1) {
                            myGifts.set(index, "None");
                            index = myGifts.indexOf(gift);
                        }

                    }
                    break;

                case "Required":
                    int index = Integer.parseInt(data[2]);
                    if(index >= 0 && index < myGifts.size()) {
                        myGifts.set(index, gift);
                    }
                    break;

                case "JustInCase":
                    myGifts.set(myGifts.size()-1, gift);
                    break;

            }
            input = sc.nextLine();
        }
        myGifts
                .stream()
                .filter(gifts -> !gifts.equals("None"))
                .forEach(gift -> System.out.printf("%s ", gift));
    }
}
