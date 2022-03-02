package id.ac.its.izzulhaq.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class ReadWebPageEx {
    public static void main(String[] args) throws IOException {
        URL url = new URL("https://www.youtube.com/");
        try(BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()))) {
            String line;
            StringBuilder sb = new StringBuilder();

            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
            }

            System.out.println(sb);
        }
    }
}
