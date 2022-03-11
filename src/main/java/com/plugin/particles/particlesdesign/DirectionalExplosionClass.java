package com.plugin.particles.particlesdesign;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public final class DirectionalExplosionClass extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    public double sec(double number) {
        return 1 / Math.cos(number);
    }


    @EventHandler
    public void CreateShape(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_AIR) System.out.println("Right click");

        if (e.getAction() == Action.LEFT_CLICK_AIR) {
            System.out.println("Debug");
            Location loc = e.getPlayer().getLocation();

            new BukkitRunnable() {
                double Mainradius = 0;
                double minorRadius = 1;

                @Override
                public void run() {
                    for (double newangle = 0; newangle <= 2 * Math.PI; newangle += Math.PI / 8) {
                        double x = Math.cos(newangle) * minorRadius;
                        double z = Math.sin(newangle) * minorRadius;
                        double y = minorRadius;
                        loc.add(x, y, z);
                        e.getPlayer().spawnParticle(Particle.FLAME, loc, 1, 0, 0, 0, 0);
                        loc.subtract(x, y, z);
                        for (double angle = 0; angle <= Math.PI * 2; angle += Math.PI / 8) {
                            double x1 = Math.cos(angle) * minorRadius;
                            double z1 = Math.sin(angle) * minorRadius;


                            loc.add(x1, 1, z1);
                            e.getPlayer().spawnParticle(Particle.FLAME, loc, 1, 0, 0, 0, 0);
                            loc.subtract(x1, 1, z1);
                        }
                    }

                    minorRadius -= 0.1;
                    Mainradius += 0.1;
                    if (Mainradius == 1) cancel();
                }
            }.runTaskTimer(this, 0, 0);


        }

    }
}




