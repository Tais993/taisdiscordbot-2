package nl.tijsbeek.taisdiscordbot.bot.commands;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import nl.tijsbeek.taisdiscordbot.database.DB;
import org.jetbrains.annotations.NotNull;

public class CommandHandler extends ListenerAdapter {
    DB db = DB.getInstance();
    CommandMap commandMap = new CommandMap(db);

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) {
            return;
        }

        String prefix = (event.isFromGuild()) ? getGuildPrefix(event.getGuild()) : "!";

        if (event.getMessage().getContentRaw().startsWith(prefix)) {
            CommandReceivedEvent e = new CommandReceivedEvent(event, prefix);
            commandMap.executeCommand(e);
        }
    }

    private String getGuildPrefix(Guild guild) {
        return db.getGuilds().getGuildPrefix(guild);
    }
}