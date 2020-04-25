package org.rcbox.rchat.chatroom;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.rcbox.rchat.events.ChatroomEvent;
import org.rcbox.rchat.events.SendingMessageEvent;

public final class GlobalChatroom extends BaseChatroom {

    public final static GlobalChatroom instance = new GlobalChatroom();

    public GlobalChatroom() {

        super("Global", "G", ChatColor.GREEN, null);
    }
}
