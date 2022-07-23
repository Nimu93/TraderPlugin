package com.nimu.TraderPlugin.shop;

import com.nimu.TraderPlugin.TraderPlugin;
import com.nimu.TraderPlugin.manager.ShopManager;
import com.nimu.TraderPlugin.money.Money;
import com.nimu.TraderPlugin.utils.InventoryUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class PlayerActionShop {
    private static boolean canSellItems(HumanEntity humanEntity, ItemSell itemStack, int amount){
        Inventory playerInventory = humanEntity.getInventory();
        int quantity =0;
        for (int i = 0; i < playerInventory.getSize()-5; i++){
            if (playerInventory.getItem(i) == null){
                continue;
            }
            if (playerInventory.getItem(i).getType().equals(Material.valueOf(itemStack.getName().toUpperCase()))){
                quantity += playerInventory.getItem(i).getAmount();
            }
        }
        return quantity >= amount;
    }
    private static boolean canBuyItems(HumanEntity humanEntity, ItemSell itemSell, int amount){
        Money moneyPlayer = TraderPlugin.MONEY_MANAGER.getMoneyForPlayer(Bukkit.getPlayer(humanEntity.getUniqueId()));
        int priceOfItem = itemSell.getPrice_to_buy();

        return (moneyPlayer.getAmount() >= amount*priceOfItem) &&
                (humanEntity.getInventory().getMaxStackSize()*(humanEntity.getInventory().getSize()-5)
                        >= InventoryUtils.StackAvailableSlot(humanEntity, new ItemStack(Material.valueOf(itemSell.getName().toUpperCase()))));
    }

    public static boolean SellItem(HumanEntity humanEntity, ItemSell itemStack, int amount){
        Money moneyPlayer = TraderPlugin.MONEY_MANAGER.getMoneyForPlayer(Bukkit.getPlayer(humanEntity.getUniqueId()));
        int sellPriceOfItem = itemStack.getPrice_to_sell();
        if (canSellItems(humanEntity,itemStack,amount)){
            moneyPlayer.AddMoney(sellPriceOfItem*amount);
            InventoryUtils.RemoveItems(humanEntity, new ItemStack(Material.valueOf(itemStack.getName().toUpperCase())), amount);
            return true;
        }
        return false;
    }
    public static boolean BuyItem(HumanEntity humanEntity, ItemSell itemSell, int amount){
        Money moneyPlayer = TraderPlugin.MONEY_MANAGER.getMoneyForPlayer(Bukkit.getPlayer(humanEntity.getUniqueId()));
        int priceOfItem = itemSell.getPrice_to_buy();
        if (canBuyItems(humanEntity, itemSell, amount)){
            moneyPlayer.RemoveMoney(priceOfItem*amount);
            InventoryUtils.AddItems(humanEntity, new ItemStack(Material.valueOf(itemSell.getName().toUpperCase())), amount);
            return true;
        }
        return false;
    }
}
