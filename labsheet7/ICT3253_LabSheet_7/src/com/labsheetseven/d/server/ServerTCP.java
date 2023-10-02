package com.labsheetseven.d.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class ServerTCP {
    public static void main(String[] args) throws IOException {
        ServerSocket servsock = new ServerSocket(12121);

        HashMap<String, Double> prices = new HashMap<String, Double>() {{
            put("MT001", 2500.00);
            put("MT002", 1200.00);
            put("MT003", 350.00);
            put("MT004", 990.00);
            put("ES001", 4000.00);
            put("ES002", 3400.00);
            put("ES003", 6500.00);
            put("ES004", 1500.00);
            put("MLS0001", 750.00);
            put("MLS0002", 4500.00);
        }};

        HashMap<String, Double> discounts = new HashMap<String, Double>() {{
            put("MT001", 5.0);
            put("MT002", 2.5);
            put("MT003", 0.0);
            put("MT004", 2.0);
            put("ES001", 10.0);
            put("ES002", 7.5);
            put("ES003", 15.0);
            put("ES004", 5.0);
            put("MLS0001", 0.0);
            put("MLS0002", 10.0);
        }};

        while (true) {
            Socket sock = servsock.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            PrintWriter out = new PrintWriter(sock.getOutputStream(), true);

            String itemCode = in.readLine();
            Double initialPrice = prices.get(itemCode);
            Double discount = discounts.get(itemCode);
            Double finalPrice = initialPrice * (1 - discount / 100);

            out.println("Initial price: " + initialPrice);
            out.println("Discount (%): " + discount);
            out.println("Final price: " + finalPrice);

            sock.close();
        }
    }
}
