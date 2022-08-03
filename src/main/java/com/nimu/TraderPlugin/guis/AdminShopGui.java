package com.nimu.TraderPlugin.guis;

import com.nimu.TraderPlugin.TraderPlugin;
import com.nimu.TraderPlugin.shop.ItemSell;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;


public class AdminShopGui implements Listener {
    private final Inventory ShopInventory;

    public AdminShopGui(){
        ShopInventory = Bukkit.createInventory(null, 27, "Admin Trader Shop");
        initGui();
    }

    private void initGui(){
        for (ItemSell itemSell: TraderPlugin.SHOP_MANAGER.getJsonShop().getItemsSell()) {
            ShopInventory.addItem(GuiUtils.createGuiItem(Material.valueOf(itemSell.getName().toUpperCase()), itemSell.getName(),
                    0,
                    "Sell Price: " + itemSell.getPrice_to_sell() + " (Left click for buying)",
                    "Buy Price: " + itemSell.getPrice_to_buy() + "  (Right click for buying)","If you want to remove this item left click on it"));
        }
    }

    public void openInventory(final HumanEntity ent) {
        ent.openInventory(ShopInventory);
    }

    public static void inventoryClick(InventoryClickEvent e)
    {

        if (e.getCurrentItem() == null || (e.getCurrentItem().getType().equals(Material.AIR))){
            return;
        }
        if (e.getCurrentItem() != null){
           if (e.isLeftClick()){
               TraderPlugin.SHOP_MANAGER.RemoveItem(TraderPlugin.SHOP_MANAGER.getJsonShop().getItemFromName(e.getCurrentItem().getType()));
                e.getWhoClicked().closeInventory();
           }
            e.setCancelled(true);
        }
    }
}
