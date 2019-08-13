package OOP.Reflection.pr0304Barracks.core;

import OOP.Reflection.pr0304Barracks.contracts.CommandInterpreter;
import OOP.Reflection.pr0304Barracks.contracts.Executable;
import OOP.Reflection.pr0304Barracks.contracts.Repository;
import OOP.Reflection.pr0304Barracks.contracts.UnitFactory;
;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Engine implements Runnable {

    private CommandInterpreter commandInterpreter;

    public Engine(CommandInterpreter commandInterpreter) {
        this.commandInterpreter = commandInterpreter;
    }

    @Override
    public void run() {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        while (true) {
            try {
                String input = reader.readLine();
                String[] data = input.split("\\s+");
                Executable executable = commandInterpreter.interpretCommand(data);
                String result = executable.execute();
                if (result.equals("fight")) {
                    break;
                }
                System.out.println(result);
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
