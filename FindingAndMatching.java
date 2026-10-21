import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

public class FindingAndMatching {
	
static class Dish{
	// Private final variables
	private final String name;
    private final boolean vegetarian;
    private final int calories;
    private final Type type;
    
    // Constructor
    public Dish(String name, boolean vegetarian, int calories, Type type) {
    	this.name = name;
    	this.vegetarian = vegetarian;
    	this.calories = calories;
    	this.type = type;
    }
    
    // getter method
    
    public String getName() {return name;}
    public boolean isVegetarian() {return vegetarian;}
    public int getCalories() {return calories;}
    public Type getType() {return type;}
    
    @Override
    public String toString()
    {
    	return name;
    }
    
    // Self declared type
    public enum Type { MEAT, FISH, OTHER }
}

	public static void main(String[] args) {
		
		// menu inventory
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
        
        
		// AnyMatch
        if(menu.stream().anyMatch(Dish::isVegetarian))
        {
        	System.out.println("The menu does contain some vegetarian options!");
        }
        
        // ---- short-circuit demo: anyMatch stops at the first match ----
        System.out.println("\n=== anyMatch stops early ===");
        boolean found = menu.stream().anyMatch(d -> {
            System.out.println("checking " + d.getName());
            return d.isVegetarian();
        });
        
        System.out.println("Found a vegetarian dish? " + found);
        
        // ---- allMatch ----
        System.out.println("\n=== allMatch ===");
        System.out.println("Is the menu healthy (all under 1000)? "
                + menu.stream().allMatch(d -> d.getCalories() < 1000));
        
        System.out.println("Is every dish under 500? "
                + menu.stream().allMatch(d -> d.getCalories() < 500));
        
        System.out.println("Is any dish under 500? "
                + menu.stream().anyMatch(d -> d.getCalories() < 500));
        
        // ---- noneMatch ----
        System.out.println("\n=== noneMatch ===");
        System.out.println("No dish at 1000+ calories? "
                + menu.stream().noneMatch(d -> d.getCalories() >= 1000));
        
        // ---- findAny with ifPresent ----
        System.out.println("\n=== findAny ===");
        menu.stream()
        .filter(Dish::isVegetarian)
        .findAny()
        .ifPresent(d-> System.out.println("Found a vegetarian dish named: " + d.getName()));
        
        // ---- an empty Optional ----
        System.out.println("\n=== empty Optional ===");
        Optional<Dish> tooHeavy = menu.stream()
                .filter(d -> d.getCalories() > 1000)
                .findAny();
        
        System.out.println("Dish over 1000 calories present? " + tooHeavy.isPresent());
        System.out.println("orElse fallback: " + tooHeavy.orElse(null));

        // ---- findFirst ----
        System.out.println("\n=== findFirst ===");
        List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);
        
        Optional<Integer> firstSquare = someNumbers.stream()
                .map(x -> x * x)
                .filter(x -> x % 3 == 0)
                .findFirst();
        
        System.out.println("First square divisible by 3: " + firstSquare.get());

	}

}
