package org.rcbox.rchat;

import org.bukkit.entity.Player;

import java.util.regex.Pattern;

public final class Util {

    private final static Pattern COLOR = Pattern.compile("(?i)&([0-F])");
    public static String color(String line) {
        return COLOR.matcher(line).replaceAll("\u00A7$1");
    }

    public static String getSuPlPr(Player player) {
        String fullName = "";

        String prefix = Rchat.chat.getPlayerPrefix(player);
        String name = player.getDisplayName();
        String suffix = Rchat.chat.getPlayerSuffix(player);

        if (prefix != null && !prefix.isEmpty())
            fullName += String.format("%s ", prefix);
        fullName += name;
        if (suffix != null && !suffix.isEmpty())
            fullName += String.format(" %s", suffix);
        return fullName;
    }

}
