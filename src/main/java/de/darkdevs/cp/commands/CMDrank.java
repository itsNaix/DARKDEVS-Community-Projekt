package de.darkdevs.cp.commands;

import de.darkdevs.cp.utils.IntUtils;
import de.darkdevs.cp.utils.ranks.RankHandler;
import de.darkdevs.cp.utils.ranks.RankUtils;
import de.darkdevs.cp.utils.var;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

//TODO Fertigstellen. Claimed by NaiX :)

public class CMDrank implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (cmd.getName().equalsIgnoreCase("rank") && sender instanceof Player) {

            Player p = (Player) sender;

            if (args.length != 0) {

                // Get rank from player
                if (args[0].equalsIgnoreCase("get")) {

                    String getResult =  var.pr + "%RANKNAME% (%RANKID%)";

                    if (args.length == 2) {
                        Player target = Bukkit.getPlayerExact(args[1]);
                        if (var.userExists(target, "players_rank")) {
                            p.sendMessage(getResult.replace("%RANKNAME%", RankUtils.getRankName(target)).replace("%RANKID%", Integer.toString(RankHandler.getRankID(target))));
                        }
                    }
                    if (args.length == 1) {

                        if (var.userExists(p, "players_rank")) {
                            p.sendMessage(getResult.replace("%RANKNAME%", RankUtils.getRankName(p)).replace("%RANKID%", Integer.toString(RankHandler.getRankID(p))));
                        }

                    }

                }

                // Set players rank
                if (args[0].equalsIgnoreCase("add")){

                    if (args.length == 2) {
                        RankHandler.addRank(p, Integer.parseInt(args[1]));
                    }

                }

                if (args[0].equalsIgnoreCase("remove")) {
                    if (args.length == 2) {
                        RankHandler.remRank(p, Integer.parseInt(args[1]));
                    }
                }


            }

        }

        return false;
    }

}
