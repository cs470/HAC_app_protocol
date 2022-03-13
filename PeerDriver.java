package HAC;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
// import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
public class PeerDriver{

    public static void main(String[] args) throws IOException
    {
        // Scanner scanner = new Scanner(System.in);
        // System.out.println("Please enter the path to the file:");
        // String fileName = scanner.nextLine();

        // List<String> ipAddressesList = Files.readAllLines(Paths.get(fileName));
        // System.out.print(ipAddressesList.toString());
        // scanner.close();

        try
        {
            while (true)
            {
                // for (int i=0; i < ipAddressesList.size(); i++)
                // {
                    DatagramSocket socket = new DatagramSocket(9876);
                    byte[] incomingData = new byte[1024];
                    DatagramPacket packet = new DatagramPacket(incomingData, incomingData.length);
                    System.out.println("Waiting for a message...");
                    //System.out.println();

                    socket.receive(packet);
                    String msg = new String(packet.getData());
                    System.out.println("Received: " + msg + " from " + packet.getAddress());

                    String reply = "Thanks";
                    byte[] data = reply.getBytes();
                    
                    DatagramPacket replyPacket = 
                            new DatagramPacket(data, data.length, 
                            packet.getAddress(), 3466);
                            
                    // Thread.sleep(5000);
                    socket.send(replyPacket);
                //}
            }
        } catch (Exception e) {
        e.printStackTrace();
    }
    
    }
				
}
