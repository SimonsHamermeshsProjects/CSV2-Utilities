import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Created by simonhamermesh on 2/8/16.
 */
public class CSVReader {


    public static String readCSV() throws Exception {

        String line;
        BufferedReader br = new BufferedReader(new FileReader("mini_DART_shapes.csv"));
        StringBuilder stringBuilder = new StringBuilder();

        while ((line = br.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append("\n");
        }
        br.close();
        return stringBuilder.toString();
    }


    public static void readTwoCSVs(String x, String y) throws Exception {
        String splitBy = ",";
        String line;
        BufferedReader[] bufferedReaders = new BufferedReader[2];
        BufferedReader br = new BufferedReader(new FileReader(x));
        BufferedReader br2 = new BufferedReader(new FileReader(y));
        bufferedReaders[0] = br;
        bufferedReaders[1] = br2;

        for (int i = 0; i < 6; i++) {
            while ((line = bufferedReaders[i%2].readLine()) != null) {
                String[] b = line.split(splitBy);
                for (String a : b) {
                    System.out.print(a + ":");
                }
                System.out.println();
            }
        }
        bufferedReaders[0].close();
        bufferedReaders[1].close();
    }


}
