package modernjavainaction.chap02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilteringApples {

  public static void main(String... args) {

    // Create a list of Apple objects.
    // Each Apple has a weight and a color.
    List<Apple> inventory = Arrays.asList(
        new Apple(80, Color.GREEN),
        new Apple(155, Color.GREEN),
        new Apple(120, Color.RED));

    // ---------------------------------------------------------
    // APPROACH 1: Filter apples by color
    // ---------------------------------------------------------
    //
    // We pass the inventory and the color we are looking for.
    // The method returns only apples matching that color.
    //
    // Expected result:
    // [Apple{color=GREEN, weight=80},
    //  Apple{color=GREEN, weight=155}]
    List<Apple> greenApples = filterApplesByColor(inventory, Color.GREEN);
    System.out.println(greenApples);

    // Same method, but this time we look for RED apples.
    //
    // Expected result:
    // [Apple{color=RED, weight=120}]
    List<Apple> redApples = filterApplesByColor(inventory, Color.RED);
    System.out.println(redApples);

    // ---------------------------------------------------------
    // APPROACH 2: Pass the filtering behavior as an object
    // ---------------------------------------------------------
    //
    // Instead of telling filter() exactly HOW to filter the apples,
    // we give it an ApplePredicate object.
    //
    // AppleColorPredicate contains the rule:
    // "Keep the apple if its color is GREEN."
    //
    // This is an example of BEHAVIOR PARAMETERIZATION:
    // the behavior used by filter() is passed into the method.
    //
    // Expected result:
    // [Apple{color=GREEN, weight=80},
    //  Apple{color=GREEN, weight=155}]
    List<Apple> greenApples2 = filter(
        inventory,
        new AppleColorPredicate()
    );
    System.out.println(greenApples2);

    // Here we use a different predicate.
    //
    // AppleWeightPredicate contains the rule:
    // "Keep the apple if its weight is greater than 150."
    //
    // Expected result:
    // [Apple{color=GREEN, weight=155}]
    List<Apple> heavyApples = filter(
        inventory,
        new AppleWeightPredicate()
    );
    System.out.println(heavyApples);

    // This predicate combines two conditions:
    // 1. The apple must be RED.
    // 2. The apple must weigh more than 150.
    //
    // There is no apple satisfying both conditions in our inventory,
    // so the result is an empty list.
    //
    // Expected result:
    // []
    List<Apple> redAndHeavyApples = filter(
        inventory,
        new AppleRedAndHeavyPredicate()
    );
    System.out.println(redAndHeavyApples);

    // ---------------------------------------------------------
    // APPROACH 3: Anonymous class
    // ---------------------------------------------------------
    //
    // Instead of creating a separate class such as
    // AppleColorPredicate, we can define the behavior directly
    // where we need it.
    //
    // This anonymous class implements ApplePredicate and provides
    // its own implementation of the test() method.
    //
    // The rule is:
    // "Keep the apple if its color is RED."
    //
    // Expected result:
    // [Apple{color=RED, weight=120}]
    List<Apple> redApples2 = filter(
        inventory,
        new ApplePredicate() {

          @Override
          public boolean test(Apple a) {
            return a.getColor() == Color.RED;
          }
        }
    );

    System.out.println(redApples2);
  }

  // -----------------------------------------------------------
  // FILTERING METHOD #1
  // -----------------------------------------------------------
  //
  // This method specifically filters GREEN apples.
  //
  // The problem with this approach is that the method is
  // hard-coded to one particular condition: GREEN.
  //
  // If we wanted RED apples, we would need another method.
  public static List<Apple> filterGreenApples(List<Apple> inventory) {

    // Create a new list to store apples that pass the condition.
    List<Apple> result = new ArrayList<>();

    // Loop through every apple in the inventory.
    for (Apple apple : inventory) {

      // Check whether the current apple is GREEN.
      if (apple.getColor() == Color.GREEN) {

        // If it is green, add it to the result list.
        result.add(apple);
      }
    }

    // Return all matching apples.
    return result;
  }

  // -----------------------------------------------------------
  // FILTERING METHOD #2
  // -----------------------------------------------------------
  //
  // This is an improvement over filterGreenApples().
  //
  // Instead of hard-coding GREEN, we pass the desired color
  // as a parameter.
  //
  // This allows the same method to filter RED, GREEN, etc.
  public static List<Apple> filterApplesByColor(
      List<Apple> inventory,
      Color color) {

    // List that will contain the apples that match the color.
    List<Apple> result = new ArrayList<>();

    // Examine every apple in the inventory.
    for (Apple apple : inventory) {

      // Compare the apple's color with the requested color.
      if (apple.getColor() == color) {

        // Add matching apples to the result.
        result.add(apple);
      }
    }

    return result;
  }

  // -----------------------------------------------------------
  // FILTERING METHOD #3
  // -----------------------------------------------------------
  //
  // This method filters apples based on weight.
  //
  // The condition is:
  // apple weight > weight parameter
  //
  // Notice that we now have separate methods for color and weight.
  // As more requirements appear, we would need more methods.
  public static List<Apple> filterApplesByWeight(
      List<Apple> inventory,
      int weight) {

    List<Apple> result = new ArrayList<>();

    for (Apple apple : inventory) {

      // Keep the apple if it weighs more than the given weight.
      if (apple.getWeight() > weight) {
        result.add(apple);
      }
    }

    return result;
  }

  // -----------------------------------------------------------
  // FILTERING METHOD #4: Behavior Parameterization
  // -----------------------------------------------------------
  //
  // This is the most flexible method in this example.
  //
  // Instead of passing a specific condition such as:
  //   Color.GREEN
  // or:
  //   weight > 150
  //
  // we pass an ApplePredicate.
  //
  // ApplePredicate represents a piece of filtering behavior.
  //
  // The filter() method doesn't need to know WHAT the condition is.
  // It simply asks the predicate:
  //
  //     "Does this apple pass the test?"
  //
  // The predicate answers true or false.
  public static List<Apple> filter(
      List<Apple> inventory,
      ApplePredicate p) {

    // List to store apples that pass the predicate.
    List<Apple> result = new ArrayList<>();

    // Check every apple in the inventory.
    for (Apple apple : inventory) {

      // Call the predicate's test() method.
      //
      // If test() returns true, this apple satisfies
      // whatever condition the predicate represents.
      if (p.test(apple)) {

        // Add the apple to the result.
        result.add(apple);
      }
    }

    return result;
  }

  // -----------------------------------------------------------
  // ENUM: Color
  // -----------------------------------------------------------
  //
  // An enum is used to represent a fixed set of possible colors.
  //
  // In this example, an Apple can be either RED or GREEN.
  enum Color {
    RED,
    GREEN
  }

  // -----------------------------------------------------------
  // APPLE CLASS
  // -----------------------------------------------------------
  //
  // This class represents an Apple.
  //
  // Each Apple has:
  // - a weight
  // - a color
  public static class Apple {

    // Weight of the apple.
    private int weight = 0;

    // Color of the apple.
    private Color color;

    // Constructor used to create an Apple.
    //
    // Example:
    // new Apple(80, Color.GREEN)
    //
    // creates a green apple weighing 80.
    public Apple(int weight, Color color) {
      this.weight = weight;
      this.color = color;
    }

    // Getter for the weight.
    public int getWeight() {
      return weight;
    }

    // Setter for the weight.
    public void setWeight(int weight) {
      this.weight = weight;
    }

    // Getter for the color.
    public Color getColor() {
      return color;
    }

    // Setter for the color.
    public void setColor(Color color) {
      this.color = color;
    }

    // ---------------------------------------------------------
    // toString()
    // ---------------------------------------------------------
    //
    // Defines how an Apple should be displayed when printed.
    //
    // Without this method, System.out.println(apple) would
    // print something similar to:
    //
    // FilteringApples$Apple@1a2b3c
    //
    // With this method, we get something more useful:
    //
    // Apple{color=GREEN, weight=80}
    @SuppressWarnings("boxing")
    @Override
    public String toString() {
      return String.format(
          "Apple{color=%s, weight=%d}",
          color,
          weight
      );
    }
  }

  // -----------------------------------------------------------
  // APPLE PREDICATE INTERFACE
  // -----------------------------------------------------------
  //
  // A predicate is something that evaluates a condition and
  // returns either true or false.
  //
  // This interface says that any ApplePredicate must provide
  // a test() method that accepts an Apple and returns boolean.
  //
  // Different classes can implement this interface with
  // different filtering rules.
  interface ApplePredicate {

    boolean test(Apple a);
  }

  // -----------------------------------------------------------
  // PREDICATE: AppleWeightPredicate
  // -----------------------------------------------------------
  //
  // This predicate checks whether an apple weighs more than 150.
  //
  // It implements ApplePredicate, so it must provide test().
  static class AppleWeightPredicate implements ApplePredicate {

    @Override
    public boolean test(Apple apple) {

      // Return true if the apple weighs more than 150.
      // Otherwise return false.
      return apple.getWeight() > 150;
    }
  }

  // -----------------------------------------------------------
  // PREDICATE: AppleColorPredicate
  // -----------------------------------------------------------
  //
  // This predicate checks whether an apple is GREEN.
  static class AppleColorPredicate implements ApplePredicate {

    @Override
    public boolean test(Apple apple) {

      // Return true only for green apples.
      return apple.getColor() == Color.GREEN;
    }
  }

  // -----------------------------------------------------------
  // PREDICATE: AppleRedAndHeavyPredicate
  // -----------------------------------------------------------
  //
  // This predicate checks TWO conditions:
  //
  // 1. The apple must be RED.
  // 2. The apple must weigh more than 150.
  //
  // The && operator means BOTH conditions must be true.
  static class AppleRedAndHeavyPredicate implements ApplePredicate {

    @Override
    public boolean test(Apple apple) {

      return apple.getColor() == Color.RED
          && apple.getWeight() > 150;
    }
  }
}


/*The main idea of this example is the progression:

filterGreenApples() → filtering logic is hard-coded.

filterApplesByColor() → some flexibility by passing the color.

filter() + ApplePredicate → the entire filtering behavior can be passed in.

Anonymous class → define that behavior without creating a separate named class.

That last idea is what leads directly into lambda expressions in the next part of Modern Java in Action.
*/