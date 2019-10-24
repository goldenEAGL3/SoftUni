package Retake16April2019.arena;

public class Gladiator {

    private String name;
    private Stat stat;
    private Weapon weapon;

    public Gladiator(String name, Stat stat, Weapon weapon) {
        this.name = name;
        this.stat = stat;
        this.weapon = weapon;
    }

    public String getName() {
        return this.name;
    }

    public int getStatPower() {
        int statPower = this.stat.getAgility() +
                this.stat.getFlexibility() +
                this.stat.getIntelligence() +
                this.stat.getSkills() +
                this.stat.getStrength();

        return statPower;
    }

    public int getWeaponPower() {
        int weaponPower = this.weapon.getSharpness() +
                this.weapon.getSize() +
                this.weapon.getSolidity();

        return weaponPower;
    }

    public int getTotalPower() {
        return this.getStatPower() + this.getWeaponPower();
    }

    @Override
    public String toString() {
        return String.format("%s – %d%n" +
                        "  Weapon Power: %d%n" +
                        "  Stat Power: %d%n",
                this.getName(),
                this.getTotalPower(),
                this.getWeaponPower(),
                this.getStatPower());
    }
}
