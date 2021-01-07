package nl.tijsbeek.taisdiscordbot.bot.commands.help;

import net.dv8tion.jda.api.entities.Member;
import nl.tijsbeek.taisdiscordbot.bot.commands.ACommandInfo;
import nl.tijsbeek.taisdiscordbot.bot.commands.CommandReceivedEvent;
import nl.tijsbeek.taisdiscordbot.bot.commands.JDACommand;

@ACommandInfo(
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

        Member member = e.getMember();

        e.getChannel().sendMessage("Voice state is null: " + (member.getVoiceState() == null)).queue();
        e.getChannel().sendMessage("Is In voice channel: " + member.getVoiceState().inVoiceChannel()).queue();
        e.getChannel().sendMessage("In channel: " + member.getVoiceState().getChannel()).queue();
    }
}
