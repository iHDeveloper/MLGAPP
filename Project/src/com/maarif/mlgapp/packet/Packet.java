package com.maarif.mlgapp.packet;

import com.maarif.mlgapp.until.Console;
import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

public class Packet implements Serializable{
    
    private Map<Object, Object> map = null;

    public Packet() {
        map = new HashMap<>();
    }
    
    public Object get(Object key){
        return map.get(key);
    }
    
    public void set(Object key, Object value){
        put(key, value);
    }
    
    public void put(Object key, Object value){
        map.put(key, value);
    }
    
    @Override
    public String toString(){
        return String.format("[PACKET] : %s", getClass().toString());
    }
    
    public void typeString(){
        String packet = toString();
        Console.info(">> "+packet);
        for (Object key : map.keySet()) {
            Object value = map.get(key);
            Console.info("["+key+"] : "+value);
        }
    }
    
}
