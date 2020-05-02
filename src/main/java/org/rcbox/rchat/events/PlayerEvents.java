package org.rcbox.rchat.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.rcbox.rchat.Rchat;
import org.rcbox.rchat.chatroom.GlobalChatroom;

public class PlayerEvents implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        Rchat.cM.joinChatroom(event.getPlayer(), GlobalChatroom.instance);
    }
}
