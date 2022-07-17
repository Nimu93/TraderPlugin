package com.nimu.TraderPlugin.money;



import com.nimu.TraderPlugin.TraderPlugin;
import com.nimu.TraderPlugin.database.DatabaseUtils;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public class Money {

  int amount;
  Player player;

  public Money(Player player, int amount){
    this.amount = amount;
    this.player = player;
  }

  public int AddMoney(int quantity) throws SQLException {
      amount += quantity;
      DatabaseUtils.UpdateMoney(TraderPlugin.DATABASE, this);
      return amount;
  }
    public int RemoveMoney(int quantity) throws SQLException {
        amount -= quantity;
        DatabaseUtils.UpdateMoney(TraderPlugin.DATABASE, this);
        return amount;
    }
  public void Translate(Money otherPlayer, int quantity) throws SQLException {
      if (!otherPlayer.player.isDead() && TraderPlugin.getInstance().getServer().getPlayer(otherPlayer.player.getUniqueId()) != null){
          if (amount - quantity > 0){
            otherPlayer.AddMoney(quantity);
            RemoveMoney(quantity);
        }
      else {
          player.sendMessage("Not enough money");
      }
      }else {
          player.sendMessage("Player " + otherPlayer.player.getDisplayName() + " is not connected !");
      }
      player.sendMessage("You sent " + quantity + " to " + otherPlayer.player.getDisplayName());
  }

  public int getAmount(){
      return amount;
  }

  public Player getPlayer(){
      return player;
  }

}
