package nl.tijsbeek.taisdiscordbot.database;

import nl.tijsbeek.taisdiscordbot.database.guilds.Guilds;

public class DB {
    private static final DB db = new DB(new Guilds());

    DB(Guilds guilds) {
        this.guilds = guilds;
    }

    public static DB getInstance() {
        return db;
    }

    Guilds guilds;

    public Guilds getGuilds() {
        return guilds;
    }
}
