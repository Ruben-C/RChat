package org.rcbox.rchat.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class SendingMessageEvent extends Event implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Player fromPlayer;
    private final Player toPlayer;
    private final String msg;
    private boolean isCancelled;

    public SendingMessageEvent(Player fromPlayer, Player toPlayer, String msg) {
        this.fromPlayer = fromPlayer;
        this.toPlayer = toPlayer;
        this.msg = msg;
        this.isCancelled = false;
    }

    @Override
    public boolean isCancelled() {
        return this.isCancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.isCancelled = cancel;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }
}
