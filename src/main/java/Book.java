import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import java.util.Scanner;


public class Book {

    public Book(){}

    public int result = 0;
    public int number = 0;
    public String movie;

    public void chooseMovie() throws IOException {
//        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose the movie you want");
        boolean more = true;
        while(more){
            JSONObject obj;
            jsonRF js = new jsonRF();
            obj = js.readMovies(1);
            JSONArray array = (JSONArray) obj.get("movies");
//            long start = System.currentTimeMillis();Y
//            long end = start + 10*1000;
            String movieName = new timeoutCancel().Input();
//            String movieName = scanner.nextLine();
            if (movieName.equals("E")) {
                result = 1;
                return;
            }
            if (movieName.equals("timeout")) {
                result = 2;
                return;
            }
            for (int i = 0; i < array.size(); i++) {
                JSONObject temp = (JSONObject) array.get(i);
                if (movieName.equals((temp.get("name")))){
                    more = false;
                    movie = movieName;
                }
            }
            if (more == true){
                System.out.println("Wrong movie name, try again");
            }

        }
        result = 0;
        return;
    }

    public void studentNumber() throws IOException {
//        Scanner scanner = new Scanner(System.in);
        int studentnum = 0;
        System.out.println("Number of students:");
        boolean more = true;
        while(more){
            String studentNumber = new timeoutCancel().Input();
//            String studentNumber = scanner.nextLine();
            if (studentNumber.equals("E")) {
                result = 1;
                return;
            }
            if (studentNumber.equals("timeout")) {
                result = 2;
                return;
            }
            more = false;
            try{
                studentnum = Integer.parseInt(studentNumber);
            }catch(Exception e){
                System.out.println("Wrong input type, try again");
                more = true;
            }
            if (studentnum < 0){
                System.out.println("Wrong students number, try again");
                more = true;
            }
        }
        number = number + studentnum;
        result = 0;
        return;
    }

    public void childrenNumber() throws IOException {
//        Scanner scanner = new Scanner(System.in);
        int childrennum = 0;
        System.out.println("Number of children:");
        boolean more = true;
        while(more){
//            String childNumber = scanner.nextLine();
            String childNumber = new timeoutCancel().Input();
            if (childNumber.equals("E")) {
                result = 1;
                return;
            }
            if (childNumber.equals("timeout")) {
                result = 2;
                return;
            }
            more = false;
            try{
                childrennum = Integer.parseInt(childNumber);
            }catch(Exception e){
                System.out.println("Wrong input type, try again");
                more = true;
            }
            if (childrennum < 0){
                System.out.println("Wrong students number, try again");
                more = true;
            }
        }
        number = number + childrennum;
        result = 0;
        return;
    }

    public void adultNumber() throws IOException {
//        Scanner scanner = new Scanner(System.in);
        int adultnum = 0;
        boolean more = true;
        System.out.println("Number of adults:");
        while(more){
//            String adultNumber = scanner.nextLine();
            String adultNumber = new timeoutCancel().Input();
            if (adultNumber.equals("E")) {
                result = 1;
                return;
            }
            if (adultNumber.equals("timeout")) {
                result = 2;
                return;
            }
            more = false;
            try{
                adultnum = Integer.parseInt(adultNumber);
            }catch(Exception e){
                System.out.println("Wrong input type, try again");
                more = true;
            }
            if (adultnum < 0){
                System.out.println("Wrong students number, try again");
                more = true;
            }
        }
        number = number + adultnum;
        result = 0;
        return;
    }

    public void seniorNumber() throws IOException {
//        Scanner scanner = new Scanner(System.in);
        int seniornum = 0;
        System.out.println("Number of seniors/pensioners:");
        boolean more = true;
        while(more){
//            String seniorNumber = scanner.nextLine();
            String seniorNumber = new timeoutCancel().Input();
            if (seniorNumber.equals("E")) {
                result = 1;
                return;
            }
            if (seniorNumber.equals("timeout")) {
                result = 2;
                return;
            }
            more = false;
            try{
                seniornum = Integer.parseInt(seniorNumber);
            }catch(Exception e){
                System.out.println("Wrong input type, try again");
                more = true;
            }
            if (seniornum < 0){
                System.out.println("Wrong students number, try again");
                more = true;
            }
        }
        number = number + seniornum;
        result = 0;
        return;
    }

    public void studentSeats() throws IOException {
//        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose seats(front/middle/rear) for students:");
        boolean more = true;
        while(more){
//            String seat = scanner.nextLine();
            String seat = new timeoutCancel().Input();
            if (seat.equals("E")) {
                result = 1;
                return;
            }
            if (seat.equals("timeout")) {
                result = 2;
                return;
            }
            more = false;
            if (!seat.equals("front") && !seat.equals("middle") && !seat.equals("rear")){
                System.out.println("Wrong seats input, try again");
                more = true;
            }
        }
        result = 0;
        return;
    }

    public void childSeats() throws IOException {
//        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose seats(front/middle/rear) for children:");
        boolean more = true;
        while(more){
//            String seat = scanner.nextLine();
            String seat = new timeoutCancel().Input();
            if (seat.equals("E")) {
                result = 1;
                return;
            }
            if (seat.equals("timeout")) {
                result = 2;
                return;
            }
            more = false;
            if (!seat.equals("front") && !seat.equals("middle") && !seat.equals("rear")){
                System.out.println("Wrong seats input, try again");
                more = true;
            }
        }
        result = 0;
        return;
    }

    public void adultSeats() throws IOException {
//        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose seats(front/middle/rear) for adults:");
        boolean more = true;
        while(more){
//            String seat = scanner.nextLine();
            String seat = new timeoutCancel().Input();
            if (seat.equals("E")) {
                result = 1;
                return;
            }
            if (seat.equals("timeout")) {
                result = 2;
                return;
            }
            more = false;
            if (!seat.equals("front") && !seat.equals("middle") && !seat.equals("rear")){
                System.out.println("Wrong seats input, try again");
                more = true;
            }
        }
        result = 0;
        return;
    }

    public void seniorSeats() throws IOException {
//        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose seats(front/middle/rear) for seniors/pensioners:");
        boolean more = true;
        while(more){
//            String seat = scanner.nextLine();
            String seat = new timeoutCancel().Input();
            if (seat.equals("E")) {
                result = 1;
                return;
            }
            if (seat.equals("timeout")) {
                result = 2;
                return;
            }
            more = false;
            if (!seat.equals("front") && !seat.equals("middle") && !seat.equals("rear")){
                System.out.println("Wrong seats input, try again");
                more = true;
            }
        }
        result = 0;
        return;
    }


    public String generateNumber() {
        String temp = "0123456789";
        StringBuilder res = new StringBuilder();
        Random random = new Random();
        while (true) {
            int i = random.nextInt(10);
            char c = temp.charAt(i);
            if(res.indexOf(String.valueOf(c)) == -1){
                res.append(c);
            }
            if (res.length() == 6) {
                break;
            }
        }
        return res.toString();
    }


    public void cardpay(String username, String number){
//        Scanner scanner = new Scanner(System.in);
        boolean card = true;
        while (card){
            JSONParser parser = new JSONParser();
            try {
                Object obj = parser.parse(new FileReader("src/main/resources/credit_cards.json"));
                JSONArray jsonArray = (JSONArray) obj;
                for (int i = 0; i < jsonArray.size(); i++) {
                    JSONObject creditcard = (JSONObject) jsonArray.get(i);
                    if (creditcard.get("name").equals(username) && creditcard.get("number").equals(number)) {
                        System.out.println("Book successful");
                        card = false;
                        return;
                    }
                }
            }catch (ParseException e) {
                System.out.println("Invalid JSON file.");
            }catch (IOException e) {
                System.out.println("Error reading JSON file.");
            }
            if (card == true){
                System.out.println("Wrong name or number, try again");
                return;
            }
        }
    }

    public void giftcard() throws IOException {
//        Scanner scanner = new Scanner(System.in);
//        Scanner scanner = new Scanner(System.in);
        boolean giftcard = true;
        System.out.println("giftcard number: ");
        while(giftcard){
            JSONParser parser = new JSONParser();
            String giftcardnumber = new timeoutCancel().Input();
            if (giftcardnumber.equals("E")) {
                result = 1;
                return;
            }
            if (giftcardnumber.equals("timeout")) {
                result = 2;
                return;
            }
//            String giftcardnumber = scanner.nextLine();
            try {
                Object obj = parser.parse(new FileReader("src/main/resources/gift_card.json"));
                JSONArray jsonArray = (JSONArray) obj;
                for (int i = 0; i < jsonArray.size(); i++) {
                    JSONObject giftCard = (JSONObject) jsonArray.get(i);
                    if (giftcardnumber.equals(giftCard.get("number")) && giftCard.get("redeemed").equals("false")) {
                        giftCard.replace("redeemed", "true");
                        System.out.println("Book successful");
                        FileWriter file = new FileWriter("src/main/resources/gift_card.json");
                        file.write(jsonArray.toJSONString());
                        file.flush();
                        result = 0;
                        return;
                    }
                }

            } catch (ParseException e) {
                System.out.println("Invalid JSON file.");
            } catch (IOException e) {
                System.out.println("Error reading JSON file.");
            }
            if (giftcard == true){
                System.out.println("Wrong giftcardnumber or already redeemed,try again");
            }
        }
        result = 0;
        return;

    }

    public boolean savecard(String username){
        JSONObject path = null;
        JSONArray array = null;
        String config_path = "";
        JSONParser parser = new JSONParser();
        try {
            config_path = Files.readString(Paths.get("src/main/resources/customer.json"));
            path = (JSONObject) parser.parse(config_path);
            array = (JSONArray) path.get("user");
            for (int i = 0; i < array.size(); i++) {
                JSONObject user = (JSONObject) array.get(i);
                if (username.equals(user.get("account"))) {
                    user.replace("savecard", "true");
                    FileWriter file = new FileWriter("src/main/resources/customer.json");
                    file.write(path.toJSONString());
                    file.flush();
                    return true;
                }
            }

        } catch (ParseException e) {
            System.out.println("Invalid JSON file.");
        } catch (IOException e) {
            System.out.println("Error reading JSON file.");
        }
        return false;
    }

    public boolean issavedcard(String account) {
        JSONObject path = null;
        JSONArray array = null;
        String config_path = "";
        JSONParser parser = new JSONParser();

        try {
            config_path = Files.readString(Paths.get("src/main/resources/customer.json"));
            path = (JSONObject) parser.parse(config_path);
            array = (JSONArray) path.get("user");
            for (int i = 0; i < array.size(); i++) {
                JSONObject user = (JSONObject) array.get(i);
                JSONObject temp = (JSONObject) array.get(i);
                if (account.equals(temp.get("account")) && "true".equals(temp.get("savecard"))) {
                    return true;
                }
            }

        } catch (ParseException e) {
            System.out.println("Invalid JSON file.");
        } catch (IOException e) {
            System.out.println("Error reading JSON file.");
        }

        return false;
    }

}
