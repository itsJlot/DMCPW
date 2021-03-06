package tk.jlot.discordmc;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IGuild;
import java.io.PrintStream;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by Jlot on 7/10/2017.
 */
public class DAPIWrap
{
    public IDiscordClient cli;
    private PrintStream voidthis,sysOut = System.out,sysErr = System.err;
    private String guild = "175756109431308288";
    private String channel = "general";
    public DAPIWrap setGuild(String loc)
    {
        guild = loc;
        return this;
    }
    public DAPIWrap setChannel(String loc)
    {
        channel = loc;
        return this;
    }
    public DAPIWrap sendMessage(String message)
    {
        sendMessage(guild,channel,message);
        return this;
    }
    public DAPIWrap sendMessage(String channel,String message)
    {
        sendMessage(guild,channel,message);
        return this;
    }
    public DAPIWrap status(String status)
    {
        this.cli.changePlayingText(status);
        return this;
    }
    public DAPIWrap prints(boolean on)
    {
        if(!on)
        {
            System.setOut(voidthis);
            System.setErr(voidthis);
        }
        else
        {
            System.setOut(sysOut);
            System.setErr(sysErr);
        }
        return this;
    }

    public DAPIWrap(String token)
    {
        voidthis = new PrintStream(new OutputStream(){
            public void write(int b)
            {}
        });
        try
        {
            System.out.println("Stuff is visible");
            prints(false);
            ClientBuilder cb = new ClientBuilder();
            cb.withToken(token);
            this.cli = cb.build();
            this.cli.login();
            int counter = 0;
            while(!this.cli.isReady() && counter < 20)
            {
                Thread.sleep(100);
                counter++;
            }
            prints(true);
            System.out.println("DiscordMC started successfully");
        }
        catch (Exception e)
        {
            System.err.println("An error occured with DiscordMC");
            e.printStackTrace(System.err);
            logout();
            prints(true);
        }
            cli.getDispatcher().registerListener(new IListener<MessageReceivedEvent>() {
                @Override
                public void handle(MessageReceivedEvent mre) {
                    Bukkit.getServer().getPluginManager().callEvent(new DiscordMessageEvent(mre.getGuild().getStringID(),mre.getChannel().getName(),
                            mre.getAuthor().getName(),mre.getAuthor().getLongID(),mre.getMessage().getContent()));
//                    System.out.println(mre.getChannel().getName());
                }
            });
    }
    public boolean banByID(long id) {
        try {
            getGuild(guild).banUser(id);
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }
    public IGuild getGuild(String guildID)
    {
        List<IGuild> guilds = this.cli.getGuilds();

        IGuild guild = cli.getGuilds().get(0);
        for (int i = 0; i < guilds.size(); i++) {
            IGuild g = guilds.get(i);
            System.out.println(i);
            if (g != null) {
                if (g.getStringID().equals(guildID)) {
                    guild = g;
                    break;
                }

            }
        }
        if (guild == null) {
            prints(true);
            System.out.println("The specified guild id was not found, make sure the bot is on your server");
        }
        return guild;
    }
    public DAPIWrap sendMessage(String guildID,String chat,String message)
    {

        prints(false);
        this.cli.changePlayingText("UHC");
        IGuild guild = getGuild(guildID);

        IChannel c = guild.getChannelsByName(chat).get(0);
        c.sendMessage(message);
        return this;
    }
    public DAPIWrap logout()
    {
        this.cli.logout();
        return this;
    }
}
