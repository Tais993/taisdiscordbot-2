package nl.tijsbeek.taisdiscordbot.bot.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import nl.tijsbeek.taisdiscordbot.bot.commands.help.JDAEntities;
import nl.tijsbeek.taisdiscordbot.database.DB;

import java.awt.*;
import java.time.Instant;
import java.util.ArrayList;

public class JDACommand {
    private ACommandInfo aCommandInfo;
    private CommandMap commandMap;
    private CommandReceivedEvent e;
    private DB db;

    protected void execute(CommandReceivedEvent e) {
        e.getChannel().sendMessage("Something is wrong! Contact the creator.").queue();
    }

    protected void guildOnly(CommandReceivedEvent e) {
        e.getChannel().sendMessage("This command is guild only!").queue();
    }
    protected void dmOnly(CommandReceivedEvent e) {
        e.getChannel().sendMessage("This command is DM's only!").queue();
    }


    protected void botModeratorOnly(CommandReceivedEvent e) {
        e.getChannel().sendMessage("This command is only for bot moderators!").queue();
    }

    protected void serverModeratorOnly(CommandReceivedEvent e) {
        e.getChannel().sendMessage("This command is only for server moderators").queue();
    }

//    protected void sendError(String error) {
//        EmbedBuilder eb = getDefaultEmbed();
//        eb.setTitle("Error " + error);
//
//        eb.setDescription("```" + e.getPrefix() + getCommandInfo().pseudoCommand() + "\n");
//        eb.appendDescription(e.getPrefix() + getCommandInfo().exampleCommand() + "```");
//
//        e.getChannel().sendMessage(eb.build()).queue();
//    }

    protected void sendCodeError(String error) {
        EmbedBuilder eb = getDefaultEmbed();

        eb.setTitle("There has been a error within the code");
        eb.setDescription(error);
        eb.appendDescription("Please report this to the bot creator!");

        e.getChannel().sendMessage(eb.build()).queue();
    }

    private EmbedBuilder getShortArgError(String error) {
        EmbedBuilder eb = getDefaultEmbed();


        eb.setTitle("Invalid argument " + error);

        eb.setDescription("```" + e.getPrefix() + getCommandInfo().pseudoCommand() + "\n");
        eb.appendDescription(e.getPrefix() + getCommandInfo().exampleCommand() + "```");

        return eb;
    }

    protected void sendInvalidEntityError(Object object) {
        JDAEntities entity = JDAEntities.valueOf(object);
        if (entity == null) {
            sendCodeError("Given object isn't a valid entity!");
            return;
        }

        EmbedBuilder eb = getDefaultEmbed();

        eb.setTitle("Invalid " + entity.getName() + "!");

        eb.setDescription("```" + e.getPrefix() + getCommandInfo().pseudoCommand() + "\n");
        eb.appendDescription(e.getPrefix() + getCommandInfo().exampleCommand() + "```");

        eb.appendDescription("Either mention the " + entity.getName() + " or type the ID manually");
        e.getChannel().sendMessage(eb.build()).queue();
    }

    protected void sendUnexpectedArgError(String titleError, int atArg) {
        EmbedBuilder eb = getShortArgError(titleError);

        String argsAsString = e.getArgsAsString();
        ArrayList<String> args = e.getArgs();
        String arg = args.get(atArg);
        int indexOfArg = argsAsString.indexOf(arg);

        if (indexOfArg > 7) {
            eb.appendDescription("..." + argsAsString.substring(indexOfArg - 7, indexOfArg + arg.length()) + "<-- *HERE*");
        } else {
            eb.appendDescription("..." + argsAsString.substring(0, indexOfArg + arg.length()) + "<-- *HERE*");
        }

        e.getChannel().sendMessage(eb.build()).queue();
    }

    protected void sendUnexpectedArgError(String titleError, int atArg, String descriptionError) {
        EmbedBuilder eb = getShortArgError(titleError);

        String argsAsString = e.getArgsAsString();
        ArrayList<String> args = e.getArgs();
        String arg = args.get(atArg);
        int indexOfArg = argsAsString.indexOf(arg);

        eb.appendDescription(descriptionError + "\n");

        if (indexOfArg > 7) {
            eb.appendDescription("..." + argsAsString.substring(indexOfArg - 7, indexOfArg + arg.length()) + "<-- *HERE*");
        } else {
            eb.appendDescription("..." + argsAsString.substring(0, indexOfArg + arg.length()) + "<-- *HERE*");
        }

        e.getChannel().sendMessage(eb.build()).queue();
    }

    protected EmbedBuilder getDefaultEmbed() {
        EmbedBuilder eb = new EmbedBuilder();

        eb.setColor(new Color(219, 65, 5));
//        eb.setAuthor("Tais", "https://github.com/Tais993/TaisDiscordBot", e.getJDA().getSelfUser().getAvatarUrl());
        eb.setFooter(e.getUser().getAsTag());
        eb.setTimestamp(Instant.now());

        return eb;
    }

    protected void setEvent(CommandReceivedEvent e) {
        this.e = e;
    }

    public void setCommandInfo(ACommandInfo aCommandInfo) {
        this.aCommandInfo = aCommandInfo;
    }

    public void setCommandMap(CommandMap commandMap) {
        this.commandMap = commandMap;
    }

    public void setDb(DB db) {
        this.db = db;
    }

    public CommandMap getCommandMap() {
        return commandMap;
    }

    public DB getDb() {
        return db;
    }

    public ACommandInfo getCommandInfo() {
        return aCommandInfo;
    }
}
