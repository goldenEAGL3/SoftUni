package Retake16April2019.arena;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class FightingArena {
    private String name;
    private Map<String, Gladiator> gladiators;

    public FightingArena(String name) {
        this.name = name;
        this.gladiators = new LinkedHashMap<>();
    }

    public String getName() {
        return this.name;
    }

    public void add(Gladiator gladiator) {
        this.gladiators.put(gladiator.getName(), gladiator);
    }

    public void remove(String name) {
        this.gladiators.remove(name);
    }

    public Gladiator getGladiatorWithHighestStatPower() {
        Gladiator gladiator = null;
        gladiator = this.gladiators.values().stream().sorted((a,b) -> b.getStatPower() - a.getStatPower())
                .collect(Collectors.toList()).get(0);
        return gladiator;
    }

    public Gladiator getGladiatorWithHighestWeaponPower() {
        Gladiator gladiator = null;
        gladiator = this.gladiators.values().stream().sorted((a,b) -> b.getWeaponPower() - a.getWeaponPower())
                .collect(Collectors.toList()).get(0);
        return gladiator;
    }

    public Gladiator getGladiatorWithHighestTotalPower() {
        Gladiator gladiator = null;
        gladiator = this.gladiators.values().stream().sorted((a,b) -> b.getTotalPower() - a.getTotalPower())
                .collect(Collectors.toList()).get(0);
        return gladiator;
    }

    public int getCount() {
        return this.gladiators.size();
    }

    @Override
    public String toString() {
        return String.format("%s – %d gladiators are participating.", this.getName(), this.getCount());
    }
}
