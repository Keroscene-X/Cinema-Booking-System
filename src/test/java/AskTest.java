import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class AskTest {

    @Test
    public void log_test_1() throws IOException {
        Ask ask = new Ask();
        assertEquals(3,ask.log(2,"11111","12345"));
    }

    @Test
    public void log_test_2() throws IOException {
        Ask ask = new Ask();
        assertEquals(5,ask.log(2,"2222222","1234567"));
    }

    @Test
    public void log_test_3() throws IOException {
        Ask ask = new Ask();
        assertEquals(6,ask.log(2,"1111111","1234567"));
    }

    @Test
    public void log_test_4() throws IOException {
        Ask ask = new Ask();
        assertEquals(100,ask.log(2,"1234567","1234567"));
    }

    @Test
    public void log_test_5() throws IOException {
        Ask ask = new Ask();
        assertEquals(100,ask.log(2,"1234","1234"));
    }


    @Test
    void guest_1() {
        Ask ask = new Ask();
        ByteArrayInputStream in = new ByteArrayInputStream("N".getBytes());
        System.setIn(in);
        int count = ask.guest(0);
        assertEquals(1,count);
    }

    @Test
    void guest_2() {
        Ask ask = new Ask();
        ByteArrayInputStream in = new ByteArrayInputStream("Y".getBytes());
        System.setIn(in);
        int count = ask.guest(0);
        assertEquals(2,count);
    }

    @Test
    void search_1() {
        Ask ask = new Ask();
        ByteArrayInputStream in = new ByteArrayInputStream("L".getBytes());
        System.setIn(in);
        int count = ask.search(1);
        assertEquals(11,count);
    }

    @Test
    void search_2() {
        Ask ask = new Ask();
        ByteArrayInputStream in = new ByteArrayInputStream("N".getBytes());
        System.setIn(in);
        int count = ask.search(1);
        assertEquals(12,count);
    }

    @Test
    void search_3() {
        Ask ask = new Ask();
        ByteArrayInputStream in = new ByteArrayInputStream("C".getBytes());
        System.setIn(in);
        int count = ask.search(1);
        assertEquals(13,count);
    }

    @Test
    void search_4() {
        Ask ask = new Ask();
        ByteArrayInputStream in = new ByteArrayInputStream("NL".getBytes());
        System.setIn(in);
        int count = ask.search(1);
        assertEquals(14,count);
    }

    @Test
    void search_5() {
        Ask ask = new Ask();
        ByteArrayInputStream in = new ByteArrayInputStream("NC".getBytes());
        System.setIn(in);
        int count = ask.search(1);
        assertEquals(15,count);
    }

    @Test
    void search_6() {
        Ask ask = new Ask();
        ByteArrayInputStream in = new ByteArrayInputStream("LC".getBytes());
        System.setIn(in);
        int count = ask.search(1);
        assertEquals(16,count);
    }

    @Test
    void turnBack_1() {
        Ask ask = new Ask();
        ByteArrayInputStream in = new ByteArrayInputStream("O".getBytes());
        System.setIn(in);
        int count = ask.turnBack(11);
        assertEquals(1,count);
    }

    @Test
    void turnBack_2() {
        Ask ask = new Ask();
        ByteArrayInputStream in = new ByteArrayInputStream("B".getBytes());
        System.setIn(in);
        int count = ask.turnBack(11);
        assertEquals(2,count);
    }


}