package com.nimu.TraderPlugin.listeners;


import com.nimu.TraderPlugin.TraderPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.SQLException;

public class MoneyListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) throws SQLException {
        TraderPlugin.MONEY_MANAGER.AddPlayerMoney(event.getPlayer());
    }
}
