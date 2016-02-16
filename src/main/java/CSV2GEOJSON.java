/**
 * Created by simonhamermesh on 2/12/16.
 */

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;


public class CSV2GEOJSON {

    private static String firstLine;
    private static String nextLine;
    private static String storedPropertyKey;
    private static String storedPropertyValue;
    private static String outputFolderPath;
    private static String outputFilePath;
    private static String lineProperty;
    private static String outputString;
    private static String inputFilePath;
    private static int propertiesColumn;
    private static int latColumn;
    private static int lonColumn;
    private static BufferedReader bufferedReader;
    private static HashMap<Integer,String> stringLibrary = new HashMap<Integer, String>();
    static {
        stringLibrary.put(1,"{" + "\n" + "\"type\": \"Feature\"," + "\n" +
                "\"properties\": {" + "\n");

        stringLibrary.put(2,"}," + "\n" + "\"geometry\": {" + "\n" + "\"type\":\"LineString\"," +
                "\n" + "\"coordinates\":[" + "\n");

        stringLibrary.put(3,"]}}");}


    /**
     * This method specifically logs out each separate json linestring feature to separate files.  This is used to create separate files for each object, as opposed to one
     * long array of all the objects together.  It also relies on the makeCoordinatesFeature() to parse through the CSV and create individual json objects.
     * **/
    public static void writeOutSeparateLineFeatures(String folderPath, String filePath,
                                                      int propsColumn,int inputLatColumn, int inputLonColumn)throws Exception {
        outputFolderPath  = folderPath;
        inputFilePath = filePath;
        bufferedReader = new BufferedReader(new FileReader(inputFilePath));
        //Account for index vs column number.
        propertiesColumn = propsColumn-1;
        latColumn = inputLatColumn-1;
        lonColumn = inputLonColumn-1;

        storeFirstLine();
        storePropertyKey();

        do{
            outputString = makeCoordinateFeatures();
            Logger.logger(outputString,outputFilePath);}
        while(nextLine != null);

    }

    /**
     *  This is the main method for taking a csv and formatting it into a geojson array string.  file path is the location of the csv to read.
     *  props column is the target column for the properties feature, this can be expanded to as many properties as desired and related strings to be stored
     *  in the StringLibrary HashMap.  Lat column and lon column are explanatory.
     *  First action is to open the buffered reader, adjust the column values to index values, and call the storeFirstLine(). This reads the first line and stores it separately
     *  for future references to header values.  The readNextLine() is separate then br.readLine() because the result is stored globally and referenced by multiple methods
     *  before reading the next line again. The property key is pulled out of the first line. Other property keys can pulled similarly.
     *
     *  This method specifically puts together an array of json objects gathered from a csv that would have many of them listed.  While there are lines to read, this will read them
     *  , make a LineString feature, and add it to the array. This array can then be returned for logging or displaying.
     *
     *  The makeCoordinateFeatures() is where each individual json linestring feature is produced.
     */
    public static JSONArray makeJSONArrayfromCSV(String filePath, int propsColumn,
                                                               int inputLatColumn, int inputLonColumn)throws Exception {

        inputFilePath = filePath;
        bufferedReader = new BufferedReader(new FileReader(inputFilePath));
        //Account for index vs column number.
        propertiesColumn = propsColumn - 1;
        latColumn = inputLatColumn - 1;
        lonColumn = inputLonColumn - 1;

        storeFirstLine();
        storePropertyKey();
        StringBuilder stringBuilder = new StringBuilder();
        JSONObject jsonObject;
        JSONArray jsonArray = new JSONArray();
        do {
            jsonObject = new JSONObject(makeCoordinateFeatures());
            jsonArray.put(jsonObject);
           }
        while (nextLine != null) ;

        //To send to JSON Object Array maker.
        return jsonArray  ;
    }

    private static void storeFirstLine() throws Exception {
        readNextLine();
        firstLine = nextLine;
    }

    private static void readNextLine() throws Exception {
       nextLine = bufferedReader.readLine();
    }

    private static void storePropertyKey(){
        storedPropertyKey = firstLine.split(",")[propertiesColumn];
    }


    /**
     * This is the meat of it all.  Before this method is called, another method will have initialized all pertinent fields and
     * read off the first line of the CSV to store keys. When this is called, the first action is to read the second line of the CSV.
     * If this line is not null, it will find the value corresponding to the property key and store it for reference. An output file path will be created in case of
     * need. This could be extracted to a separate method for more control over output destination. The lineProperty string is created using the makeLineProperty().
     * In this case it is a simple key:value pair and placed into the json object. This could be expanded to multiple properties, ie. color, thickness, other geojson properties.
     *
     * The json string begins to be prepared. The first string from the StringLibrary is appended, the Properties are inserted appropriately,
     * the geometry{type:LineString,coordinates:[.... } string is readied and then the coordinates are begun to be iterated through.
     *
     * While there is a next line and it matches the property value in its "set", the line will be split by comma and formatted as a [lon,lat] array.
     * This will finish by reading the next line and storing to determine whether to add another set of coordinates to this object.  If there are no more lines, then iterating is done.
     * If there is another line, but does not belong to the set, the loop breaks, the last comma is cut off, a cap string is added, and the entire json formatted string is returned.
     *
     * The final string is a complete GeoJson LineString formatted feature. This can be then logged out individually or appended to all strings for one large searchable object.
     * At this point once in a json string, Java ceases to be really useful. Object can be parsed and sorted at great difficulty or with dependencies. I recommend taking the output
     * over to JavaScript and dealing with it there.
     * **/
    public static String makeCoordinateFeatures() throws Exception{
        readNextLine();
        String recordLine = nextLine;
        StringBuilder stringBuilder = new StringBuilder();

        if(recordLine != null) {
            storePropertyValue(recordLine);
            //These use public returns because I may want to use those features again.
            outputFilePath = outputFolderPath + "/" + makeFileName();
            lineProperty = makeProperty();
            stringBuilder.append(stringLibrary.get(1));
            stringBuilder.append(lineProperty);
            stringBuilder.append(stringLibrary.get(2));
        }

        while((recordLine != null) && (recordLine.split(",")[propertiesColumn].equals(storedPropertyValue) )) {
            String[] recordLineArray = recordLine.split(",");
            stringBuilder.append("[").append(recordLineArray[lonColumn]).append(",").append("\n");
            stringBuilder.append(recordLineArray[latColumn]).append("\n").append("]").append(",").append("\n");
            readNextLine();
            recordLine = nextLine;
        }
        //Delete the last comma, the last char is \n, second to last is the comma. Then add the cap.
        stringBuilder.deleteCharAt(stringBuilder.length()-2).append(stringLibrary.get(3));

        return stringBuilder.toString();
    }

    private static void storePropertyValue(String recordLine){
        storedPropertyValue = recordLine.split(",")[propertiesColumn];
    }

    public static String makeFileName(){
        return storedPropertyKey + "_" + storedPropertyValue + ".geojson";
    }

    public static String makeProperty(){
        return "\"" + storedPropertyKey + "\"" + ":" + "\"" + storedPropertyValue + "\"";
    }
}
