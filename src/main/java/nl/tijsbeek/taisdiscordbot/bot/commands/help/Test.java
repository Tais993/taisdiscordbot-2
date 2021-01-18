package nl.tijsbeek.taisdiscordbot.bot.commands.help;

import net.dv8tion.jda.api.entities.Member;
import nl.tijsbeek.taisdiscordbot.bot.commands.ACommandInfo;
import nl.tijsbeek.taisdiscordbot.bot.commands.CommandReceivedEvent;
import nl.tijsbeek.taisdiscordbot.bot.commands.JDACommand;

@ACommandInfo(
        commandAliases = {"test", "t"},
        category = "test",
        pseudoCommand = "test",
        exampleCommand = "test",
        shortCommandDescription = "Testing",
        fullCommandDescription = """
                Testing
                """
)
public class Test extends JDACommand {
    @Override
    protected void execute(CommandReceivedEvent e) {
        super.setEvent(e);

        Member member = e.getMember();

        super.sendUnexpectedArgError("No Friends Found", 1, "Expecting a mentioned user");
        super.sendUnexpectedArgError("No Friends Found", 1);

//        e.getChannel().sendMessage("Voice state is null: " + (member.getVoiceState() == null)).queue();
//        e.getChannel().sendMessage("Is In voice channel: " + member.getVoiceState().inVoiceChannel()).queue();
//        e.getChannel().sendMessage("In channel: " + member.getVoiceState().getChannel()).queue();
    }
}
