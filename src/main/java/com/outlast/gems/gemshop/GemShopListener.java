package com.outlast.gems.gemshop;

import com.outlast.gems.database.CacheManager;
import com.outlast.gems.database.PlayerCache;
import com.outlast.gems.database.PlayerData;
import com.outlast.gems.main.Gems;
import com.outlast.gems.util.CustomItems;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class GemShopListener implements Listener {

    private final Gems main;
    private final CustomItems items;





    public GemShopListener(final Gems main, final CustomItems items) {
        this.main = main;
        this.items = items;
    }

    private void buyItem(Player player, final int playerBalance, final int cost, final ItemStack item, final int amount){

        if (playerBalance >= cost){

            item.setAmount(amount);
            player.getInventory().addItem(item);
            player.sendMessage(item.getAmount() + "x " + item.getItemMeta().getDisplayName() + " §chas been added to your inventory!");
            player.closeInventory();

        } else {

            player.sendMessage("You do not have enough money to buy this item");
            player.closeInventory();

        }


    }



    @EventHandler
    public void shopInteract(InventoryClickEvent x) {

        HumanEntity p = x.getWhoClicked();
        Player pl = Bukkit.getPlayer(x.getWhoClicked().getName());
        final PlayerCache playerCache = CacheManager.playerData.get(p);
        final int balance = playerCache.getGems();


        //COST OF ITEMS
        final int slotZeroCost = 400;




        if (x.getView().getTitle().equals("GemShop")) {

            x.setCancelled(true);
            x.setResult(Event.Result.DENY);

                //SLOT in inventory
            if (x.getSlot() == 0) {

                //USING METHOD TO BUY ITEM
                //PARAMETERS: player, balance of player, cost of the item in the slot (in this case slot 0), item to give player (comes from the CustomItems class), amount
                buyItem(pl, balance, slotZeroCost, items.getIron(), 1);



            }



        }


    }

}