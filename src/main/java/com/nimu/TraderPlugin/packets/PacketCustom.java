package com.nimu.TraderPlugin.packets;

import com.nimu.TraderPlugin.TraderPlugin;

import java.util.logging.Level;

public class PacketCustom {
    int id;
    String message;
    public PacketCustom(int id, String message){
        this.id = id;
        this.message = message;
    }

    public static PacketCustom getPacket(String message){
        String[] mess = message.split("/",2)[1].split(":",2);
        if (mess.length != 2){
            TraderPlugin.LOGGER.log(Level.SEVERE, "IMPOSSIBLE");
            return null;
        }
        StringBuilder res = new StringBuilder();
        for (char c: mess[0].toCharArray()){
            if (Character.isDigit(c)){
                res.append(c);
            }
        }
        return new PacketCustom(Integer.parseInt(res.toString()), mess[1]);
    }
    public int getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    @Override
    public String toString(){
        return "/" + id + ":" + message;
    }
}
