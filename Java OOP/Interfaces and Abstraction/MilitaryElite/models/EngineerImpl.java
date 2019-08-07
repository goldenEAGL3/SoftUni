package InterfacesAndAbstractions.MilitaryElite.models;

import InterfacesAndAbstractions.MilitaryElite.enumerations.Corps;
import InterfacesAndAbstractions.MilitaryElite.interfaces.Engineer;
import InterfacesAndAbstractions.MilitaryElite.interfaces.Repair;

import java.util.ArrayList;
import java.util.List;

public class EngineerImpl extends SpecialisedSoldierImpl implements Engineer {
    private List<Repair> repairs;

    public EngineerImpl(int id, String firstName, String lastName, double salary, Corps corps) {
        super(id, firstName, lastName, salary, corps);
        this.repairs = new ArrayList<>();
    }

    @Override
    public void addRepair(Repair repair) {
        this.repairs.add(repair);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append(System.lineSeparator()).append(String.format("Corps: %s", this.getCorps()))
        .append(System.lineSeparator()).append("Repairs:");
        for (Repair repair : repairs) {
            sb.append(System.lineSeparator()).append("  ").append(repair.toString());
        }
        return sb.toString();
    }
}

