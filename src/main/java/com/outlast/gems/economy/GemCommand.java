package com.outlast.gems.economy;

import com.outlast.gems.database.CacheManager;
import com.outlast.gems.database.PlayerCache;
import com.outlast.gems.main.Gems;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GemCommand implements CommandExecutor {

    private Gems main;

    public GemCommand(Gems main){

        this.main = main;

    }




    private final String prefix = "§6§l[!]§7 ";
    private final String noperm = prefix + "You have no permission to perform this command";


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

         final Player player = (Player) sender;
        final PlayerCache playerCache = CacheManager.playerData.get(player);


        //  ARGS length 0 commands
        // /gem
        if (args.length == 0) { //


            player.sendMessage(prefix + "Your gems balance is §c" + playerCache.getGems());

        }

        if (args.length == 1) {


            final String arg = args[0].toLowerCase();
            // COMMANDS
            switch (arg) {

                case "help":
                    player.sendMessage("§c§l[§6§lCommands§c§l]");
                    player.sendMessage("§6§l/gems help: §f§cDisplays this menu.");
                    player.sendMessage("§6§l/gems set [username] [amount]: §f§cSet a players gems.");
                    player.sendMessage("§6§l/gems give [username] [amount]: §f§cGive a player gems.");
                    player.sendMessage("§6§l/gems remove [username] [amount]: §f§cTake a players gems.");
                    break;

                default:
                    player.sendMessage(prefix + "Unknown command");
                    break;

            }

        }

        //ARGS length 3 commands
        else
        if (args.length == 3) {

            final int value = Integer.valueOf(args[2]);
            final Player target = Bukkit.getPlayer(args[1]);
            final PlayerCache targetCache = CacheManager.playerData.get(target);
            final int targetCurrentGems = targetCache.getGems();

            final String arg = args[0].toLowerCase();
            // COMMANDS
            switch (arg) {



                case "set":

                    if(player.hasPermission("outlast.admin")){

                        targetCache.setGems(value);

                        player.sendMessage(prefix + "The gems of the target has been updated to: §6" + value);
                        target.sendMessage(prefix + "Your gems have been updated to: §6" + value);

                    } else {

                        player.sendMessage(noperm);


                    }


                    break;

                case "give":

                    if(player.hasPermission("outlast.admin")){

                        targetCache.setGems(targetCurrentGems + value);

                        target.sendMessage(prefix + "You have just recieved §6" + value + " §7Gems!");

                    } else {

                        player.sendMessage(noperm);


                    }


                    break;


                case "remove":

                    if(player.hasPermission("outlast.admin")){

                        if(targetCurrentGems >= value) {

                            targetCache.setGems(targetCurrentGems - value);

                            target.sendMessage(prefix + "You have just been robbed for §6" + value + " §7Gems!");
                        } else {

                            player.sendMessage(prefix + "The target does not have enough Gems: §6" + targetCache.getGems());

                        }

                    } else {

                        player.sendMessage(noperm);


                    }


                    break;



                default:
                    player.sendMessage(prefix + "Unknown command");
                    break;
            }
        }



        return false;
    }
}
