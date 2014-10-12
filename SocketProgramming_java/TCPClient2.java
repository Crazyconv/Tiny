import java.net.*;
import java.io.*;
import java.util.Scanner;

public class TCPClient2{
	public static void main(String[] args){
		Socket socket = null;
		DataOutputStream os = null;
		DataInputStream is = null;
		Scanner sc = new Scanner(System.in);

		try{
			socket = new Socket("192.168.22.1",5000);
			os = new DataOutputStream(socket.getOutputStream());
			is = new DataInputStream(socket.getInputStream());
			while(true){
				System.out.print("Send message to server: ");
				String sendMessage = sc.nextLine();
				os.writeUTF(sendMessage);
				if(sendMessage.equals("end")){
					socket.close();
					is.close();
					os.close();
					break;
				}

				String receiveMessage = is.readUTF();
				System.out.println("Receive from server: " + receiveMessage);
			}
		} catch(Exception e){
			System.out.println(e.getMessage());
		}
	}	
}