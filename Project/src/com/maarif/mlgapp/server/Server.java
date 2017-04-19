package com.maarif.mlgapp.server;

import com.maarif.mlgapp.packet.Packet;
import com.maarif.mlgapp.until.Console;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server{

	private int port = 25565;
	private List<Client> clients;
	private ServerSocket server = null;
	
	public Server(int port) {
            this.port = port;
            clients = new ArrayList<>();
	}
	
	public void start() throws IOException{
		server = new ServerSocket(port);
		new Thread(new ServerThread(this)).start();
	}
	
	public void stop() throws IOException{
		server.close();
	}
	
        public ServerSocket getSocket(){
            return server;
        }
        
        public List<Client> getClients(){
            return clients;
        }
        
}
class ServerThread implements Runnable{

	Server server;
	
	public ServerThread(Server server) {
		this.server = server;
	}
	
	@Override
	public void run() {
            Console.info("Waiting for connect to port "+server.getSocket().getLocalPort()+"...");
            while(true){
		try {
                    Socket post = server.getSocket().accept();
                    Console.server(String.format("[%s] Connected", post.getRemoteSocketAddress()));
                    // TODO handle the client
                    try {
                        Client client = new Client(post);
                        /*ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(post.getOutputStream()));
                        ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(post.getInputStream()));
                        Console.info(String.format("[%s] logging in", post.getRemoteSocketAddress()));
                        PacketInfo info = (PacketInfo)in.readObject();
                        PacketLogin login = (PacketLogin)in.readObject();
                        ((MLGClient)client).postPacket(info);
                        ((MLGClient)client).postPacket(login);
                        Console.info(String.format("[%s] connected as %s!", client.getName(), client.getPlatform()));*/
                        new Thread(new ServerClientReadWriteThread(client)).start();
                        // TODO after handle
                        server.getClients().add(client);
                    } catch (Exception e) {
                        post.close();
                    }
		} catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
		}
            }
	}
	
}
class ServerClientReadWriteThread implements Runnable{
    private Client client;

    public ServerClientReadWriteThread(Client client) {
        this.client = client;
    }
    
    @Override
    public void run() {
        while(true){
            try{
                MLGClient mlgclient = (MLGClient) client;
                Socket post = mlgclient.getHandle().connection;
                ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(post.getOutputStream()));
                ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(post.getInputStream()));
                while(true){
                    Object obj = in.readObject();
                    if(obj instanceof Packet){
                        Packet packet = (Packet)obj;
                        mlgclient.postPacket(packet);
                    }
                }
            }catch(IOException ex){
                Console.info(String.format("[%s] disconnected!", client.getName()));
                Main.getServer().getClients().remove(client);
                return;
            }catch (ClassNotFoundException ex) {
                Logger.getLogger(ServerClientReadWriteThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
