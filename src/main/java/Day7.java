import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Day7 {
    public static void main(String args []) {
        try {
            Day7 d7 = new Day7(
                Utils.readStringLinesForDay("/Users/jeroendijkmeijer/dev/aoc2020/src/main/resources/seven.txt")
            );
            System.out.println(d7.countBags("shiny gold"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static List<String> rules = new ArrayList<>();
    private static final HashMap<String, String[]> regulations = new HashMap<>();
    private static final String MY_BAG = "shiny gold";

    public Day7(List<String> lines) {
        rules = lines;
        for (String rule : rules) {
            String[] bags = rule.split(" bags contain ");
            String[] innerBags = bags[1].replace(".", "").split(", ");
            regulations.put(bags[0], innerBags);
        }
    }

    public static long resolveDaySevenPartOne() {
        return rules.stream().filter(Day7::validateRule).count();
    }

    public static int resolveDaySevenPartTwo() {
        return countBags(MY_BAG);
    }

    private static boolean validateRule(String rule) {
        String currentBag = rule.split(" bags contain ")[0];
        return !currentBag.equals(MY_BAG) && containsBag(currentBag);
    }

    private static boolean containsBag(String bag) {
        if (bag.equals(MY_BAG)) {
            return true;
        }

        if (!regulations.containsKey(bag) || bag.equals("no other")) {
            return false;
        }

        String[] content = regulations.get(bag);

        for (String innerBag : content) {
            String bagName = innerBag
                    .split(" ", 2)[1]
                    .split(" bag")[0];

            if (containsBag(bagName)) {
                return true;
            }
        }

        return false;
    }

    private static int countBags(String bag) {
        if (!regulations.containsKey(bag)) {
            return 0;
        }

        int sum = 0;

        for (String innerBag : regulations.get(bag)) {
            if (innerBag.equals("no other bags")) {
                continue;
            }

            String[] bagParts = innerBag.split(" ", 2);
            int thisBag = Integer.parseInt(bagParts[0]);
            String name = bagParts[1].split(" bag")[0];
            sum += thisBag + thisBag * countBags(name);
        }

        return sum;
    }

}
