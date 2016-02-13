/**
 * Created by simonhamermesh on 2/7/16.
 */
public class App {
      @SuppressWarnings("rawtypes")
    public static void main(String[] args) throws Exception  {

        //String file1 = "/Users/simonhamermesh/Desktop/CSVreader/DART_data.csv";
        //String file2 = "/Users/simonhamermesh/Desktop/CSVreader/DART_shapes.csv";
          System.out.println(CSVReader.readCSV());
        String file1 = "/Users/simonhamermesh/Desktop/CSVreader/mini_DART_data.csv";
        String file2 = "/Users/simonhamermesh/Desktop/CSVreader/mini_DART_shapes.csv";


          System.out.println(CSV2GEOJSON.csv2LineCoordinates("mini_DART_shapes.csv", 1,3,4) );

        //csv2json.csv2geoJSON_singleJSONobject(file1);
        //csv2json.csv2geoJSON_separateJSONobject(file1);
        //csvReader.read2Lines(file1 , file2 );


        //Logger.logger(csv2json.csv2geoJSON_singleJSONobject(file1),"mini_DART_data.json");
        //Logger.logger(csv2json.csv2geoJSON_singleJSONobject(file2),"mini_DART_shapes.json");


    }
}
