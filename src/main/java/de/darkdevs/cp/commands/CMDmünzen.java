package de.darkdevs.cp.commands;

import de.darkdevs.cp.utils.IntUtils;
import de.darkdevs.cp.utils.MoneyHandler;
import de.darkdevs.cp.utils.var;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMDmünzen implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (cmd.getName().equalsIgnoreCase("münzen") && sender instanceof Player) {

            Player p = (Player) sender;
            String money = Integer.toString(MoneyHandler.getMoney(p));
            if (args.length == 0) p.sendMessage(var.pr + "Dein derzeitiges Vermögen beträgt " + money);
            else if (args.length == 2 && p.isOp()) {
                // Add money to yourself
                if (args[0].equalsIgnoreCase("add")) {
                    if (IntUtils.stringIsInt(args[1])) {
                        int value = Integer.parseInt(args[1]);
                        MoneyHandler.addMoney(p, value);
                        p.sendMessage(var.pr + "Deinem Vermögen wurden §6" + args[1] + " §rMünzen hinzugefügt!");
                    }

                } else if (args[0].equalsIgnoreCase("set")) {
                    if (IntUtils.stringIsInt(args[1])){
                        int value = Integer.parseInt(args[1]);
                        MoneyHandler.setMoney(p, value);
                        p.sendMessage(var.pr + "Dein Vermögen beträgt nun §6" + args[1] + " §rMünzen!");
                    }
                } else {
                    p.sendMessage(var.invalidUsage);
                }

                // Add money to another player
            } else if (args.length == 3 && p.isOp()) {

                if (args[0].equalsIgnoreCase("add")) {
                    Player target = Bukkit.getPlayerExact(args[2]);
                    if (IntUtils.stringIsInt(args[1]) && target.isOnline()){
                        int value = Integer.parseInt(args[1]);
                        MoneyHandler.addMoney(target, value);
                        p.sendMessage(var.pr + "Dem vermögen von §6" + args[2] + " §rwurden §6" + args[1] + " §rMünzen hinzugefügt!");
                        target.sendMessage(var.pr + "Deinem Vermögen wurden §6" + args[1] + " §rMünzen hinzugefügt!");
                    }

                } else if (args[0].equalsIgnoreCase("set")) {
                    Player target = Bukkit.getPlayerExact(args[2]);
                    if (IntUtils.stringIsInt(args[1]) && target.isOnline()) {
                        int value = Integer.parseInt(args[1]);
                        MoneyHandler.setMoney(target, value);
                        p.sendMessage(var.pr + "Das Vermögen von §6" + args[2] + " §rbeträgt nun §6" + args[1] + " §rMünzen!");
                        target.sendMessage(var.pr + "Dein Vermögen beträgt nun §6" + args[1] + " §rMünzen!");
                    }
                }

            } else {

                p.sendMessage(var.pr + "§cNot enough Permissions!");

            }

        }

        return false;
    }

}
