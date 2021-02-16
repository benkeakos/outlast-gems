package com.outlast.gems.database;

import com.outlast.gems.main.Gems;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import java.util.HashMap;

    public class CacheManager implements Listener {

        private Gems main;



        public CacheManager(final Gems main, final HashMap<Player, PlayerCache> playerData) {
            this.main = main;
            this.playerData = playerData;


        }


        public static HashMap<Player, PlayerCache> playerData;



        @EventHandler
        private void onPlayerJoin(final PlayerJoinEvent event) {

            final Player player = event.getPlayer();

            final String uuid = player.getUniqueId().toString();

            if (PlayerData.checkPlayerInDatabase(uuid)) {


                final int gems = PlayerData.getPlayerGems(uuid);

                playerData.put(player, new PlayerCache(gems));


            } else {

                PlayerData.addPlayerToDatabase(uuid);
                playerData.put(player, new PlayerCache());



            }


        }

        @EventHandler
        private void onPlayerLeave(final PlayerQuitEvent event) {

            final Player player = event.getPlayer();

            final String uuid = player.getUniqueId().toString();

            final PlayerCache playerCache = playerData.get(player);

            final int gems = playerCache.getGems();

            PlayerData.setPlayerGems(gems, uuid);

            playerData.remove(player);


        }


}
