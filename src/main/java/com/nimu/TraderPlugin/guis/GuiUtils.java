package com.nimu.TraderPlugin.guis;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class GuiUtils {
    public static ItemStack createGuiItem(Material m, String name, int data, String... lore){
        ItemStack item = new ItemStack(m, 1, (short) data);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(name);
        itemMeta.setLore(Arrays.asList(lore));
        item.setItemMeta(itemMeta);
        return item;
    }
}
