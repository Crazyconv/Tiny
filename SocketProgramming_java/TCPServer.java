import java.net.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TCPServer {
	public static void main(String[] args){
		if(args.length < 1){
			System.out.println("TCPServer port number not specified.");
			System.exit(1);
		}

		ServerSocket parentSocket = null;
		try{
			int portNo = Integer.valueOf(args[0]).intValue();
			parentSocket = new ServerSocket(portNo);

			while(true){
				Socket childSocket = parentSocket.accept();
				ClientHandler client = new ClientHandler(childSocket);
				Thread thread = new Thread(client);
				thread.start();
			}
		} catch (Exception e){
			System.out.println(e.getMessage());
		} 
	}
}

class ClientHandler implements Runnable{
	private Socket socket;
	public ClientHandler(Socket socket){
		this.socket = socket;
	}
	public void run(){
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(new DataInputStream(socket.getInputStream())));
			DataOutputStream os = new DataOutputStream(socket.getOutputStream());
			while(true){
				String receiveMessage = br.readLine();
				System.out.println("Receive from client: " + receiveMessage);
				if(receiveMessage.equals("end")){
					socket.close();
					br.close();
					os.close();
					break;
				}

				String receiveTime = (new SimpleDateFormat()).format((new Date()));
				String replyMessage = "I have received your message at " + receiveTime;			
				os.writeBytes(replyMessage);
			}
		} catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}