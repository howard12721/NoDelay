package com.gmail.kobun127.nodelay;

import org.bukkit.configuration.file.FileConfiguration;

public class Config {
    public static void reloadConfig() {
        NoDelay.getPlugin().reloadConfig();
        FileConfiguration configuration = NoDelay.getPlugin().getConfig();

        active = configuration.getBoolean("is-active");
        delay = configuration.getInt("hit-delay");
        enableToBlockDamage = configuration.getBoolean("enable-to-block-damage");
        enableToMobs = configuration.getBoolean("enable-to-mobs");
    }

    public static boolean active = true;
    public static int delay = 0;
    public static boolean enableToBlockDamage = false;
    public static boolean enableToMobs = true;

    public static int getDelay() {
        return delay;
    }

    public static boolean isActive() {
        return active;
    }

    public static boolean isEnableToBlockDamage() {
        return enableToBlockDamage;
    }

    public static boolean isEnableToMobs() {
        return enableToMobs;
    }
}
