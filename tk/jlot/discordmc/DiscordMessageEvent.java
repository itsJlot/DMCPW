
package tk.jlot.discordmc;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Created by Jlot on 7/10/2017.
 */
public class DiscordMessageEvent extends Event implements Cancellable
{
    private final String message;
    private String msg;
    private String username;
    private String uid;
    private String guildID;
    private String channel;
    private boolean cancelled = false;
    private static HandlerList handlers = new HandlerList();
    public DiscordMessageEvent(String guildID, String channel, String username, String uid, String message)
    {
        super();
        this.message = "";
        this.setMsg(message);
        this.setUsername(username);
        this.setUid(uid);
        this.setGuildID(guildID);
        this.setChannel(channel);
    }
    public String getMessage() {
        return message;
    }
    public boolean isCancelled() {
        return cancelled;
    }
    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }
    @Override
    public HandlerList getHandlers()
    {
        return handlers;
    }
    public static HandlerList getHandlerList()
    {
        return handlers;
    }
    public String getMsg() {
        return message;
    }

    public void setMsg(String message) {
        this.msg = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getGuildID() {
        return guildID;
    }

    public void setGuildID(String guildID) {
        this.guildID = guildID;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }
}
