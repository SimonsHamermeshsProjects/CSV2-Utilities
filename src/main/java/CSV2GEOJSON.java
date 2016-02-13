/**
 * Created by simonhamermesh on 2/12/16.
 */

import org.apache.commons.lang3.StringUtils;
import org.json.CDL;
import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InterruptedIOException;
import java.util.HashMap;


public class CSV2GEOJSON {

    public static HashMap<Integer,String> stringLibrary = new HashMap<Integer, String>();
    static {
        stringLibrary.put(1,"{" + "\n" + "\"type\": \"Feature\"," + "\n" + "\"properties\": {" + "\n" + "\"Line Property\": ");
        stringLibrary.put(2,"}," + "\n" + "\"geometry\": {" + "\n" + "\"type\":\"LineString\"," + "\n" + "\"coordinates\":[" + "\n");
        stringLibrary.put(3,"]}}");}

    public static String csv2LineCoordinates(String filePath,int propertiesColumn,int latColumn, int lonColumn) throws Exception {

        BufferedReader br = new BufferedReader(new FileReader(filePath));
        StringBuilder stringBuilder = new StringBuilder();

        //Account for index vs column number.
        propertiesColumn--;
        latColumn--;
        lonColumn--;

        String recordLine;
        String[] recordLineArray;
        String splitBy = ",";
        String firstLine = br.readLine();


        stringBuilder.append(stringLibrary.get(1));
        if (StringUtils.isNumeric(firstLine.split(splitBy)[propertiesColumn])) {

            stringBuilder.append(Integer.parseInt(firstLine.split(splitBy)[propertiesColumn]));

        } else {

            stringBuilder.append("\"").append(firstLine.split(splitBy)[propertiesColumn]).append("\"");

        }
        stringBuilder.append(stringLibrary.get(2));


        while((recordLine = br.readLine()) != null) {
            recordLineArray = recordLine.split(splitBy);
            stringBuilder.append("[").append(recordLineArray[lonColumn]).append(",").append("\n");
            stringBuilder.append(recordLineArray[latColumn]).append("\n").append("]").append(",").append("\n") ;
        }

        //Delete the last comma, the last char is \n, second to last is the comma.
        stringBuilder.deleteCharAt(stringBuilder.length()-2);

        stringBuilder.append(stringLibrary.get(3));

        return stringBuilder.toString();
    }
}
