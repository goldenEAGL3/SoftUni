package OOP.Reflection.pr0304Barracks.core.commands;

import OOP.Reflection.pr0304Barracks.contracts.Executable;


public abstract class CommandImpl implements Executable {

    private String data[];

    protected CommandImpl(String[] data) {
        this.data = data;
    }

    public String[] getData() {
        return this.data;
    }
}
