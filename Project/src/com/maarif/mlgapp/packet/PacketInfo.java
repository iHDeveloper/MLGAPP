package com.maarif.mlgapp.packet;


public class PacketInfo extends Packet{

    public PacketInfo(Object a) {
        super();
        type(a);
    }
    
    /*
    a = Platform
    */
    
    public void type(Object a){
        set("a", a);
    }
    
    public Object getA(){
        return get("a");
    }
    
}
