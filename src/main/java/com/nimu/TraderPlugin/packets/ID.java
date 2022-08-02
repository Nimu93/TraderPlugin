package com.nimu.TraderPlugin.packets;

public enum ID {
    JSONSHOP(0),
    MONEY(1),
    CHECKVERIF(2),
    DONE(3);
    ID(int i){
        this.i = i;
    }
    private final int i;

    public int getType()
    {
        return i;
    }
}
