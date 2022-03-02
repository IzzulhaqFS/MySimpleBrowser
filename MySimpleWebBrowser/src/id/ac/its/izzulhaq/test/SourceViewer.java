package id.ac.its.izzulhaq.test;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.zip.GZIPInputStream;

public class SourceViewer {

    private static String getWebPageSource(String url) throws IOException {
        URL url1 = new URL(url);
        URLConnection uc = url1.openConnection();
        BufferedReader in = null;

        if (uc.getHeaderField("Content-Encoding") != null && uc.getHeaderField("Content-Encoding").equals("gzip")) {
           in = new BufferedReader(new InputStreamReader(new GZIPInputStream(uc.getInputStream())));
        }
        else {
            in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
        }

        String inputLine;
        StringBuilder sb = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            sb.append(inputLine);
        }

        in.close();

        return sb.toString();
    }

    public static void main(String[] args) {
            try {
                System.out.println(getWebPageSource("https://its.ac.id"));
            } catch (IOException e) {
                System.err.println(e);
            }
    }
}