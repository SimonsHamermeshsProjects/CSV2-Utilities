import java.io.BufferedReader;
import java.io.FileReader;

/**
 * CSV READER - Contains methods for reading from a CSV and returning a single string containing the String representation
 * of that CSV.
 * Created by simonhamermesh on 2/8/16.
 */
public class CSVReader {

    /**
     * readCSVwriteCSV - Takes as an argument the file path where CSV is stored. Returns CSV as a single String.
     */
    public static String readCSVwriteCSV(String filePath) throws Exception {

        String line;
        StringBuilder stringBuilder = new StringBuilder();

        BufferedReader br = new BufferedReader(new FileReader(filePath));


        while ((line = br.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append("\n");
        }
        br.close();

        return stringBuilder.toString();
    }

    /**
     * readTwoCSVs - Takes two file paths as arguments. Returns single string with multiple CSVs.
     * Future development to accessorize will include taking arguments to define patterns of
     * interspersion and serialization.
     */
    public static String readTwoCSVs(String filePath1, String filePath2) throws Exception {
        String splitBy = ",";
        String line;
        StringBuilder stringBuilder = new StringBuilder();

        BufferedReader br1 = new BufferedReader(new FileReader(filePath1));
        BufferedReader br2 = new BufferedReader(new FileReader(filePath2));
        BufferedReader[] bufferedReaders = new BufferedReader[2];
        bufferedReaders[0] = br1;
        bufferedReaders[1] = br2;

        for (int i = 0; i < 6; i++) {
            while ((line = bufferedReaders[i%2].readLine()) != null) {
                String[] b = line.split(splitBy);
                for (String a : b) {
                    stringBuilder.append(a).append(",");
                }
                stringBuilder.append("\n");
            }
        }
        bufferedReaders[0].close();
        bufferedReaders[1].close();

        return stringBuilder.toString();
    }


}
