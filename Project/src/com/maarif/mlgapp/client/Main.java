package com.maarif.mlgapp.client;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
        
        private static ClientConnection client;
    
	public static void main(String[] args) {
            try {
                client = new ClientConnection("iHDeveloper", "123456");
                Thread.sleep(5000);
            } catch (Exception ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
	}

}
