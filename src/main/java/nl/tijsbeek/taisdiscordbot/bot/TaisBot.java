package nl.tijsbeek.taisdiscordbot.bot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import nl.tijsbeek.taisdiscordbot.bot.commands.CommandHandler;

import javax.security.auth.login.LoginException;
import javax.swing.filechooser.FileSystemView;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class TaisBot {
    public static boolean startBot() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(FileSystemView.getFileSystemView().getDefaultDirectory().getPath() + "\\Discord bot\\token\\tais.token")));
            String token = br.readLine();
            br.close();
            JDA jda = JDABuilder.createLight(token)
                    .enableIntents(
                            GatewayIntent.GUILD_MESSAGES,
                            GatewayIntent.DIRECT_MESSAGES
                    )
                    .enableCache(
                            CacheFlag.VOICE_STATE
                    )
                    .setMemberCachePolicy(MemberCachePolicy.VOICE)
                    .addEventListeners(new CommandHandler())
                    .build()
                    .awaitReady();

        } catch (IOException | LoginException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
