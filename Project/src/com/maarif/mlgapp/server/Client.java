package com.maarif.mlgapp.server;

import java.net.Socket;


public class Client extends MLGClient{

    public Client(Socket s) {
        super(s);
    }
    
    public String getName(){
        return super.username;
    }
    
    public String getPlatform(){
        return super.platform;
    }
    
}
