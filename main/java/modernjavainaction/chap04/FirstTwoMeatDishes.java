package modernjavainaction.chap04;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;

import java.util.List;

public class FirstTwoMeatDishes {

    public static void main(String[] args) {

    	// Using Java8 Stream logic to filter first 2 meat items.
        List<Dish> firstTwoMeatDishes = menu.stream()
        		
            .filter(dish -> dish.getType() == Dish.Type.MEAT)
            .limit(2)
            .collect(toList());

        System.out.println(firstTwoMeatDishes);
    }
    
    public static final List<Dish> menu = Arrays.asList(
    	      new Dish("pork", false, 800, Dish.Type.MEAT),
    	      new Dish("beef", false, 700, Dish.Type.MEAT),
    	      new Dish("chicken", false, 400, Dish.Type.MEAT),
    	      new Dish("french fries", true, 530, Dish.Type.OTHER),
    	      new Dish("rice", true, 350, Dish.Type.OTHER),
    	      new Dish("season fruit", true, 120, Dish.Type.OTHER),
    	      new Dish("pizza", true, 550, Dish.Type.OTHER),
    	      new Dish("prawns", false, 400, Dish.Type.FISH),
    	      new Dish("salmon", false, 450, Dish.Type.FISH)
    	  );
}