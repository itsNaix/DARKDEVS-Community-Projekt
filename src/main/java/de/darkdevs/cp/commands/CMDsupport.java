package de.darkdevs.cp.commands;

import de.darkdevs.cp.utils.InventoryUtils;
import de.darkdevs.cp.utils.ranks.RankHandler;
import de.darkdevs.cp.utils.support.SupportHandler;
import de.darkdevs.cp.utils.var;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMDsupport implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (cmd.getName().equalsIgnoreCase("support") && sender instanceof Player) {

            Player p = (Player) sender;

            if (args.length != 0) {

                // TEAM BEREICH
                if (RankHandler.getRankID(p) == 3 ) {

                    if (args[0].equalsIgnoreCase("close")) {
                        try {
                            SupportHandler.setClosed(Integer.parseInt(args[1]));
                            p.sendMessage(var.pr + "§7 Das Ticket mit der ID §9§l" + args[1] + " §7wurde geschlossen!");
                        } catch (Exception ex) {
                            p.sendMessage(var.pr + var.invalidUsage);
                        }
                    }

                    if (args[0].equalsIgnoreCase("answer")) {

                        try {
                            // BUILD MESSAGE
                            String message = "";
                            for ( String s : args ) {
                                message = message + s + " ";
                            }
                            // TRIM MESSAGE
                            message = message.substring(9, message.length());
                            // SendMessage & ANSWER
                            int id = Integer.parseInt(args[1]);
                            SupportHandler.answerTicket(id, message);
                            p.sendMessage(var.pr + "§7Das Ticket von §9§l" + SupportHandler.getName(id) + " §7wurde beantwortet!");

                            // CHECK IF P IS ON -> TRUE: SEND
                            if (Bukkit.getOnlinePlayers().contains(Bukkit.getPlayerExact(SupportHandler.getName(id)))) {
                                Player target = Bukkit.getPlayerExact(SupportHandler.getName(id));
                                target.sendMessage(var.pr + SupportHandler.getAnswer(id));
                                SupportHandler.setClosed(id);
                                SupportHandler.setReceived(id);
                            } else {
                                p.sendMessage(var.pr + "§7Der Spieler ist im moment §7§lnicht online§7, er wird benachrichtigt, wenn er den Server betritt!");
                            }

                        } catch (Exception ex) {

                        }

                    }

                    if (args[0].equalsIgnoreCase("menu")) {

                        p.openInventory(InventoryUtils.SupportMenü());

                    }

                } else /* SPIELER BEREICH */  {

                    if (args[0].equalsIgnoreCase("hilfe")) {

                    } else {

                        String message = "";
                        for ( String s : args ) {
                            message = message + s + " ";
                        }
                        if (message != "" || message!= " ") {
                            SupportHandler.createTicket(p, message);
                            p.sendMessage("§5BOT §8| §7SupportBot §8>> §7Dein Ticket wird so bald wie möglich von einem Staff bearbeitet!");
                        } else {
                            p.sendMessage(var.err + "Deine Supportanfrage konnte nicht gesendet werden da du keine Nachricht angegeben hast!");
                        }

                    }

                }

            } else {

                p.sendMessage(var.err + "Bitte gib eine Nachricht ein. §c§lBenutzung: §c§o/support <NACHRICHT>");

            }

        }

        return false;
    }

}
