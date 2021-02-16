package com.outlast.gems.database;

public class PlayerCache {


    private int gems;




    public int getGems() {
        return gems;
    }

    public void setGems(int gems) {
        this.gems = gems;
    }

    public PlayerCache(final int gems) {
        this.gems = gems;
    }

    public PlayerCache() {

        this.gems = 0;
    }


}
