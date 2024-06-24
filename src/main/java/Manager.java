import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Set;
public class Manager extends staffAction{
    public Manager(String filePath) {
        super(filePath);
    }

    public boolean addStaff(String account, String password) {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("src/main/resources/staff.json"));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray jsonArray = (JSONArray) jsonObject.get("staff");
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject staffOBJ = (JSONObject) jsonArray.get(i);
                if (staffOBJ.get("account").equals(account)) {
                    System.out.println("Staff already exists");
                    return false;
                }
            }
            JSONObject newStaff = new JSONObject();
            newStaff.put("account", account);
            newStaff.put("password", password);
            newStaff.put("manager", "false");
            jsonArray.add(newStaff);
            FileWriter file = new FileWriter("src/main/resources/staff.json");
            file.write(jsonObject.toJSONString());
            file.flush();
            System.out.println("New staff added successfully");
            return true;
        } catch (ParseException e) {
            System.out.println("Invalid JSON file.");
        } catch (IOException e) {
            System.out.println("Error reading JSON file.");
        }
        return false;
    }

    public boolean removeStaff(String account) {
        JSONObject staff = null;
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("src/main/resources/staff.json"));
            JSONObject jsonObject = (JSONObject) obj;
            int staffCount = 0;
            JSONArray jsonArray = (JSONArray) jsonObject.get("staff");
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject staffOBJ = (JSONObject) jsonArray.get(i);
                if (staffOBJ.get("account").equals(account)) {
                    staff = staffOBJ;
                }
                else{
                    staffCount += 1;
                }
            }
            if (staffCount == jsonArray.size()){
                System.out.println("Staff does not exist");
                return false;
            }
            jsonArray.remove(staff);
            FileWriter file = new FileWriter("src/main/resources/staff.json");
            file.write(jsonObject.toJSONString());
            file.flush();
            System.out.println("Staff deleted successfully");
            return true;
        } catch (ParseException e) {
            System.out.println("Invalid JSON file.");
        } catch (IOException e) {
            System.out.println("Error reading JSON file.");
        }
        return false;
    }

    public boolean listStaff() {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("src/main/resources/staff.json"));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray jsonArray = (JSONArray) jsonObject.get("staff");
            for(int i =0; i < jsonArray.size();i++){
                JSONObject staff = (JSONObject) jsonArray.get(i);
                System.out.println(staff.get("account"));
            }
            return true;
        } catch (ParseException e) {
            System.out.println("Invalid JSON file.");
        } catch (IOException e) {
            System.out.println("Error reading JSON file.");
        }
        return false;
    }
}