package Classes.CatLady;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();
        List<Cat> catsList = new ArrayList<>();
        while(!"End".equals(input)) {
            String[] data = input.split("\\s+");
            Cat newCat = new Cat(data[0], data[1]);
            double parameter = Double.parseDouble(data[2]);
            switch (data[0]) {
                case "Siamese":
                    newCat.setEarSize(parameter);
                    break;

                case "Cymric":
                    newCat.setFurLength(parameter);
                    break;

                case "StreetExtraordinaire":
                    newCat.setDecibelOfMeows(parameter);
                    break;
            }
            catsList.add(newCat);
            input = sc.nextLine();
        }
        String catToDisplay = sc.nextLine();
        catsList.stream()
                .filter(cats -> cats.getName().equals(catToDisplay))
                .forEach(Cat::getInfo);
    }
}
