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
        // System.out.println(dailyCaseCount.length);

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

        // Find the largest and smallest daily increase
        int largest = Integer.MIN_VALUE;
        int smallest = Integer.MAX_VALUE;
        int largestIndex = -1;
        int smallestIndex = -1;
        for(int j = 1; j < dailyCaseCount.length; j++){
          if( dailyCaseCount[j] > largest ){
            largest = dailyCaseCount[j];
            largestIndex = j;
          }
          if( dailyCaseCount[j] < smallest && dailyCaseCount[j] > 0){
            smallest = dailyCaseCount[j];
            smallestIndex = j;
          }
        }
        System.out.printf("The largest daily increase of %d occurred on day %d\n", largest, largestIndex);
        System.out.printf("The smallest daily increase of %d occurred on day %d\n", smallest, smallestIndex);

        // Find the weekly average increase
        int weekNum = 1;
        int sevenDaySum = 0;
        for (int k = 0; k < dailyCaseCount.length; k++){
          if(k % 7 == 0){
            if(k != 0){
              System.out.printf("Average daily increase for week %d is %.2f.\n", weekNum, ((double)sevenDaySum/7));
              weekNum ++;
            }
            sevenDaySum = 0;
          }
          sevenDaySum  = sevenDaySum + dailyCaseCount[k];
        }
    }
}