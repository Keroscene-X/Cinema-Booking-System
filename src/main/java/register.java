import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class register {

    public register() {
    }

    public boolean checkUsername(String username, String password) {
        // check size
        if (username.length() != 5 || password.length() != 5) {
            return false;
        }
        // check rename
        jsonRF jr = new jsonRF();
        JSONArray array;
        array = jr.readUser();

        for (int i = 0; i < array.size(); i++) {
            JSONObject temp = (JSONObject) array.get(i);
            if (username.equals(temp.get("account"))) {
                return false;
            }
        }
        return true;
    }

    public boolean saveAcounter(String username, String password){
        JSONObject path = null;
        JSONArray array = null;
        String config_path = "";
        JSONParser parser = new JSONParser();

        try {
            JSONObject object = new JSONObject();
            config_path = Files.readString(Paths.get("src/main/resources/customer.json"));
            path = (JSONObject) parser.parse(config_path);
            array = (JSONArray) path.get("user");
            object.put("savecard","false");
            object.put("password",password);
            object.put("account",username);
            array.add(object);
            FileWriter file = new FileWriter("src/main/resources/customer.json");
            file.write(path.toJSONString());
            file.flush();
            return true;
        } catch (IOException | ParseException e) {
            System.out.println("Error reading JSON file.");
            return false;
        }
    }
}
