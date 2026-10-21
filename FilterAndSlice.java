import java.util.Arrays;
import java.util.List;
import static java.util.stream.Collectors.toList;

class Dish {
    
    // Private variables
    private final String name;
    private final boolean vegetarian;
    private final int calories;
    private final Type type;

    // Parameterized Constructor
    public Dish(String name, boolean vegetarian, int calories, Type type) {
        this.name = name;
        this.vegetarian = vegetarian;
        this.calories = calories;
        this.type = type;
    }

    // Getter Methods
    public String getName() 
    { 
        return name; 
    }
    public boolean isVegetarian() 
    { 
        return vegetarian; 
    }
    
    public int getCalories() 
    { 
        return calories; 
    }
    
    public Type getType() 
    { 
        return type; 
    }

    @Override
    public String toString() 
    {
        return name;
    }
    
    public enum Type { MEAT, FISH, OTHER }
}

public class FilterAndSlice{
  public static void main(String args[]) {

        List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH)
        );

        List<Dish> specialMenu = Arrays.asList(
            new Dish("seasonal fruit", true, 120, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER)
        );
        
        // ---- filter ----
        System.out.println("=== filter with a predicate ===");
        List<Dish> vegetarian = menu.stream()
        .filter(Dish::isVegetarian)
        .collect(toList());
        
        System.out.println(vegetarian);
        
        // ---- distinct ----
        System.out.println("\n=== distinct ===");
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream()
               .filter(i -> i % 2 == 0)
               .distinct()
               .forEach(System.out::println);
        
        // ---- takeWhile and dropWhile (Java 9+) ----
        System.out.println("\n=== takeWhile and dropWhile ===");
        List<Dish> taken = specialMenu.stream()
                .takeWhile(d -> d.getCalories() < 320)
                .collect(toList());
        List<Dish> dropped = specialMenu.stream()
                .dropWhile(d -> d.getCalories() < 320)
                .collect(toList());
        System.out.println("takeWhile: " + taken);
        System.out.println("dropWhile: " + dropped);
        
        // ---- filter vs takeWhile on a list where order matters ----
        
        /*
         * takeWhile() — takes elements from the beginning while the condition is true, then stops.
         * 
			dropWhile() — skips elements from the beginning while the condition is true, then returns the rest.
         */
        
        System.out.println("\n=== filter vs takeWhile ===");
        
        List<Integer> mixed = Arrays.asList(1, 2, 5, 1);
        
        System.out.println("filter < (3): " + mixed.stream().filter(i->i<3).collect(toList()));
        System.out.println("takewhile < (3): " + mixed.stream().takeWhile(i-> i < 3).collect(toList()));
        System.out.println("dropWhile < (3): " + mixed.stream().dropWhile(i->i<3).collect(toList()));
        
        // ---- limit ----
        System.out.println("\n=== limit ===");
        List<Dish> firstThree = menu.stream()
        		.filter(i->i.getCalories()>300)
        		.limit(2)
        		.collect(toList());
        System.out.println(firstThree);
        
        // ---- skip ----
        System.out.println("\n=== skip ===");
        List<Dish> afterSkipping = menu.stream()
                .filter(d -> d.getCalories() > 300)
                .skip(2)
                .collect(toList());
        System.out.println("Over 300 calories, skipping the first two: " + afterSkipping);
   
  }
}
