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
			while(true){
				byte[] buffer = new byte[256];
				DatagramPacket request = new DatagramPacket(buffer, 256);
				socket.receive(request);

				// remove extra empty characters
				int i;
				for(i=0; i<256 && buffer[i]!=0; i++);
				System.out.println("Receive massage: " + new String(buffer,0,i));


				String receiveTime = (new SimpleDateFormat()).format((new Date()));
				String replyMessage = "I have received your message at " + receiveTime;
				DatagramPacket reply = new DatagramPacket(replyMessage.getBytes(),replyMessage.length(),request.getAddress(),request.getPort());
				socket.send(reply);
			}
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