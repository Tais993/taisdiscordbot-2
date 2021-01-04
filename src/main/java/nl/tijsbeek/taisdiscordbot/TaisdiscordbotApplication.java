package nl.tijsbeek.taisdiscordbot;

import nl.tijsbeek.taisdiscordbot.bot.TaisBot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TaisdiscordbotApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaisdiscordbotApplication.class, args);
        TaisBot.startBot();
//        CommandMap commandMap = CommandMap.getInstance();
    }
}
