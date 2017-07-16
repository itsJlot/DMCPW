package tk.jlot.discordmc;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;


/**
 * Created by Jlot on 7/9/2017.
 * edited 7/9/2017
 */
public class DiscordMC extends JavaPlugin
{
    private static DAPIWrap dapi;
    @Override
    public void onEnable()
    {
        String token = getConfig().getString("token");
        String channel = getConfig().getString("channel");
        String guild = getConfig().getString("guild");
        dapi = new DAPIWrap(token);
        dapi.setGuild(guild);
    }
    public DAPIWrap getDAPI()
    {
        return dapi;
    }
    public static DAPIWrap getDiscordAPI()
    {
        return dapi;
    }
    public DAPIWrap sendMessage(String message)
    {
        dapi.sendMessage(message);
        return dapi;
    }
    public DAPIWrap setGuild(String guild)
    {
        dapi.setGuild(guild);
        return dapi;
    }
    public DAPIWrap setChannel(String channel)
    {
        dapi.setChannel(channel);
        return dapi;
    }
}
