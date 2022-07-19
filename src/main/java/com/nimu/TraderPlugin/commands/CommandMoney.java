package com.nimu.TraderPlugin.commands;

import com.nimu.TraderPlugin.TraderPlugin;
import com.nimu.TraderPlugin.money.Money;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.sql.SQLException;
import java.util.logging.Level;

public class CommandMoney {
    public static boolean showHelp(Player p){
        if (!p.isOnline()){
            p.sendMessage(ChatColor.RED + "NOT POSSIBLE");
            return false;
        }
        else {
            p.sendMessage("After");
            return true;
        }
    }
    public static boolean showMoney(Player p){
        if (!p.hasPermission("traderplugin.everyone.show")){
            p.sendMessage(ChatColor.RED + "You don't have the permission to do this!");
            return false;}
        if (!p.isOnline()){
            p.sendMessage(ChatColor.RED + "NOT POSSIBLE");
            return false;
        }
        try {
            Money playerMoney = TraderPlugin.MONEY_MANAGER.getMoneyForPlayer(p);
            if (playerMoney == null){
                p.sendMessage(ChatColor.RED + "CRITICAL ERROR send message to Admin");
                return false;
            }
            p.sendMessage(ChatColor.GREEN + "You have " + playerMoney.getAmount() +ChatColor.AQUA + "$");
        } catch (SQLException throwables) {
            TraderPlugin.LOGGER.log(Level.SEVERE, throwables.getMessage());
        }
        return true;
    }

    public static boolean showMoney(Player p, Player otherp){
        if (!p.hasPermission("traderplugin.everyone.show")){
            p.sendMessage(ChatColor.RED + "You don't have the permission to do this!");
            return false;}
        if (!p.isOnline()){
            p.sendMessage(ChatColor.RED + "NOT POSSIBLE");
            return false;
        }
        try {
            Money playerMoney = TraderPlugin.MONEY_MANAGER.getMoneyForPlayer(otherp);
            if (playerMoney == null){
                p.sendMessage(ChatColor.GREEN + "The player doesn't have money");
                return false;
            }
            p.sendMessage(ChatColor.GREEN + "You have " + playerMoney.getAmount() +ChatColor.AQUA + "$");
        } catch (SQLException throwables) {
            TraderPlugin.LOGGER.log(Level.SEVERE, throwables.getMessage());
        }
        return true;
    }

    public static boolean AdminMoneyManage(Player p, Player otherp, int quantity, boolean isRemoving){
        try {
            Money player = TraderPlugin.MONEY_MANAGER.getMoneyForPlayer(otherp);
            if (player == null){
                p.sendMessage(ChatColor.RED + "CRITICAL");
                return false;
            }
            if (!isRemoving) {
                if (!p.hasPermission("traderplugin.admin.add")){
                    p.sendMessage(ChatColor.RED + "You don't have the permission to do this!");
                    return false;}
                player.AddMoney(quantity);
            }
            else {
                if (!p.hasPermission("traderplugin.admin.remove")){
                    p.sendMessage(ChatColor.RED + "You don't have the permission to do this!");
                    return false;}
                player.RemoveMoney(quantity);
            }
            p.sendMessage(ChatColor.GREEN + "The player have now " + player.getAmount() + ChatColor.AQUA+"$");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    public static boolean PayPlayer(Player p, Player otherp, int quantity){
        if (!p.hasPermission("traderplugin.everyone.pay")){
            p.sendMessage(ChatColor.RED + "You don't have the permission to do this!");
            return false;}
        if (!p.isOnline()){
            p.sendMessage(ChatColor.RED + "NOT POSSIBLE");
            return false;
        }
        try {
            Money player = TraderPlugin.MONEY_MANAGER.getMoneyForPlayer(p);
            player.Translate(TraderPlugin.MONEY_MANAGER.getMoneyForPlayer(otherp), quantity);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }
}
