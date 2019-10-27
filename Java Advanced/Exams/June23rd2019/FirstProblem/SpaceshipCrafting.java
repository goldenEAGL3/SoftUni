
import java.util.*;
import java.util.stream.Collectors;

public class SpaceshipCrafting {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayDeque<Integer> chemicalLiquids = Arrays.stream(sc.nextLine()
                .split("\\s"))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque::new));

        ArrayDeque<Integer> physicalItems = new ArrayDeque<>();
        Arrays.stream(sc.nextLine().split("\\s")).map(Integer::parseInt).forEach(physicalItems::push);

        boolean glassIsMixed = false;
        boolean aluminiumIsMixed = false;
        boolean lithiumIsMixed = false;
        boolean carbonFiberIsMixed = false;
        boolean advancedMaterialsAreMixed = false;

        LinkedHashMap<String, Integer> advancedMaterials = new LinkedHashMap<>() {{
            put("Aluminium", 0);
            put("Carbon fiber", 0);
            put("Glass", 0);
            put("Lithium", 0);
        }};

        while (!chemicalLiquids.isEmpty() && !physicalItems.isEmpty()) {
            int currentChemical = chemicalLiquids.peek();
            int currentItem = physicalItems.peek();

            int sum = currentChemical + currentItem;

            switch (sum) {
                case 25:
                    glassIsMixed = true;
                    removeElementsFromDeque(chemicalLiquids, physicalItems);
                    collectAdvancedMaterials(advancedMaterials, "Glass");
                    break;

                case 50:
                    aluminiumIsMixed = true;
                    removeElementsFromDeque(chemicalLiquids, physicalItems);
                    collectAdvancedMaterials(advancedMaterials, "Aluminium");
                    break;

                case 75:
                    lithiumIsMixed = true;
                    removeElementsFromDeque(chemicalLiquids, physicalItems);
                    collectAdvancedMaterials(advancedMaterials, "Lithium");
                    break;

                case 100:
                    carbonFiberIsMixed = true;
                    removeElementsFromDeque(chemicalLiquids, physicalItems);
                    collectAdvancedMaterials(advancedMaterials, "Carbon fiber");
                    break;

                default:
                    modifyDeques(chemicalLiquids, physicalItems);
                    break;

            }
            if (advancedMaterialsMixed(glassIsMixed, aluminiumIsMixed, lithiumIsMixed, carbonFiberIsMixed)) {
                advancedMaterialsAreMixed = true;
            }
        }

        if (advancedMaterialsAreMixed) {
            System.out.println("Wohoo! You succeeded in building the spaceship!");
        } else {
            System.out.println("Ugh, what a pity! You didn't have enough materials to build the spaceship.");
        }
        printDeque(chemicalLiquids, "Liquids");
        printDeque(physicalItems, "Physical items");

        advancedMaterials.forEach((key, value) -> System.out.printf("%s: %d%n", key, value));
    }

    private static void collectAdvancedMaterials(HashMap<String, Integer> advancedMaterials, String identifier) {
        advancedMaterials.put(identifier, advancedMaterials.get(identifier) + 1);
    }

    private static void printDeque(ArrayDeque<Integer> deque, String dequeIdentification) {
        System.out.printf("%s left: ", dequeIdentification);
        if (deque.size() > 0) {
            System.out.println(String.join(", ", deque.toString().substring(1, deque.toString().length() - 1)));
        } else {
            System.out.println("none");
        }
    }

    private static void modifyDeques(ArrayDeque<Integer> chemicalLiquids, ArrayDeque<Integer> physicalItems) {
        chemicalLiquids.poll();
        int item = physicalItems.pop();
        item += 3;
        physicalItems.push(item);
    }

    private static void removeElementsFromDeque(ArrayDeque<Integer> chemicalLiquids, ArrayDeque<Integer> physicalItems) {
        chemicalLiquids.poll();
        physicalItems.pop();
    }

    private static boolean advancedMaterialsMixed(boolean glassIsMixed, boolean aluminiumIsMixed, boolean lithiumIsMixed, boolean carbonFiberIsMixed) {
        return glassIsMixed && aluminiumIsMixed && lithiumIsMixed && carbonFiberIsMixed;
    }
}
