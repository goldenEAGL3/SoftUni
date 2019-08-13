package OOP.Reflection.pr0304Barracks.core.commands;

import OOP.Reflection.pr0304Barracks.contracts.Inject;
import OOP.Reflection.pr0304Barracks.contracts.Repository;


public class RetireCommand extends CommandImpl {
    @Inject
    private Repository repository;

    protected RetireCommand(String[] data) {
        super(data);
    }

    @Override
    public String execute() {
        this.repository.removeUnit(this.getData()[1]);
        return this.getData()[1] + " retired!";
    }
}
