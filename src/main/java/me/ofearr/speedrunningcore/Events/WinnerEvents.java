package me.ofearr.speedrunningcore.Events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;

public class WinnerEvents implements Listener {

    public static String TranslateColour(String text){
        String translated = ChatColor.translateAlternateColorCodes('&', text);

        return translated;
    }

    @EventHandler
    public void itemPickupEvent(EntityPickupItemEvent e){
        if(!(e.getEntity() instanceof Player)) return;

        Player player = (Player) e.getEntity();

        if(e.getItem().getItemStack().getType() == Material.ELYTRA){
            for(Player p : Bukkit.getOnlinePlayers()){
                p.sendMessage(TranslateColour("&a&l" + player.getDisplayName() + " &a&lhas found an &5Elytra&a&l!"));
            }
        }

        if(e.getItem().getItemStack().getType() == Material.DRAGON_HEAD){
            for(Player p : Bukkit.getOnlinePlayers()){
                p.sendMessage(TranslateColour("&a&l" + player.getDisplayName() + " &a&lhas found a &5Dragon Head&a&l!"));
            }
        }
    }
}
