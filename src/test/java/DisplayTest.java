import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DisplayTest {

    @Test
    void displayAll() {
    }

    @Test
    void displayAllWeek() {
    }

    @Test
    void findLocation() {
        Display d = new Display();
        List<String> list = d.findLocation("movie1");
        assertNotNull(list);
    }

    @Test
    void findClassification() {
        Display d = new Display();
        List<String> list = d.findClassification(1,"movie1");
        assertNotNull(list);
    }

    @Test
    void findName() {
        Display d = new Display();
        int result = d.findName(1,"movie1");
        assertEquals(1,result);
    }

    @Test
    void findName_Place() {
        Display d = new Display();
        int result = d.findName_Place(1,"movie1","place1");
        assertEquals(1,result);
    }

    @Test
    void findName_Class() {
        Display d = new Display();
        int result = d.findName_Class(1,"movie1","PG");
        assertEquals(1,result);
    }

    @Test
    void findMovie_location() {
        Display d = new Display();
        int result = d.findMovie_location(1,"movie1");
        assertEquals(1,result);
    }

    @Test
    void findClass_Place() {
        Display d = new Display();
        int result = d.findClass_Place(1,"place1","PG");
        assertEquals(1,result);
    }
}