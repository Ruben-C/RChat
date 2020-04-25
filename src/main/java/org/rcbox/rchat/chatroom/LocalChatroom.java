package org.rcbox.rchat.chatroom;

import jdk.vm.ci.meta.Local;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.rcbox.rchat.Util;
import org.rcbox.rchat.events.ChatroomEvent;
import org.rcbox.rchat.events.SendingMessageEvent;

public class LocalChatroom extends BaseChatroom {

    public final static LocalChatroom instance = new LocalChatroom();

    public LocalChatroom() {

        super("Local", "L", ChatColor.YELLOW, null);
    }

    @Override
    public boolean canReceiveMessage(Player fromPLayer, Player toPlayer) {
        String chatroom = Util.getChatroom(fromPLayer).name;
        Location fromLoc = fromPLayer.getLocation();
        Location toLoc = toPlayer.getLocation();

        if (chatroom == GlobalChatroom.instance.name || chatroom == this.name) {
            if (fromLoc.getWorld().getName() == toLoc.getWorld().getName())
                if (fromLoc.distance(toLoc) <= 100.00)
                    return true;
        }
        return false;
    }
}
