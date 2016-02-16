
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by simonhamermesh on 2/7/16.
 */
public class App {
      @SuppressWarnings("rawtypes")
      String file1 = "/Users/simonhamermesh/Desktop/CSVreader/mini_DART_data.csv";
      String file2 = "/Users/simonhamermesh/Desktop/CSVreader/mini_DART_shapes.csv";

      public static void main(String[] args) throws Exception  {

          //System.out.println(CSVReader.readCSVwriteCSV("mini_DART_shapes.csv"));
          //System.out.println(CSV2JSON.csv2JSON_singleJSONObject("mini_DART_shapes.csv"));
          //System.out.println(CSV2JSON.csv2JSON_separateJSONObject("mini_DART_shapes.csv"));
          //CSV2GEOJSON.writeOutSeparateLineFeatures("outputfolder","mini_DART_shapes.csv", 1,3,4) ;


          /** TO USE CSV TO LOG OUT GEOJSON LINESTRING OBJECT ARRAY **/

          /** This part represents an experiment with Json parsing in Java, its not fun. Log out the desired result and deal with it in Javascript. **/
          JSONArray jsonArray = CSV2GEOJSON.makeJSONArrayfromCSV("mini_DART_shapes.csv",1,3,4);
          System.out.println("Whole linestring object array." + "\n" + jsonArray);
          //Logger.logger(jsonArray.toString(2),"outputfolder/LongJSON/totalSHAPEJSONARRAY.json");
          System.out.println("Linestring Array length" + "\n" + jsonArray.length());
          System.out.println("Index get from linestring array." + "\n" + jsonArray.get(2));

          //For each object in linestring array.
          for (int i = 0; i<jsonArray.length();i++ ){

              //Get linestring object, print it to String.
              System.out.println(jsonArray.get(i).toString());
              //Create new Json object using String. Print.
              JSONObject jsonProperties = new JSONObject(jsonArray.get(i).toString());
              System.out.println(jsonProperties.toString());
              //Search value by String key.
              JSONObject jsonShape_ID = new JSONObject(jsonProperties.getString("properties"));
              System.out.println(jsonProperties.getString("properties") );

              //Split string and isolate its value.
              String shapeValue = jsonProperties.getString("properties");
              String splitby = "\"";
              String[] shapeValueArrray = shapeValue.split(splitby);
              System.out.println(Integer.parseInt(shapeValueArrray[3]));

          }

          //Print coordinate array based on search for shapeid
          for(int i = 0; i<jsonArray.length();i++){

              JSONObject typeFeatureLinestring = new JSONObject(jsonArray.get(i).toString());
              String propertyValue = typeFeatureLinestring.getString("properties").split("\"")[3];
              if (Integer.parseInt(propertyValue) == 22592){
                  System.out.println();
                  System.out.println();
                  System.out.println(propertyValue);
                  System.out.println(jsonArray.get(i));
                  System.out.println(typeFeatureLinestring.getString("geometry"));
                  
                  System.out.println();
              }


          }


      }

    }

