package me.petterim1.ns;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.server.DataPacketReceiveEvent;
import cn.nukkit.network.protocol.LoginPacket;
import cn.nukkit.plugin.PluginBase;

public class NoSpaceInName extends PluginBase implements Listener {

    private String replaceWith;

    public void onEnable() {
        saveDefaultConfig();
        replaceWith = getConfig().getString("replaceWith");
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler(ignoreCancelled = true)
    public void DataPacketReceiveEvent(DataPacketReceiveEvent e) {
        if (e.getPacket() instanceof LoginPacket) {
            LoginPacket packet = (LoginPacket) e.getPacket();
            if (packet.username != null) {
                packet.username = packet.username.replace(" ", replaceWith);
            }
        }
    }
}
