package modernjavainaction.chap03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Lambdas {

  public static void main(String... args) 
  {
	 // Printing a simple Hello World using lambda expression
	  
    Runnable r = () -> System.out.println("Hello World!");
    r.run();
    System.out.println("-------------------------------");
    
    // Date formating with lambda expression
    
    ThreadLocal<DateTimeFormatter> formatter = 
    		ThreadLocal.withInitial(() -> DateTimeFormatter.ofPattern("dd-MMM-yyyy"));

    String date = formatter.get().format(LocalDate.of(1970, 1, 1));
    
    System.out.println(date);
    
    System.out.println("-------------------------------");
    
    // Filtering Apples with lambdas
    List<Apple> inventory = Arrays.asList(
        new Apple(80, Color.GREEN),
        new Apple(155, Color.GREEN),
        new Apple(120, Color.RED)
    );
    
    // Filtering green apples with lambda expression
    List<Apple> greenApples = filter(inventory, (Apple a) -> a.getColor() == Color.GREEN);
    System.out.println(greenApples);

    // Comparing weight of apples
    Comparator<Apple> c = (Apple a1, Apple a2) -> a1.getWeight() - a2.getWeight();

    // Sorting apples in an ascending manner for their weight
    inventory.sort(c);
    System.out.println(inventory);

  }

  public static List<Apple> filter(List<Apple> inventory, ApplePredicate p) {
    List<Apple> result = new ArrayList<>();
    for (Apple apple : inventory) {
      if (p.test(apple)) {
        result.add(apple);
      }
    }
    return result;
  }

  interface ApplePredicate {

    boolean test(Apple a);

  }

}
