/* Name:       GUO JIACHUN
 * Group:      TS1
 * IP Address: 192.168.22.1
 */

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class UDPClient{
	public static void main(String args[]) {
		if(args.length < 2){
			System.out.println("Server or port no not specified.");
			System.exit(1);
		}

		DatagramSocket socket = null;
		Scanner sc = new Scanner(System.in);
		try {
			socket = new DatagramSocket();
			int portNo = Integer.valueOf(args[1]).intValue();
			InetAddress host = InetAddress.getByName(args[0]);

			// while(true){
			// 	byte[] buffer = new byte[256];
			// 	System.out.print("Enter message to send: ");
			// 	String requestMessage = sc.nextLine();
			// 	DatagramPacket request = new DatagramPacket(requestMessage.getBytes(), requestMessage.length(), host, portNo);
			// 	socket.send(request);

			// 	DatagramPacket reply = new DatagramPacket(buffer, 256);
			// 	socket.receive(reply);
			// 	// remove extra empty characters
			// 	int i;
			// 	for(i=0; i<256 && buffer[i]!=0; i++);
			// 	System.out.println("Receive message: " + new String(buffer,0,i));
			// }

			byte[] buffer = new byte[256];
			String requestMessage = "GUO JIACHUN, TS1, 192.168.22.1";
			DatagramPacket request = new DatagramPacket(requestMessage.getBytes(), requestMessage.length(), host, portNo);
			socket.send(request);

			DatagramPacket reply = new DatagramPacket(buffer, 256);
			socket.receive(reply);
			// remove extra empty characters
			int i;
			for(i=0; i<256 && buffer[i]!=0; i++);
			System.out.println("Receive message: " + new String(buffer,0,i));
		} catch(SocketException e) {
			System.out.println("Socket: " + e.getMessage());
		} catch(IOException e) {
			System.out.println("IO: " + e.getMessage());
		} finally {
			if(socket != null)
				socket.close();
		}
	}
}