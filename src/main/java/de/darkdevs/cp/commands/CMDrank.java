package de.darkdevs.cp.commands;

import de.darkdevs.cp.utils.ranks.RankHandler;
import de.darkdevs.cp.utils.var;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

/**
 * This class was created by Front. aka Scryptex on 10.06.2017.
 */
public class CMDrank implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

            //Command Usage - /rank (Gibt dir Hilfe zum Rank Befehl)
            //                /rank get <Spieler> (Zeigt den eigenen Rank bzw. den von <Spieler> an)
            //                /rank set <Spieler> {Rank/rankID}(Setzt den Rang von <Spieler> auf einen beliebigen Rang <Rank>)
            //                /rank climb <Spieler> up (Erhöht den Rang von <Spieler> um 1)
            //                /rank climb <Spieler> down (Erniedrigt den Rang von <Spieler> um 1)

            if (cmd.getName().equalsIgnoreCase("rank")) {
                if(!(sender instanceof Player)) {
                    return false;
                }
                Player p = (Player) sender;
                if (args.length == 0) {
                    p.sendMessage(var.pr + "§7Bitte gib §9/hilfe §7ein, um Hilfe für diesen Befehl zu bekommen!");
                } else if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("get")) {
                        String rank = RankHandler.getRankName(p);
                        int rankID = RankHandler.getRankID(p);
                        p.sendMessage(var.pr + "§9§LDu§r §7hast den Rang §" + RankHandler.getRankColor(p) + "§L" + rank + " §r§8(§7RankID = §9§L" + rankID + "§r§8)");
                    } else {
                        p.sendMessage(var.pr + "§7Bitte gib §9/hilfe §7ein, um Hilfe für diesen Befehl zu bekommen!");
                    }
                }
            } else if (args.length == 2) {
                Player p = (Player) sender;
                if (args[0].equalsIgnoreCase("get")) {
                    Player target = Bukkit.getPlayer(args[1]);
                    if (var.userExists(target, "players_rank")) {
                        String rank = RankHandler.getRankName(target);
                        int rankID = RankHandler.getRankID(target);
                        p.sendMessage(var.pr + "§9§L" + target.getDisplayName() + "§r §7hat den Rang §" + RankHandler.getRankColor(target) + "§L" + rank + " §r§8(§7RankID = §9§L" + rankID + "§r§8)");
                    } else {
                        p.sendMessage(var.err + "Dieser Spieler existiert nicht!");
                    }
                } else {
                    p.sendMessage(var.pr + "§7Bitte gib §9/hilfe §7ein, um Hilfe für diesen Befehl zu bekommen!");
                }
            } else if (args.length == 3) {
                if(!(sender instanceof Player)) {
                    return false;
                }
                Player p = (Player) sender;
                if (args[0].equalsIgnoreCase("set")) {
                    if (RankHandler.getRankName(p).equalsIgnoreCase("staff")) {
                        Player target = Bukkit.getPlayer(args[1]);
                        if (var.userExists(target, "players_rank")) {
                            try {
                                int rankID = Integer.parseInt(args[2]);
                                if (rankID <= 3) {
                                    if (rankID >= 0) {
                                        RankHandler.setRank(target, rankID);
                                        p.sendMessage(var.pr + "§9§L" + target.getDisplayName() + "§r §7hat den Rang §" + RankHandler.getRankColor(target) + "§L" + RankHandler.getRankName(target) + " \n" +
                                                "§r§8(§7RankID = §9§L" + rankID + "§r§8) §r§7erhalten!");
                                    } else {
                                        p.sendMessage(var.err + "Dieser Rang existiert nicht!");
                                    }
                                } else {
                                    p.sendMessage(var.err + "Dieser Rang existiert nicht!");
                                }
                            } catch (NumberFormatException e) {
                                int rankID = RankHandler.getRankID(args[2]);
                                if (rankID != 99) {
                                    RankHandler.setRank(target, rankID);
                                    p.sendMessage(var.pr + "§9§L" + target.getDisplayName() + "§r §7hat den Rang §" + RankHandler.getRankColor(target) + "§L" + RankHandler.getRankName(target) + " \n" +
                                            "§r§8(§7RankID = §9§L" + rankID + "§r§8) §r§7erhalten!");
                                } else {
                                    p.sendMessage(var.err + "Dieser Rang existiert nicht!");
                                }
                            }
                        } else {
                            p.sendMessage(var.err + "Dieser Spieler existiert nicht!");
                        }
                    } else {
                        p.sendMessage(var.err + "Dafür hast du nicht genügend Rechte!");
                    }
                } else if (args[0].equalsIgnoreCase("climb")) {
                    if (RankHandler.getRankName(p).equalsIgnoreCase("staff")) {
                        Player target = Bukkit.getPlayer(args[1]);
                        if (var.userExists(target, "players_rank")) {
                            if (args[2].equalsIgnoreCase("down")) {
                                int current = RankHandler.getRankID(target);
                                if (current != 0) {
                                    int after = current - 1;
                                    RankHandler.setRank(target, after);
                                    p.sendMessage(var.pr + "§9§L" + target.getDisplayName() + "§r §7hat den Rang §" + RankHandler.getRankColor(target) + "§L" + RankHandler.getRankName(target) + " \n" +
                                            "§r§8(§7RankID = §9§L" + after + "§r§8) §r§7erhalten!");
                                } else {
                                    p.sendMessage(var.err + "Dieser Spieler hat bereits den niedrigsten Rang!");
                                }
                            } else if (args[2].equalsIgnoreCase("up")) {
                                int current = RankHandler.getRankID(target);
                                if (current != 3) {
                                    int after = current + 1;
                                    RankHandler.setRank(target, after);
                                    p.sendMessage(var.pr + "§9§L" + target.getDisplayName() + "§r §7hat den Rang §" + RankHandler.getRankColor(target) + "§L" + RankHandler.getRankName(target) + " \n" +
                                            "§r§8(§7RankID = §9§L" + after + "§r§8) §r§7erhalten!");
                                } else {
                                    p.sendMessage(var.err + "Dieser Spieler hat bereits den höchsten Rang!");
                                }

                            }
                        } else {
                            p.sendMessage(var.err + "Dieser Spieler existiert nicht!");
                        }
                    } else {
                        p.sendMessage(var.err + "Dafür hast du nicht genügend Rechte!");
                    }
                } else {
                    p.sendMessage(var.pr + "§7Bitte gib §9/hilfe §7ein, um Hilfe für diesen Befehl zu bekommen!");
                }
            } else {
                if(!(sender instanceof Player)) {
                    return false;
                }
                Player p = (Player) sender;
                p.sendMessage(var.pr + "§7Bitte gib §9/hilfe §7ein, um Hilfe für diesen Befehl zu bekommen!");
            }

            return false;
        }
}
