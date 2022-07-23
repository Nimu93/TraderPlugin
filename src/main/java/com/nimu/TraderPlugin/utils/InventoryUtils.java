package com.nimu.TraderPlugin.utils;

import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryUtils {
    public static void RemoveItems(HumanEntity humanEntity, ItemStack itemStack, int quantity){
        Inventory inventory = humanEntity.getInventory();
        for (int i = 0; i < inventory.getSize()-5; i++) {
            if (inventory.getItem(i) == null){
                continue;
            }
            if(quantity <= 0){
                return;
            }
            ItemStack itemStack1 = inventory.getItem(i);
            if (itemStack1.getType().equals(itemStack.getType())){
                if (itemStack1.getAmount() - quantity < 0){
                    inventory.setItem(i, null);
                    quantity-=itemStack.getAmount();
                }
                else {
                    inventory.getItem(i).setAmount(itemStack1.getAmount() - quantity);
                    quantity = 0;
                }
            }
        }
    }
    public static void AddItems(HumanEntity humanEntity, ItemStack itemStack, int quantity){
        Inventory inventory = humanEntity.getInventory();
        for (int i = 0; i < inventory.getSize()-5; i++) {
            if(quantity == 0){
                return;
            }
            if (inventory.getItem(i) == null){
                ItemStack res = itemStack;
                if (quantity - itemStack.getMaxStackSize() < 0){
                    res.setAmount(quantity);
                    quantity = 0;
                }else {
                    res.setAmount(itemStack.getMaxStackSize());
                    quantity -=itemStack.getMaxStackSize();
                }
                inventory.setItem(i, res);

            }
            else if(inventory.getItem(i).getType().equals(itemStack.getType())){
                inventory.getItem(i).setAmount(quantity);
                quantity -= itemStack.getMaxStackSize();
            }
        }
    }

    public static int StackAvailableSlot(HumanEntity humanEntity, ItemStack itemStack){
        int res = 0;
        Inventory inventory = humanEntity.getInventory();
        for (int i = 0; i < inventory.getSize()-5; i++) {
                if (inventory.getItem(i) != null){
                    if (inventory.getItem(i).getType().equals(itemStack.getType())){
                       res += itemStack.getMaxStackSize()-inventory.getItem(i).getAmount();
                    }
                }
                else {
                    res+=itemStack.getMaxStackSize();
                }
            }
        return res;
    }
    public static int StackOccupiedSlot(HumanEntity humanEntity, ItemStack itemStack){
        int res = 0;
        Inventory inventory = humanEntity.getInventory();
        for (int i = 0; i < inventory.getSize()-5; i++) {
            if (inventory.getItem(i) != null){
                if (inventory.getItem(i).getType().equals(itemStack.getType())){
                    res += inventory.getItem(i).getAmount();
                }
            }
        }
        return res;
    }
}
