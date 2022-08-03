package com.nimu.TraderPlugin;

import com.nimu.TraderPlugin.database.Database;
import com.nimu.TraderPlugin.listeners.ListenerShop;
import com.nimu.TraderPlugin.listeners.MoneyListener;
import com.nimu.TraderPlugin.manager.CommandManager;
import com.nimu.TraderPlugin.manager.MoneyManager;
import com.nimu.TraderPlugin.manager.ShopManager;
import com.nimu.TraderPlugin.packets.PacketManager;
import org.bukkit.configuration.file.FileConfiguration;
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
        this.saveDefaultConfig();
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
        if (getConfig().getBoolean("useforge")) {
            ListenerShop.registerListenerGuis();
        }else {
            getServer().getPluginManager().registerEvents(new ListenerShop(), this);
            PacketManager.RegisterChannel();
        }
    }

    public static TraderPlugin getInstance(){
        return INSTANCE;
    }
}
