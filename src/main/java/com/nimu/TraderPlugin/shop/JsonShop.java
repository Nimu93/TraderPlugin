package com.nimu.TraderPlugin.shop;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.nimu.TraderPlugin.TraderPlugin;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;


public class JsonShop {
    private File jsonFile;
    private JSONObject jsonObject;
    private JSONParser jsonParser;
    private ArrayList<ItemSell> itemsSell;

    public JsonShop(String path){
        refreshConnection(path);
    }
    private void setupConnection(String path) throws IOException {
        File pathtemp = new File(path);
        if (!pathtemp.isDirectory()) {
            pathtemp.mkdir();
        }
        jsonFile = new File(path + File.separator + "TraderShop.json");
        if (!jsonFile.exists()){
            Files.copy(TraderPlugin.getInstance().getResource("TraderShop.json"), Paths.get(path + File.separator + "TraderShop.json"));
            jsonFile = new File(path + File.separator + "TraderShop.json");
        }
    }
    public void refreshConnection(String path){
        try {
            setupConnection(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        jsonParser = new JSONParser();
        try {
            jsonObject = (JSONObject) jsonParser.parse(new InputStreamReader(new FileInputStream(jsonFile), "UTF-8"));
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        itemsSell = LoadItem();

    }
    private ArrayList<ItemSell> LoadItem(){
        JSONArray json = (JSONArray) jsonObject.get("JsonShop");
        ArrayList<ItemSell> res = new ArrayList<ItemSell>();
        System.out.println(json.get(0).toString());
        Gson gson = new Gson();
        for (int i = 0; i <json.size(); i++){
           res.add(gson.fromJson(json.get(i).toString(), ItemSell.class));
        }
        return res;
    }

    public void SaveItem(String path)  {
        Gson gson = new Gson();
        try {
            FileWriter jsonFilew = new FileWriter(jsonFile);
            jsonFilew.write(gson.toJson(itemsSell));
            jsonFilew.close();
            jsonFile = new File(path + File.separator + "TraderShop.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public ItemSell getItemFromName(Material material){
        String namemat = material.name();
        for (ItemSell itemSell:itemsSell) {
            if (namemat.equalsIgnoreCase(itemSell.getName())){
                return itemSell;
            }
        }
        return null;
    }
    public ArrayList<ItemSell> getItemsSell(){
        return itemsSell;
    }

    }
