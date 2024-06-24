import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class staffActionTest {
    private JSONParser parser;
    private JSONArray original_movieDatabase;
    private JSONObject original_upcomingMovie;
    private JSONArray original_giftCards;
    private staffAction staff;

    @BeforeAll
    public void setup_jsonData() throws IOException, ParseException {
        parser = new JSONParser();
        original_movieDatabase = (JSONArray) parser.parse(new FileReader("src/main/resources/movieDatabase.json"));
        original_upcomingMovie = (JSONObject) parser.parse(new FileReader("src/main/resources/movies.json"));
        original_giftCards = (JSONArray) parser.parse(new FileReader("src/main/resources/gift_card.json"));

    }

    @BeforeEach
    public void init(){
        staff = new staffAction("src/main/resources/movieDatabase.json");
    }

    //normal case for publishing upcoming movies
    @Test
    public void publishUpcomingMovie_test_1(){
        staff.publishUpcomingMovie();
        File file = new File("src/main/resources/upcomingMovies.txt");
        assertTrue(file.exists());
    }

    //normal case for publishing upcoming movies
    @Test
    public void publishBookedMovies_test_1(){
        staff.publishBookedMovies();
        File file = new File("src/main/resources/bookedMovies.txt");
        assertTrue(file.exists());
    }

    //normal case for deleting a movie
    @Test
    public void deleteMovie_test_1(){
        String movieID = "002";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        staff.deleteMovie(movieID);
        assertEquals("delete Successfully!",outputStream.toString().replaceAll("\010*", "").replaceAll("\\*","").stripTrailing());
    }

    //abnormal case for deleting a movie
    @Test
    public void deleteMovie_test_2(){
        String movieID = "999";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        staff.deleteMovie(movieID);
        assertEquals("Cannot find the movie ID",outputStream.toString().replaceAll("\010*", "").replaceAll("\\*","").stripTrailing());
    }

    //normal case for inserting movie
    @Test
    public void insertMovie_test_1(){
        String id = "003",name = "a",c = "G",synopsis = "none",date = "25/05/2021",director = "shiji",cast = "shiji";
        ByteArrayInputStream inputStream = new ByteArrayInputStream((
                id + System.lineSeparator() + name + System.lineSeparator() + c + System.lineSeparator()
                        + synopsis + System.lineSeparator()+ date + System.lineSeparator()
                        + director + System.lineSeparator()+ cast + System.lineSeparator()).getBytes());
        System.setIn(inputStream);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        staff.insertMovie();
        assertEquals("Please enter movie ID:" + System.lineSeparator()+
                "Please enter movie's name:" + System.lineSeparator() +
                "Please enter movie's classification:" + System.lineSeparator() +
                "G (General) | PG (Parental Guidance) | M (Mature) | MA15+ (Mature Accompanies) | R18+ (Restricted)" + System.lineSeparator() +
                "Please enter movie's synopsis:" + System.lineSeparator() +
                "Please enter movie's release date (DD/MM/YYYY):" + System.lineSeparator() +
                "Please enter movie's director:" + System.lineSeparator() +
                "Please enter movie cast's name:" + System.lineSeparator() +
                "Movie added successfully!",outputStream.toString().strip().replaceAll("\010*", "").replaceAll("\\*","").stripTrailing());
    }

    //abnormal case for inserting movie
    @Test
    public void insertMovie_test_2(){
        String movieID = "001";
        ByteArrayInputStream inputStream = new ByteArrayInputStream((movieID + System.lineSeparator()).getBytes());
        System.setIn(inputStream);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        staff.insertMovie();
        assertEquals("Please enter movie ID:" + System.lineSeparator() +
                "The movie ID already exists",outputStream.toString().strip().replaceAll("\010*", "").replaceAll("\\*","").stripTrailing());
    }

    //normal case for modifying movie
    @Test
    public void modifyMovie(){
        String movieID = "001", c = "PG", date = "26/05/2099";
        ByteArrayInputStream inputStream = new ByteArrayInputStream((
                movieID + System.lineSeparator() + "class" + System.lineSeparator() + c + System.lineSeparator()
                        + "y" + System.lineSeparator()+ "date" + System.lineSeparator() + date + System.lineSeparator()
                        + "n" + System.lineSeparator()).getBytes());
        System.setIn(inputStream);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        staff.modifyMovie();
        try {
            Object obj = parser.parse(new FileReader("src/main/resources/movieDatabase.json"));
            JSONArray jsonArray = (JSONArray) obj;
            for(int i = 0; i < jsonArray.size();i++){
                JSONObject movieOBJ = (JSONObject) jsonArray.get(i);
                String id = (String) movieOBJ.get("movieID");
                if (movieID.equals(id)){
                    assertEquals(c,movieOBJ.get("class"));
                    assertEquals(date,movieOBJ.get("date"));
                }
            }

        } catch (ParseException e) {
            System.out.println("Invalid JSON file.");
        } catch (IOException e) {
            System.out.println("Error reading JSON file.");
        }
    }

    //abnormal case for modifying movie
    @Test
    public void modifyMovie_test_2(){
        String movieID = "009";
        ByteArrayInputStream inputStream = new ByteArrayInputStream((movieID + System.lineSeparator()).getBytes());
        System.setIn(inputStream);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        staff.modifyMovie();
        assertEquals("Please enter the movie ID:" + System.lineSeparator() +
                "Movie ID does not exist",outputStream.toString().strip().replaceAll("\010*", "").replaceAll("\\*","").stripTrailing());
    }

    @Test
    public void modifyMovie_test_3(){
        String movieID = "001";
        String cast = "shiji";
        ByteArrayInputStream inputStream = new ByteArrayInputStream((
                movieID + System.lineSeparator() + "c" + System.lineSeparator() +
                        "cast" + System.lineSeparator() + cast + System.lineSeparator() +
                        "n" + System.lineSeparator()).getBytes());
        System.setIn(inputStream);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        staff.modifyMovie();
        assertEquals("Please enter the movie ID:" + System.lineSeparator() +
                "Which detail information you want to modify:" + System.lineSeparator() +
                "name | class | synopsis | date | cast" + System.lineSeparator() +
                "Invalid input" + System.lineSeparator() +
                "Which detail information you want to modify:" + System.lineSeparator() +
                "name | class | synopsis | date | cast" + System.lineSeparator() +
                "Please enter new information:" + System.lineSeparator() +
                "Continue to modify? (Y/N)",outputStream.toString().strip().replaceAll("\010*", "").replaceAll("\\*","").stripTrailing());
    }

    //normal case for inserting upcoming movie
    @Test
    public void insertUpcomingMovie_test_1(){
        String movieID = "001";
        String date = "26/10/2021";
        String size = "Silver";
        ByteArrayInputStream inputStream = new ByteArrayInputStream((
                movieID + System.lineSeparator() + date + System.lineSeparator() +
                        size + System.lineSeparator()).getBytes());
        System.setIn(inputStream);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        staff.insertUpcomingMovie();
        assertEquals("Please enter movie ID:" + System.lineSeparator() +
                "Please enter movie's upcoming time (DD/MM/YYYY):" + System.lineSeparator() +
                "Please enter movie's screen size:" + System.lineSeparator() +
                "Gold | Silver | Bronze" + System.lineSeparator() +
                "Upcoming movie inserted successfully",outputStream.toString().strip().replaceAll("\010*", "").replaceAll("\\*","").stripTrailing());
    }

    //abnormal case for inserting upcoming movie
    @Test
    public void insertUpcomingMovie_test_2(){
        String movieID = "009";
        ByteArrayInputStream inputStream = new ByteArrayInputStream((
                movieID + System.lineSeparator()).getBytes());
        System.setIn(inputStream);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        staff.insertUpcomingMovie();
        assertEquals("Please enter movie ID:" + System.lineSeparator() +
                "Movie does not exist",outputStream.toString().strip().replaceAll("\010*", "").replaceAll("\\*","").stripTrailing());
    }

    //normal case for inserting gift card
    @Test
    public void giftCardInsertion_test_1(){
        String giftCard = "12345678945214GC";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        staff.giftCardInsertion(giftCard);
        assertEquals("Gift card added successfully!",outputStream.toString().strip().replaceAll("\010*", "").replaceAll("\\*","").stripTrailing());
    }

    //abnormal case for inserting gift card
    @Test
    public void giftCardInsertion_test_2(){
        String giftCard = "12345678945214GB";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        staff.giftCardInsertion(giftCard);
        assertEquals("Invalid gift card format",outputStream.toString().strip().replaceAll("\010*", "").replaceAll("\\*","").stripTrailing());
    }

    //abnormal case for inserting gift card
    @Test
    public void giftCardInsertion_test_3(){
        String giftCard = "12345678945214";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        staff.giftCardInsertion(giftCard);
        assertEquals("Invalid gift card format",outputStream.toString().strip().replaceAll("\010*", "").replaceAll("\\*","").stripTrailing());
    }

    //abnormal case for inserting gift card
    @Test
    public void giftCardInsertion_test_4(){
        String giftCard = "11111111111111GC";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        staff.giftCardInsertion(giftCard);
        assertEquals("The gift card number already exist",outputStream.toString().strip().replaceAll("\010*", "").replaceAll("\\*","").stripTrailing());
    }

    //normal case for deleting redeemed gift cards
    @Test
    public void deleteRedeemedGiftCards_test_1(){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        staff.deleteRedeemedGiftCards();
        assertEquals("Redeemed Gift card(s) deleted successfully!",outputStream.toString().strip().replaceAll("\010*", "").replaceAll("\\*","").stripTrailing());
    }

    //normal case for changing week 1 and week 2's
    @Test
    public void changeWeek_test(){
        assertTrue(staff.changeWeek());
    }


    @AfterEach
    public void rollBack_jsonData(){
        try{
            FileWriter file = new FileWriter("src/main/resources/movieDatabase.json");
            file.write(original_movieDatabase.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try{
            FileWriter file = new FileWriter("src/main/resources/movies.json");
            file.write(original_upcomingMovie.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try{
            FileWriter file = new FileWriter("src/main/resources/gift_card.json");
            file.write(original_giftCards.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try{
            File file = new File("src/main/resources/upcomingMovies.txt");
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try{
            File file = new File("src/main/resources/bookedMovies.txt");
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}