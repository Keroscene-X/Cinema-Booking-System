import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class jsonRF {
    public jsonRF(){
    }

    public JSONObject readMovies(int week){
        JSONObject path = null;
        String week_str;
        String config_path = "";
        JSONParser parser = new JSONParser();
        try {
            config_path = Files.readString(Paths.get("src/main/resources/movies.json"));
            path = (JSONObject) parser.parse(config_path);

        } catch (ParseException e) {
            System.out.println("Invalid JSON file.");
        } catch (IOException e) {
            System.out.println("Error reading JSON file.");
        }
        path = (JSONObject) path.get("weeks");
        week_str = String.valueOf(week);
        path = (JSONObject) path.get(week_str);

        return path;
    }

    public JSONArray readAllMovies(){
        JSONParser parser = new JSONParser();
        JSONArray array = new JSONArray();
        try {
            Object obj = parser.parse(new FileReader("src/main/resources/movieDatabase.json"));
            JSONArray jsonArray = (JSONArray) obj;
            array = jsonArray;
        } catch (ParseException e) {
            System.out.println("Invalid JSON file.");
        } catch (IOException e) {
            System.out.println("Error reading JSON file.");
        }
        return array;

    }

    public JSONArray readLocations(){
        JSONObject path = null;
        JSONArray array = null;
        String config_path = "";
        JSONParser parser = new JSONParser();
        try {
            config_path = Files.readString(Paths.get("src/main/resources/location.json"));
            path = (JSONObject) parser.parse(config_path);

        } catch (ParseException e) {
            System.out.println("Invalid JSON file.");
        } catch (IOException e) {
            System.out.println("Error reading JSON file.");
        }
        array = (JSONArray) path.get("locations");

        return array;
    }

    public JSONArray readUser(){
        JSONObject path = null;
        JSONArray array = null;
        String config_path = "";
        JSONParser parser = new JSONParser();
        try {
            config_path = Files.readString(Paths.get("src/main/resources/customer.json"));
            path = (JSONObject) parser.parse(config_path);

        } catch (ParseException e) {
            System.out.println("Invalid JSON file.");
        } catch (IOException e) {
            System.out.println("Error reading JSON file.");
        }
        array = (JSONArray) path.get("user");

        return array;
    }

    public JSONArray readStaff(){
        JSONObject path = null;
        JSONArray array = null;
        String config_path = "";
        JSONParser parser = new JSONParser();
        try {
            config_path = Files.readString(Paths.get("src/main/resources/staff.json"));
            path = (JSONObject) parser.parse(config_path);

        } catch (ParseException e) {
            System.out.println("Invalid JSON file.");
        } catch (IOException e) {
            System.out.println("Error reading JSON file.");
        }
        array = (JSONArray) path.get("staff");

        return array;
    }
    
}
