import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Scanner;

public class Login {
    public Login(){}

    public int login_user(String username, String password){
        jsonRF jr = new jsonRF();
        JSONArray array;
        JSONArray array2;
        int result = 0;

        int counter = 0;
        int counter2 = 0;
        array = jr.readUser();
        array2 = jr.readStaff();


        counter = 0;
        counter2 = 0;


        if(username.length() == 5 && password.length() == 5){
            for(int i = 0;i < array.size();i++){
                JSONObject temp = (JSONObject) array.get(i);
                if(username.equals(temp.get("account")) && password.equals(temp.get("password"))){

                    result = 1;
                    break;
                }
                else{
                    counter++;
                }
            }
            if(counter == array.size()){
                result = 10;
            }
        }
        else if(username.length() == 7 && password.length() == 7){
            for(int i = 0;i < array2.size();i++){
                JSONObject temp = (JSONObject) array2.get(i);
                if(username.equals(temp.get("account")) && password.equals(temp.get("password")) && temp.get("boss").equals("false")){

                    result = 2;
                    break;
                }
                else if(username.equals(temp.get("account")) && password.equals(temp.get("password")) && temp.get("boss").equals("true")){

                    result = 3;
                    break;
                }
                else{
                    counter2++;
                }
            }
            if(counter2 == array2.size()){
                result = 10;
            }
        }
        else{
            result = 11;
        }

        return result;
    }
}
