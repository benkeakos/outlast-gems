package com.outlast.gems.main;

import com.outlast.gems.economy.GemCommand;
import com.outlast.gems.gemshop.GemShopCommand;
import com.outlast.gems.database.CacheManager;
import com.outlast.gems.database.PlayerCache;
import com.outlast.gems.database.SQL;
import com.outlast.gems.gemshop.GemShopListener;
import com.outlast.gems.util.CustomItems;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;

import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;


public final class Gems extends JavaPlugin {

    ConsoleCommandSender console = Bukkit.getConsoleSender();
    private final HashMap<Player, PlayerCache> playerData = new HashMap<>();
    private final CustomItems items = new CustomItems();

    @Override
    public void onEnable() {

        registerListeners();

        registerCMD();

        SQL.connect();

        reconnect();


        SQL.createTables();


    }

    @Override
    public void onDisable() {
        SQL.disconnect();
    }

    public void reconnect() {
        Bukkit.getScheduler().runTaskTimer(this, () -> {
            try {
                final PreparedStatement ps = SQL.getConnection().prepareStatement("SELECT * FROM playerdata");
                final ResultSet rs = ps.executeQuery();
                int total = 0;
                while (rs.next()) {
                    total++;
                }
                console.sendMessage("\247c[\2476Outlast\247c] \247bDatabase connection refreshed!");
                console.sendMessage("\247c[\2476Outlast\247c] \247bNumber of unique players in database: " + total);
            } catch (final SQLException e) {
                e.printStackTrace();
            }
        }, (30 * 60 * 20L), (30 * 60 * 20L));
    }

    public void registerListeners() {

        final PluginManager plm = org.bukkit.Bukkit.getPluginManager();

        plm.registerEvents(new CacheManager(this, playerData), this);
        plm.registerEvents(new GemShopListener(this, items), this);
    }

    private void registerCMD() {
        getCommand("gems").setExecutor(new GemCommand(this));
        getCommand("gemshop").setExecutor(new GemShopCommand(this));
    }
}
