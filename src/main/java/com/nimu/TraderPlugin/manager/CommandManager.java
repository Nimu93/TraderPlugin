package com.nimu.TraderPlugin.manager;

import com.nimu.TraderPlugin.commands.CommandMoney;
import com.nimu.TraderPlugin.guis.ShopGui;
import com.nimu.TraderPlugin.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;

public class CommandManager implements CommandExecutor{
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p = (Player) commandSender;
        if (strings.length == 0 ||strings[0].equalsIgnoreCase("help") || strings.length >= 4){
            return CommandMoney.showHelp(p);
        }
        if (strings[0].equalsIgnoreCase("show")){
            if (strings.length == 1){
                return CommandMoney.showMoney(p);
            }
            if (strings.length >= 3){
                p.sendMessage("Too many argument");
                return false;
            }
            return CommandMoney.showMoney(p, Bukkit.getPlayer(strings[1]));
        }
        if (strings[0].equalsIgnoreCase("shop")){
            new ShopGui().openInventory((HumanEntity) commandSender);
        }

        if (strings[0].equalsIgnoreCase("add") && strings.length == 3){
            if (!Utils.isInteger(strings[2])){
                p.sendMessage(strings[2] + " is not an integer !");
                return false;
            }
            int quantity = Integer.parseInt(strings[2]);
            Player otherp = null;
            if (Bukkit.getPlayer(strings[1]) != null){otherp = Bukkit.getPlayer(strings[1]);}
            if (otherp == null){p.sendMessage(strings[1] + " doesn't exist"); return false;}
            return CommandMoney.AdminMoneyManage(p, otherp,quantity, false);
        }
        if (strings[0].equalsIgnoreCase("remove") && strings.length == 3){
            if (!Utils.isInteger(strings[2])){
                p.sendMessage(strings[2] + " is not an integer !");
                return false;
            }
            int quantity = Integer.parseInt(strings[2]);
            Player otherp = null;
            if (Bukkit.getPlayer(strings[1]) != null){otherp = Bukkit.getPlayer(strings[1]);}
            if (otherp == null){p.sendMessage(strings[1] + " don't exist"); return false;}
            return CommandMoney.AdminMoneyManage(p, otherp,quantity, true);
        }
    if (strings[0].equalsIgnoreCase("pay") && strings.length == 3){
        if (!Utils.isInteger(strings[2])){
            p.sendMessage(strings[2] + " is not an integer !");
            return false;
        }
        int quantity = Integer.parseInt(strings[2]);
        Player otherp = null;
        if (Bukkit.getPlayer(strings[1]) != null){otherp = Bukkit.getPlayer(strings[1]);}
        if (otherp == null){p.sendMessage(strings[1] + " doesn't exist"); return false;}
        return CommandMoney.PayPlayer(p, otherp,quantity);
    }
        return CommandMoney.showHelp(p);

    }
}
