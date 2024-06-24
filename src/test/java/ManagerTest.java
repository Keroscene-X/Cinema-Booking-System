import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ManagerTest {
    private JSONParser parser;
    private JSONObject original_staffs;
    private Manager manager;
    @BeforeAll
    public void backup_jsonData() throws IOException, ParseException {
        parser = new JSONParser();
        original_staffs = (JSONObject) parser.parse(new FileReader("src/main/resources/staff.json"));
    }

    @BeforeEach
    public void init(){
        manager = new Manager("src/main/resources/movieDatabase.json");
    }

    //normal case for adding staff
    @Test
    public void addStaff_test_1(){
        assertTrue(manager.addStaff("1234567","1234567"));
    }

    //abnormal case for adding staff
    @Test
    public void addStaff_test_2(){
        assertFalse(manager.addStaff("1111111","1234567"));
    }

    //normal case for deleting staff
    @Test
    public void removeStaff_test_1(){
        assertTrue(manager.removeStaff("1111111"));
    }

    //abnormal case for deleting staff
    @Test
    public void removeStaff_test_2(){
        assertFalse(manager.removeStaff("9999999"));
    }

    //normal case for deleting staff
    @Test
    public void listStaff_test_1(){
        assertTrue(manager.listStaff());
    }

    @AfterAll
    public void rollBack_jsonData(){
        try{
            FileWriter file = new FileWriter("src/main/resources/staff.json");
            file.write(original_staffs.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

