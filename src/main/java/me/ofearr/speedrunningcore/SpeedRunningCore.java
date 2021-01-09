package me.ofearr.speedrunningcore;

import me.ofearr.speedrunningcore.Events.GameStarterEventSuit;
import me.ofearr.speedrunningcore.Events.Teams;
import me.ofearr.speedrunningcore.Events.WinnerEvents;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;


public final class SpeedRunningCore extends JavaPlugin {

    public static String TranslateColour(String text){
        String translated = ChatColor.translateAlternateColorCodes('&', text);

        return translated;
    }

    public static boolean gameStarted;
    public static ArrayList<UUID> playerTracker = new ArrayList<>();
    public static HashMap<UUID, String> playerTeams = new HashMap<>();
    public static HashMap<UUID, Integer> playerDiamonds = new HashMap<>();

    @Override
    public void onEnable() {
        gameStarted = false;
        Bukkit.getPluginManager().registerEvents(new GameStarterEventSuit(), this);
        Bukkit.getPluginManager().registerEvents(new Teams(), this);
        Bukkit.getPluginManager().registerEvents(new WinnerEvents(), this);

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().toLowerCase().equals("playerteamblue")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (!(player.hasPermission("speedrun.admin"))) {
                    player.sendMessage(TranslateColour("&cInsufficient Permission!"));
                } else {
                    Player target = Bukkit.getPlayer(args[0]);
                    playerTeams.put(target.getUniqueId(), "BLUE");
                    target.setDisplayName(TranslateColour("&8[&9BLUE&8]" + "&9" +target.getName()));
                }
            }
        }

        if (command.getName().toLowerCase().equals("playerteamred")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (!(player.hasPermission("speedrun.admin"))) {
                    player.sendMessage(TranslateColour("&cInsufficient Permission!"));
                } else {
                    Player target = Bukkit.getPlayer(args[0]);
                    playerTeams.put(target.getUniqueId(), "RED");
                    target.setDisplayName(TranslateColour("&8[&cRED&8]" + "&c" +target.getName()));
                }
            }
        }

        if (command.getName().toLowerCase().equals("playerdiamonds")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (!(player.hasPermission("speedrun.admin"))) {
                    player.sendMessage(TranslateColour("&cInsufficient Permission!"));
                } else {
                    Player target = Bukkit.getPlayer(args[0]);
                    player.sendMessage(TranslateColour("&a" + args[0] + " has mined " + playerDiamonds.get(target.getUniqueId()) + " &3Diamonds."));
                }
            }
        }

        if (command.getName().toLowerCase().equals("startrun")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (!(player.hasPermission("speedrun.admin"))) {
                    player.sendMessage(TranslateColour("&cInsufficient Permission!"));
                } else {
                    new BukkitRunnable(){

                        int runTimer = 5;

                        @Override
                        public void run(){

                            for(Player p : Bukkit.getOnlinePlayers()){
                                p.sendTitle(TranslateColour("&c&lGame Starting!"), TranslateColour( "&a&lin: " + runTimer + "s"));
                            }

                            if (runTimer == 1) {
                                gameStarted = true;
                                for(Player p : Bukkit.getOnlinePlayers()){
                                    p.sendTitle(TranslateColour("&c&lGame Starting!"), TranslateColour("&a&lGame has started!"));
                                }
                                this.cancel();
                            }
                            runTimer--;
                        }
                    }.runTaskTimer(this, 0, 20L);
                }
            }
        }

        return false;
    }

}
