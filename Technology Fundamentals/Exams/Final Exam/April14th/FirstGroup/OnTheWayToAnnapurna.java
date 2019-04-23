package April14th.FirstGroup;

import java.util.*;

public class OnTheWayToAnnapurna {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        Map<String, List<String>> myItems = new LinkedHashMap<>();
        while (!"END".equals(input)) {

            String[] data = input.split("->");
            switch (data[0]) {
                case "Add":
                    String store = data[1];
                    String item = data[2];
                    if (!data[2].contains(",")) {
                        myItems.putIfAbsent(store, new ArrayList<>());
                        myItems.get(store).add(item);
                    } else {
                        String[] items = item.split(",");
                        myItems.putIfAbsent(store, new ArrayList<>());
                        for (String s : items) {
                            myItems.get(store).add(s);
                        }
                    }
                    break;

                case "Remove":
                    String storeToRemove = data[1];
                    myItems.remove(storeToRemove);
                    break;

            }
            input = sc.nextLine();
        }
        System.out.println("Stores list:");
        myItems.entrySet()
                .stream()
                .sorted((store1, store2) -> {
                    int sort = Integer.compare(store2.getValue().size(),store1.getValue().size());
                    if(sort == 0) {
                        sort = store2.getKey().compareTo(store1.getKey());
                    }
                    return sort;
                }).forEach(store -> {
            System.out.printf("%s%n", store.getKey());
            for (String s : store.getValue()) {
                System.out.printf("<<%s>>%n", s);
            }
        });

    }
}
