package nl.tijsbeek.taisdiscordbot.database;

public class DB {
    private static final DB db = new DB();

    public static DB getInstance() {
        return db;
    }

    Guilds guilds;

    public Guilds getGuilds() {
        return guilds;
    }
}
