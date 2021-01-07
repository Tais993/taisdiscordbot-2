package nl.tijsbeek.taisdiscordbot.bot.commands.help;

import nl.tijsbeek.taisdiscordbot.bot.commands.ACommandInfo;
import nl.tijsbeek.taisdiscordbot.bot.commands.CommandReceivedEvent;
import nl.tijsbeek.taisdiscordbot.bot.commands.JDACommand;

@ACommandInfo(
        commandAliases = {"help", "h"},
        category = "help",
        exampleCommand = "help",
        shortCommandDescription = "Get some help",
        fullCommandDescription = "Get some help"
)
public class Help extends JDACommand {

    public void execute(CommandReceivedEvent e) {
        super.setEvent(e);
        e.getChannel().sendMessage(getDefaultEmbed().build()).queue();
    }
}
