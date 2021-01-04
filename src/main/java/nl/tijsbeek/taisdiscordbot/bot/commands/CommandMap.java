package nl.tijsbeek.taisdiscordbot.bot.commands;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;

import java.util.ArrayList;
import java.util.HashMap;

public class CommandMap {
    private final HashMap<String, JDACommand> commands = new HashMap<>();
    private final ArrayList<ACommand> fullCommandList = new ArrayList<>();
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
        return classInfo.hasAnnotation(ACommand.class.getName());
    }

    private void addCommandClass(Class<?> aClass) {
        try {
            String[] commandAliases = aClass.getAnnotation(ACommand.class).commandAliases();
            JDACommand JDACommand = ((Class<JDACommand>) aClass).getDeclaredConstructor().newInstance();
            fullCommandList.add(aClass.getAnnotation(ACommand.class));

            for (String commandAlias : commandAliases) {
                commands.put(commandAlias, JDACommand);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public JDACommand getCommand(String commandName) {
        return commands.get(commandName);
    }
}