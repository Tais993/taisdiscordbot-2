package nl.tijsbeek.taisdiscordbot.bot.commands;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import nl.tijsbeek.taisdiscordbot.database.DB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

public class CommandMap {
    private final HashMap<String, JDACommand> commands = new HashMap<>();
    private final ArrayList<ACommandInfo> fullCommandList = new ArrayList<>();
    private final ArrayList<String> categories = new ArrayList<>();

    private final DB db;

    public CommandMap(DB db) {
        new ClassGraph()
                .acceptPackages("nl.tijsbeek.taisdiscordbot.bot.commands")
                .enableAnnotationInfo()
                .scan(2)
                .getAllClasses().stream().filter(this::hasCommandAnnotation)
                .map(ClassInfo::loadClass)
                .forEach(this::addCommandClass);

        this.db = db;
    }

    private boolean hasCommandAnnotation(ClassInfo classInfo) {
        return classInfo.hasAnnotation(ACommandInfo.class.getName());
    }

    private void addCommandClass(Class<?> aClass) {
        try {
            ACommandInfo aCommandInfo = aClass.getAnnotation(ACommandInfo.class);
            String[] commandAliases = aCommandInfo.commandAliases();
            JDACommand jdaCommand = ((Class<JDACommand>) aClass).getDeclaredConstructor().newInstance();
            jdaCommand.setCommandInfo(aCommandInfo);
            jdaCommand.setDb(db);
            jdaCommand.setCommandMap(this);
            fullCommandList.add(aCommandInfo);

            if (!categories.contains(aCommandInfo.category())) {
                categories.add(aCommandInfo.category());
            }

            for (String commandAlias : commandAliases) {
                commands.put(commandAlias, jdaCommand);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void executeCommand(CommandReceivedEvent e) {
        JDACommand jdaCommand = commands.get(e.getCommand());

        if (jdaCommand == null) {
            return;
        }

        ACommandInfo commandInfo = jdaCommand.getCommandInfo();
        if (e.isBotModerator()) {
            jdaCommand.execute(e);
        } else if (commandInfo.botModeratorOnly()) {
            jdaCommand.botModeratorOnly(e);
        } else if (commandInfo.guildOnly() && !e.isFromGuild()) {
            jdaCommand.guildOnly(e);
        } else if (commandInfo.dmOnly() && e.isFromGuild()) {
            jdaCommand.dmOnly(e);
        } else {
            jdaCommand.execute(e);
        }
    }

    public JDACommand getCommand(String commandName) {
        return commands.get(commandName);
    }

    public ArrayList<ACommandInfo> getFullCommandList() {
        return fullCommandList;
    }

    public ArrayList<String> getCategories() {
        return categories;
    }

    public ArrayList<ACommandInfo> getCommandsCategory(String category) {
        return (ArrayList<ACommandInfo>) fullCommandList.stream().filter(commandInfo -> {
            return commandInfo.category().equalsIgnoreCase(category);
        }).collect(Collectors.toList());
    }

    public boolean isValidCategory(String category) {
        return categories.contains(category);
    }

    public boolean isValidCommand(String commandName) {
        return commands.get(commandName) != null;
    }
}