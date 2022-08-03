package com.nimu.TraderPlugin.commands;

import com.nimu.TraderPlugin.TraderPlugin;
import com.nimu.TraderPlugin.guis.AdminShopGui;
import com.nimu.TraderPlugin.manager.ShopManager;
import com.nimu.TraderPlugin.shop.ItemSell;
import org.bukkit.ChatColor;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CommandShopConfig {
    private static ShopManager shopManager = TraderPlugin.SHOP_MANAGER;
    public static void AddItemToShop(Player p, int price_buy, int price_sell){
        if (p.getItemInHand() != null){
            ItemStack itemStack = p.getItemInHand();
            if (shopManager.getJsonShop().getItemsSell().contains(new ItemSell(itemStack.getType().name(), price_sell, price_buy))){
                p.sendMessage(ChatColor.RED + "You have already this item in the shop");
                return;
            }
            shopManager.AddItem(new ItemSell(itemStack.getType().name(), price_sell, price_buy));
            p.sendMessage(ChatColor.GREEN + itemStack.getType().name() + " is added to the shop, do a save!");
        }
        else {
            p.sendMessage(ChatColor.RED + "You don't have item in your hand");
        }
    }
    public static void ShowItemInShop(Player p){
           new AdminShopGui().openInventory(p);
    }
    public static void SaveChangeInShop(){
        shopManager.SaveChange();
    }
}
