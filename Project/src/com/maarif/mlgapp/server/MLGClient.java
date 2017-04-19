package com.maarif.mlgapp.server;

import com.maarif.mlgapp.packet.Packet;
import com.maarif.mlgapp.packet.PacketInfo;
import com.maarif.mlgapp.packet.PacketLogin;
import com.maarif.mlgapp.until.Console;
import java.net.Socket;


public class MLGClient {
    
    private ClientHandle handle;
    public String platform;
    public String username;
    
    public MLGClient(Socket s) {
        handle = new ClientHandle(s);
    }
    
    public void postPacket(Packet packet){
        if(packet instanceof PacketInfo){
            platform = (String)((PacketInfo)packet).getA();
            Console.info(String.format("[%s] logging in", getHandle().connection.getRemoteSocketAddress()));
        }
        else if(packet instanceof PacketLogin){
            username = (String)((PacketLogin)packet).getA();
            Console.info(String.format("[%s] connected as %s!", username, platform));
        }
        //packet.typeString(); know the list
        
        
    }
    
    public ClientHandle getHandle(){
        return handle;
    }
    
}
