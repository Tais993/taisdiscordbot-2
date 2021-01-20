package nl.tijsbeek.taisdiscordbot.bot.commands.help;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

public enum JDAEntities {
    USER("user"),
    MEMBER("member"),
    TEXTCHANNEL("text channel");

    String name;

    JDAEntities(String entityType) {
        this.name = entityType;
    }

    public String getName() {
        return name;
    }

    public static JDAEntities valueOf(Object object) {
        if (object == null) {
            return null;
        }

        try {
            Member member = (Member) object;
            System.out.println("It is a member");
            return MEMBER;
        } catch (ClassCastException e) {
            e.printStackTrace();
        }

        try {
            User user = (User) object;
            System.out.println("It is a user");
            return USER;
        } catch (ClassCastException ignore) {}

        try {
            TextChannel textChannel = (TextChannel) object;
            System.out.println("It is a textChannel");
            return TEXTCHANNEL;
        } catch (ClassCastException ignore) {}

        System.out.println("None?");
        return null;
    }
}