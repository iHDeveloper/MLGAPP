package com.maarif.mlgapp.until;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Console {

	public static void info(String message){
		send("INFO", message);
	}
	
	public static void server(String message){
		send("SERVE", message);
	}
	
	public static void warn(String message){
		send("WARN", message);
	}
	
	public static void err(String message){
		send("ERR", message);
	}
	
	public static void thread(String message){
		send("THREAD", message);
	}
	
	public static void send(String prefix, String message){
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		System.out.println("["+dateFormat.format(date)+" "+prefix+"] : "+message);
	}
	
}
