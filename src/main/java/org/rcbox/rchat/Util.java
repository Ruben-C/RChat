package org.rcbox.rchat;

import org.bukkit.entity.Player;
import org.rcbox.rchat.chatroom.BaseChatroom;
import org.rcbox.rchat.chatroom.GlobalChatroom;

import java.util.HashMap;
import java.util.UUID;

public final class Util {

    private static HashMap<UUID, BaseChatroom> chatList = new HashMap<UUID, BaseChatroom>();

    public static BaseChatroom getChatroom(Player player) {
        if (!chatList.containsKey(player.getUniqueId())) {
            chatList.put(player.getUniqueId(), GlobalChatroom.instance);
        }

        return chatList.get(player.getUniqueId());
    }

    public static void setPlayerChatroom(Player player, BaseChatroom chatroom) {
        chatList.put(player.getUniqueId(), chatroom);
    }
}
