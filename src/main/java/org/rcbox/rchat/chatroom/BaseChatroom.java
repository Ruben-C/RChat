package org.rcbox.rchat.chatroom;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.rcbox.rchat.Rchat;
import org.rcbox.rchat.Util;
import org.rcbox.rchat.events.ChatroomEvent;
import org.rcbox.rchat.events.SendingMessageEvent;

public abstract class BaseChatroom {

    private final String name;
    private final String alias;
    private final ChatColor color;
    private final String txtPattern = "%s[%s]\u00A7r %s\u00A7r: %s";
    private final String permission;

    public BaseChatroom(String name, String alias, ChatColor color, String permission) {
        this.name = name;
        this.alias = alias;
        this.color = color;
        this.permission = permission;
    }

    public BaseChatroom() {
        this("", "", ChatColor.BLUE, null);
    }

    public void sendMessage(Player fromPlayer, String msg) {
        ChatroomEvent cEvent = new ChatroomEvent(fromPlayer, this.name, msg);

        Bukkit.getPluginManager().callEvent(cEvent);

        if (cEvent.isCancelled())
            return;

        msg = String.format(this.txtPattern,
                this.color,
                this.alias,
                Util.getSuPlPr(fromPlayer),
                msg);

        if (fromPlayer.hasPermission("rchat.color"))
            msg = Util.color(msg);

        for (Player toPlayer: Bukkit.getOnlinePlayers()) {

            if (canReceiveMessage(fromPlayer, toPlayer)) {

                SendingMessageEvent smEvent = new SendingMessageEvent(fromPlayer, toPlayer, msg);
                Bukkit.getPluginManager().callEvent(smEvent);

                if (cEvent.isCancelled())
                    continue;

                toPlayer.sendMessage(msg);
            }
        }
    }

    public boolean canReceiveMessage(Player fromPLayer, Player toPlayer) {
        if (permission == null || toPlayer.hasPermission(permission)) {
            String chatroom = Rchat.cM.getChatroomFromPlayer(toPlayer).name;
            if (chatroom == GlobalChatroom.instance.getName() || chatroom == this.name)
                return true;
        }
        return false;
    }

    public String getName() { return this.name; }
    public String getAlias() { return this.alias; }
    public ChatColor getColor() { return this.color; }
    public boolean hasPermission() { return this.permission != null; }
    public String getPermission() { return this.permission; }
    public String getChatPrefix() { return this.color + this.alias; }
}
