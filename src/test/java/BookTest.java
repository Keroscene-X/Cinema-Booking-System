import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import java.util.HashSet;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BookTest {
    private JSONParser parser;
    private JSONObject original_customer;
    private JSONArray original_giftCards;

    @BeforeAll
    public void setup_jsonData() throws IOException, ParseException {
        parser = new JSONParser();
        original_customer = (JSONObject) parser.parse(new FileReader("src/main/resources/customer.json"));
        original_giftCards = (JSONArray) parser.parse(new FileReader("src/main/resources/gift_card.json"));

    }

    @Test
    void issavedcard_test_1(){
        Book book = new Book();
        assertTrue(book.issavedcard("11111"));
    }

    @Test
    void issavedcard_test_2(){
        Book book = new Book();
        assertTrue(book.issavedcard("22222"));
    }

    @Test
    void savecard_test_1(){
        Book book = new Book();
        assertTrue(book.savecard("22222"));
    }

    @Test
    public void cardpay_test_1(){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        Book book = new Book();
        book.cardpay("Charles","40691");
        assertEquals("Book successful",outputStream.toString().strip().replaceAll("\010*", "").replaceAll("\\*","").stripTrailing());
    }

    @Test
    public void cardpay_test_2(){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        Book book = new Book();
        book.cardpay("Charles","40690");
        assertEquals("Wrong name or number, try again",outputStream.toString().strip().replaceAll("\010*", "").replaceAll("\\*","").stripTrailing());
    }

    @Test
    public void testGenerate() {
        Book book = new Book();
        String number = book.generateNumber();
        HashSet<Character> set = new HashSet<>();
        for(int i = 0 ; i < number.length() ; i++) {
            set.add(number.charAt(i));
        }
        assertEquals(6,set.size());
    }

    @Test
    public void studentseats_test_1(){
        try{
            String input = "E";
            ByteArrayInputStream inputStream = new ByteArrayInputStream((
                    input + System.lineSeparator()).getBytes());
            System.setIn(inputStream);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));
            Book book = new Book();
            book.studentSeats();
            assertEquals("Choose seats(front/middle/rear) for students:", outputStream.toString().strip());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Test
    public void studentseats_test_2(){
        try{
            String input = "timeout";
            ByteArrayInputStream inputStream = new ByteArrayInputStream((
                    input + System.lineSeparator()).getBytes());
            System.setIn(inputStream);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));
            Book book = new Book();
            book.studentSeats();
            assertEquals("Choose seats(front/middle/rear) for students:", outputStream.toString().strip());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Test
    public void childseats_test_1(){
        try{
            String input = "E";
            ByteArrayInputStream inputStream = new ByteArrayInputStream((
                    input + System.lineSeparator()).getBytes());
            System.setIn(inputStream);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));
            Book book = new Book();
            book.childSeats();
            assertEquals("Choose seats(front/middle/rear) for children:", outputStream.toString().strip());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Test
    public void childseats_test_2(){
        try{
            String input = "timeout";
            ByteArrayInputStream inputStream = new ByteArrayInputStream((
                    input + System.lineSeparator()).getBytes());
            System.setIn(inputStream);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));
            Book book = new Book();
            book.childSeats();
            assertEquals("Choose seats(front/middle/rear) for children:", outputStream.toString().strip());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Test
    public void adultseats_test_1(){
        try{
            String input = "E";
            ByteArrayInputStream inputStream = new ByteArrayInputStream((
                    input + System.lineSeparator()).getBytes());
            System.setIn(inputStream);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));
            Book book = new Book();
            book.adultSeats();
            assertEquals("Choose seats(front/middle/rear) for adults:", outputStream.toString().strip());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Test
    public void adultseats_test_2(){
        try{
            String input = "timeout";
            ByteArrayInputStream inputStream = new ByteArrayInputStream((
                    input + System.lineSeparator()).getBytes());
            System.setIn(inputStream);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));
            Book book = new Book();
            book.adultSeats();
            assertEquals("Choose seats(front/middle/rear) for adults:", outputStream.toString().strip());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Test
    public void seniorseats_test_1(){
        try{
            String input = "E";
            ByteArrayInputStream inputStream = new ByteArrayInputStream((
                    input + System.lineSeparator()).getBytes());
            System.setIn(inputStream);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));
            Book book = new Book();
            book.seniorSeats();
            assertEquals("Choose seats(front/middle/rear) for seniors/pensioners:", outputStream.toString().strip());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Test
    public void seniorseats_test_2(){
        try{
            String input = "timeout";
            ByteArrayInputStream inputStream = new ByteArrayInputStream((
                    input + System.lineSeparator()).getBytes());
            System.setIn(inputStream);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));
            Book book = new Book();
            book.seniorSeats();
            assertEquals("Choose seats(front/middle/rear) for seniors/pensioners:", outputStream.toString().strip());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Test
    public void studedntnumber_test_1(){
        try{
            String input = "E";
            ByteArrayInputStream inputStream = new ByteArrayInputStream((
                    input + System.lineSeparator()).getBytes());
            System.setIn(inputStream);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));
            Book book = new Book();
            book.studentNumber();
            assertEquals("Number of students:", outputStream.toString().strip());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Test
    public void studentnumber_test_2(){
        try{
            String input = "timeout";
            ByteArrayInputStream inputStream = new ByteArrayInputStream((
                    input + System.lineSeparator()).getBytes());
            System.setIn(inputStream);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));
            Book book = new Book();
            book.studentNumber();
            assertEquals("Number of students:", outputStream.toString().strip());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Test
    public void childrennumber_test_1(){
        try{
            String input = "E";
            ByteArrayInputStream inputStream = new ByteArrayInputStream((
                    input + System.lineSeparator()).getBytes());
            System.setIn(inputStream);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));
            Book book = new Book();
            book.childrenNumber();
            assertEquals("Number of children:", outputStream.toString().strip());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Test
    public void childrennumber_test_2(){
        try{
            String input = "timeout";
            ByteArrayInputStream inputStream = new ByteArrayInputStream((
                    input + System.lineSeparator()).getBytes());
            System.setIn(inputStream);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));
            Book book = new Book();
            book.childrenNumber();
            assertEquals("Number of children:", outputStream.toString().strip());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Test
    public void adultnumber_test_1(){
        try{
            String input = "E";
            ByteArrayInputStream inputStream = new ByteArrayInputStream((
                    input + System.lineSeparator()).getBytes());
            System.setIn(inputStream);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));
            Book book = new Book();
            book.adultNumber();
            assertEquals("Number of adults:", outputStream.toString().strip());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Test
    public void adultnumber_test_2(){
        try{
            String input = "timeout";
            ByteArrayInputStream inputStream = new ByteArrayInputStream((
                    input + System.lineSeparator()).getBytes());
            System.setIn(inputStream);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));
            Book book = new Book();
            book.adultNumber();
            assertEquals("Number of adults:", outputStream.toString().strip());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Test
    public void seniornumber_test_1(){
        try{
            String input = "E";
            ByteArrayInputStream inputStream = new ByteArrayInputStream((
                    input + System.lineSeparator()).getBytes());
            System.setIn(inputStream);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));
            Book book = new Book();
            book.seniorNumber();
            assertEquals("Number of seniors/pensioners:", outputStream.toString().strip());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Test
    public void seniornumber_test_2(){
        try{
            String input = "timeout";
            ByteArrayInputStream inputStream = new ByteArrayInputStream((
                    input + System.lineSeparator()).getBytes());
            System.setIn(inputStream);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));
            Book book = new Book();
            book.seniorNumber();
            assertEquals("Number of seniors/pensioners:", outputStream.toString().strip());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Test
    public void chooseMovieTest_1() {
        try{
            String movieName = "movie1";
            ByteArrayInputStream inputStream = new ByteArrayInputStream((movieName + System.lineSeparator()).getBytes());
            System.setIn(inputStream);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));
            Book book = new Book();
            book.chooseMovie();
            assertEquals("Choose the movie you want",outputStream.toString().strip());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Test
    public void chooseMovieTest_2() {
        try{
            String movieName = "E";
            ByteArrayInputStream inputStream = new ByteArrayInputStream((movieName + System.lineSeparator()).getBytes());
            System.setIn(inputStream);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));
            Book book = new Book();
            book.chooseMovie();
            assertEquals(book.result,1);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @AfterEach
    public void rollBack_jsonData(){
        try{
            FileWriter file = new FileWriter("src/main/resources/customer.json");
            file.write(original_customer.toJSONString());
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
