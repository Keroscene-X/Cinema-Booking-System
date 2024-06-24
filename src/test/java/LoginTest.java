import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.*;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LoginTest {
    private JSONParser parser;
    private JSONArray original_movieDatabase;
    private JSONObject original_upcomingMovie;
    private JSONArray original_giftCards;
    private Login login;

    @BeforeAll
    public void setup_jsonData() throws IOException, ParseException {
        parser = new JSONParser();
        original_movieDatabase = (JSONArray) parser.parse(new FileReader("src/main/resources/movieDatabase.json"));
        original_upcomingMovie = (JSONObject) parser.parse(new FileReader("src/main/resources/movies.json"));
        original_giftCards = (JSONArray) parser.parse(new FileReader("src/main/resources/gift_card.json"));
    }
    @BeforeEach
    public void init() {
        login = new Login();
    }
    @Test
    public void login_userTest() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        int result = login.login_user("11111", "12345");
        assertEquals(1, result);
    }
    @Test
    public void login_staffTest() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        int result = login.login_user("1111111", "1234567");
        assertEquals(3, result);
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
    }
}
