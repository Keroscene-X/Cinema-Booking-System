import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class jsonRFTest {

    @Test
    void readMovies() {
        jsonRF jr = new jsonRF();
        JSONObject obj = jr.readMovies(1);
        assertNotNull(obj);
    }

    @Test
    void readAllMovies() {
        jsonRF jr = new jsonRF();
        JSONArray obj = jr.readAllMovies();
        assertNotNull(obj);
    }

    @Test
    void readLocations() {
        jsonRF jr = new jsonRF();
        JSONArray obj = jr.readLocations();
        assertNotNull(obj);
    }

    @Test
    void readUser() {
        jsonRF jr = new jsonRF();
        JSONArray obj = jr.readUser();
        assertNotNull(obj);
    }

    @Test
    void readStaff() {
        jsonRF jr = new jsonRF();
        JSONArray obj = jr.readStaff();
        assertNotNull(obj);
    }
}