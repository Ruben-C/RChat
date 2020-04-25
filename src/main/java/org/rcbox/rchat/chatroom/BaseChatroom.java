package org.rcbox.rchat.chatroom;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.rcbox.rchat.Util;
import org.rcbox.rchat.events.ChatroomEvent;
import org.rcbox.rchat.events.SendingMessageEvent;

public abstract class BaseChatroom {

    public final String name;
    public final String alias;
    public final ChatColor color;
    public final String txtPattern = "%s[%s] \u00A7r%s: %s";
    public final String permission;

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

        for (Player toPlayer: Bukkit.getOnlinePlayers()) {

            if (canReceiveMessage(fromPlayer, toPlayer)) {

                SendingMessageEvent smEvent = new SendingMessageEvent(fromPlayer, toPlayer, msg);
                Bukkit.getPluginManager().callEvent(smEvent);

                if (cEvent.isCancelled())
                    continue;

                toPlayer.sendMessage(String.format(this.txtPattern,
                        this.color,
                        this.alias,
                        fromPlayer.getDisplayName(),
                        msg));
            }
        }
    }

    public boolean canReceiveMessage(Player fromPLayer, Player toPlayer) {
        if (permission == null || toPlayer.hasPermission(permission)) {
            String chatroom = Util.getChatroom(toPlayer).name;
            if (chatroom == GlobalChatroom.instance.name || chatroom == this.name)
                return true;
        }
        return false;
    }

    public String getName() { return this.name; }
    public String getAlias() { return this.alias; }
}
