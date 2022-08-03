package com.nimu.TraderPlugin.manager;

import com.nimu.TraderPlugin.commands.CommandShopConfig;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandAdminManager implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player player = (Player)commandSender;
        if (!commandSender.isOp()){
            player.sendMessage(ChatColor.RED + "You don't have enough permission");
            return false;
        }
        if (strings.length == 1){
            if (strings[0].equals("show")) {
                CommandShopConfig.ShowItemInShop(player);
                return true;
            }
            if (strings[0].equals("save")){
                CommandShopConfig.SaveChangeInShop();
                return true;
            }
        }
        if (strings.length == 3 && strings[0].equals("add")){
            CommandShopConfig.AddItemToShop(player, Integer.parseInt(strings[1]), Integer.parseInt(strings[2]));
        }
        return false;
    }
}
