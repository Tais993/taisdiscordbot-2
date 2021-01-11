package nl.tijsbeek.taisdiscordbot.database;

import java.util.HashMap;

public class Guilds {
    private final HashMap<Long, String> guildPrefix = new HashMap<>();

    public void addGuildPrefix(long guildId, String prefix) {
        guildPrefix.put(guildId, prefix);
    }

    public String getGuildPrefix(long guildId) {
        return guildPrefix.get(guildId);
    }
}
