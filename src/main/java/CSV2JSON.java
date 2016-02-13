import org.json.CDL;
import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Created by simonhamermesh on 2/8/16.
 */
public class CSV2JSON {

    public static String csv2JSON_singleJSONobject (String x) throws Exception  {

        BufferedReader br = new BufferedReader(new FileReader(x));
        String line;
        String longLine = "";
        String firstLine = br.readLine();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(firstLine);
        stringBuilder.append("\n");

        while((line = br.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append("\n");
            longLine = stringBuilder.toString();
        }
        JSONArray jsonArray = CDL.toJSONArray(longLine);
        System.out.println(jsonArray.toString(2));
        br.close();

        return jsonArray.toString(2);
    }


    public static void csv2JSON_separateJSONobject (String x) throws Exception  {


        BufferedReader br = new BufferedReader(new FileReader(x));
        String line;
        String longLine;
        String firstLine = br.readLine();
        StringBuilder stringBuilder;


        while((line = br.readLine()) != null) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(firstLine);
            stringBuilder.append("\n");
            stringBuilder.append(line);
            longLine = stringBuilder.toString();
            JSONArray jsonArray = CDL.toJSONArray(longLine);
            System.out.println(jsonArray.toString(2));
        }

        br.close();

    }

}
