package December16th;

import java.util.*;
import java.util.stream.Collectors;

public class Concert {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Map<String, Map<Integer, List<String>>> myBands = new LinkedHashMap<>();

        String input = sc.nextLine();
        while (!"start of concert".equals(input)) {
            String[] data = input.split("; ");
            int playTime = 0;
            String bandName = data[1];
            if ("Add".equals(data[0])) {
                if (!myBands.containsKey(bandName)) {
                    myBands.put(bandName, new TreeMap<>());
                    myBands.get(bandName).put(playTime, new ArrayList<>());
                } else {
                    for (Map.Entry<Integer, List<String>> kvp : myBands.get(bandName).entrySet()) {
                        playTime = kvp.getKey();
                    }
                }
                String[] bandMembers = data[2].split(", ");
                for (String bandMember : bandMembers) {
                    if (!myBands.get(bandName).get(playTime).contains(bandMember)) {
                        myBands.get(bandName).get(playTime).add(bandMember);
                    }
                }
            } else if ("Play".equals(data[0])) {
                playTime = Integer.parseInt(data[2]);
                if (!myBands.containsKey(bandName)) {
                    myBands.put(bandName, new LinkedHashMap<>());
                    myBands.get(bandName).put(playTime, new ArrayList<>());
                } else {
                    int oldPlayTime = 0;
                    List<String> helpList = new ArrayList<>();
                    for (Map.Entry<Integer, List<String>> kvp : myBands.get(bandName).entrySet()) {
                        oldPlayTime = kvp.getKey();
                        helpList.addAll(kvp.getValue());
                    }
                    myBands.get(bandName).remove(oldPlayTime);
                    myBands.get(bandName).put(oldPlayTime + playTime, new ArrayList<>());
                    for (String s : helpList) {
                        myBands.get(bandName).get(oldPlayTime + playTime).add(s);
                    }
                }

            }
            input = sc.nextLine();
        }
        myBands = myBands.entrySet()
                .stream()
                .sorted((a,b) -> {
                    int firstKey = 0;
                    int secKey = 0;
                    for (Integer keyA : a.getValue().keySet()) {
                        firstKey = keyA;
                    }
                    for (Integer keyB : b.getValue().keySet()) {
                        secKey = keyB;
                    }
                    int sort = Integer.compare(secKey, firstKey);
                    if(sort == 0) {
                        sort = a.getKey().compareTo(b.getKey());
                    }
                    return sort;
                }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a,b) -> a, LinkedHashMap::new));
        int totalTime = 0;
        for (Map.Entry<String, Map<Integer, List<String>>> outerLoop : myBands.entrySet()) {
            for (Integer time : outerLoop.getValue().keySet()) {
                totalTime += time;
            }
        }
        System.out.printf("Total time: %d%n", totalTime);
        for (Map.Entry<String, Map<Integer, List<String>>> outerLoop : myBands.entrySet()) {
            System.out.printf("%s -> ", outerLoop.getKey());
            for (Integer time : outerLoop.getValue().keySet()) {
                System.out.printf("%d%n", time);
            }
        }
        input = sc.nextLine();
        System.out.println(input);
        myBands.get(input).forEach((key, value) -> {
            for (String s : value) {
                System.out.printf("=> %s%n", s);
            }
        });
    }
}
