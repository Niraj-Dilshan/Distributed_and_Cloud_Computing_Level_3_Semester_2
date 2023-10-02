package com.labsheetseven.d.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientTCP {
    public static void main(String[] args) throws Exception {
        Socket sock = new Socket("127.0.0.1", 12121);
        BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        PrintWriter out = new PrintWriter(sock.getOutputStream(), true);

        String itemCode = "MLS0002";  // replace with the actual item code
        out.println(itemCode);

        System.out.println(in.readLine());
        System.out.println(in.readLine());
        System.out.println(in.readLine());

        sock.close();
    }
}
