package nl.tijsbeek.taisdiscordbot.bot.commands.help;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

public enum JDAEntities {
    USER("user"),
    MEMBER("member"),
    TEXTCHANNEL("text channel"),
    NULL("NULL");

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

        JDAEntities entity = NULL;

        try {
            Member member = (Member) object;
            System.out.println("It is a member");
            entity = MEMBER;
        } catch (ClassCastException ignore) {}

        try {
            User user = (User) object;
            System.out.println("It is a user");
            entity = USER;
        } catch (ClassCastException ignore) {}

        try {
            TextChannel textChannel = (TextChannel) object;
            System.out.println("It is a textChannel");
            entity = TEXTCHANNEL;
        } catch (ClassCastException ignore) {}

        System.out.println("" + entity.getName());
        return entity;
    }
}