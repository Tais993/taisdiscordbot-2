package nl.tijsbeek.taisdiscordbot.bot.commands.help;

import nl.tijsbeek.taisdiscordbot.bot.commands.ACommand;
import nl.tijsbeek.taisdiscordbot.bot.commands.CommandReceivedEvent;
import nl.tijsbeek.taisdiscordbot.bot.commands.JDACommand;

@ACommand(
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
