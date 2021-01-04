package nl.tijsbeek.taisdiscordbot.bot.commands;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ACommand {
    String[] commandAliases();
    String category();
    String exampleCommand();
    String shortCommandDescription();
    String fullCommandDescription();
}
