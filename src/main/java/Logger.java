import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * Created by simonhamermesh on 2/8/16.
 */
public class Logger {

    public static void logger(String x, String y) throws Exception {

        PrintWriter out = new PrintWriter(new FileWriter(y, false), false);
        out.write(x);
        out.close();

    }
}
