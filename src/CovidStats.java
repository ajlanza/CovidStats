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

        System.out.println(covid19.length);
        
    }
}