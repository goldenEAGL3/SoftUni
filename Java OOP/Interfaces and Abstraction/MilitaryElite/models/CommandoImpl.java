package InterfacesAndAbstractions.MilitaryElite.models;

import InterfacesAndAbstractions.MilitaryElite.enumerations.Corps;
import InterfacesAndAbstractions.MilitaryElite.interfaces.Commando;
import InterfacesAndAbstractions.MilitaryElite.interfaces.Mission;

import java.util.ArrayList;
import java.util.List;

public class CommandoImpl extends SpecialisedSoldierImpl implements Commando {
    private List<Mission> missions;

    public CommandoImpl(int id, String firstName, String lastName, double salary, Corps corps) {
        super(id, firstName, lastName, salary, corps);
        missions = new ArrayList<>();
    }

    @Override
    public void addMission(Mission mission) {
        this.missions.add(mission);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append(System.lineSeparator()).append(String.format("Corps: %s", this.getCorps()))
                .append(System.lineSeparator()).append("Missions:");
        for (Mission mission : missions) {
            sb.append(System.lineSeparator()).append("  ").append(mission.toString());
        }
        return sb.toString();
    }
}
