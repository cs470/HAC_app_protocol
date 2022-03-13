package HAC;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;  
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
// import java.nio.file.Files;
// import java.nio.file.Paths;
// import java.util.ArrayList;
// import java.util.List;
// import java.util.Scanner;
import java.net.SocketTimeoutException;

public class Peer
{
  //private String status;
  public static String[] peerList = {"192.168.1.26", "192.168.1.27"};
  public static String[] statusList = {"Down", "Down"};
  
  public Peer() throws IOException
  {
    // Scanner scanner = new Scanner(System.in);
    // System.out.println("Please enter the path to the file:");
    // String fileName = scanner.nextLine();

    // List<String> ipAddressesList = Files.readAllLines(Paths.get(fileName));
    // System.out.print(ipAddressesList.toString());
    // scanner.close();
    // Peer.peerList = (ArrayList<String>) ipAddressesList;
  }
  
  public static void startSender()
  {
    new Thread()
    {
      @Override
      public void run() {
        try 
        {
          DatagramSocket socket = new DatagramSocket();
          String status = "Alive";
          // String version = "1.1";
          // int flag = 0;
          // byte[] reserved = new byte[1024];
          //byte[] totalBytes = status.getBytes() + version.getBytes() + flag.getBytes() + reserved;

          while (true)
          {
            for (int i=0; i<peerList.length;i++)
            {
              DatagramPacket packet = new DatagramPacket(status.getBytes(), status.length(), InetAddress.getByName(peerList[i]), 3466);
              socket.send(packet);
              System.out.println("Message sent from: " + InetAddress.getLocalHost());
              Thread.sleep(5000);
            }
          }
        } 
        catch (Exception e) 
        {
          e.printStackTrace();
        }
      }
    }.start();
  }
  
  public static void startReceiver()
  {
    new Thread()
    {
      @Override
      public void run() {
        try 
        {
          DatagramSocket socket = new DatagramSocket(3466);
          // String version = "1.1";
          // int flag = 0;
          // byte[] reserved = new byte[1024];
          //byte[] totalBytes = status.getBytes() + version.getBytes() + flag.getBytes() + reserved;

          while (true)
          {
            LocalTime now = java.time.LocalTime.now();
            DatagramPacket reply = new DatagramPacket(new byte[1024], 1024);
            socket.setSoTimeout(5000);
            socket.receive(reply);
            String msg = new String(reply.getData());
            System.out.println("Received: " + msg + " from " + reply.getAddress());
            System.out.println();
            //Thread.sleep(5000);
          }
          //socket.close();
          //System.out.println("Socket closed");
        } 
        catch (SocketTimeoutException e) 
        {
          e.printStackTrace();
        } catch (SocketException e) {
          e.printStackTrace();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }.start();
  }
  public static void main(String[] args) 
  {
    startReceiver();
    startSender();
  } 
}

