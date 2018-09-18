package com.zhoujie.tc;

import android.util.Log;

import java.net.InetAddress;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Arrays;


public class WakeupOverLan {

    public static void sendWol(String ipAddress, String macAddress) {

        try {
            EthernetAddress ethernetAddresses = new EthernetAddress(macAddress);
            InetAddress host = InetAddress.getByName(ipAddress);
            int port = 9;
            DatagramSocket socket = new DatagramSocket();
            byte[] wakeupFrame = createWakeupFrame(ethernetAddresses);
            DatagramPacket packet = new DatagramPacket(wakeupFrame, wakeupFrame.length, host, port);
            socket.send(packet);
            Log.d("WakeupOverLan", "Packet sent");
        } catch (Exception e) {
            System.err.println(e);
        };

    }


    protected static byte[] createWakeupFrame(EthernetAddress ethernetAddress) {
        byte[] ethernetAddressBytes = ethernetAddress.toBytes();
        byte[] wakeupFrame = new byte[6 + 16 * ethernetAddressBytes.length];

        Arrays.fill(wakeupFrame, 0, 6, (byte)0xFF);
        for (int j = 6; j < wakeupFrame.length; j += ethernetAddressBytes.length) {
            System.arraycopy(ethernetAddressBytes, 0, wakeupFrame, j, ethernetAddressBytes.length);
        }
        return wakeupFrame;
    }

}

