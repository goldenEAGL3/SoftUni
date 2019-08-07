package InterfacesAndAbstractions.MilitaryElite.models;

import InterfacesAndAbstractions.MilitaryElite.enumerations.Corps;
import InterfacesAndAbstractions.MilitaryElite.interfaces.SpecialisedSoldier;

public class SpecialisedSoldierImpl extends PrivateImpl implements SpecialisedSoldier {
    private Corps corps;

    public SpecialisedSoldierImpl(int id, String firstName, String lastName, double salary, Corps corps) {
        super(id, firstName, lastName, salary);
        this.corps = corps;
    }


    @Override
    public String getCorps() {
        return this.corps.toString();
    }
}
