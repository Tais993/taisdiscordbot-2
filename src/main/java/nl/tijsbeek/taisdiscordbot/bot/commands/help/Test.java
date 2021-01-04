package nl.tijsbeek.taisdiscordbot.bot.commands.help;

import nl.tijsbeek.taisdiscordbot.bot.commands.ACommand;
import nl.tijsbeek.taisdiscordbot.bot.commands.CommandReceivedEvent;
import nl.tijsbeek.taisdiscordbot.bot.commands.JDACommand;

@ACommand(
        commandAliases = {"test"},
        category = "test",
        exampleCommand = "test",
        shortCommandDescription = "Testing",
        fullCommandDescription = "Testing"
)
public class Test extends JDACommand {
    @Override
    protected void execute(CommandReceivedEvent e) {
        super.setEvent(e);


    }
}
