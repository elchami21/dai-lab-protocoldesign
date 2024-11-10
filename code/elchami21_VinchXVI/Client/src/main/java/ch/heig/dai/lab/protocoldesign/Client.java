package ch.heig.dai.lab.protocoldesign;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class Client {
    final String SERVER_ADDRESS = "localhost"; // Changed from 1.2.3.4 for local testing
    final int SERVER_PORT = 1234;

    public static void main(String[] args) {
        // Create a new client and run it
        Client client = new Client();
        client.run();
    }

    private void run() {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
             BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));
             BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Client connected\n");

            // Read welcome message from server
            String welcome = in.readLine();
            System.out.println(welcome + "\n");

            while (true) {
                System.out.println("Allowed operations are:\nADD\nSUB\nMUL");
                System.out.println("Example: ADD 5 2");
                System.out.println("Enter 'X' to exit\n");

                // Reading user input
                String calc = reader.readLine();

                // Check for exit condition
                if (calc == null || calc.equalsIgnoreCase("X")) {
                    System.out.println("Closing connection...");
                    break;
                }

                // Send calculation request to server
                out.write(calc + "\n");
                out.flush();

                // Read and print server response
                String response = in.readLine();
                System.out.println("Server response: " + response + "\n");
            }

        } catch (UnknownHostException e) {
            System.err.println("Error: Cannot find host " + SERVER_ADDRESS);
        } catch (IOException e) {
            System.err.println("Error: Lost connection to server - " + e.getMessage());
        }
    }
}