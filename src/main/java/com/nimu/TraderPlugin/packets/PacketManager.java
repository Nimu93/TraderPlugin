package com.nimu.TraderPlugin.packets;

import com.nimu.TraderPlugin.TraderPlugin;
import org.bukkit.Bukkit;

public class PacketManager {
    public static void RegisterChannel(){
        Bukkit.getMessenger().registerOutgoingPluginChannel(TraderPlugin.getInstance(), "TraderPlugin");
        Bukkit.getMessenger().registerIncomingPluginChannel(TraderPlugin.getInstance(), "TraderPlugin", new PacketListener());
    }
}
