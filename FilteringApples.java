import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// A simple Apple class: each apple has a weight and a color
class Apple {
    private int weight;
    private String color;

    public Apple(int weight, String color) {
        this.weight = weight;
        this.color = color;
    }

    public int getWeight() {
        return weight;
    }

    public String getColor() {
        return color;
    }

    // Makes printing an apple readable
    @Override
    public String toString() {
        return "Apple(" + weight + ", " + color + ")";
    }
}

// The "rule card" interface: any rule must answer true or false for an apple
interface ApplePredicate {
    boolean test(Apple apple);
}

// Rule 1: apples heavier than 150
class AppleHeavyWeightPredicate implements ApplePredicate {
    public boolean test(Apple apple) {
        return apple.getWeight() > 150;
    }
}

// Rule 2: green apples
class AppleGreenColorPredicate implements ApplePredicate {
    public boolean test(Apple apple) {
        return "green".equals(apple.getColor());
    }
}

// Rule 3: red apples heavier than 150
class AppleRedAndHeavyPredicate implements ApplePredicate {
    public boolean test(Apple apple) {
        return "red".equals(apple.getColor()) && apple.getWeight() > 150;
    }
}

// Rule 4: apples that are NOT red
class AppleNotRedPredicate implements ApplePredicate {
    public boolean test(Apple apple) {
        return !"red".equals(apple.getColor());
    }
}

public class FilteringApples {

    public static void main(String[] args) {
        // Our inventory: three apples
        List<Apple> inventory = Arrays.asList(
            new Apple(80, "green"),
            new Apple(155, "green"),
            new Apple(120, "red")
        );

        System.out.println("Full inventory: " + inventory);
        System.out.println();

        // Same method, different rule cards
        List<Apple> heavyApples = filterApples(inventory, new AppleHeavyWeightPredicate());
        System.out.println("Heavy apples (over 150): " + heavyApples);

        List<Apple> greenApples = filterApples(inventory, new AppleGreenColorPredicate());
        System.out.println("Green apples: " + greenApples);

        List<Apple> redAndHeavyApples = filterApples(inventory, new AppleRedAndHeavyPredicate());
        System.out.println("Red and heavy apples: " + redAndHeavyApples);

        List<Apple> notRedApples = filterApples(inventory, new AppleNotRedPredicate());
        System.out.println("Not red apples: " + notRedApples);

        // Same idea using a lambda expression (shorter, no separate class needed)
        List<Apple> redApples = filterApples(inventory, (Apple apple) -> "red".equals(apple.getColor()));
        System.out.println("Red apples (lambda): " + redApples);
    }

    // The reusable "worker": the loop never changes, only the rule does
    public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }
}
