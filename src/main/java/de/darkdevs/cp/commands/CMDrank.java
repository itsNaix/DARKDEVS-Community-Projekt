package de.darkdevs.cp.commands;

import de.darkdevs.cp.utils.ranks.RankHandler;
import de.darkdevs.cp.utils.var;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * This class was created by NaiX on 12.06.2017.
 */

public class CMDrank implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

            //Command Usage - /rank (Gibt dir Hilfe zum Rank Befehl)
            //                /rank get <Spieler> (Zeigt den eigenen Rank bzw. den von <Spieler> an)
            //                /rank set <Spieler> {Rank/rankID}(Setzt den Rang von <Spieler> auf einen beliebigen Rang <Rank>)
            //                /rank up <Spieler> (Erhöht den Rang von <Spieler> um 1)
            //                /rank down <Spieler> (Erniedrigt den Rang von <Spieler> um 1)

            if (cmd.getName().equalsIgnoreCase("rank")) {
                if (!(sender instanceof Player)) {
                    return false;
                }
                Player p = (Player) sender;
                if (args.length == 0) {
                    p.sendMessage(var.pr + "§7Bitte gib §9/hilfe §7ein, um Hilfe für diesen Befehl zu bekommen!");
                }

                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("get")) {
                        String Rank = RankHandler.getRankName(p);
                        int RankID = RankHandler.getRankID(p);
                        p.sendMessage(var.pr + "§" + RankHandler.getRankColor(p) + "§LDu§r §7hast den Rang §" + RankHandler.getRankColor(p) + "§L" + Rank + " §r§8(§7RankID = §" + RankHandler.getRankColor(p) + "§L" + RankID + "§r§8)");
                    } else {
                        p.sendMessage(var.pr + "§7Bitte gib §9/hilfe §7ein, um Hilfe für diesen Befehl zu bekommen!");
                    }
                }

                if (args.length == 2) {

                    if (args[0].equalsIgnoreCase("get")){
                        Player target = Bukkit.getPlayerExact(args[1]);
                        if (var.userExists(target, "players_rank")) {
                            String Rank = RankHandler.getRankName(target);
                            int RankID = RankHandler.getRankID(target);
                            p.sendMessage(var.pr + "§" + RankHandler.getRankColor(target) + "§L" + target.getDisplayName() + "§r §7hat den Rang §" + RankHandler.getRankColor(target) + "§L" + Rank + " §r§8(§7RankID = §" + RankHandler.getRankColor(target) +"§L" + RankID + "§r§8)");
                        } else {
                            p.sendMessage(var.err + "Dieser Spieler existiert nicht!");
                        }
                    }

                    if (args[0].equalsIgnoreCase("up")) {

                        if (RankHandler.getRankName(p).equalsIgnoreCase("staff") || p.isOp()) {

                            Player target = Bukkit.getPlayerExact(args[1]);
                            if (var.userExists(target, "players_rank")) {

                                int current = RankHandler.getRankID(target);
                                if (current != 3) {
                                    int updated = current + 1;
                                    RankHandler.setRank(target, updated);
                                    p.sendMessage(var.pr + "§L" + target.getDisplayName() + "§r §7hat den Rang §" + RankHandler.getRankColor(target) + "§L" + RankHandler.getRankName(target) + "§r§8 (§7RankID = §L" + updated + "§r§8) §r§7erhalten!");
                                    target.sendMessage(var.pr + "§LDu §7hast den Rang §" + RankHandler.getRankColor(target) + "§L" + RankHandler.getRankName(target) + "§r§8(§7RankID = §L" + updated + "§r§8) §r§7erhalten!");
                                } else {
                                    p.sendMessage(var.err + "Dieser Spieler hat bereits den höchsten Rang!");
                                }

                            } else {
                                p.sendMessage(var.err + "Dieser Spieler existiert nicht!");
                            }

                        } else {
                            p.sendMessage(var.err + "Dafür hast du nicht genügend Rechte!");
                        }

                    }

                    if (args[0].equalsIgnoreCase("down")) {

                        if (RankHandler.getRankName(p).equalsIgnoreCase("staff") || p.isOp()) {

                            Player target = Bukkit.getPlayerExact(args[1]);
                            if (var.userExists(target, "players_rank")) {

                                int current = RankHandler.getRankID(target);
                                if (current != 0) {
                                    int updated = current - 1;
                                    RankHandler.setRank(target, updated);
                                    p.sendMessage(var.pr + "§7§L" + target.getDisplayName() + "§r §7hat den Rang §" + RankHandler.getRankColor(target) + "§L" + RankHandler.getRankName(target) + "§r§8 (§7RankID = §7§L" + updated + "§r§8) §r§7erhalten!");
                                    target.sendMessage(var.pr + "§LDu §7hast den Rang §" + RankHandler.getRankColor(target) + "§L" + RankHandler.getRankName(target) + "§r§8(§7RankID = §L" + updated + "§r§8) §r§7erhalten!");

                                } else {
                                    p.sendMessage(var.err + "Dieser Spieler hat bereits den niedrigsten Rang!");
                                }

                            } else {
                                p.sendMessage(var.err + "Dieser Spieler existiert nicht!");
                            }

                        } else {
                            p.sendMessage(var.err + "Dafür hast du nicht genügend Rechte!");
                        }

                    }

                }

                if (args.length == 3) {
                    if (args[0].equalsIgnoreCase("set")) {

                        if (RankHandler.getRankName(p).equalsIgnoreCase("staff") || p.isOp()) {
                            Player target = Bukkit.getPlayerExact(args[1]);
                            if (var.userExists(target, "players_rank")) {
                                try {
                                    int rankID = Integer.parseInt(args[2]);
                                    if (rankID <= 3 && rankID >= 0) {
                                        RankHandler.setRank(target, rankID);
                                        if (p != target) { p.sendMessage(var.pr + "§7§L" + target.getDisplayName() + "§r §7hat den Rang §" + RankHandler.getRankColor(target) + "§L" + RankHandler.getRankName(target) + "§r§8(§7RankID = §7§L" + rankID + "§r§8) §r§7erhalten!"); }
                                        target.sendMessage(var.pr + "§7§LDu §7hast den Rang §" + RankHandler.getRankColor(target) + "§L" + RankHandler.getRankName(target) + "§r§8 (§7RankID = §L" + rankID + "§r§8) §r§7erhalten!");

                                    } else {
                                        p.sendMessage(var.err + "§7Dieser Rang existiert nicht!");
                                    }
                                } catch (NumberFormatException ex) {
                                    int RankID = RankHandler.getRankID(args[2]);
                                    if (RankID != 99) {
                                        RankHandler.setRank(target, RankID);
                                        if (p != target) { p.sendMessage(var.pr + "§7§L" + target.getDisplayName() + "§r §7hat den Rang §" + RankHandler.getRankColor(target) + "§L" + RankHandler.getRankName(target) + "§r§8(§7RankID = §7§L" + RankID + "§r§8) §r§7erhalten!"); }
                                        target.sendMessage(var.pr + "§7§LDu §7hast den Rang §" + RankHandler.getRankColor(target) + "§L" + RankHandler.getRankName(target) + "§r§8 (§7RankID = §L" + RankID + "§r§8) §r§7erhalten!");
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

                    }

                }

            }

            return false;
        }
}
