package tk.jlot.discordmc;

import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;


/**
 * Created by Jlot on 7/9/2017.
 * edited 7/9/2017
 */
public class DiscordMC extends JavaPlugin implements Listener
{
    DAPIWrap dapi;
    @Override
    public void onDisable()
    {

    }
    @Override
    public void onEnable()
    {
        getServer().getPluginManager().registerEvents(this,this);
        dapi = new DAPIWrap("MTg3MjY1OTM0NDY4MDU1MDQw.DERKkg.xjhS9YyOou9iSV5egMYBX-4g3pw",true,getServer().getPluginManager());
        dapi.setChannel("general");
        dapi.setGuild("263851622063538186");
    }
    @EventHandler
    public void onLogin(PlayerJoinEvent pje)
    {
        System.out.println(pje.getPlayer().getName() + " joined the server");
        dapi.sendMessage(pje.getPlayer().getName() + " joined the server");
    }
    @EventHandler
    public void whatDoIUseHereAsAnEventHandler(Event e)
    {
        if(e instanceof DiscordMessageEvent)
            System.out.println(((DiscordMessageEvent) e).getMessage());
    }
}