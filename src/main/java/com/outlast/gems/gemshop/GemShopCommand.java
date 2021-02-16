package com.outlast.gems.gemshop;

import com.outlast.gems.gemshop.GemShopGUI;
import com.outlast.gems.main.Gems;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GemShopCommand implements CommandExecutor {


    private Gems main;

    public GemShopCommand(Gems main){

        this.main = main;

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;

        if(player.hasPermission("outlast.gemshop")) {

            GemShopGUI.openClassInventory(player);

        } else {

            player.sendMessage("§6§l[!]§7 You have no permission to perform this command");

        }


        return false;
    }
}
