package org.rcbox.rchat;

import net.milkbowl.vault.chat.Chat;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.rcbox.rchat.chatroom.GlobalChatroom;
import org.rcbox.rchat.chatroom.LocalChatroom;
import org.rcbox.rchat.chatroom.StaffChatroom;
import org.rcbox.rchat.events.PlayerEvents;
import org.rcbox.rchat.handlers.PlayerHandler;
import org.rcbox.rchat.managers.ChatManager;

public final class Rchat extends JavaPlugin {

    public static Rchat instance;

    public static Chat chat = null;
    public static ChatManager cM = new ChatManager();

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("[RChat] Plugin has been Enabled");
        Rchat.instance = this;
        Bukkit.getPluginManager().registerEvents(new PlayerHandler(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerEvents(), this);

        setupChat();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("[RChat] Plugin has been Disabled");
    }

    private void setupChat() {
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        chat = rsp.getProvider();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            switch (label.toLowerCase()){
                case "g":
                    if (args.length == 0)
                        cM.joinChatroom(player, GlobalChatroom.instance);
                    else
                        GlobalChatroom.instance.sendMessage(player, String.join(" ", args));
                    break;
                case "l":
                    if (args.length == 0)
                        cM.joinChatroom(player, LocalChatroom.instance);
                    else
                        LocalChatroom.instance.sendMessage(player, String.join(" ", args));
                    break;
                case "s":
                    if (args.length == 0)
                        cM.joinChatroom(player, StaffChatroom.instance);
                    else
                        StaffChatroom.instance.sendMessage(player, String.join(" ", args));
                    break;
            }
        }

        return true;
    }
}
