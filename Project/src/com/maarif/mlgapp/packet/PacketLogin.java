package com.maarif.mlgapp.packet;


public class PacketLogin extends Packet{

    public PacketLogin(String a, String b) {
        super();
        type(a, b);
    }
    
    /*
    a = username*
    b = password*
    #c = skippasscode (optional)
    */
    private void type(String a, String b){
        set("a", a);
        set("b", b);
    }
    
    public Object getA(){
        return get("a");
    }
    
    public Object getB(){
        return get("b");
    }
    
}
