package modernjavainaction.chap04;

import java.util.Arrays;
import java.util.List;
import static java.util.stream.Collectors.toList;

public class map{
    public static void main(String[] args) {

    	// Storing the number list
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
    
        System.out.println("Before");
        System.out.println(numbers);
        System.out.println("-------------");
        
        // Powering them with themselves
        List<Integer> squares = numbers.stream()
                .map(n -> n * n)
                .collect(toList());

        System.out.println("After");
        System.out.println(squares);
    }
}
