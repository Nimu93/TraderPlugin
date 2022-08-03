package com.nimu.TraderPlugin.packets;

import com.nimu.TraderPlugin.TraderPlugin;
import com.nimu.TraderPlugin.shop.PlayerActionShop;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.io.Console;

public class PacketListener implements PluginMessageListener {
    @Override
    public void onPluginMessageReceived(String s, Player player, byte[] bytes) {
        if (s.equals("TraderPlugin")){
        String content = new String(bytes);
        PacketCustom packetCustom = PacketCustom.getPacket(content);
        assert packetCustom != null;
        if (packetCustom.id == ID.CHECKVERIF.getType()){

            String[] arg = packetCustom.message.split("!");
            if (arg[2].equals("false")){
            if(PlayerActionShop.BuyItem(player, TraderPlugin.SHOP_MANAGER.getJsonShop().getItemFromName(Material.getMaterial(arg[1].toUpperCase())), Integer.parseInt(arg[0]))
            ){
                player.sendMessage(ChatColor.GREEN +"You buy it");
            }
            else {player.sendMessage(ChatColor.RED +"Not enough money");}
            }
            else {
                PlayerActionShop.SellItem(player, TraderPlugin.SHOP_MANAGER.getJsonShop().getItemFromName(Material.getMaterial(arg[1].toUpperCase())), Integer.parseInt(arg[0]));

            }
        }
    }
    }
}
