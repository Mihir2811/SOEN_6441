package modernjavainaction.chap04;

import java.util.Arrays;
import java.util.List;
import static java.util.stream.Collectors.toList;

public class flatmap {
    public static void main(String[] args) {

    	
    	// 2 Array List
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        System.out.println("Array list 1");
        System.out.println(numbers1);
        
        List<Integer> numbers2 = Arrays.asList(3, 4);
        System.out.println("Array list 2");
        System.out.println(numbers2 + "\n");

        // Streaming them to find matching pair from numbers1 to numbers2
        
        List<int[]> pairs = numbers1.stream()
        		
                .flatMap(a -> numbers2.stream()
                .map(b -> new int[]{a, b}))
                .collect(toList());

		System.out.println("The pairs are ");
		
        pairs.forEach(pair ->
                System.out.println("(" + pair[0] + ", " + pair[1] + ")"));
        		
        System.out.println("------------------------------------------");
        
        // -----------------------------------------------------------------------------------
        
        // numbers whose sum is divisible by 3
        
        List<Integer> numbers3 = Arrays.asList(1, 2, 3);
        List<Integer> numbers4 = Arrays.asList(3, 4);

        List<int[]> pairs1 = numbers3.stream()
                .flatMap(a -> numbers4.stream()
                .filter(b -> (a + b) % 3 == 0)
                .map(b -> new int[]{a, b}))
                .collect(toList());

        System.out.println("The pairs whose sum are divisible by 3 are ");
        
        pairs1.forEach(pair1 -> System.out.println("(" + pair1[0] + ", " + pair1[1] + ")"));
    }
}
