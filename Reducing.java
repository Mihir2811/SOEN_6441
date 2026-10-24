package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Reducing {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        // Java 7 style: a loop with a variable that changes
        int sum7 = 0;
        for (int x : numbers) {
            sum7 += x;
        }
        System.out.println("Java 7 sum: " + sum7);

        // Java 8 style: reduce with a start value and a combining lambda
        int sum = numbers.stream().reduce(0, (a, b) -> a + b);
        System.out.println("Sum with reduce: " + sum);

        int product = numbers.stream().reduce(1, (a, b) -> a * b);
        System.out.println("Product: " + product);

        // Without a start value, the result is an Optional
        Optional<Integer> sumOpt = numbers.stream().reduce((a, b) -> a + b);
        System.out.println("Sum without start value: " + sumOpt.get());

        // Parallel version: same answer, work can be split across cores
        int parallelSum = numbers.parallelStream().reduce(0, Integer::sum);
        System.out.println("Parallel sum: " + parallelSum);

        // Maximum and minimum using method references
        int max = numbers.stream().reduce(0,Integer::max);
        int min = numbers.stream().reduce(0,Integer::min);
        System.out.println("Max: " + max + ", Min: " + min);

        // Empty stream without a start value: no result
        Optional<Integer> emptySum = new ArrayList<Integer>().stream().reduce(Integer::sum);
        System.out.println("Empty stream has a result? " + emptySum.isPresent());

        // Empty stream with a start value: returns the start value
        int emptyWithStart = new ArrayList<Integer>().stream().reduce(0, Integer::sum);
        System.out.println("Empty stream with start value 0: " + emptyWithStart);
    }
}
