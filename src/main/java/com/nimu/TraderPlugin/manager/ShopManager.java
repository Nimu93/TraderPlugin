package com.nimu.TraderPlugin.manager;

import com.nimu.TraderPlugin.TraderPlugin;
import com.nimu.TraderPlugin.shop.ItemSell;
import com.nimu.TraderPlugin.shop.JsonShop;
import org.bukkit.inventory.ItemStack;


public class ShopManager {
    JsonShop JsonShop;

    public ShopManager() {
        JsonShop = new JsonShop(TraderPlugin.getInstance().PATH);
    }
    public void AddItem(ItemSell itemSell){
        if (JsonShop.getItemsSell().contains(itemSell)){return;}
        JsonShop.getItemsSell().add(itemSell);
    }
    public void RemoveItem(ItemSell itemSell){
        if (!JsonShop.getItemsSell().contains(itemSell)){return;}
        JsonShop.getItemsSell().remove(itemSell);
    }
    public void SaveChange(){
        JsonShop.SaveItem(TraderPlugin.getInstance().PATH);
        JsonShop.refreshConnection(TraderPlugin.getInstance().PATH);
    }
    public void ChangeItem(ItemSell itemSell){
        RemoveItem(itemSell);
        AddItem(itemSell);
    }
    public int getPriceOfItem(ItemStack stack){
        for (ItemSell itemsell:JsonShop.getItemsSell()) {
            if (itemsell.getName().equals(stack.getType().name())){
                return itemsell.getPrice_to_buy();
            }
        }
        return -1;
    }
    public int getSellPriceOfItem(ItemStack stack){
        for (ItemSell itemsell:JsonShop.getItemsSell()) {
            if (itemsell.getName().equals(stack.getType().name())){
                return itemsell.getPrice_to_sell();
            }
        }
        return -1;
    }

    public com.nimu.TraderPlugin.shop.JsonShop getJsonShop() {
        return JsonShop;
    }
}
