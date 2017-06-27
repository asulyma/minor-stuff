package networking;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class TestWriteServer {
    private static int serverPort = 998;
    private static int clientPort = 999;
    private static int bufferSize = 1024;
    private static DatagramSocket datagramSocket;
    private static byte dataBuffer[] = new byte[bufferSize];

    public static void workServer() throws Exception {
        int position = 0;
        while (true) {
            int c = System.in.read();
            switch (c) {
                case -1:
                    System.out.println("Server is stopped!");
                    datagramSocket.close();
                    return;
                case '\r':
                    break;
                case '\n':
                    datagramSocket.send(new DatagramPacket(dataBuffer, position, InetAddress.getLocalHost(), clientPort));
                    position = 0;
                    break;
                default:
                    dataBuffer[position++] = (byte) c;
            }
        }
    }

    public static void workClient() throws Exception {
        while (true) {
            DatagramPacket packet = new DatagramPacket(dataBuffer, dataBuffer.length);
            datagramSocket.receive(packet);
            System.out.println(new String(packet.getData(), 0, packet.getLength()));
        }
    }

    public static void main(String[] args) throws Exception {
        int arg = Integer.parseInt(new Scanner(System.in).nextLine());
        if(arg == 1){
            datagramSocket = new DatagramSocket(serverPort);
            workServer();
        } else {
            datagramSocket = new DatagramSocket(clientPort);
            workClient();
        }
    }
}
