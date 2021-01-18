package nl.tijsbeek.taisdiscordbot.database.guilds;

import net.dv8tion.jda.api.entities.Guild;

import java.util.HashMap;

public class Guilds {
    private final HashMap<Long, GuildDB> guilds = new HashMap<>();

    public GuildDB getGuild(long guildId) {
        GuildDB guildDB = guilds.get(guildId);
        if (guildDB == null) {
            guildDB = new GuildDB(guildId);
            guilds.put(guildId, guildDB);
        }
        return guildDB;
    }

    public void setGuildPrefix(long guildId, String prefix) {
        guilds.replace(guildId, getGuild(guildId).setPrefix(prefix));
    }

    public String getGuildPrefix(long guildId) {
        return getGuild(guildId).getPrefix();
    }

    public String getGuildPrefix(Guild guild) {
        return getGuild(guild.getIdLong()).getPrefix();
    }
}
