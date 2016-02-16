import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * Created by simonhamermesh on 2/8/16.
 */
public class Logger {

    public static void logger(String outputString, String filePath) throws Exception {

        PrintWriter out = new PrintWriter(new FileWriter(filePath, false), false);
        out.write(outputString);
        out.close();

    }
}
