package com.outlast.gems.gemshop;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class GemShopGUI {

    public static void openClassInventory(Player p) {


        Inventory inv = Bukkit.createInventory(null, 54,  "GemShop");



        //PLACEHOLDER ITEM WITH PRICE LORE

        final ItemStack iron = new ItemStack(Material.IRON_INGOT);
        final ItemMeta ironMeta = iron.getItemMeta();
        ironMeta.setDisplayName("§f§lPlaceholder Item");
        List<String> ironLore = new ArrayList<String>();

        //USE ironLore.add to add lore to the item. The lore has to be an ArrayList which is initialized above
        //MUST HAVE A SEPERATE ARRAY FOR EACH ITEMSTACK

        ironLore.add(" ");
        ironLore.add("§6* This item costs: §c400 GEMS");


        ironMeta.setLore(ironLore);

        iron.setItemMeta(ironMeta);



        inv.setItem(0, iron);


        p.openInventory(inv);

    }



}
