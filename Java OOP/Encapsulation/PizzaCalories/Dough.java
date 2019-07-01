package Encapsulation.PizzaCalories;

public class Dough {
    private String flourType;
    private String bakingTechnique;
    private double weight;
    private FlourType flourTypeEnumerations;
    private BakingTechnique bakingTechniqueEnumerations;

    public Dough(String flourType, String bakingTechnique, double weight) {
        this.setFlourType(flourType);
        this.setBakingTechnique(bakingTechnique);
        this.setWeight(weight);
    }

    private void setFlourType(String flourType) {
        boolean contains = false;
        for (FlourType value : FlourType.values()) {
            if (value.toString().equals(flourType)) {
                flourTypeEnumerations = value;
                contains = true;
            }
        }
        if (contains) {
            this.flourType = flourType;
        } else {
            throw new IllegalArgumentException("Invalid type of dough.");
        }
    }

    private void setBakingTechnique(String bakingTechnique) {
        boolean contains = false;
        for (BakingTechnique value : BakingTechnique.values()) {
            if (value.toString().equals(bakingTechnique)) {
                bakingTechniqueEnumerations = value;
                contains = true;
            }
        }
        if (contains) {
            this.bakingTechnique = bakingTechnique;
        } else {
            throw new IllegalArgumentException("Invalid type of dough.");
        }

    }

    private void setWeight(double weight) {
        if (weight < 1 || weight > 200) {
            throw new IllegalArgumentException("Encapsulation.PizzaCalories.Dough weight should be in the range [1..200].");
        }
        this.weight = weight;
    }

    public double calculateCalories() {

        return 2 * this.weight
                * this.flourTypeEnumerations.getModifier()
                * this.bakingTechniqueEnumerations.getModifier();
    }


}
