package HAC;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class PeerSender implements Runnable{
    private final MulticastSocket socket;

    public PeerSender(MulticastSocket socket)
    {
        this.socket = socket;
    }

    @Override
    public void run() {
        try{
        InetAddress group = InetAddress.getByName("224.1.1.1");
        String message = "testing 123";

        DatagramPacket packet = new DatagramPacket(message.getBytes(), message.length(), group, 3456);
        socket.send(packet);
        System.out.println("Message sent from: " + InetAddress.getLocalHost());
        socket.close();
        // Thread.sleep(3000);
    } catch (Exception e)
    {
        e.printStackTrace();
    }
        
    }

    
}
