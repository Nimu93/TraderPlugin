package com.nimu.TraderPlugin;

import com.nimu.TraderPlugin.database.Database;
import com.nimu.TraderPlugin.manager.MoneyManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.logging.Logger;

public class TraderPlugin extends JavaPlugin {
    private static TraderPlugin INSTANCE;
    public final String PATH = this.getDataFolder().getPath();
    public static final Logger LOGGER = Logger.getLogger("SimpleClans");
    public static Database DATABASE;
    public static MoneyManager MONEY_MANAGER;

    @Override
    public void onEnable(){
        try {
            DATABASE = new Database(PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        MONEY_MANAGER = new MoneyManager(DATABASE);
        INSTANCE = this;
    }

    public static TraderPlugin getInstance(){
        return INSTANCE;
    }
}
