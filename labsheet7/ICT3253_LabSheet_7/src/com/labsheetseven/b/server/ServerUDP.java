package com.labsheetseven.b.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ServerUDP {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(4445);
        byte[] buffer = new byte[256];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        while (true) {
            socket.receive(packet);
            String received = new String(packet.getData(), 0, packet.getLength());
            System.out.println("Client: " + received);
            if (received.equals("end")) {
                break;
            }
        }
        socket.close();
    }
}
