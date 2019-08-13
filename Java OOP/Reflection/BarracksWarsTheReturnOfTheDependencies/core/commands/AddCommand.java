package OOP.Reflection.pr0304Barracks.core.commands;

import OOP.Reflection.pr0304Barracks.contracts.Inject;
import OOP.Reflection.pr0304Barracks.contracts.Repository;
import OOP.Reflection.pr0304Barracks.contracts.Unit;
import OOP.Reflection.pr0304Barracks.contracts.UnitFactory;

public class AddCommand extends CommandImpl {
    @Inject
    private Repository repository;
    @Inject
    private UnitFactory unitFactory;

    protected AddCommand(String[] data) {
        super(data);
    }

    @Override
    public String execute() {
        String unitType = this.getData()[1];
        Unit unitToAdd = this.unitFactory.createUnit(unitType);
        this.repository.addUnit(unitToAdd);
        return unitType + " added!";

    }
}
