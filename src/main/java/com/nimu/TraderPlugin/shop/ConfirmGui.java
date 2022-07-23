package com.nimu.TraderPlugin.shop;

import com.nimu.TraderPlugin.guis.GuiUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ConfirmGui implements Listener {
    private final Inventory ConfirmInventory;
    boolean isBuying;
    String title;

    public ConfirmGui(boolean isBuying){
        this.isBuying = isBuying;
        title = "Confirm " + (isBuying ? "Buy":"Sell");
        ConfirmInventory = Bukkit.createInventory(null, 27,title);
        initGui();
    }

    private void initGui(){
        for (int _ =0; _ < 8; _++) {
            ConfirmInventory.setItem(_,GuiUtils.createGuiItem(Material.STAINED_GLASS_PANE, "&eConfirm",15, "CONFIRM"));
        }
        ConfirmInventory.addItem(GuiUtils.createGuiItem(Material.WOOL, "&eConfirm "+title,13, "Click here for confirm "+title));
        ConfirmInventory.addItem(GuiUtils.createGuiItem(Material.WOOL, "&eCancel "+title,14, "Click here for cancel "+title));
        for (int _ =0; _ < 8; _++) {
            ConfirmInventory.setItem(_,GuiUtils.createGuiItem(Material.STAINED_GLASS_PANE, "&eConfirm",15, "CONFIRM"));
        }
    }

    public void openInventory(final HumanEntity ent) {
        ent.openInventory(ConfirmInventory);
    }
    @EventHandler
    private void inventoryClick(InventoryClickEvent e)
    {
        if (e.getCurrentItem() == null || (e.getCurrentItem().getType().equals(Material.AIR))){
            return;
        }
        if (e.getCurrentItem().equals(new ItemStack(Material.WOOL,13))){

        }
        if (e.getCurrentItem() != null){
            e.setCancelled(true);
        }
    }
}
