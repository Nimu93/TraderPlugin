package com.nimu.TraderPlugin.database;

import com.google.common.base.Function;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    public Connection connection;
    public String NameDB;
    public File DBfile;
    public Database(String path) throws IOException {
        NameDB = "TraderPlugin";
        setupConnection(path);
    }
    private void setupConnection(String path) throws IOException {
        File pathtemp = new File(path);
        if (!pathtemp.isDirectory()){
            pathtemp.mkdir();
        }
        DBfile = new File(path + File.separator + NameDB + ".db");

        try {
            Class.forName("org.sqlite.JDBC");

            connection = DriverManager.getConnection("jdbc:sqlite:" + DBfile.getAbsolutePath());
        } catch (SQLException | ClassNotFoundException throwables) {
            System.out.println(throwables.getMessage());
        }
    }

    public void Finish(){
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }


    //connection = DriverManager.getConnection("jdbc:sqlite:" + file.getAbsolutePath());
}
