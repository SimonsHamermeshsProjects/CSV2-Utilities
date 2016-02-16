import org.json.CDL;
import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * CSV2JSON - Contains methods for reading from CSVs and converting to JSONArray objects.
 * Created by simonhamermesh on 2/8/16.
 */
public class CSV2JSON {

    /**
     *csv2JSON_singleJSONObject - Takes a file path as an argument. Returns a JSONArray from CSV.
     */
    public static String csv2JSON_singleJSONObject (String x) throws Exception  {

        BufferedReader br = new BufferedReader(new FileReader(x));
        String line;
        String firstLine = br.readLine();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(firstLine).append("\n");

        //Reads in CSV and spits out CSV in String format.
        while((line = br.readLine()) != null) {
            stringBuilder.append(line).append("\n");
        }
        br.close();

        System.out.println(stringBuilder.toString());
        //Converts JSON formatted array of Strings to JSON Array.
        JSONArray jsonArray = CDL.toJSONArray(stringBuilder.toString());

        return jsonArray.toString(2);
    }

    /**
     *csv2JSON_separateJSONObject - Takes a file path as an argument. Returns a separate JSONArray
     * for each record in CSV.
     */
    public static String csv2JSON_separateJSONObject (String x) throws Exception  {


        BufferedReader br = new BufferedReader(new FileReader(x));
        String line;
        String longLine;
        String firstLine = br.readLine();
        StringBuilder stringBuilderEachJSONOBject;
        StringBuilder stringBuilderAllJSONObjects = new StringBuilder();


        while((line = br.readLine()) != null) {
            stringBuilderEachJSONOBject = new StringBuilder();
            stringBuilderEachJSONOBject.append(firstLine);
            stringBuilderEachJSONOBject.append("\n");
            stringBuilderEachJSONOBject.append(line);
            JSONArray jsonArray = CDL.toJSONArray(stringBuilderEachJSONOBject.toString());
            stringBuilderAllJSONObjects.append(jsonArray.toString(2)).append("\n");
        }
        br.close();

        return stringBuilderAllJSONObjects.toString();
    }

    public static String takeJSONsPrintJSONArray (String x) throws Exception  {

        csv2JSON_separateJSONObject(x);


        return "";
    }

}
