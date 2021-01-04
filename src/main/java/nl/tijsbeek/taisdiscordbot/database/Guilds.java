package nl.tijsbeek.taisdiscordbot.database;

import java.util.HashMap;

public class Guilds {
    private final HashMap<Long, String> guildPrefix = new HashMap<>();

    private static final Guilds guilds = new Guilds();

    private Guilds getInstance() {
        return guilds;
    }

    public String getGuildPrefix(long guildId) {
        return guildPrefix.get(guildId);
    }
}
