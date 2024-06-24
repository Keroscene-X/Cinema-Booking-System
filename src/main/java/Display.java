import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Display {
    public Display(){}

    public void displayAll(int week){
        jsonRF j = new jsonRF();

        JSONArray list = (JSONArray) j.readMovies(week).get("movies");
        for(int i = 0;i < list.size();i++){
            JSONObject temp = (JSONObject) list.get(i);
            System.out.println("The Movie is: " + temp.get("name") + "|" + " Summary: " + temp.get("synopsis") + "|" + " Classification: " + temp.get("class") + "|"
                    + " Cast: " + temp.get("cast") + "|" + " Releasing date: " + temp.get("date") + "|" + " Upcoming dates: " + temp.get("upcoming") + "|" + " Screen size: " + temp.get("size"));
        }
    }

    public void displayAllWeek(){
        jsonRF j = new jsonRF();

        JSONArray list = j.readAllMovies();
        for(int i = 0;i < list.size();i++){
            JSONObject temp = (JSONObject) list.get(i);
            System.out.println("The Movie is: " + temp.get("name") + "|" + " movie ID:" + temp.get("movieID") + " | " + " Summary: " + temp.get("synopsis") + "|" + " Classification: " + temp.get("class") + "|"
                    + " Cast: " + temp.get("cast") + "|" + " Releasing date: " + temp.get("date"));
        }
    }

    public List<String> findLocation(String input){
        List<String> result = new ArrayList<String>();


        int count = 0;
        jsonRF js = new jsonRF();
        JSONArray obj = null;
        obj = js.readLocations();
        for(int i = 0;i < obj.size();i++){
            JSONObject temp = (JSONObject) obj.get(i);
            if(input.equals(temp.get("place"))){
                //System.out.println(temp.get("movies"));
                List<String> current = (List<String>) temp.get("movies");
                System.out.println("Movies List in this cinema: ");
                for(int j = 0;j < current.size();j++){
                    System.out.println(current.get(j));
                }
                result = current;

                break;
            }
            else{
                count++;
            }
        }
        if(count == obj.size()){
            System.out.println("Try again!");
        }

        return result;

    }

    public List<String> findClassification(int week,String input){
        List<String> result = new ArrayList<String>();

        int count = 0;
        jsonRF js = new jsonRF();
        JSONObject obj = null;
        obj = js.readMovies(week);
        JSONArray list = (JSONArray) js.readMovies(week).get("movies");

        for(int i = 0;i < list.size();i++){
            JSONObject temp = (JSONObject) list.get(i);
            if(temp.get("class").equals(input)){
                //System.out.println("Print test" + temp.get("class"));
                result.add((String) temp.get("name"));
            }
            else{
                count++;
            }
        }
        //System.out.println("SIze: " + result.size());
        if(count == list.size()){
            System.out.println("Try again!");
        }

        return result;

    }

    public int findName(int week,String input){
        int result = 0;

        int count = 0;

        result = 0;
        count = 0;
        JSONObject obj;
        jsonRF js = new jsonRF();
        obj = js.readMovies(week);
        JSONArray list = (JSONArray) obj.get("movies");

        for(int i = 0;i < list.size();i++){
            JSONObject temp = (JSONObject) list.get(i);
            if(input.equals(temp.get("name"))){
                System.out.println("The Movie is: " + temp.get("name") + "|" + " Summary: " + temp.get("synopsis") + "|" + " Classification: " + temp.get("class") + "|"
                        + " Cast: " + temp.get("cast") + "|" + " Releasing date: " + temp.get("date") + "|" + " Upcoming dates: " + temp.get("upcoming") + "|" + " Screen Type: " + temp.get("size"));
                result = 1;
                break;
            }
            else{
                count++;
            }
        }
        if(count == list.size() && !input.equals("E")){
            System.out.println("Try again.");
        }
        if(input.equals("E")){
            result = 2;

        }
        return result;
    }

    public int findName_Place(int week,String input,String input2){
        int result = 0;

        int count = 0;

        result = 0;
        count = 0;
        JSONObject obj;
        jsonRF js = new jsonRF();
        obj = js.readMovies(week);
        JSONArray list = (JSONArray) obj.get("movies");

        for(int i = 0;i < list.size();i++){
            JSONObject temp = (JSONObject) list.get(i);
            if(input.equals(temp.get("name"))){

                break;
            }
            else{
                count++;
            }
        }


        if(count == list.size() && !input.equals("E")){
            System.out.println("No selected film. Try again.");
            return 0;
        }
        List<String> film = new ArrayList<String>();

        count = 0;
        film = findLocation(input2);
        if(film.size() == 0){
            System.out.println("Selected film is not shown is this location. Try again.");
            return 0;
        }
        else{
            for(int i = 0;i < film.size();i++){
                if(input.equals(film.get(i))){
                    findName(week,input);
                    result = 1;
                    break;
                }
                else{
                    count ++;
                }
            }
            if(count == film.size()){
                System.out.println("This film is not shown in this location. Try again.");
            }
        }






        if(input.equals("E")){
            result = 2;

        }




        return result;
    }

    public int findName_Class(int week,String input,String input2){
        int result = 0;

        int count = 0;

        result = 0;
        count = 0;
        JSONObject obj;
        jsonRF js = new jsonRF();
        obj = js.readMovies(week);
        JSONArray list = (JSONArray) obj.get("movies");

        for(int i = 0;i < list.size();i++){
            JSONObject temp = (JSONObject) list.get(i);
            if(input.equals(temp.get("name"))){

                break;
            }
            else{
                count++;
            }
        }


        if(count == list.size() && !input.equals("E")){
            System.out.println("No selected film. Try again.");
            return 0;
        }
        List<String> film = new ArrayList<String>();

        count = 0;
        film = findClassification(week,input2);
        if(film.size() == 0){
            System.out.println("Selected film is not shown is this classification. Try again.");
            return 0;
        }
        else{
            for(int i = 0;i < film.size();i++){
                if(input.equals(film.get(i))){
                    findName(week,input);
                    result = 1;
                    break;
                }
                else{
                    count ++;
                }
            }
            if(count == film.size()){
                System.out.println("This film is not shown in this classification. Try again.");
            }
        }


        if(input.equals("E")){
            result = 2;

        }


        return result;
    }



    public int findMovie_location(int week,String input){

        int result = 0;
        int count = 0;

        result = findName(week,input);
        if(result == 1){
            count = 1;
        }
        else{
            count = 0;
        }
        return count;


    }


    public int findClass_Place(int week,String input,String input2){
        int result = 0;

        int count = 0;

        result = 0;
        count = 0;
        JSONObject obj;
        jsonRF js = new jsonRF();
        obj = js.readMovies(week);
        JSONArray list = (JSONArray) obj.get("movies");

        List<String> loca = new ArrayList<String>();
        loca = findLocation(input);
        List<String> clas = new ArrayList<String>();
        clas = findClassification(week,input2);

        for(int i = 0;i < loca.size();i++){
            for(int j = 0;j < clas.size();j++){
                if(loca.get(i).equals(clas.get(j))){
                    findName(week,loca.get(i));
                    result = 1;
                }
            }
        }



        return result;
    }
}
