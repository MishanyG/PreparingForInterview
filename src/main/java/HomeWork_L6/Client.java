package HomeWork_L6;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Client {
    public static void main(String[] args) {

        try(Socket socket = new Socket("localhost", 8080)) {
            BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));

            wr.write("GET /url HTTP/1.1\r\n");
            wr.write("Host: localhost:8080\r\n");
            wr.write("User-Agent: HttpClient\r\n");
            wr.write("Accept: text/html\r\n");
            wr.write("Accept-Language: ru-RU\r\n");
            wr.write("Connection: close\r\n");
            wr.write("-------------------------------------------------\r\n");
            wr.write("POST /url?param=paramOne HTTP/1.1\r\n");
            wr.write("Host: localhost:8080\r\n");
            wr.write("User-Agent: HttpClient\r\n");
            wr.write("Accept: text/html\r\n");
            wr.write("Accept-Language: ru-RU\r\n");
            wr.write("Connection: close\r\n");
            wr.write("\r\n");
            wr.flush();

            BufferedReader rd = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println(rd.readLine());
            wr.close();
            rd.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
