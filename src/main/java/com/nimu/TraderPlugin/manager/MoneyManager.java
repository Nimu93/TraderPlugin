package com.nimu.TraderPlugin.manager;



import com.nimu.TraderPlugin.TraderPlugin;
import com.nimu.TraderPlugin.database.Database;
import com.nimu.TraderPlugin.database.DatabaseUtils;
import com.nimu.TraderPlugin.money.Money;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.function.Supplier;
import java.util.logging.Level;

public class MoneyManager {

    public Database database;
   public MoneyManager(Database database){
       this.database = database;
       try {
           ResultSet resultSet = this.database.getConnection().getMetaData().getTables(null, null, "money_table", null);
           if (!resultSet.next()){
               String sql = "CREATE TABLE money_table (playerUUID, playermoney);";
               Statement statement = database.getConnection().createStatement();
               statement.execute(sql);
           }
       } catch (SQLException ex){
           TraderPlugin.LOGGER.log(Level.SEVERE, (Supplier<String>) ex);
       }
   }

   public Money getMoneyForPlayer(Player playerEntity) {
       try {
           int i = DatabaseUtils.getMoney(TraderPlugin.DATABASE, playerEntity.getUniqueId().toString());
           if (i == -1) {

               return null;
           }
           return new Money(playerEntity, i);
       } catch (SQLException throwables) {
           throwables.printStackTrace();
       }
       return null;
   }

   public void AddPlayerMoney(Player playerEntity) throws SQLException {
       DatabaseUtils.AddUserMoney(TraderPlugin.DATABASE, new Money(playerEntity, 500));
   }
}
