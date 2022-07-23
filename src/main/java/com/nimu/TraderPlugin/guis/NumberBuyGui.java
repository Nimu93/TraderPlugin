package com.nimu.TraderPlugin.guis;

import com.nimu.TraderPlugin.TraderPlugin;
import com.nimu.TraderPlugin.shop.ItemSell;
import com.nimu.TraderPlugin.utils.InventoryUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class NumberBuyGui implements Listener {
    private final Inventory NumInventory;
    static boolean isBuying;
    static String title;
    static ItemSell itemSell;
    public NumberBuyGui(boolean isBuying, ItemSell itemSell){
        NumberBuyGui.isBuying = isBuying;
        title = "Number " + (isBuying ? "Buy":"Sell");
        NumInventory = Bukkit.createInventory(null, 27,title);
        NumberBuyGui.itemSell = itemSell;
        initGui();
    }

    private void initGui(){
        Material m = Material.valueOf(itemSell.getName().toUpperCase());
        ItemStack one =GuiUtils.createGuiItem(m, title+" one", 0);
        ItemStack stack = GuiUtils.createGuiItem(m, title+" stack", 0);;
        ItemStack max =GuiUtils.createGuiItem(m, title+" max you have", 0);;
        NumInventory.addItem(one,stack,max);
    }

    public void openInventory(final HumanEntity ent) {
        ent.openInventory(NumInventory);
    }

    public static void inventoryClick(InventoryClickEvent e)
    {
        if (e.getCurrentItem() == null || (e.getCurrentItem().getType().equals(Material.AIR))){
            return;
        }
        if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getType().equals(Material.valueOf(itemSell.getName().toUpperCase()))){
            int number = 0;
            if(e.getCurrentItem().getItemMeta().getDisplayName().equals(title+" one")){
                number = 1;
            }
            else if(e.getCurrentItem().getItemMeta().getDisplayName().equals(title+" stack")){
                number = Material.valueOf(itemSell.getName().toUpperCase()).getMaxStackSize();
            }
            else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(title+" max you have")){
           if (isBuying) {
               number = InventoryUtils.StackAvailableSlot(e.getWhoClicked(), new ItemStack((Material.valueOf(itemSell.getName().toUpperCase()))));
            }else {
                number = InventoryUtils.StackOccupiedSlot(e.getWhoClicked(), new ItemStack((Material.valueOf(itemSell.getName().toUpperCase()))));
           }
            }
            ConfirmGui confirmGui = new ConfirmGui(isBuying, itemSell, number);
            e.getWhoClicked().closeInventory();
            confirmGui.openInventory(e.getWhoClicked());
        }
        if (e.getCurrentItem() != null){
            e.setCancelled(true);
        }
    }
}
