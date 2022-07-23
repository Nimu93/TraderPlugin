package com.nimu.TraderPlugin.listeners;

import com.nimu.TraderPlugin.TraderPlugin;
import com.nimu.TraderPlugin.guis.ConfirmGui;
import com.nimu.TraderPlugin.guis.NumberBuyGui;
import com.nimu.TraderPlugin.guis.ShopGui;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ListenerGuis implements Listener {
    public static void registerListenerGuis(){
        TraderPlugin.getInstance().getServer().getPluginManager().registerEvents(new ShopGui(), TraderPlugin.getInstance());
    }

    @EventHandler
    public void inventoryClick(InventoryClickEvent e){
        if (e.getCurrentItem() == null || (e.getCurrentItem().getType().equals(Material.AIR))){
            return;
        }
        if (e.getClickedInventory().getName().equals("Trader Shop")){
            ShopGui.inventoryClick(e);
        }
        else if (e.getClickedInventory().getName().startsWith("Number")){
            NumberBuyGui.inventoryClick(e);
        }
        else if (e.getClickedInventory().getName().startsWith("Confirm")){
            ConfirmGui.inventoryClick(e);
        }
    }
}
