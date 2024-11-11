package ch.heig.dai.lab.protocoldesign;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class Server {
    final int SERVER_PORT = 1234;

    public static void main(String[] args) {
        Server server = new Server();
        server.run();
    }

    private void run() {
        System.out.println("Starting server on port " + SERVER_PORT);

        try (ServerSocket serverSocket = new ServerSocket(SERVER_PORT)) {
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), StandardCharsets.UTF_8));
                     BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream(), StandardCharsets.UTF_8))) {

                    System.out.println("New client connected from " + clientSocket.getInetAddress());

                    // Send welcome message
                    String welcomeMsg = "Welcome to Calculator Server! Supported operations: ADD, SUB, MUL";
                    out.write(welcomeMsg + "\n");
                    out.flush();

                    // Handle client requests
                    String request;
                    while ((request = in.readLine()) != null) {
                        String response = processRequest(request);
                        out.write(response + "\n");
                        out.flush();
                    }
                } catch (IOException e) {
                    System.err.println("Error handling client: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port " + SERVER_PORT);
        }
    }

    private String processRequest(String request) {
        String[] parts = request.split(" ");

        // Check if request has correct format
        if (parts.length != 3) {
            return "Error: Invalid format. Use: OPERATION NUMBER1 NUMBER2";
        }

        String operation = parts[0].toUpperCase();
        double num1, num2;

        // Parse numbers
        try {
            num1 = Double.parseDouble(parts[1]);
            num2 = Double.parseDouble(parts[2]);
        } catch (NumberFormatException e) {
            return "Error: Invalid numbers provided";
        }

        // Process operation
        try {
            double result;
            switch (operation) {
                case "ADD":
                    result = num1 + num2;
                    break;
                case "SUB":
                    result = num1 - num2;
                    break;
                case "MUL":
                    result = num1 * num2;
                    break;
                default:
                    return "Unknown Operation: " + operation;
            }
            return String.format("%.2f", result);
        } catch (Exception e) {
            return "Error: Calculation failed";
        }
    }
}