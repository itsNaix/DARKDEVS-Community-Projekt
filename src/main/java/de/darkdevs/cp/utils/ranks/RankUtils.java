package de.darkdevs.cp.utils.ranks;

import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class RankUtils {

    public static String getRankName(Player p) {
        List<String> ranks = Arrays.asList("Einwohner", "Stammspieler", "Bürgermeister", "Staff");
        return ranks.get(RankHandler.getRankID(p));
    }

    public static int getIDfromRank(String rank) {

        List<String> ranks = Arrays.asList("Einwohner", "Stammspieler", "Bürgermeister", "Staff");
        int i = 0;
        for ( String s : ranks ) {
            if (s.equalsIgnoreCase(rank)) return i;
            else i++;
        }
        return 0;
    }

}
