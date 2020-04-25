package org.rcbox.rchat.chatroom;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.rcbox.rchat.Util;
import org.rcbox.rchat.events.ChatroomEvent;
import org.rcbox.rchat.events.SendingMessageEvent;

public class StaffChatroom extends BaseChatroom {

    public final static StaffChatroom instance = new StaffChatroom();

    public StaffChatroom() {

        super("Staff", "S", ChatColor.BLUE, "rchat.staff");
    }
}
