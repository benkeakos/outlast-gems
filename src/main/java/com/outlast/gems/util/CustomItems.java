package com.outlast.gems.util;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CustomItems {

    private final ItemStack iron;


    public ItemStack getIron() {
        return iron;
    }



    //
    //  YOU CAN NOT USE THESE ITEMS INSIDE OF A GUI
    //
    //


    public CustomItems() {



        // IRON

        final ItemStack iron = new ItemStack(Material.IRON_INGOT);
        final ItemMeta ironMeta = iron.getItemMeta();
        ironMeta.setDisplayName("§f§lPlaceholder Item");
        iron.setItemMeta(ironMeta);

        this.iron = iron;





    }




}
