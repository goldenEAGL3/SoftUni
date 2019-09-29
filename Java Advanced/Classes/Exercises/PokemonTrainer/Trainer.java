package Classes.PokemonTrainer;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Trainer {

    private String name;
    private int numberOfBadges;
    private static final int DEFAULT_BADGES_VALUES = 0;
    private List<Pokemon> pokemonsCollected;

    public Trainer(String name) {
        this.name = name;
        this.numberOfBadges = Trainer.DEFAULT_BADGES_VALUES;
        pokemonsCollected = new ArrayList<>();
    }

    public List<Pokemon> getPokemonsCollected() {
        return pokemonsCollected;
    }

    public String getName() {
        return this.name;
    }

    public int getNumberOfBadges() {
        return this.numberOfBadges;
    }

    public void setNumberOfBadges() {
        this.numberOfBadges += 1;
    }

    public boolean hasSpecifiedPokemonElement(String element) {
        for (Pokemon pokemon : this.getPokemonsCollected()) {
            if (pokemon.getElement().equals(element)) {
                return true;
            }
        }
        return false;
    }
    public void reducingPokemonHealth() {
        List<Pokemon> pokemonsToBeDeleted = new ArrayList<>();
        for (Pokemon pokemon : this.getPokemonsCollected()) {
            pokemon.setHealth();
            if(pokemon.getHealth() <= 0) {
                pokemonsToBeDeleted.add(pokemon);
            }
        }
        if(!pokemonsToBeDeleted.isEmpty()) {
            this.getPokemonsCollected().removeAll(pokemonsToBeDeleted);
        }

    }
}
