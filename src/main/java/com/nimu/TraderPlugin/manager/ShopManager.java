package com.nimu.TraderPlugin.manager;

import com.nimu.TraderPlugin.TraderPlugin;
import com.nimu.TraderPlugin.shop.ItemSell;
import com.nimu.TraderPlugin.shop.JsonShop;


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
}
