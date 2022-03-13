package HAC;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class PeerReceiver implements Runnable{
    private final MulticastSocket socket;

    public PeerReceiver(MulticastSocket socket)
    {
        this.socket = socket;
    }

    @Override
    public void run() 
    {
        try
        {
            while (true)
            {
                InetAddress group = InetAddress.getByName("224.1.1.1");
                MulticastSocket socket = new MulticastSocket(3456);
                //NetworkInterface nif = NetworkInterface.getByName("utun4");
                socket.joinGroup(group);
                
                DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);
                System.out.println("Waiting for a  multicast message...");
                System.out.println();

                socket.receive(packet);
                String msg = new String(packet.getData());
                System.out.println("[Multicast  Receiver] Received:" + msg);

                String reply = "Thanks";
                byte[] data = reply.getBytes();
                
                DatagramPacket replyPacket = 
                        new DatagramPacket(data, data.length, 
                        packet.getAddress(), 3466);
                        
                // Thread.sleep(5000);
                socket.send(replyPacket);
                System.out.println("Reply sent");
            }
        } catch (Exception e) {
        e.printStackTrace();
    }
        
    }
    
}
