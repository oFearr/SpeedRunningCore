package me.ofearr.speedrunningcore.Events;

import me.ofearr.speedrunningcore.SpeedRunningCore;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class Teams implements Listener {

    public static String TranslateColour(String text){
        String translated = ChatColor.translateAlternateColorCodes('&', text);

        return translated;
    }

    @EventHandler
    public void EntityAttack(EntityDamageByEntityEvent e){
        if(!(e.getEntity() instanceof Player)) return;
        if(!(e.getDamager() instanceof Player)) return;

        Player damager = (Player) e.getDamager();
        Player damaged = (Player) e.getEntity();

        if(SpeedRunningCore.playerTeams.get(damager.getUniqueId()) == "RED" && SpeedRunningCore.playerTeams.get(damaged.getUniqueId()) == "RED"){
            e.setCancelled(true);
            damager.sendMessage(TranslateColour("&8[&bTEAMS&8] &8> &cYou cannot attack your team!"));
        }

        else if(SpeedRunningCore.playerTeams.get(damager.getUniqueId()) == "BLUE" && SpeedRunningCore.playerTeams.get(damaged.getUniqueId()) == "BLUE"){
            e.setCancelled(true);
            damager.sendMessage(TranslateColour("&8[&bTEAMS&8] &8> &cYou cannot attack your team!"));
        }

    }
}
