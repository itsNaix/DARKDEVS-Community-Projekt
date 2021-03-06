package de.darkdevs.cp.commands;

import de.darkdevs.cp.utils.IntUtils;
import de.darkdevs.cp.utils.MoneyHandler;
import de.darkdevs.cp.utils.ranks.RankHandler;
import de.darkdevs.cp.utils.var;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class CMDmünzen implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (cmd.getName().equalsIgnoreCase("münzen")) {

            Player p = (Player) sender;
            String money = Integer.toString(MoneyHandler.getMoney(p));
            if (args.length == 0) p.sendMessage(var.pr + "Dein derzeitiges Vermögen beträgt §6" + money);
            else if (args.length == 2 && (RankHandler.getRankName(p).equalsIgnoreCase("staff") || sender instanceof ConsoleCommandSender)) {
                // Add money to yourself
                if (args[0].equalsIgnoreCase("add")) {
                    if (IntUtils.stringIsInt(args[1])) {
                        int value = Integer.parseInt(args[1]);
                        MoneyHandler.addMoney(p, value);
                        p.sendMessage(var.pr + "Deinem Vermögen wurden §6" + args[1] + " §rMünzen hinzugefügt!");
                    }

                    // Set money of yourself
                } else if (args[0].equalsIgnoreCase("set")) {
                        if (IntUtils.stringIsInt(args[1])) {
                            int value = Integer.parseInt(args[1]);
                            MoneyHandler.setMoney(p, value);
                            p.sendMessage(var.pr + "Dein Vermögen beträgt nun §6" + args[1] + " §rMünzen!");
                        }
                    // Remove money from yourself
                } else if(args[0].equalsIgnoreCase("remove")){
                    if (IntUtils.stringIsInt(args[1])){
                        int value = Integer.parseInt(args[1]);
                        MoneyHandler.remMoney(p, value);
                        p.sendMessage(var.pr + "Deinem Vermögen wurden §6" + args[1] + " §rMünzen abgezogen!");
                    }
                } else {
                    p.sendMessage(var.invalidUsage);
                }

            } else if (args.length == 3 && (RankHandler.getRankName(p).equalsIgnoreCase("staff") || sender instanceof ConsoleCommandSender)) {

                // Add money to another player
                if (args[0].equalsIgnoreCase("add")) {
                    try {
                        Player target = Bukkit.getPlayerExact(args[2]);
                        if (IntUtils.stringIsInt(args[1]) && target.isOnline()){
                            int value = Integer.parseInt(args[1]);
                            MoneyHandler.addMoney(target, value);
                            p.sendMessage(var.pr + "Dem vermögen von §6" + args[2] + " §rwurden §6" + args[1] + " §rMünzen hinzugefügt!");
                            target.sendMessage(var.pr + "Deinem Vermögen wurden §6" + args[1] + " §rMünzen hinzugefügt!");
                        }
                    } catch (Exception ex) {
                        p.sendMessage(var.invalidUsage);
                    }

                    // Set money of another player
                } else if (args[0].equalsIgnoreCase("set")) {
                    try {
                        Player target = Bukkit.getPlayerExact(args[2]);
                        if (IntUtils.stringIsInt(args[1]) && target.isOnline()) {
                            int value = Integer.parseInt(args[1]);
                            MoneyHandler.setMoney(target, value);
                            p.sendMessage(var.pr + "Das Vermögen von §6" + args[2] + " §rbeträgt nun §6" + args[1] + " §rMünzen!");
                            target.sendMessage(var.pr + "Dein Vermögen beträgt nun §6" + args[1] + " §rMünzen!");
                        }
                    } catch (Exception ex) {
                        p.sendMessage(var.invalidUsage);
                    }
                    // Remove money from another player
                } else if (args[0].equalsIgnoreCase("remove")) {
                    try {
                        Player target = Bukkit.getPlayerExact(args[2]);
                        if (IntUtils.stringIsInt(args[1]) && target.isOnline()) {
                            int value = Integer.parseInt(args[1]);
                            MoneyHandler.remMoney(target, value);
                            p.sendMessage(var.pr + "Dem vermögen von §6" + args[2] + " §rwurden §6" + args[1] + " §rMünzen abgezogen!");
                            target.sendMessage(var.pr + "Deinem Vermögen wurden §6" + args[1] + " §rMünzen abgezogen!");
                        }
                    } catch (Exception ex) {
                        p.sendMessage(var.invalidUsage);
                    }
                } else {
                    p.sendMessage(var.invalidUsage);
                }
            } else {
                p.sendMessage(var.err + "Dafür hast du nicht genügend Rechte!");
            }

        }

        return false;
    }

}
