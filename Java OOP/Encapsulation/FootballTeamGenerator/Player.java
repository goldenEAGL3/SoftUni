package Encapsulation.FootballTeamGenerator;

public class Player {
    private String name;
    private int endurance;
    private int sprint;
    private int dribble;
    private int passing;
    private int shooting;

    public Player(String name, int endurance, int sprint, int dribble, int passing, int shooting) {
        this.setName(name);
        this.setEndurance(endurance);
        this.setSprint(sprint);
        this.setDribble(dribble);
        this.setPassing(passing);
        this.setShooting(shooting);
    }

    private void setName(String name) {
        if (name.trim().isEmpty()) {
            throw new IllegalArgumentException("A name should not be empty.");
        }
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    private void setEndurance(int endurance) {
        validateData("Endurance", endurance);
        this.endurance = endurance;
    }

    private void setSprint(int sprint) {
        validateData("Sprint", sprint);
        this.sprint = sprint;
    }

    private void setDribble(int dribble) {
        validateData("Dribble", dribble);
        this.dribble = dribble;
    }

    private void setPassing(int passing) {
        validateData("Passing", passing);
        this.passing = passing;
    }

    private void setShooting(int shooting) {
        validateData("Shooting", shooting);
        this.shooting = shooting;
    }

    private void validateData(String source, int data) {
        if (data < 0 || data > 100) {
            throw new IllegalArgumentException(String.format("%s should be between 0 and 100.", source));
        }

    }

    public double overallSkillLevel() {
        return (this.endurance + this.sprint + this.dribble + this.passing + this.shooting) / 5.0;
    }
}
