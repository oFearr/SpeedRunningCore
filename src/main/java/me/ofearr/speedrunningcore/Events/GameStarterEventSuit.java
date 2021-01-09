package me.ofearr.speedrunningcore.Events;

import me.ofearr.speedrunningcore.SpeedRunningCore;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class GameStarterEventSuit implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();

        if(SpeedRunningCore.gameStarted == false){
            SpeedRunningCore.playerTracker.add(player.getUniqueId());
            SpeedRunningCore.playerDiamonds.put(player.getUniqueId(), 0);
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e){
        Player player = e.getPlayer();

        if(SpeedRunningCore.playerTracker.contains(player.getUniqueId()) && SpeedRunningCore.gameStarted == false){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent e){
        if(!(e.getEntity() instanceof Player)){
            return;
        }
        Player player = (Player) e.getEntity();

        if(SpeedRunningCore.playerTracker.contains(player.getUniqueId()) && SpeedRunningCore.gameStarted == false){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerBlockBreak(BlockBreakEvent e){
        Player player = e.getPlayer();

        if(SpeedRunningCore.playerTracker.contains(player.getUniqueId()) && SpeedRunningCore.gameStarted == false){
            e.setCancelled(true);
        }

        if(SpeedRunningCore.gameStarted == true && e.getBlock().getType() == Material.DIAMOND_ORE){
            int plusDia = SpeedRunningCore.playerDiamonds.get(player.getUniqueId()).intValue() + 1;
            SpeedRunningCore.playerDiamonds.put(player.getUniqueId(), plusDia);
        }
    }

    @EventHandler
    public void onPlayerBlockPlace(BlockPlaceEvent e){
        Player player = e.getPlayer();

        if(SpeedRunningCore.playerTracker.contains(player.getUniqueId()) && SpeedRunningCore.gameStarted == false){
            e.setCancelled(true);
        }
    }
}
