package com.labsheetseven.d.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.HashMap;

public class ServerUDP {
    public static void main(String[] args) throws IOException {
        DatagramSocket ds = new DatagramSocket(12121);

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

        byte[] receive = new byte[65535];

        while (true) {
            DatagramPacket DpReceive = new DatagramPacket(receive, receive.length);
            ds.receive(DpReceive);

            String itemCode = data(receive).toString();
            Double initialPrice = prices.get(itemCode);
            Double discount = discounts.get(itemCode);
            Double finalPrice = initialPrice * (1 - discount / 100);

            String result = "Initial price: " + initialPrice + "\nDiscount: " + discount + "\nFinal price: " + finalPrice;
            DatagramPacket DpSend = new DatagramPacket(result.getBytes(), result.getBytes().length, DpReceive.getAddress(), DpReceive.getPort());
            ds.send(DpSend);

            receive = new byte[65535];
        }
    }

    public static StringBuilder data(byte[] a) {
        if (a == null)
            return null;
        StringBuilder ret = new StringBuilder();
        int i = 0;
        while (a[i] != 0) {
            ret.append((char) a[i]);
            i++;
        }
        return ret;
    }
}
