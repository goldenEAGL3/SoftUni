package OOP.Reflection.pr0304Barracks.core;

import OOP.Reflection.pr0304Barracks.contracts.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class CommandInterpreterImpl implements CommandInterpreter {
    private static final String DEFAULT_PACKAGE_NAME = "OOP.Reflection.pr0304Barracks.core.commands.";
    private Repository repository;
    private UnitFactory unitFactory;

    public CommandInterpreterImpl(Repository repository, UnitFactory unitFactory) {
        this.repository = repository;
        this.unitFactory = unitFactory;
    }
    @SuppressWarnings("unchecked")
    @Override
    public Executable interpretCommand(String[] data) {
        String commandName = Character.toUpperCase(data[0].charAt(0)) + data[0].substring(1) + "Command";
        Executable executable = null;
        try {
            Class<? extends Executable> executableClass = (Class<? extends Executable>)
                    Class.forName(CommandInterpreterImpl.DEFAULT_PACKAGE_NAME + commandName);
            Constructor constructor = executableClass.getDeclaredConstructor(String[].class);
            constructor.setAccessible(true);
            executable = (Executable) constructor.newInstance(new Object[]{data});
            Field[] executableFields = executable.getClass().getDeclaredFields();
            Field[] thisCommandImpl = CommandInterpreterImpl.class.getDeclaredFields();

            for (Field executableField : executableFields) {
                if(executableField.isAnnotationPresent(Inject.class)) {
                    for (Field field : thisCommandImpl) {
                        if(executableField.getType().getSimpleName().equals(field.getType().getSimpleName())) {
                            executableField.setAccessible(true);
                            executableField.set(executable, field.get(this));
                        }
                    }
                }
            }



        } catch (ClassNotFoundException
                | NoSuchMethodException
                | InstantiationException
                | IllegalAccessException
                | InvocationTargetException e) {
            e.printStackTrace();
        }
        return executable;
    }
}
