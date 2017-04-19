package com.maarif.mlgapp.client;

import com.maarif.mlgapp.packet.PacketInfo;
import com.maarif.mlgapp.packet.PacketLogin;
import com.maarif.mlgapp.until.Console;
//import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientConnection {
    
    private Socket s;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public ClientConnection(String username, String password) throws IOException {
        s = null;
        out = null;
        in = null;
        Console.info("Connecting to server...");
        s = new Socket("127.0.0.1", 25565);
        Console.info("Getting Output/Input...");
        out = new ObjectOutputStream(new BufferedOutputStream(s.getOutputStream()));
        //in = new ObjectInputStream(new BufferedInputStream(s.getInputStream()));
        Console.info("Sending packets...");
        PacketInfo info = new PacketInfo(System.getProperty("os.name"));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(ClientConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        PacketLogin login = new PacketLogin(username, "");
        out.writeObject(info);
        out.writeObject(login);
        out.flush();
        Console.info("Completed!");
    }
    
}
