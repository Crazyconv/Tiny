import java.net.*;
import java.io.*;
import java.util.Scanner;

public class TCPClient{
	public static void main(String[] args){
		if(args.length < 2){
			System.out.println("Server or port no not specified.");
			System.exit(1);
		}

		Socket socket = null;
		DataOutputStream os = null;
		DataInputStream is = null;
		int portNo = Integer.valueOf(args[1]).intValue();
		Scanner sc = new Scanner(System.in);

		try{
			socket = new Socket(args[0], portNo);
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