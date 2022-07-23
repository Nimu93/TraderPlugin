package com.nimu.TraderPlugin.guis;

import com.nimu.TraderPlugin.TraderPlugin;
import com.nimu.TraderPlugin.shop.ItemSell;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ShopGui implements Listener {
    private final Inventory ShopInventory;

    public ShopGui(){
        ShopInventory = Bukkit.createInventory(null, 27, "Trader Shop");
        initGui();
    }

    private void initGui(){
        for (ItemSell itemSell:TraderPlugin.SHOP_MANAGER.getJsonShop().getItemsSell()) {
            ShopInventory.addItem(GuiUtils.createGuiItem(Material.valueOf(itemSell.getName().toUpperCase()), itemSell.getName(),
                    0,
                    "Sell Price: " + itemSell.getPrice_to_sell() + " (Left click for buying)",
                    "&bBuy Price: " + itemSell.getPrice_to_buy() + "  (Right click for buying)"));
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
                ItemStack itemStack = e.getCurrentItem();
                if (itemStack.hasItemMeta() && itemStack.getItemMeta().hasLore()){
                    NumberBuyGui numberBuyGui = new NumberBuyGui(false, TraderPlugin.SHOP_MANAGER.getJsonShop().getItemFromName(itemStack.getType()));
                    e.getWhoClicked().closeInventory();
                    numberBuyGui.openInventory(e.getWhoClicked());

                }
            }
            else if (e.isRightClick()){
                ItemStack itemStack = e.getCurrentItem();
                if (itemStack.hasItemMeta() && itemStack.getItemMeta().hasLore()){
                    NumberBuyGui numberBuyGui = new NumberBuyGui(true, TraderPlugin.SHOP_MANAGER.getJsonShop().getItemFromName(itemStack.getType()));
                    e.getWhoClicked().closeInventory();
                    numberBuyGui.openInventory(e.getWhoClicked());
                }
            }
            e.setCancelled(true);
        }
    }
}
