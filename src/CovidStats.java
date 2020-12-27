import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class CovidStats {
    public static void main(String[] args) {
        int[] covid19 = null;
        try {
            // read the data from the file
            Path path = Paths.get("data.txt");
            Stream<String> lines = Files.lines(path);

            //convert to array of int
            covid19 = lines.mapToInt(Integer::parseInt).toArray();

        } catch(IOException e) {
            e.printStackTrace();
        }

        int[] dailyCaseCount = new int[covid19.length];
        int previousDayValue = 0;
        for(int i = 0; i < covid19.length; i++){
          int dailyIncrease = covid19[i] - previousDayValue;
          dailyCaseCount[i] = dailyIncrease;
          previousDayValue = covid19[i];
        }
        for(int n: dailyCaseCount){
          System.out.println(n);
        }
        System.out.println(dailyCaseCount.length);

        int sum = 0;
        for( int n : dailyCaseCount ){
          sum = sum + n;
        }
        double average = (double)sum / (double)dailyCaseCount.length;
        System.out.printf("Average new cases per day %.2f\n", average);

        
        // How many days has it been since the number of cases doubled??
        int totalCount = covid19[covid19.length -1];
        int halfCount = totalCount / 2;

        int halfCountIndex = -1;
        int i = 0;
        while( i < covid19.length && halfCountIndex == -1){
          if( covid19[i] > halfCount) {
            halfCountIndex = i - 1;
          }
          i = i + 1;
        }

        int numDaysSinceDouble = (covid19.length - 1) - halfCountIndex;
        System.out.printf("The number of days since the number of cases doubled is: %d\n", numDaysSinceDouble);

        
    }
}