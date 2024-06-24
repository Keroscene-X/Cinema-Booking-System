import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class registerTest {
    private JSONObject original_customers;
    private JSONParser parser;
    @BeforeAll
    void backupData(){
        parser = new JSONParser();
        try{
            original_customers = (JSONObject) parser.parse(new FileReader("src/main/resources/customer.json"));
        }catch(IOException | ParseException e){
            e.printStackTrace();
        }
    }

    //abnormal for check customers
    @Test
    void checkusername_test_1(){
        register register = new register();
        assertFalse(register.checkUsername("123456","123456"));
    }

    //abnormal for check customers
    @Test
    void checkusername_test_2(){
        register register = new register();
        assertFalse(register.checkUsername("11111","12345"));
    }

    //normal for check customers
    @Test
    void checkusername_test_3(){
        register register = new register();
        assertTrue(register.checkUsername("11444","12345"));
    }

    //normal for check customers
    @Test
    void saveacounter_test_1(){
        register register = new register();
        assertTrue(register.saveAcounter("11333","12345"));
    }


    @AfterAll
    void rollbackData(){
        try{
            FileWriter file = new FileWriter("src/main/resources/customer.json");
            file.write(original_customers.toJSONString());
            file.flush();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
