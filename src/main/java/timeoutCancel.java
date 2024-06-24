import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class timeoutCancel {
    public String Input() throws IOException {
        String line;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        long startTime = System.currentTimeMillis();
        while ((System.currentTimeMillis() - startTime) < 120*1000 && !in.ready()) {}
        if (in.ready()) {
            line = in.readLine();
        } else {
            line = "timeout";
            System.out.println("\nTime out\n");
        }
        return line;
    }
}
