package tk.jlot.discordmc;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;


/**
 * Created by Jlot on 7/9/2017.
 * edited 7/16/2017
 */
public class DiscordMCPluginWrapper extends JavaPlugin implements Listener
{
//    DAPIWrap dapi;
    @Override
    public void onDisable()
    {

    }
    @Override
    public void onEnable()
    {
//        getServer().getPluginManager().registerEvents(this,this);
//        dapi = new DAPIWrap("TOKEN",true,getServer().getPluginManager());
//        dapi.setChannel("general");
//        dapi.setGuild("263851622063538186");
    }
//    @EventHandler
//    public void onLogin(PlayerJoinEvent pje)
//    {
//        System.out.println(pje.getPlayer().getName() + " joined the server");
//        dapi.sendMessage(pje.getPlayer().getName() + " joined the server");
//    }
//    @EventHandler
//    public void HandleMessage(DiscordMessageEvent e)
//    {
//        Bukkit.getServer().broadcastMessage(e.getUsername() + ": " + e.getMsg());
        //e.getMessage is something else. lol
//    }

}
