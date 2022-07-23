package com.nimu.TraderPlugin.guis;

import com.nimu.TraderPlugin.TraderPlugin;
import com.nimu.TraderPlugin.guis.GuiUtils;
import com.nimu.TraderPlugin.shop.ItemSell;
import com.nimu.TraderPlugin.shop.PlayerActionShop;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ConfirmGui implements Listener {
    private final Inventory ConfirmInventory;
    static boolean isBuying;
    String title;
    static ItemSell itemSell;
    static int number;
    public ConfirmGui(){
        ConfirmInventory = Bukkit.createInventory(null, 27,"Example");
    }
    public ConfirmGui(boolean isBuying, ItemSell itemSell, int number){
        ConfirmGui.isBuying = isBuying;
        title = "Confirm " + (isBuying ? "Buy":"Sell");
        ConfirmInventory = Bukkit.createInventory(null, 27,title);
        ConfirmGui.itemSell = itemSell;
        ConfirmGui.number = number;
        initGui();
    }

    private void initGui(){
        for (int _ =0; _ < 8; _++) {
            ConfirmInventory.setItem(_,GuiUtils.createGuiItem(Material.STAINED_GLASS_PANE, "&eConfirm",15, "CONFIRM"));
        }
        ConfirmInventory.addItem(GuiUtils.createGuiItem(Material.WOOL, "Confirm "+title,13, " Click here for confirm "+title));
        ConfirmInventory.addItem(GuiUtils.createGuiItem(Material.WOOL, "Cancel "+title,14, " Click here for cancel "+title));
        for (int _ =0; _ < 8; _++) {
            ConfirmInventory.setItem(_,GuiUtils.createGuiItem(Material.STAINED_GLASS_PANE, "&eConfirm",15, "CONFIRM"));
        }
    }

    public void openInventory(final HumanEntity ent) {
        ent.openInventory(ConfirmInventory);
    }
    public static void inventoryClick(InventoryClickEvent e)
    {

        if (e.getCurrentItem() == null || (e.getCurrentItem().getType().equals(Material.AIR))){
            return;
        }
        if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().startsWith("Confirm")){
            if (isBuying) {
                if (!PlayerActionShop.BuyItem(e.getWhoClicked(), itemSell, number)) {
                    e.getWhoClicked().closeInventory();
                    e.getWhoClicked().sendMessage(ChatColor.RED + "You can't buy this !");
                    new ShopGui().openInventory(e.getWhoClicked());
                } else {
                    e.getWhoClicked().closeInventory();
                    e.getWhoClicked().sendMessage(ChatColor.GREEN + "Congratulation for your purchase: " + itemSell.getName() + " for " + itemSell.getPrice_to_buy()*number);
                }
            } else {
                if (!PlayerActionShop.SellItem(e.getWhoClicked(), itemSell, number)){
                    e.getWhoClicked().closeInventory();
                    e.getWhoClicked().sendMessage(ChatColor.RED + "You can't sell this !");
                    new ShopGui().openInventory(e.getWhoClicked());
            }else {
                e.getWhoClicked().closeInventory();
                e.getWhoClicked().sendMessage(ChatColor.GREEN + "Congratulation for your sale: " + itemSell.getName() + " for " + itemSell.getPrice_to_sell()*number);
            }
            }

        }
        if (e.getCurrentItem() != null){
            e.setCancelled(true);
        }
    }
}
