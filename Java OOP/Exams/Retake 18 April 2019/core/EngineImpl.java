package core;

import core.interfaces.Engine;


public class EngineImpl implements Engine {
    private InputReaderImpl inputReader;
    private CommandHandlerImpl commandHandler;

    public EngineImpl() {

        this.inputReader = new InputReaderImpl();
        commandHandler = new CommandHandlerImpl();
    }

    @Override
    public void run() {
        String output = "";

        inputReader.readLines();
        output = commandHandler.handle(inputReader.getLines());

        System.out.println(output);
    }
}
