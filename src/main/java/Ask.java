import java.util.Scanner;

public class Ask {
    public String user;
    public Ask(){

    }

    public int log(int count, String username, String password) {

        while (count == 2) {

            System.out.println("Login system.");
            Login lg = new Login();

            if (password == null) {
                return count;
            }
            int result = lg.login_user(username, password);
            System.out.println(result);
            // User.
            if (result == 1) {
                count = 3;
            }
            // Staff
            else if (result == 2) {
                count = 5;
            }
            // Manager
            else if(result == 3){
                count = 6;
            }
            else if(result == 10){
                System.out.println("Wrong inputs. Try Again.");
                count = 100;
            }
            else if(result == 11){
                System.out.println("Invalid inputs. Try Again.");
                count = 100;
            }
        }
        user = username;
        return count;
    }

    public int guest(int count){
        while(count == 0){
            System.out.println("Do you want to login or register? Y/N/R");
            Scanner scanner = new Scanner(System.in);
            String login = scanner.nextLine();
            if(login.equals("N")){
                count = 1;
                //count = search(count);
            }
            else if(login.equals("Y")){
                count = 2;
                break;
            }
            else if(login.equals("R")){
                System.out.println("2");
                count = 17;
            }
            else{
                System.out.println("Wrong input. Try again.");
                count = 0;
            }
        }
        return count;
    }

    public int search(int count){
        Display d = new Display();

        while(count == 1){
            System.out.println("Do you want search by Name or Location or Classification or combination?(Enter N for name/L for location/C for classification/ NL:combination for name and location/ NC:combination for name and classification/ LC:combination for location and classification)");
            Scanner scanner1 = new Scanner(System.in);
            String search = scanner1.nextLine();
            if(search.equals("L")){
                //List<String> result = d.findLocation();
                //d.findMovie_location(1);
                count = 11;
            }
            else if(search.equals("N")){
                //d.findName(1);
                count = 12;
            }
            else if(search.equals("C")){
                //d.findMovie_class(1);
                count = 13;
            }
            else if(search.equals("NL")){
                count = 14;
            }
            else if(search.equals("NC")){
                count = 15;
            }
            else if(search.equals("LC")){
                count = 16;
            }
            else{
                System.out.println("Wrong input. Try again.");
            }
        }
        return count;
    }

    public int turnBack(int count){
        while(count > 10){

            Scanner scanner2 = new Scanner(System.in);
            System.out.println("See other options or book it? O/B");
            String options = scanner2.nextLine();
            if(options.equals("O")){
                count = 1;
                break;
            }
            else if(options.equals("B")){
                System.out.println("Please login.");
                count = 2;
                break;
            }
            else{
                System.out.println("Wrong input. Try again.");
            }
        }
        return count;
    }


}
