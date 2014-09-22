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
			byte[] buffer = new byte[1000];
			while(true){
				System.out.print("Enter message to send: ");
				String requestMessage = sc.next();
				DatagramPacket request = new DatagramPacket(requestMessage.getBytes(), requestMessage.length(), host, portNo);
				socket.send(request);
				DatagramPacket reply = new DatagramPacket(buffer, 1000);
				socket.receive(reply);
				System.out.println("Receive message: " + new String(buffer));
			}
		} catch(SocketException e) {
			if(socket != null)
				socket.close();
			System.out.println(e.getMessage());
			System.exit(1);
		} catch(IOException e) {
			socket.close();
			System.out.println(e.getMessage());
			System.exit(1);
		}
	}
}