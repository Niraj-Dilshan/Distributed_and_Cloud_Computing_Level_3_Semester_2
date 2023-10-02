package com.labsheetseven.d.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClientUDP {
    public static void main(String[] args) throws IOException {
        DatagramSocket ds = new DatagramSocket();
        InetAddress ip = InetAddress.getLocalHost();
        byte buf[] = null;

        String itemCode = "MT001";
        buf = itemCode.getBytes();

        DatagramPacket DpSend = new DatagramPacket(buf, buf.length, ip, 12121);
        ds.send(DpSend);

        byte[] receive = new byte[65535];
        DatagramPacket DpReceive = new DatagramPacket(receive, receive.length);
        ds.receive(DpReceive);

        System.out.println("Prices from server:-\n" + data(receive));
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
