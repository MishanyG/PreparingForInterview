package HomeWork_L6;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try(ServerSocket socket = new ServerSocket(8080)) {
            Socket client = socket.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));

            String line = " ";
            while(! line.equals("")) {
                line = in.readLine();
                System.out.println(line);
                out.write("OK");
            }

            out.flush();
            out.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
