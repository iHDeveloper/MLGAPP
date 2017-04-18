package com.maarif.mlgapp.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server{

	private int port = 25565;
	
	ServerSocket server = null;
	
	public Server(int port) {
		this.port = port;
	}
	
	public void start() throws IOException{
		server = new ServerSocket(port);
		
		new Thread().start();
	}
	
	public void stop() throws IOException{
		server.close();
	}
	
}
class ServerThread implements Runnable{

	private Server server;
	
	public ServerThread(Server server) {
		this.server = server;
	}
	
	@Override
	public void run() {
		while(true){
			try {
				Socket post = server.server.accept();
				// TODO handle the client
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
