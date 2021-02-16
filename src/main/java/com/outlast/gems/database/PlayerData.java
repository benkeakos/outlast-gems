package com.outlast.gems.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerData {


    // Add a player to the database
    public static void addPlayerToDatabase(final String uuid) {
        try {
            final PreparedStatement ps = SQL.getConnection().prepareStatement("INSERT INTO playerdata (uuid, gems) VALUES (?,?)");

            ps.setString(1, uuid);
            ps.setInt(2, 0);
            ps.executeUpdate();

        } catch (final SQLException e) {
            e.printStackTrace();
        }
    }


    //Check if uuid of player is in database

    public static boolean checkPlayerInDatabase(final String uuid) {

        try {
            final PreparedStatement ps = SQL.getConnection().prepareStatement("SELECT * FROM playerdata WHERE uuid=?");

            ps.setString(1, uuid);
            final ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return true;

            } else {

                return false;

            }

        } catch (final SQLException x) {
            x.printStackTrace();
        }

        return false;
    }

    //Get the Gems of a player

    public static Integer getPlayerGems(final String uuid) {
        try {
            final PreparedStatement ps = SQL.getConnection().prepareStatement("SELECT gems FROM playerdata WHERE uuid = ?");
            ps.setString(1, uuid);
            final ResultSet rs = ps.executeQuery();
            int gems;
            if (rs.next()) {
                gems = rs.getInt("gems");
                return (gems);
            }
        } catch (final SQLException x) {
            x.printStackTrace();
        }
        return null;
    }

    //Set the Gems of a player

    public static void setPlayerGems(final int gems, final String uuid) {
        try {
            final PreparedStatement ps = SQL.getConnection().prepareStatement("UPDATE playerdata SET gems=? WHERE uuid=?");
            ps.setInt(1, gems);
            ps.setString(2, uuid);
            ps.executeUpdate();
        } catch (final SQLException e) {
            e.printStackTrace();
        }

    }

}
