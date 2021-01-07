package nl.tijsbeek.taisdiscordbot.bot.commands;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;

import java.util.ArrayList;
import java.util.HashMap;

public class CommandMap {
    private final HashMap<String, JDACommand> commands = new HashMap<>();
    private final ArrayList<ACommandInfo> fullCommandList = new ArrayList<>();
    private final ArrayList<String> categories = new ArrayList<>();

    private static final CommandMap commandMap = new CommandMap();

    public static CommandMap getInstance() {
        return commandMap;
    }

    public CommandMap() {
        new ClassGraph()
                .acceptPackages("nl.tijsbeek.taisdiscordbot.bot.commands")
                .enableAnnotationInfo()
                .scan(2)
                .getAllClasses().stream().filter(this::hasCommandAnnotation)
                .map(ClassInfo::loadClass)
                .forEach(this::addCommandClass);
    }

    private boolean hasCommandAnnotation(ClassInfo classInfo) {
        return classInfo.hasAnnotation(ACommandInfo.class.getName());
    }

    private void addCommandClass(Class<?> aClass) {
        try {
            ACommandInfo aCommandInfo = aClass.getAnnotation(ACommandInfo.class);
            String[] commandAliases = aCommandInfo.commandAliases();
            JDACommand JDACommand = ((Class<JDACommand>) aClass).getDeclaredConstructor().newInstance();
            JDACommand.setCommandInfo(aCommandInfo);
            fullCommandList.add(aCommandInfo);

            if (!categories.contains(aCommandInfo.category())) {
                categories.add(aCommandInfo.category());
            }

            for (String commandAlias : commandAliases) {
                commands.put(commandAlias, JDACommand);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void runCommand(String commandName) {
        JDACommand jdaCommand = commands.get(commandName);

    }

    public JDACommand getCommand(String commandName) {
        return commands.get(commandName);
    }

    public boolean isValidCommand(String commandName) {
        return commands.get(commandName) != null;
    }
}