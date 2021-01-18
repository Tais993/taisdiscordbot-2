package nl.tijsbeek.taisdiscordbot.bot.commands;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.utils.MiscUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;

public class CommandReceivedEvent {
    private final MessageReceivedEvent e;

    private final ArrayList<String> args;
    private final boolean hasArgs;
    private final String argsAsString;


    private final String prefix;
    private final String command;

    CommandReceivedEvent(MessageReceivedEvent event, String prefix) {
        e = event;

        ArrayList<String> args = new ArrayList<>(Arrays.asList(e.getMessage().getContentRaw().split("\\s+")));
        this.prefix = prefix;
        command = args.get(0).replace(prefix, "");
        args.remove(0);
        if (args.size() >= 1) {
            hasArgs = true;
            this.args = args;

            StringBuilder argsAsString = new StringBuilder();
            args.forEach(arg -> argsAsString.append(" ").append(arg));
            this.argsAsString = argsAsString.toString().trim();
        } else {
            hasArgs = false;
            this.args = null;
            argsAsString = "";
        }
    }

    public JDA getJDA() {
        return e.getJDA();
    }

    public MessageChannel getChannel() {
        return e.getChannel();
    }

    public TextChannel getTextChannel() {
        return e.getTextChannel();
    }

    public User getUser() {
        return e.getAuthor();
    }

    public Message getMessage() {
        return e.getMessage();
    }

    public Boolean isFromGuild() {
        return e.isFromGuild();
    }

    public Member getMember() {
        return e.getMember();
    }

    public Guild getGuild() {
        return e.getGuild();
    }

    public ArrayList<String> getArgs() {
        return args;
    }

    public Boolean isHasArgs() {
        return hasArgs;
    }

    public String getArgsAsString() {
        return argsAsString;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getCommand() {
        return command;
    }

    public Long getArgAsUserId(int argIndex) {
        if (args.size() <= argIndex)
            return -1L;

        String arg = args.get(argIndex);

        Matcher matcher = Message.MentionType.USER.getPattern().matcher(arg);
        if (matcher.matches()) {
            arg = matcher.group(1);
        }

        try {
            return MiscUtil.parseSnowflake(arg);
        } catch (NumberFormatException ignore) {
            return -1L;
        }
    }

    public Long getArgAsChannelId(int argIndex) {
        if (args.size() <= argIndex)
            return -1L;

        String arg = args.get(argIndex);

        Matcher matcher = Message.MentionType.CHANNEL.getPattern().matcher(arg);
        if (matcher.matches()) {
            arg = matcher.group(1);
        }

        try {
            return MiscUtil.parseSnowflake(arg);
        } catch (NumberFormatException ignore) {
            return -1L;
        }
    }

    public Long getArgAsRoleId(int argIndex) {
        if (args.size() <= argIndex)
            return -1L;

        String arg = args.get(argIndex);

        Matcher matcher = Message.MentionType.ROLE.getPattern().matcher(arg);
        if (matcher.matches()) {
            arg = matcher.group(1);
        }

        try {
            return MiscUtil.parseSnowflake(arg);
        } catch (NumberFormatException ignore) {
            return -1L;
        }
    }

    public Boolean isBotModerator() {
        return true;
    }
}
