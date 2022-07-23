package com.nimu.TraderPlugin;

import com.nimu.TraderPlugin.database.Database;
import com.nimu.TraderPlugin.guis.GuiUtils;
import com.nimu.TraderPlugin.guis.ShopGui;
import com.nimu.TraderPlugin.listeners.ListenerGuis;
import com.nimu.TraderPlugin.listeners.MoneyListener;
import com.nimu.TraderPlugin.manager.CommandManager;
import com.nimu.TraderPlugin.manager.MoneyManager;
import com.nimu.TraderPlugin.manager.ShopManager;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.logging.Logger;

public class TraderPlugin extends JavaPlugin {
    private static TraderPlugin INSTANCE;
    public final String PATH = this.getDataFolder().getPath();
    public static final Logger LOGGER = Logger.getLogger("TraderPlugin");
    public static Database DATABASE;
    public static MoneyManager MONEY_MANAGER;
    public static ShopManager SHOP_MANAGER;

    @Override
    public void onEnable(){
        try {
            DATABASE = new Database(PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        MONEY_MANAGER = new MoneyManager(DATABASE);
        INSTANCE = this;
        SHOP_MANAGER = new ShopManager();
        this.getCommand("t").setExecutor(new CommandManager());
        getServer().getPluginManager().registerEvents(new MoneyListener(), this);
        ListenerGuis.registerListenerGuis();
        getServer().getPluginManager().registerEvents(new ListenerGuis(), this);

    }

    public static TraderPlugin getInstance(){
        return INSTANCE;
    }
}
