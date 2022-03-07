package HAC;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
// import java.nio.file.Files;
// import java.nio.file.Paths;
// // import java.util.ArrayList;
// import java.util.List;
// import java.util.Scanner;
import java.net.UnknownHostException;

public class PeerDriver{

    public static void main(String[] args) throws UnknownHostException
    {
        // Scanner scanner = new Scanner(System.in);
        // System.out.println("Please enter the name of the file:");
        // String fileName = scanner.nextLine();

        // List<String> ipAddressesList = Files.readAllLines(Paths.get(fileName));
        // System.out.print(ipAddressesList.toString());
        // scanner.close();

        int mcPort = 18777; 
        InetAddress mcIPAddress = InetAddress.getByName("230.1.1.1"); 
        try (MulticastSocket  mcSocket = new MulticastSocket(mcPort); )
        { 
            System.out.println("Multicast Receiver running at:" 
                    + mcSocket.getInetAddress()); 
            System.out.println(NetworkInterface.getNetworkInterfaces());
            // Join the group 
            mcSocket.joinGroup(mcIPAddress); 

            DatagramPacket packet = new DatagramPacket(new byte[1024], 1024); 

            while (true) { 
                System.out.println("Waiting for a multicast message..."); 
                mcSocket.receive(packet); 
                String msg = new String(packet.getData(), 
                                        packet.getOffset(), 
                                        packet.getLength()); 
                System.out.println("[Multicast Receiver] Received: " + msg + "from " 
                    + packet.getAddress()); 
                mcSocket.leaveGroup(mcIPAddress);
            } 

    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    }
}
