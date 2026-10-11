package modernjavainaction.chap02;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FilteringApplesTest {

  private List<FilteringApples.Apple> inventory;

  @BeforeEach
  void setUp() {
    inventory = Arrays.asList(
        new FilteringApples.Apple(80, FilteringApples.Color.GREEN),
        new FilteringApples.Apple(155, FilteringApples.Color.GREEN),
        new FilteringApples.Apple(120, FilteringApples.Color.RED)
    );
  }

  @Test
  void testFilterGreenApples() {
    List<FilteringApples.Apple> greenApples = FilteringApples.filterGreenApples(inventory);
    assertEquals(2, greenApples.size());
    assertEquals(FilteringApples.Color.GREEN, greenApples.get(0).getColor());
    assertEquals(FilteringApples.Color.GREEN, greenApples.get(1).getColor());
  }

  @Test
  void testFilterApplesByColor() {
    List<FilteringApples.Apple> greenApples = FilteringApples.filterApplesByColor(inventory, FilteringApples.Color.GREEN);
    assertEquals(2, greenApples.size());

    List<FilteringApples.Apple> redApples = FilteringApples.filterApplesByColor(inventory, FilteringApples.Color.RED);
    assertEquals(1, redApples.size());
    assertEquals(FilteringApples.Color.RED, redApples.get(0).getColor());
  }

  @Test
  void testFilterApplesByWeight() {
    List<FilteringApples.Apple> heavyApples = FilteringApples.filterApplesByWeight(inventory, 100);
    assertEquals(2, heavyApples.size());

    List<FilteringApples.Apple> veryHeavyApples = FilteringApples.filterApplesByWeight(inventory, 150);
    assertEquals(1, veryHeavyApples.size());
    assertEquals(155, veryHeavyApples.get(0).getWeight());
  }

  @Test
  void testFilterWithColorPredicate() {
    List<FilteringApples.Apple> greenApples = FilteringApples.filter(
        inventory,
        new FilteringApples.AppleColorPredicate()
    );
    assertEquals(2, greenApples.size());
  }

  @Test
  void testFilterWithWeightPredicate() {
    List<FilteringApples.Apple> heavyApples = FilteringApples.filter(
        inventory,
        new FilteringApples.AppleWeightPredicate()
    );
    assertEquals(1, heavyApples.size());
    assertEquals(155, heavyApples.get(0).getWeight());
  }

  @Test
  void testFilterWithRedAndHeavyPredicate() {
    // Inventory contains no red & heavy apples (>150)
    List<FilteringApples.Apple> redAndHeavy = FilteringApples.filter(
        inventory,
        new FilteringApples.AppleRedAndHeavyPredicate()
    );
    assertTrue(redAndHeavy.isEmpty());

    // Test with a custom inventory containing a matching apple
    List<FilteringApples.Apple> customInventory = Arrays.asList(
        new FilteringApples.Apple(160, FilteringApples.Color.RED)
    );
    List<FilteringApples.Apple> result = FilteringApples.filter(
        customInventory,
        new FilteringApples.AppleRedAndHeavyPredicate()
    );
    assertEquals(1, result.size());
  }

  @Test
  void testFilterWithAnonymousClass() {
    List<FilteringApples.Apple> redApples = FilteringApples.filter(
        inventory,
        new FilteringApples.ApplePredicate() {
          @Override
          public boolean test(FilteringApples.Apple a) {
            return a.getColor() == FilteringApples.Color.RED;
          }
        }
    );
    assertEquals(1, redApples.size());
    assertEquals(FilteringApples.Color.RED, redApples.get(0).getColor());
  }

  @Test
  void testAppleGettersSettersAndToString() {
    FilteringApples.Apple apple = new FilteringApples.Apple(100, FilteringApples.Color.RED);
    assertEquals(100, apple.getWeight());
    assertEquals(FilteringApples.Color.RED, apple.getColor());

    apple.setWeight(200);
    apple.setColor(FilteringApples.Color.GREEN);
    assertEquals(200, apple.getWeight());
    assertEquals(FilteringApples.Color.GREEN, apple.getColor());

    String str = apple.toString();
    assertTrue(str.contains("GREEN"));
    assertTrue(str.contains("200"));
  }

  @Test
  void testDirectPredicateTesting() {
    FilteringApples.Apple greenApple = new FilteringApples.Apple(90, FilteringApples.Color.GREEN);
    FilteringApples.Apple redHeavyApple = new FilteringApples.Apple(160, FilteringApples.Color.RED);
    FilteringApples.Apple redLightApple = new FilteringApples.Apple(100, FilteringApples.Color.RED);

    // AppleColorPredicate
    assertTrue(new FilteringApples.AppleColorPredicate().test(greenApple));
    assertFalse(new FilteringApples.AppleColorPredicate().test(redLightApple));

    // AppleWeightPredicate
    assertTrue(new FilteringApples.AppleWeightPredicate().test(redHeavyApple));
    assertFalse(new FilteringApples.AppleWeightPredicate().test(greenApple));

    // AppleRedAndHeavyPredicate
    assertTrue(new FilteringApples.AppleRedAndHeavyPredicate().test(redHeavyApple));
    assertFalse(new FilteringApples.AppleRedAndHeavyPredicate().test(redLightApple));
    assertFalse(new FilteringApples.AppleRedAndHeavyPredicate().test(greenApple));
  }

  @Test
  void testMainMethod() {
    assertDoesNotThrow(() -> FilteringApples.main());
  }
}