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

public class staffAction {
    String filePath;
    public staffAction(String filePath){
        this.filePath = filePath;
    }

    public void publishUpcomingMovie(){
        jsonRF j = new jsonRF();
        JSONArray list = (JSONArray) j.readMovies(2).get("movies");
        try {
            PrintWriter writer = new PrintWriter("src/main/resources/upcomingMovies.txt", "UTF-8");
            for (int i = 0; i < list.size(); i++) {
                JSONObject temp = (JSONObject) list.get(i);
                writer.println("The Movie name is: " + temp.get("name") +"|" + "movie ID: " + temp.get("movieID") + "|" + " Summary: " + temp.get("synopsis") + "|" + " Classification: " + temp.get("class") + "|"
                        + " Cast: " + temp.get("cast") + "|" + " Releasing date: " + temp.get("date") + "|" + " Upcoming dates: " + temp.get("upcoming") + "|" + " Screen size: " + temp.get("size"));
            }
            writer.close();
        }catch(IOException e){
            System.out.println("Invalid file format");
        }
    }

    public void publishBookedMovies(){
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("src/main/resources/book.json"));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray list = (JSONArray) jsonObject.get("booked");
            PrintWriter writer = new PrintWriter("src/main/resources/bookedMovies.txt", "UTF-8");
            for (int i = 0; i < list.size(); i++) {
                JSONObject temp = (JSONObject) list.get(i);
                writer.println("The Movie name is: " + temp.get("moviename") + "|" + " Session: " + temp.get("session") + "|" + " Booked seats: " + temp.get("booked") + "|"
                        + " Available seats: " + temp.get("available"));
            }
            writer.close();
        }catch(IOException | ParseException e){
            System.out.println("Invalid file format");
        }
    }

    public boolean changeWeek(){
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("src/main/resources/movies.json"));
            JSONObject jsonObject = (JSONObject) obj;
            JSONObject week = (JSONObject) jsonObject.get("weeks");
            JSONObject week_1 = (JSONObject) week.get("1");
            JSONArray week_1_movie = (JSONArray) week_1.get("movies");
            JSONObject week_2 = (JSONObject) week.get("2");
            JSONArray week_2_movie = (JSONArray) week_2.get("movies");
            week_1_movie.clear();
            for(int i = 0; i < week_2_movie.size();i++){
                week_1_movie.add(week_2_movie.get(i));
            }
            week_2_movie.clear();
            FileWriter file = new FileWriter("src/main/resources/movies.json");
            file.write(jsonObject.toJSONString());
            file.flush();
            System.out.println("This week's movies changed successfully!");
            return true;
        }catch(IOException | ParseException e){
            System.out.println("Invalid file format");
            return false;
        }
    }

    public void deleteMovie(String movieID){
        JSONParser parser = new JSONParser();
        boolean deleted = false;
        try {
            Object obj = parser.parse(new FileReader(filePath));
            JSONArray jsonArray = (JSONArray) obj;
            for(int i = 0; i < jsonArray.size();i++){
                JSONObject movieOBJ = (JSONObject) jsonArray.get(i);
                String id = (String) movieOBJ.get("movieID");
                if (movieID.equals(id)){
                    jsonArray.remove(i);
                    System.out.println("delete Successfully!");
                    deleted = true;
                }
            }
            if (! deleted){
                System.out.println("Cannot find the movie ID");
                return;
            }
            FileWriter file = new FileWriter(filePath);
            file.write(jsonArray.toJSONString());
            file.flush();
        } catch (ParseException e) {
            System.out.println("Invalid JSON file.");
        } catch (IOException e) {
            System.out.println("Error reading JSON file.");
        }
    }

    public void insertMovie(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter movie ID:");
        String movieID = scanner.next();
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(filePath));
            JSONArray jsonArray = (JSONArray) obj;
            for(int i = 0; i < jsonArray.size();i++){
                JSONObject movieOBJ = (JSONObject) jsonArray.get(i);
                String id = (String) movieOBJ.get("movieID");
                if (movieID.equals(id)){
                    System.out.println("The movie ID already exists");
                    return;
                }
            }
        } catch (ParseException e) {
            System.out.println("Invalid JSON file.");
        } catch (IOException e) {
            System.out.println("Error reading JSON file.");
        }
        JSONObject movie = new JSONObject();
        System.out.println("Please enter movie's name:");
        String movieName = scanner.next();
        movie.put("name", movieName);
        System.out.println("Please enter movie's classification:");
        System.out.println("G (General) | PG (Parental Guidance) | M (Mature) | MA15+ (Mature Accompanies) | R18+ (Restricted)");
        String movieClass = scanner.next();
        movie.put("class", movieClass);
        System.out.println("Please enter movie's synopsis:");
        String movieSynopsis = scanner.next();
        movie.put("synopsis", movieSynopsis);
        System.out.println("Please enter movie's release date (DD/MM/YYYY):");
        String movieDate = scanner.next();
        movie.put("release date", movieDate);
        System.out.println("Please enter movie's director:");
        String movieDirector = scanner.next();
        movie.put("director", movieDirector);
        System.out.println("Please enter movie cast's name:");
        String movieCast = scanner.next();
        movie.put("cast", movieCast);
        movie.put("movieID", movieID);
        try {
            Object obj = parser.parse(new FileReader(filePath));
            JSONArray jsonArray = (JSONArray) obj;
            jsonArray.add(movie);
            FileWriter file = new FileWriter(filePath);
            file.write(jsonArray.toJSONString());
            file.flush();
            System.out.println("Movie added successfully!");
        }catch (ParseException e) {
            System.out.println("Invalid JSON file.");
        } catch (IOException e) {
            System.out.println("Error reading JSON file.");
        }
    }

    public void insertUpcomingMovie(){
        JSONObject movie = new JSONObject();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter movie ID:");
        String movieID = scanner.next();
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(filePath));
            JSONArray jsonArray = (JSONArray) obj;
            Object obj_2 = parser.parse(new FileReader("src/main/resources/movies.json"));
            JSONObject jsonObject = (JSONObject) obj_2;
            JSONObject moviesWeek = (JSONObject) jsonObject.get("weeks");
            JSONObject moviesWeek_1 = (JSONObject) moviesWeek.get("1");
            JSONArray movies = (JSONArray) moviesWeek_1.get("movies");
            int idCheck = 0;
            for(int i = 0; i < jsonArray.size();i++){
                JSONObject movieOBJ = (JSONObject) jsonArray.get(i);
                String id = (String) movieOBJ.get("movieID");
                if (!movieID.equals(id)){
                    idCheck += 1;
                }
                else{
                    movie = movieOBJ;
                }
            }
            if (idCheck == jsonArray.size()){
                System.out.println("Movie does not exist");
                return;
            }
            System.out.println("Please enter movie's upcoming time (DD/MM/YYYY):");
            String upcoming = scanner.next();
            movie.put("upcoming", upcoming);
            System.out.println("Please enter movie's screen size:");
            System.out.println("Gold | Silver | Bronze");
            String size = scanner.next();
            movie.put("size", size);
            movies.add(movie);
            FileWriter file = new FileWriter("src/main/resources/movies.json");
            file.write(jsonObject.toJSONString());
            file.flush();
            System.out.println("Upcoming movie inserted successfully");
        } catch (ParseException e) {
            System.out.println("Invalid JSON file.");
        } catch (IOException e) {
            System.out.println("Error reading JSON file.");
        }
    }

    public void modifyMovie(){
        JSONParser parser = new JSONParser();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the movie ID:");
        String movieID = scanner.next();
        try {
            Object obj = parser.parse(new FileReader(filePath));
            JSONArray jsonArray = (JSONArray) obj;
            for(int i = 0; i < jsonArray.size();i++){
                JSONObject movieOBJ = (JSONObject) jsonArray.get(i);
                String id = (String) movieOBJ.get("movieID");
                if (movieID.equals(id)){
                    boolean more = true;
                    while(more){
                        Set<String> movieKey = movieOBJ.keySet();
                        System.out.println("Which detail information you want to modify:");
                        System.out.println("name | class | synopsis | date | cast");
                        String key = scanner.next();
                        if (!movieKey.contains(key)){
                            System.out.println("Invalid input");
                            continue;
                        }
                        System.out.println("Please enter new information:");
                        if (key.equals("class")){
                            System.out.println("G (General) | PG (Parental Guidance) | M (Mature) | MA15+ (Mature Accompanies) | R18+ (Restricted)");
                        }
                        else if (key.equals("date")){
                            System.out.println("(DD/MM/YYYY)");
                        }
                        String info = scanner.next();
                        movieOBJ.replace(key,info);
                        System.out.println("Continue to modify? (Y/N)");
                        String con = scanner.next().toUpperCase();
                        if(con.equals("N")){
                            FileWriter file = new FileWriter(filePath);
                            file.write(jsonArray.toJSONString());
                            file.flush();
                            return;
                        }
                    }
                }
            }
        } catch (ParseException e) {
            System.out.println("Invalid JSON file.");
        } catch (IOException e) {
            System.out.println("Error reading JSON file.");
        }
        System.out.println("Movie ID does not exist");
    }

    public void giftCardInsertion(String cardNum){
        if (cardNum.length() != 16){
            System.out.println("Invalid gift card format");
            return;
        }
        if (!cardNum.endsWith("GC")){
            System.out.println("Invalid gift card format");
            return;
        }
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("src/main/resources/gift_card.json"));
            JSONArray jsonArray = (JSONArray) obj;
            for (int i = 0; i < jsonArray.size(); i++){
                JSONObject giftCard = (JSONObject) jsonArray.get(i);
                if (cardNum.equals(giftCard.get("number"))){
                    System.out.println("The gift card number already exist");
                    return;
                }
            }
            JSONObject newGiftCard = new JSONObject();
            newGiftCard.put("number", cardNum);
            newGiftCard.put("redeemed", "false");
            jsonArray.add(newGiftCard);
            FileWriter file = new FileWriter("src/main/resources/gift_card.json");
            file.write(jsonArray.toJSONString());
            file.flush();
            System.out.println("Gift card added successfully!");
        }catch (ParseException e) {
            System.out.println("Invalid JSON file.");
        } catch (IOException e) {
            System.out.println("Error reading JSON file.");
        }
    }

    public void deleteRedeemedGiftCards(){
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("src/main/resources/gift_card.json"));
            JSONArray jsonArray = (JSONArray) obj;
            JSONArray newGiftCard = new JSONArray();
            for (int i = 0; i < jsonArray.size(); i++){
                JSONObject giftCard = (JSONObject) jsonArray.get(i);
                if (giftCard.get("redeemed").equals("false")){
                    newGiftCard.add(giftCard);
                }
            }
            FileWriter file = new FileWriter("src/main/resources/gift_card.json");
            file.write(newGiftCard.toJSONString());
            file.flush();
            System.out.println("Redeemed Gift card(s) deleted successfully!");
        }catch (ParseException e) {
            System.out.println("Invalid JSON file.");
        } catch (IOException e) {
            System.out.println("Error reading JSON file.");
        }
    }
}