package nl.tijsbeek.taisdiscordbot.bot.commands;

import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;
import java.time.Instant;

public class JDACommand {
    CommandReceivedEvent e;

    protected void execute(CommandReceivedEvent e) {
        e.getChannel().sendMessage("Something is wrong! Contact the creator.").queue();
    }

    protected EmbedBuilder getDefaultEmbed() {
        EmbedBuilder eb = new EmbedBuilder();

        eb.setColor(new Color(219, 65, 5));
        eb.setAuthor("Tais", "https://github.com/Tais993/TaisDiscordBot", e.getJDA().getSelfUser().getAvatarUrl());
        eb.setTimestamp(Instant.now());

        return eb;
    }

    protected void setEvent(CommandReceivedEvent e) {
        this.e = e;
    }
}