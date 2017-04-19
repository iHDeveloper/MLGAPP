package com.maarif.mlgapp.server;
 
import java.net.Socket;


public class ClientHandle { 
    
    public Socket connection;

    public ClientHandle(Socket s) {
        connection = s; 
    }
    
}
