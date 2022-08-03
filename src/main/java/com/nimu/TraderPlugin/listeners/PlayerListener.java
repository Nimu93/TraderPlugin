package com.nimu.TraderPlugin.listeners;

import com.nimu.TraderPlugin.TraderPlugin;
import com.nimu.TraderPlugin.packets.ID;
import com.nimu.TraderPlugin.packets.PacketCustom;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListener implements Listener {
    @EventHandler
    public void onPlayerConnect(PlayerJoinEvent e){
            PacketCustom packetCustom = new PacketCustom(ID.JSONSHOP.getType(), TraderPlugin.SHOP_MANAGER.getJsonShop().jsonObject.toJSONString());
            e.getPlayer().sendPluginMessage(TraderPlugin.getInstance(), "TraderPlugin", packetCustom.toString().getBytes());
    }

}
