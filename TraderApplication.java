package main;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Trader {
	// Private variables
    private final String name;
    private final String city;

    // Constructor
    public Trader(String name, String city) 
    {
        this.name = name;
        this.city = city;
    }

    // Getter methods
    
    public String getName() { return name; }
    public String getCity() { return city; }

    @Override
    public String toString() 
    {
        return "Trader:" + name + " in " + city;
    }
}

class Transaction {
	// Private variables
    private final Trader trader;
    private final int year;
    private final int value;

 // Constructor
    public Transaction(Trader trader, int year, int value) {
        this.trader = trader;
        this.year = year;
        this.value = value;
    }

    
 // Getter methods
    public Trader getTrader() { return trader; }
    public int getYear() { return year; }
    public int getValue() { return value; }

    @Override
    public String toString() 
    {
        return "{" + trader + ", year: " + year + ", value:" + value + "}";
    }
}

public class TradersApplication {
	
	public static void main(String[] args) {
		
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan  = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
            new Transaction(brian, 2011, 300),
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950)
        );
        
     // 1. Transactions in 2011, sorted by value (low to high)
        List<Transaction> ans1 =  transactions.stream()
        		.filter(i->i.getYear()==2011)
        		.sorted(comparing(Transaction::getValue))
        		.collect(toList());
        
        System.out.println("1. 2011 transactions: " +ans1);
        
     // 2. Unique cities where the traders work
        List<String> ans2 = transactions.stream()
        		.map(i->i.getTrader().getCity())
        		.distinct()
        		.collect(toList());
        
        System.out.println("2. Cities: " +ans2);
        
        // 3. Cambridge traders, sorted by name
        List<Trader> cambridgeTraders = transactions.stream()
                .map(Transaction::getTrader)
                .filter(tr -> tr.getCity().equals("Cambridge"))
                .distinct()
                .sorted(comparing(Trader::getName))
                .collect(toList());
        System.out.println("3. Cambridge traders: " + cambridgeTraders);
        
        // 4. All trader names, sorted and joined into one string
        String ans4 = transactions.stream()
                .map(t -> t.getTrader().getName())
                .distinct()
                .sorted()
                .collect(joining());
        
        System.out.println("4. All names: " + ans4 );
        
        // 5. Is any trader based in Milan?
        boolean ans5 = transactions.stream()
                .anyMatch(t -> t.getTrader()
                		.getCity().equals("Milan"));
        
        System.out.println("5. Any trader in Milan? " + ans5);
        
        // 6. Values of transactions made by Cambridge traders
        List<Integer> ans6 = transactions.stream()
                .filter(t -> "Cambridge".equals(t.getTrader().getCity()))
                .map(Transaction::getValue)
                .collect(toList());
        System.out.println("6. Cambridge transaction values: " + ans6);
        
        // 7. Highest transaction value
        Optional<Integer> highest = transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max);
        System.out.println("7. Highest value: " + highest.get());

        // 8. Transaction with the smallest value
        Optional<Transaction> smallest = transactions.stream()
                .min(comparing(Transaction::getValue));
        System.out.println("8. Smallest transaction: " + smallest.get());

	}
 }
