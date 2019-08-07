package InterfacesAndAbstractions.MilitaryElite.Application;

import InterfacesAndAbstractions.MilitaryElite.interfaces.Soldier;

import java.util.ArrayList;
import java.util.List;

public class Army {
    private List<Soldier> soldiers;

    public Army() {
        this.soldiers = new ArrayList<>();
    }

    public List<Soldier> getSoldiers() {
        return this.soldiers;
    }

    public void addSoldier(Soldier soldier) {
        this.soldiers.add(soldier);
    }
}
