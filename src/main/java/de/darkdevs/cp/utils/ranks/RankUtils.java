package de.darkdevs.cp.utils.ranks;

import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class RankUtils {

    public static String getRankName(Player p) {
        List<String> ranks = Arrays.asList("Einwohner", "Stammspieler", "Bürgermeister", "Staff");
        return ranks.get(RankHandler.getRankID(p));
    }

}
