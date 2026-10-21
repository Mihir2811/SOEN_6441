import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

class Dish2 {
	
	// private variables
    private final String name;
    private final boolean vegetarian;
    private final int calories;
    private final Type type;

    //parameterized constructor
    public Dish2(String name, boolean vegetarian, int calories, Type type) {
        this.name = name;
        this.vegetarian = vegetarian;
        this.calories = calories;
        this.type = type;
    }

    //getter methods
    public String getName() 
    { return name; }
    
    public boolean isVegetarian() 
    { return vegetarian; }
    
    public int getCalories() 
    { return calories; }
    
    public Type getType() 
    { return type; }

    @Override
    public String toString() {
        return name;
    }

    public enum Type { MEAT, FISH, OTHER }
}

public class MappingDemo {

    public static void main(String[] args) {
    	
        List<Dish2> menu = Arrays.asList(
            new Dish2("pork", false, 800, Dish2.Type.MEAT),
            new Dish2("beef", false, 700, Dish2.Type.MEAT),
            new Dish2("chicken", false, 400, Dish2.Type.MEAT),
            new Dish2("french fries", true, 530, Dish2.Type.OTHER),
            new Dish2("rice", true, 350, Dish2.Type.OTHER),
            new Dish2("season fruit", true, 120, Dish2.Type.OTHER),
            new Dish2("pizza", true, 550, Dish2.Type.OTHER),
            new Dish2("prawns", false, 300, Dish2.Type.FISH),
            new Dish2("salmon", false, 450, Dish2.Type.FISH)
        );

        // ---- 1. map: one value out for each value in ----
        System.out.println("=== map: Dish2 names ===");
        List<String> Dish2Names = menu.stream()
                .map(Dish2::getName)
                .collect(toList());
        
        System.out.println(Dish2Names);
        
        // ----------------------------------------------------------------------------------------------

        System.out.println("\n=== map: length of each word ===");
        List<String> words = Arrays.asList("Java8", "Lambdas", "In", "Action");
        List<Integer> wordLengths = words.stream()
                .map(String::length)
                .collect(toList());
        
        System.out.println(wordLengths);
        
     // ----------------------------------------------------------------------------------------------

        System.out.println("\n=== map chained: length of each Dish2 name ===");
        List<Integer> Dish2NameLengths = menu.stream()
        		//using map twice
                .map(Dish2::getName)
                .map(String::length)
                .collect(toList());
        
        System.out.println(Dish2NameLengths);
        
     // ----------------------------------------------------------------------------------------------

        // ---- 2. The wrong attempt: map gives arrays, not letters ----
        System.out.println("\n=== map with split: one array per word ===");
        List<String> hello = Arrays.asList("Hello", "World");
        
        List<String[]> arrays = hello.stream()
                .map(w -> w.split(""))
                .collect(toList());
        
        for (String[] letters : arrays) {
            System.out.println(Arrays.toString(letters));
        }
        
     // ----------------------------------------------------------------------------------------------

        System.out.println("\n=== distinct after map: arrays are not merged ===");
        long countWithMap = hello.stream()
                .map(w -> w.split(""))
                .distinct()
                .count();
        
        System.out.println("Count: " + countWithMap + " (we wanted the unique letters)");
        
     // ----------------------------------------------------------------------------------------------

        // ---- 3. The fix: flatMap ----
        System.out.println("\n=== flatMap: unique letters ===");
        List<String> uniqueCharacters = hello.stream()
                .map(w -> w.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(toList());
        
        System.out.println(uniqueCharacters);
        
     // ----------------------------------------------------------------------------------------------

        // ---- 4. flatMap on a list of lists ----
        System.out.println("\n=== flatMap: flatten a list of lists ===");
        List<List<Integer>> nested = Arrays.asList(
                Arrays.asList(1, 2),
                Arrays.asList(3, 4, 5));
        
        List<Integer> flat = nested.stream()
                .flatMap(List::stream)
                .collect(toList());
        
        System.out.println(flat);
    }
}
