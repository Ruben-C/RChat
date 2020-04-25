package org.rcbox.rchat.handlers;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.rcbox.rchat.Util;
import org.rcbox.rchat.chatroom.BaseChatroom;

public class PlayerChatHandler implements Listener {

    @EventHandler
    public void onPlayerChat(PlayerChatEvent event) {
        String msg = event.getMessage();
        Player player = event.getPlayer();

        BaseChatroom chatroom = Util.getChatroom(event.getPlayer());

        if (!event.isCancelled()) {
            chatroom.sendMessage(player, msg);
            event.setCancelled(true);
        }
    }
}
