package HAC;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
// import java.util.ArrayList;

public class Peer 
{
    // private int status;
    // private ArrayList<String> peerList;
    // public Peer(ArrayList<String> peerList)
    // {
    //     this.peerList = peerList;
    //     status = 1;
    // }
    private DatagramSocket socket;
    private InetAddress group;
    private byte[] buf;

    // public void multicast(String multicastMessage) throws IOException {
    //     socket = new DatagramSocket();
    //     group = InetAddress.getByName("230.0.0.0");
    //     buf = multicastMessage.getBytes();

    //     DatagramPacket packet 
    //       = new DatagramPacket(buf, buf.length, group, 4446);
    //     socket.send(packet);
    //     socket.close();
    // }
    public static void main(String[] args) { 
      int port = 18777; 
      String mcIPStr = "230.1.1.1"; 
      try( DatagramSocket udpSocket = new DatagramSocket();  ) { 
          // Prepare a message 
          InetAddress mcIPAddress = InetAddress.getByName(mcIPStr); 

          for (int i = 0; i <3; i++){
            byte[] msg = "Hello multicast socket".getBytes(); 
            DatagramPacket packet = new DatagramPacket(msg, msg.length); 
            packet.setAddress(mcIPAddress); 
            packet.setPort(port);
            udpSocket.send(packet); 
            System.out.println("Sent a multicast message."); 
            Thread.sleep(3000);
          }
          System.out.println("Exiting application"); 
      } catch (Exception e) { 
          e.printStackTrace(); 
      }
  } 
}
