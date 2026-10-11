package modernjavainaction.chap03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ExecuteAround {

  private static final String FILE = ExecuteAround.class.getResource("./data.txt").getFile();

  public static void main(String... args) throws IOException 
  {
    String result = processFileLimited();
    System.out.println(result);

    System.out.println("---");

    String oneLine = processFile((BufferedReader b) -> b.readLine());
    System.out.println(oneLine);

    String twoLines = processFile((BufferedReader b) -> b.readLine() + b.readLine());
    System.out.println(twoLines);
    
    String numLines = processFile((BufferedReader b) -> 
    { 
    	int count = 0;
        while (b.readLine() != null)
        {
        	count = count + 1;
        }
        return String.valueOf(count);
    });
    
    System.out.println("---");
    System.out.println("Total number of lines are " + numLines);
  }

  
  // Methods
  // We always use try catch to avoid file handling errors
  
  public static String processFileLimited() throws IOException 
  {
    try (BufferedReader br = new BufferedReader(new FileReader(FILE))) 
    {
    	//Reads a single line
      return br.readLine();
    }
  }

  public static String processFile(BufferedReaderProcessor p) throws IOException 
  {
    try (BufferedReader br = new BufferedReader(new FileReader(FILE))) 
    {
      return p.process(br);
    }
  }

  // Interface
  public interface BufferedReaderProcessor
  {

    String process(BufferedReader b) throws IOException;

  }

}
