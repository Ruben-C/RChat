package org.rcbox.rchat.managers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.rcbox.rchat.chatroom.BaseChatroom;
import org.rcbox.rchat.chatroom.GlobalChatroom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class ChatManager {

    private HashMap<String, BaseChatroom> syncChat;

    public ChatManager() {

        syncChat = new HashMap<>();
    }

    public void joinChatroom(Player player,BaseChatroom chatroom) {

        String sUDID = player.getUniqueId().toString();

        if (chatroom.hasPermission()) {
            if (!player.hasPermission(chatroom.getPermission())) {
                player.sendMessage("[\u00A7bRChat\u00A7r] \u00A7cYou do not have permissions to join that chatroom.");
                return;
            }
        }

        syncChat.put(sUDID, chatroom);
        player.sendMessage(String.format("You has joined %s%s\u00A7r chatroom", chatroom.getColor(), chatroom.getName()));
    }

    public BaseChatroom getChatroomFromPlayer(Player player) {

        String sUDID = player.getUniqueId().toString();

        if (!syncChat.containsKey(sUDID))
            joinChatroom(player, GlobalChatroom.instance);

        return syncChat.get(sUDID);
    }

    public List<Player> getPlayersinChatroom(BaseChatroom chatroom) {

        List<Player> inChatroom = new ArrayList<Player>();
        for (Player p : Bukkit.getOnlinePlayers()) {
            String sUDID = p.getUniqueId().toString();
            if (!syncChat.containsKey(sUDID))
                continue;
            BaseChatroom inst = syncChat.get(sUDID);
            if (!chatroom.getName().equalsIgnoreCase(inst.getName()))
                continue;
            inChatroom.add(p);
        }

        return inChatroom;
    }
}
