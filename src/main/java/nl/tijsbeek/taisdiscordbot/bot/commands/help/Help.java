package nl.tijsbeek.taisdiscordbot.bot.commands.help;

import net.dv8tion.jda.api.EmbedBuilder;
import nl.tijsbeek.taisdiscordbot.bot.commands.ACommandInfo;
import nl.tijsbeek.taisdiscordbot.bot.commands.CommandMap;
import nl.tijsbeek.taisdiscordbot.bot.commands.CommandReceivedEvent;
import nl.tijsbeek.taisdiscordbot.bot.commands.JDACommand;

@ACommandInfo(
        commandAliases = {"help", "h"},
        category = "help",
        pseudoCommand = "help (command/category/all)",
        exampleCommand = "help all",
        shortCommandDescription = "Get some help",
        fullCommandDescription = "Get some help"
)
public class Help extends JDACommand {

    public void execute(CommandReceivedEvent e) {
        super.setEvent(e);

        CommandMap cm = getCommandMap();
        String input = e.getArgs().get(0);

        if (cm.isValidCommand(input)) {
            helpCommand(e, input, e.getPrefix());
        } else if (cm.isValidCategory(input)) {
            helpCategory(e, input, e.getPrefix());
        } else if (input.equals("all")) {
            helpAll(e);
        }
    }

    public void helpCommand(CommandReceivedEvent e, String commandName, String prefix) {
        JDACommand jdaCommand = getCommandMap().getCommand(commandName);

        if (jdaCommand == null) {
            e.getChannel().sendMessage("Not a command: " + e.getArgs().get(0)).queue();
            return;
        }

        ACommandInfo commandInfo = jdaCommand.getCommandInfo();

        EmbedBuilder eb = getDefaultEmbed();

        eb.setTitle("Help " + commandName + " command");
        eb.setDescription("```" + prefix + commandInfo.pseudoCommand() + "\n");
        eb.appendDescription(prefix + commandInfo.exampleCommand() + "```");
        eb.appendDescription("\nCommand description: \n");
        eb.appendDescription(commandInfo.fullCommandDescription());
        eb.appendDescription("\nAliases: ");
        eb.appendDescription(formatAliases(commandInfo));

        e.getChannel().sendMessage(eb.build()).queue();
    }

    public String formatAliases(ACommandInfo commandInfo) {
        StringBuilder formatted = new StringBuilder();
        for (String alias : commandInfo.commandAliases()) {
            formatted.append("\n*").append(alias).append("*");
        }
        return formatted.toString();
    }

    public void helpCategory(CommandReceivedEvent e, String category, String prefix) {

        if (!getCommandMap().isValidCategory(category)) {
            e.getChannel().sendMessage("Not a category: " + e.getArgs().get(0)).queue();
            return;
        }
        EmbedBuilder eb = getDefaultEmbed();

        eb.setTitle("Help " + category.toLowerCase() + " category");
        getCommandMap().getCommandsCategory(category).forEach(commandInfo -> {
            eb.addField(commandInfo.commandAliases()[0], commandInfo.shortCommandDescription() + "```" + prefix + commandInfo.exampleCommand() + "```", true);
        });

        e.getChannel().sendMessage(eb.build()).queue();
    }

    public void helpAll(CommandReceivedEvent e) {
        e.getUser().openPrivateChannel().queue(channel -> {
            getCommandMap().getCategories().forEach(category -> {
                helpCategory(e, category, e.getPrefix());
            });
        }, failure -> {
            e.getChannel().sendMessage("Cannot DM user!").queue();
        });
    }
}
