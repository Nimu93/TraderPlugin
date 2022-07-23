package com.nimu.TraderPlugin.shop;

public class ItemSell {
    String name;
    int price_to_sell;
    int price_to_buy;

    public ItemSell(){}
    public ItemSell(String name, int price_to_sell, int price_to_buy){
        this.name = name;
        this.price_to_buy = price_to_buy;
        this.price_to_sell = price_to_sell;
    }

    public String getName(){
        return name;
    }
    public int getPrice_to_sell(){
        return price_to_sell;
    }
    public int getPrice_to_buy(){
        return price_to_buy;
    }

    public void setName(){
        this.name = name;
    }
    public void setPrice_to_sell(){ this.price_to_sell = price_to_sell;
    }
    public void setPrice_to_buy(){
        this.price_to_buy = price_to_buy;
    }
  /*  @Override
    public String toString(){
        return "ItemSell [price_buy=" + price_to_buy + ", price_sell=" + price_to_sell + ", item_name=" + name + "]";
    }*/
}
