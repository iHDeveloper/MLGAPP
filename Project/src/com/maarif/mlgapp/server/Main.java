package com.maarif.mlgapp.server;

import com.maarif.mlgapp.until.Console;

public class Main {
        
        private static Server server = null;
    
	public static void main(String[] args) {
            Console.info("Loading....");
            try{
                server = new Server(25565);
                server.start();
                Console.server("Listening on "+server.getSocket().getLocalSocketAddress());
                System.out.println();
            }
            catch(Exception ex){
                Console.err("ERR: "+ex.getMessage());
                System.exit(0);
            }
	}
        
        public static Server getServer(){
            return server;
        }

}
