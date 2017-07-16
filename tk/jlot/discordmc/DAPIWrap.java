package tk.jlot.discordmc;

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
    PrintStream voidthis,sysOut = System.out,sysErr = System.err;
    String guild = "175756109431308288";
    String channel = "general";
    public void setGuild(String loc)
    {
        guild = loc;
    }
    public void setChannel(String loc)
    {
        channel = loc;
    }
    public void sendMessage(String message)
    {
        sendMessage(guild,channel,message);
    }
    public void status(String status)
    {
        this.cli.changePlayingText(status);
    }
    public void prints(boolean on)
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
    }
    public DAPIWrap(String token,boolean listener,PluginManager s)
    {
        voidthis = new PrintStream(new OutputStream(){
            public void write(int b)
            {
                //NO-OP
            }
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
        if(listener)
        {
            cli.getDispatcher().registerListener(new IListener<MessageReceivedEvent>() {
                @Override
                public void handle(MessageReceivedEvent mre) {
//                    s.callEvent(new DiscordMessageEvent(mre.getGuild().getStringID(),mre.getChannel().getName(),
//                            mre.getAuthor().getName(),mre.getAuthor().getDiscriminator(),mre.getMessage().getContent()));
                    System.out.println(mre.getChannel().getName());
                }
            });
        }
    }
    public void sendMessage(String guildID,String chat,String message)
    {

        prints(false);
        this.cli.changePlayingText("UHC");
        List<IGuild> guilds = this.cli.getGuilds();

        IGuild guild = null;
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
            return;
        }

        IChannel c = guild.getChannelsByName(chat).get(0);
        c.sendMessage(message);
    }
    public void logout()
    {
        this.cli.logout();
    }
}
