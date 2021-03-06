package nl.tijsbeek.taisdiscordbot.bot.commands;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ACommandInfo {
    String[] commandAliases();
    String category();
    String pseudoCommand();
    String exampleCommand();
    String shortCommandDescription();
    String fullCommandDescription();

    boolean guildOnly() default false;
    boolean dmOnly() default false;
    boolean botModeratorOnly() default false;
}
