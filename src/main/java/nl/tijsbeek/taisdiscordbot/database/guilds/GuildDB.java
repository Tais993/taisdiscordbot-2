package nl.tijsbeek.taisdiscordbot.database.guilds;

public class GuildDB {
    private long guildId;
    private String prefix = "!";

    public GuildDB(long guildId) {
        this.guildId = guildId;
    }

    public long getGuildId() {
        return guildId;
    }

    public GuildDB setGuildId(long guildId) {
        this.guildId = guildId;
        return this;
    }

    public String getPrefix() {
        return prefix;
    }

    public GuildDB setPrefix(String prefix) {
        this.prefix = prefix;
        return this;
    }

    @Override
    public String toString() {
        return "GuildDB{" +
                "guildId=" + guildId +
                ", prefix='" + prefix + '\'' +
                '}';
    }
}
