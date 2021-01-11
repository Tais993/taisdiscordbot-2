package nl.tijsbeek.taisdiscordbot.bot.commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import nl.tijsbeek.taisdiscordbot.database.DB;
import org.jetbrains.annotations.NotNull;

public class CommandHandler extends ListenerAdapter {
    CommandMap commandMap = CommandMap.getInstance();
    DB db = DB.getInstance();

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) {
            return;
        }

        CommandReceivedEvent e = new CommandReceivedEvent(event);
        commandMap.executeCommand(e);
    }
}