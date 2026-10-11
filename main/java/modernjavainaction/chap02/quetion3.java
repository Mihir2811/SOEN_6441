package modernjavainaction.chap02;
import java.util.Scanner;

public class quetion3 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter numbers for the list"+ "\n");
		
		String input = sc.nextLine();
		
		String[] split = input.split(" ");
		
		int sum = 0;
		
		for (int i=0; i<split.length; i++) {
			
			int number = Integer.parseInt(split[i]);
			
			if(i%2==0) {
				sum = sum + (number*3);
			}
			else {
				sum = sum + (number*1);
			}
		}
		
		if(sum%10==0) {
			System.out.println("Pass: "+ sum + "\n");
		}
		else {
			int needed = 10 - (sum%10);
			
			System.out.println("Required to add: "+ needed + "\n");
		}

	}

}
