import java.net.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UDPServer {
	public static void main(String args[]) {
		if(args.length < 1){
			System.out.println("UDPServer port number not specified.");
			System.exit(1);
		}

		DatagramSocket socket = null;
		try{
			int portNo = Integer.valueOf(args[0]).intValue();
			socket = new DatagramSocket(portNo);
			byte[] buffer = new byte[1000];
			while(true){
				DatagramPacket request = new DatagramPacket(buffer, 1000);
				socket.receive(request);
				System.out.println("Reveice massage: " + new String(buffer));

				String receiveTime = (new SimpleDateFormat()).format((new Date()));
				String replyMessage = "I have received your message at " + receiveTime;
				DatagramPacket reply = new DatagramPacket(replyMessage.getBytes(),replyMessage.length(),request.getAddress(),request.getPort());
				socket.send(reply);
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