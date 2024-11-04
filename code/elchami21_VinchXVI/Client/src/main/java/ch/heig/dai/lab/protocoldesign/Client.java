package ch.heig.dai.lab.protocoldesign;

import java.io.*;
import java.net.*;

public class Client {
    final String SERVER_ADDRESS = "1.2.3.4";
    final int SERVER_PORT = 1234;

    public static void main(String[] args) {
        // Create a new client and run it
        Client client = new Client();
        client.run();
    }

    private void run() {

        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT));
            var in = new BufferedReader( new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            var out = new BufferedWriter( new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8)))
        {

            System.out.println("Client connected\n" );

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


            while (true) {

                System.out.println("Allowed operations are \n ADD \n SUB \n MUL\n");
                System.out.println("Exemple : ADD 5 2\n");
                System.out.println("\'X\' to exit\n");

                // Reading data using readLine
                String calc = reader.readLine();

                if(calc.equals("X")) break;

                out.write(calc + "\n");
                out.flush;

                // Printing the read line
                System.out.println(in.readline());
            }
        }
    }
}