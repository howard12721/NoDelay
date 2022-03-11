package com.gmail.kobun127.nodelay;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class EntityDamageEvents implements Listener {
    public static Set<EntityDamageEvent.DamageCause> blockDamages = new HashSet<>(Arrays.asList(
            EntityDamageEvent.DamageCause.LAVA,
            EntityDamageEvent.DamageCause.FALL,
            EntityDamageEvent.DamageCause.FALLING_BLOCK,
            EntityDamageEvent.DamageCause.BLOCK_EXPLOSION,
            EntityDamageEvent.DamageCause.CONTACT,
            EntityDamageEvent.DamageCause.FIRE,
            EntityDamageEvent.DamageCause.FIRE_TICK,
            EntityDamageEvent.DamageCause.FLY_INTO_WALL,
            EntityDamageEvent.DamageCause.HOT_FLOOR
            ));
    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (!Config.isActive()) return;
        EntityDamageEvent.DamageCause damageCause = event.getCause();
        Entity entity = event.getEntity();
        if (!(entity instanceof LivingEntity)) {
            return;
        }
        LivingEntity livingEntity = (LivingEntity) entity;

        if (!Config.isEnableToBlockDamage() && blockDamages.contains(damageCause)) return;
        if (!Config.isEnableToMobs() && !entity.getType().equals(EntityType.PLAYER)) return;

        new BukkitRunnable() {
            @Override
            public void run() {
                livingEntity.setNoDamageTicks(Config.getDelay());
            }
        }.runTask(NoDelay.getPlugin());
    }
}
