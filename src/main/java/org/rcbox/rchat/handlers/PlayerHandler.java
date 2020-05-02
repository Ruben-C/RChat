package org.rcbox.rchat.handlers;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.rcbox.rchat.Rchat;
import org.rcbox.rchat.chatroom.BaseChatroom;
import org.rcbox.rchat.chatroom.GlobalChatroom;

public class PlayerHandler implements Listener {

    @EventHandler
    public void onPlayerChat(PlayerChatEvent event) {
        String msg = event.getMessage();
        Player player = event.getPlayer();

        BaseChatroom chatroom = Rchat.cM.getChatroomFromPlayer(event.getPlayer());

        if (!event.isCancelled()) {
            chatroom.sendMessage(player, msg);
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        Rchat.cM.joinChatroom(player, GlobalChatroom.instance);
    }
}
