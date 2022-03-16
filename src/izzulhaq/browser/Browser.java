package izzulhaq.browser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class Browser {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the URL");
        String url = scanner.next();
        String[] splitUrl = url.split("/", 4);

        String protocol = splitUrl[0];
        String server = splitUrl[2];
        String path;
        if (splitUrl[3].equals("")) {
            path = "/";
        }
        else {
            path = "/" + splitUrl[3];
        }

        System.out.println("Protocol: " + protocol);
        System.out.println("Server: " + server);
        System.out.println("Path: " + path);

        int port = 80;

        try {
            Socket socket = new Socket(server, port);
            System.out.println("Terhubung dengan " + server + " di port " + port + ".");

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

//            String requestLine = "GET " + path + " HTTP/1.0\r\n\r\n";
//            String requestHeader = "";
            out.write("GET " + path + " HTTP/1.0\r\n\r\n");
            out.flush();
            System.out.println("Mengirim request " + path + " ke " + server + ".");

            StringBuilder response = new StringBuilder();
            String line = in.readLine();
            while (line != null && !line.equals("0")) {
                response.append(line);
                response.append("\r\n");
//                System.out.println(line);
                line = in.readLine();
            }
            System.out.println(response.substring(response.indexOf("\r\n\r\n") + 1, response.lastIndexOf("\r\n")));

            in.close();
            out.close();
            socket.close();
            System.out.println("Koneksi terputus");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        scanner.close();
    }
}
