package org.rcbox.rchat;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.rcbox.rchat.chatroom.GlobalChatroom;
import org.rcbox.rchat.chatroom.LocalChatroom;
import org.rcbox.rchat.chatroom.StaffChatroom;
import org.rcbox.rchat.handlers.PlayerChatHandler;

public final class Rchat extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("[RChat] Plugin has been Enabled");
        Bukkit.getPluginManager().registerEvents(new PlayerChatHandler(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("[RChat] Plugin has been Disabled");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            switch (label.toLowerCase()){
                case "g":
                    if (args.length == 0)
                        Util.setPlayerChatroom(player, GlobalChatroom.instance);
                    else
                        GlobalChatroom.instance.sendMessage(player, String.join(" ", args));
                    break;
                case "l":
                    if (args.length == 0)
                        Util.setPlayerChatroom(player, LocalChatroom.instance);
                    else
                        LocalChatroom.instance.sendMessage(player, String.join(" ", args));
                    break;
                case "s":
                    if (args.length == 0)
                        Util.setPlayerChatroom(player, StaffChatroom.instance);
                    else
                        StaffChatroom.instance.sendMessage(player, String.join(" ", args));
                    break;
            }
        }

        return true;
    }
}
