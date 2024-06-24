



import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.io.*;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class system{
    public static void main(String[] args) throws IOException {
        System.out.println("Hello.");

        Display d = new Display();
        Ask ask = new Ask();
        register register = new register();

        int count = 0;

        while(count == 0){
            d.displayAll(1);
            count = 0;
            count = ask.guest(count);

            String accountOne = "";
            /*if (count == 2){
                Scanner scanner = new Scanner(System.in);
                System.out.println("Username: ");
                String username = scanner.nextLine();
                count = ask.log(count,username);
                account = username;*/



            // 选择不login后， count等于1.
            while(count == 1){
                count = ask.search(count);


                // Count: 11 - 16: Filters.
                if(count == 11){
                    List<String> locations = new ArrayList<String>();
                    int counter = 0;
                    while(counter == 0){
                        Scanner scanner = new Scanner(System.in);
                        System.out.println("Enter the location.");
                        String input = scanner.nextLine();
                        locations = d.findLocation(input);
                        if(locations.size() != 0){
                            counter = 2;
                        }
                    }
                    while(counter == 2){
                        int result = 0;

                        Scanner sc = new Scanner(System.in);
                        System.out.println("Which one do you want to see for details?");
                        String input = sc.nextLine();
                        for(int i = 0;i < locations.size();i++){
                            if(input.equals(locations.get(i))){
                                result = 1;
                                break;
                            }
                            else{
                                result = 0;
                            }
                        }
                        if(result == 1){
                            counter = d.findMovie_location(1,input);
                            if(counter == 1){
                                counter = 1;
                            }
                            else if(counter == 0){
                                counter = 2;
                            }
                        }
                        else if(result == 0){
                            counter = 2;
                            System.out.println("Your input film is not shown in this location. Try again.");
                        }
                    }
                }



                else if(count == 12){
                    int counter = 0;
                    while(counter == 0){
                        d.displayAll(1);
                        Scanner scanner = new Scanner(System.in);
                        System.out.println("Enter the Film name.");
                        String input = scanner.nextLine();

                        if(d.findName(1, input) == 1){
                            counter = 1;
                        }
                        else{
                            counter = 0;
                        }
                    }
                }


                else if(count == 13){
                    List<String> classifications = new ArrayList<String>();
                    int counter = 0;
                    while(counter == 0){
                        System.out.println("Which classification you want to view? PG/G/M/M15/R18");
                        Scanner sc = new Scanner(System.in);
                        String type = sc.nextLine();
                        if(!type.equals("PG") && !type.equals("G") && !type.equals("M") && !type.equals("M15") && !type.equals("R18")){
                            System.out.println("Wrong input. Please Try Again.");

                        }
                        else{
                            System.out.println("Movies under " + type + " Classification: ");
                            classifications = d.findClassification(1,type);
                            if(classifications.size() != 0){
                                for(int i = 0;i < classifications.size();i++){
                                    System.out.println(classifications.get(i));
                                }
                                counter = 1;

                            }
                            else{
                                System.out.println("There is no film under this classification. Try again.");
                            }

                        }

                        while(counter == 1){
                            int counts = 0;
                            Scanner scanner = new Scanner(System.in);
                            System.out.println("Enter the Film name.");
                            String name = scanner.nextLine();
                            for(int i = 0;i < classifications.size();i++){
                                if(name.equals(classifications.get(i))){
                                    break;
                                }
                                else{
                                    counts++;
                                }
                            }
                            if(counts == classifications.size()){
                                System.out.println("There is no film under this classification. Try again.");
                            }
                            else{
                                d.findName(1,name);
                                break;
                            }
                        }

                    }



                }


                else if(count == 14){
                    //int counter = 0;
                    while(true){
                        Scanner scanner = new Scanner(System.in);
                        Scanner scanner2 = new Scanner(System.in);
                        System.out.println("Enter the Film name.");
                        String name = scanner.nextLine();
                        System.out.println("Enter the Location name.");
                        String place = scanner2.nextLine();
                        if(d.findName_Place(1,name,place) == 1){
                            break;
                        }
                    }
                }

                else if(count == 15){
                    //int counter = 0;
                    while(true){
                        Scanner scanner = new Scanner(System.in);
                        Scanner scanner2 = new Scanner(System.in);
                        System.out.println("Enter the Film name.");
                        String name = scanner.nextLine();
                        System.out.println("Enter the Classification.");
                        String clas = scanner2.nextLine();
                        if(d.findName_Class(1,name,clas) == 1){
                            break;
                        }
                    }
                }

                else if(count == 16){
                    //int counter = 0;
                    while(true){
                        Scanner scanner = new Scanner(System.in);
                        Scanner scanner2 = new Scanner(System.in);
                        System.out.println("Enter the Location.");
                        String location = scanner.nextLine();
                        System.out.println("Enter the Classification.");
                        String clas = scanner2.nextLine();
                        if(d.findClass_Place(1,location,clas) == 1){
                            break;
                        }
                    }
                }
                count = ask.turnBack(count);

            }

            if(count == 2){

                while(true){
                    Scanner sc = new Scanner(System.in);
                    System.out.println("Username: ");
                    String username = sc.nextLine();
                    if (username.equals("E")) {
                        count = 0;
                        break;
                    }
                    String password = PasswordField.readPassword("Password: ");
                    count = ask.log(count,username,password);
                    if(count == 3){
                        accountOne = username;
                        count = 3;
                        break;
                    }
                    else if(count == 5 || count == 6){
                        System.out.println("Welcome, Staff Member.");
                        break;
                    }
                    else{
                        count = 2;
                    }
                }

            }

            if (count == 17){
                Scanner sc = new Scanner(System.in);
                System.out.println("Registration system");
                String username = "";
                String password = "";

                while (true){
                    System.out.println("Please enter your username (max five digits): ");
                    username = sc.nextLine();
                    System.out.println("Please enter your password: ");
                    password = sc.nextLine();
                    if (register.checkUsername(username, password)){
                        break;
                    }else{
                        System.out.println("The user name or pass word is not available, please re-enter it.");
                        continue;
                    }
                }
                register.saveAcounter(username, password);
                count = 3;

            }

            while(count == 3){
                d.displayAll(1);
                System.out.println("Enter E to cancel");
                Scanner scanner = new Scanner(System.in);
                Book book = new Book();


                try {
                    book.chooseMovie();
                    if (book.result == 1) {
                        system.cancelReprot(ask.user, book.result);
                        count = 0;
                        break;
                    } else if (book.result == 2) {
                        system.cancelReprot(ask.user, book.result);
                        count = 0;
                        break;
                    }
                    book.childrenNumber();
                    if (book.result == 1) {
                        system.cancelReprot(ask.user, book.result);
                        count = 0;
                        break;
                    } else if (book.result == 2) {
                        system.cancelReprot(ask.user, book.result);
                        count = 0;
                        break;
                    }
                    book.childSeats();
                    if (book.result == 1) {
                        system.cancelReprot(ask.user, book.result);
                        count = 0;
                        break;
                    } else if (book.result == 2) {
                        system.cancelReprot(ask.user, book.result);
                        count = 0;
                        break;
                    }
                    book.studentNumber();
                    if (book.result == 1) {
                        system.cancelReprot(ask.user, book.result);
                        count = 0;
                        break;
                    } else if (book.result == 2) {
                        system.cancelReprot(ask.user, book.result);
                        count = 0;
                        break;
                    }
                    book.studentSeats();
                    if (book.result == 1) {
                        system.cancelReprot(ask.user, book.result);
                        count = 0;
                        break;
                    } else if (book.result == 2) {
                        system.cancelReprot(ask.user, book.result);
                        count = 0;
                        break;
                    }
                    book.adultNumber();
                    if (book.result == 1) {
                        system.cancelReprot(ask.user, book.result);
                        count = 0;
                        break;
                    } else if (book.result == 2) {
                        system.cancelReprot(ask.user, book.result);
                        count = 0;
                        break;
                    }
                    book.adultSeats();
                    if (book.result == 1) {
                        system.cancelReprot(ask.user, book.result);
                        count = 0;
                        break;
                    } else if (book.result == 2) {
                        system.cancelReprot(ask.user, book.result);
                        count = 0;
                        break;
                    }
                    book.seniorNumber();
                    if (book.result == 1) {
                        system.cancelReprot(ask.user, book.result);
                        count = 0;
                        break;
                    } else if (book.result == 2) {
                        system.cancelReprot(ask.user, book.result);
                        count = 0;
                        break;
                    }
                    book.seniorSeats();
                    if (book.result == 1) {
                        system.cancelReprot(ask.user, book.result);
                        count = 0;
                        break;
                    } else if (book.result == 2) {
                        system.cancelReprot(ask.user, book.result);
                        count = 0;
                        break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                System.out.println("Choose your payment method(credit card or gift card): ");
                while (true){
//                    String paymethod = scanner.nextLine();
                    String paymethod = null;
                    try {
                        paymethod = new timeoutCancel().Input();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (paymethod.equals("E")) {
                        system.cancelReprot(ask.user, 1);
                        count = 0;
                        break;
                    } else if (paymethod.equals("timeout")) {
                        system.cancelReprot(ask.user, 2);
                        count = 0;
                        break;
                    } else if (paymethod.equals("credit card")){
                        //saved card account
                        if(book.issavedcard(accountOne)){
                            System.out.println("You was bound a credit card");
                            break;
                        }else{
                            //pay
                            System.out.println("Name: ");
//                            String username = scanner.nextLine();
                            String username = null;
                            try {
                                username = new timeoutCancel().Input();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            if (username.equals("E")) {
                                system.cancelReprot(ask.user, 1);
                                count = 0;
                                break;
                            } else if(username.equals("timeout")) {
                                system.cancelReprot(ask.user, 2);
                                count = 0;
                                break;
                            }
                            String number = PasswordField.readPassword("Number: ");
                            book.cardpay(username,number);
                            //save card
                            while (true){
                                System.out.println("Do you need to bind a credit card? (Y/N)");
                                String binded = null;
                                try {
                                    binded = new timeoutCancel().Input();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                if (binded.equals("E")) {
                                    system.cancelReprot(ask.user, 1);
                                    count = 0;
                                    break;
                                } else if (binded.equals("timeout")) {
                                    system.cancelReprot(ask.user, 2);
                                    count = 0;
                                    break;
                                }
//                                String binded = scanner.nextLine();
                                if (binded.equals("Y")){
                                    book.savecard(accountOne);
                                    break;
                                }
                                else if (binded.equals("N")) {
                                    break;
                                }
                                else {
                                    System.out.println("Wrong input");
                                }
                            }
                            break;
                        }
                    }
                    else if (paymethod.equals("gift card")){
                        try {
                            book.giftcard();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if (book.result == 1) {
                            system.cancelReprot(ask.user, book.result);
                            count = 0;
                            break;
                        } else if (book.result == 2) {
                            system.cancelReprot(ask.user, book.result);
                            count = 0;
                            break;
                        }
                        break;
                    }

                    else{
                        System.out.println("Wrong payment method, try again: ");
                    }


                }
                if (count != 0) {
                    String receipt = book.generateNumber();
                    System.out.println("Your transaction ID: " +receipt);
                }

                JSONObject path = null;
                JSONArray array = null;
                String config_path = "";
                JSONParser parser = new JSONParser();
                try{
                    config_path = Files.readString(Paths.get("src/main/resources/book.json"));
                    path = (JSONObject) parser.parse(config_path);
                    array = (JSONArray) path.get("booked");
                    for (int i = 0; i < array.size(); i++){
                        JSONObject movie = (JSONObject) array.get(i);
                        if (book.movie.equals(movie.get("moviename"))){
                            // json String -> int
                            String bookString = (String) movie.get("booked");
                            int bookInt = Integer.parseInt(bookString);
                            String availableString = (String) movie.get("available");
                            int availableInt = Integer.parseInt(availableString);
                            // calculate numbers
                            bookInt += book.number;
                            availableInt -= book.number;
                            // int -> String
                            bookString = String.valueOf(bookInt);
                            availableString = String.valueOf(availableInt);

                            movie.replace("booked", bookString);
                            movie.replace("available", availableString);
                            FileWriter file = new FileWriter("src/main/resources/book.json");
                            file.write(path.toJSONString());
                            file.flush();
                        }
                    }
                }  catch (ParseException e) {
                    System.out.println("Invalid JSON file.");
                } catch (IOException e) {
                    System.out.println("Error reading JSON file.");
                }

                count = 0;
            }

            while(count == 5){
                d.displayAllWeek();
                staffAction staff = new staffAction("src/main/resources/movieDatabase.json");
                staff.publishUpcomingMovie();
                staff.publishBookedMovies();
                Scanner staffScanner = new Scanner(System.in);
                System.out.println("Please choose an action:");
                System.out.println("1. Insert Movie");
                System.out.println("2. Delete Movie");
                System.out.println("3. Modify Movie");
                System.out.println("4. Insert Gift Card");
                System.out.println("5. Delete redeemed gift cards");
                System.out.println("6. Insert upcoming movie");
                System.out.println("7. Update this week's movies");
                System.out.println("8. Exit");
                System.out.println("-----------------------------");
                int i = staffScanner.nextInt();
                if (i == 1){
                    staff.insertMovie();
                }
                else if (i == 2){
                    System.out.println("Please enter the movie ID you want to delete:");
                    String id = staffScanner.next();
                    staff.deleteMovie(id);
                }
                else if (i == 3){
                    staff.modifyMovie();
                }
                else if (i == 4){
                    System.out.println("Please enter new gift card number:");
                    String cardNum = staffScanner.next();
                    staff.giftCardInsertion(cardNum);
                }
                else if (i == 5){
                    staff.deleteRedeemedGiftCards();
                }
                else if (i ==6){
                    staff.insertUpcomingMovie();
                }
                else if(i == 7){
                    staff.changeWeek();
                }
                else if (i == 8){
                    System.exit(1);
                }
            }

            while(count == 6){
                d.displayAllWeek();
                Manager manager = new Manager("src/main/resources/movieDatabase.json");
                Scanner managerScanner = new Scanner(System.in);
                System.out.println("Please choose an action:");
                System.out.println("1. Insert Movie");
                System.out.println("2. Delete Movie");
                System.out.println("3. Modify Movie");
                System.out.println("4. Insert Gift Card");
                System.out.println("5. Delete redeemed gift cards");
                System.out.println("6. Insert upcoming movie");
                System.out.println("7. Add new staff");
                System.out.println("8. Remove old staff");
                System.out.println("9. Change this week's movies");
                System.out.println("10. Exit");
                System.out.println("-----------------------------");
                int i = managerScanner.nextInt();
                if (i == 1){
                    manager.insertMovie();
                }
                else if (i == 2){
                    System.out.println("Please enter the movie ID you want to delete:");
                    String id = managerScanner.next();
                    manager.deleteMovie(id);
                }
                else if (i == 3){
                    manager.modifyMovie();
                }
                else if (i == 4){
                    System.out.println("Please enter new gift card number:");
                    String cardNum = managerScanner.next();
                    manager.giftCardInsertion(cardNum);
                }
                else if (i == 5){
                    manager.deleteRedeemedGiftCards();
                }
                else if (i ==6){
                    manager.insertUpcomingMovie();
                }
                else if (i == 7){
                    System.out.println("Please enter new staff's account:");
                    String account = managerScanner.next();
                    System.out.println("Please enter new staff's password:");
                    String password = managerScanner.next();
                    manager.addStaff(account,password);
                }
                else if (i == 8){
                    manager.listStaff();
                    System.out.println("Please enter staff's account:");
                    String account = managerScanner.next();
                    manager.removeStaff(account);
                }
                else if(i == 9){
                    manager.changeWeek();
                }
                else if (i == 10){
                    System.exit(1);
                }
            }
        }
    }
    private static void cancelReprot(String user, int type) {
        try {
            RandomAccessFile randomFile = new RandomAccessFile("src/main/resources/cancelreport.txt", "rw");
            long fileLength = randomFile.length();
//            FileWriter file = new FileWriter("src/main/resources/cancelreport.txt");
//            PrintStream ps = new PrintStream(new FileOutputStream(file));
//            BufferedWriter bw = new BufferedWriter(file);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.println(df.format(System.currentTimeMillis()));
            if (type == 1) {
                randomFile.seek(fileLength);

                randomFile.writeBytes(user + " cancel at " + df.format(System.currentTimeMillis()) + " due to the manual cancellation (Exit)." + "\r\n");

                randomFile.close();
//                bw.write(user + " cancel at " + df.format(System.currentTimeMillis()) + "self");
//                bw.close();
//                file.close();
            } else {
                randomFile.seek(fileLength);

                randomFile.writeBytes(user + " cancel at " + df.format(System.currentTimeMillis()) + " due to the long periods of inactivity (Time out)." + "\r\n");

                randomFile.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
