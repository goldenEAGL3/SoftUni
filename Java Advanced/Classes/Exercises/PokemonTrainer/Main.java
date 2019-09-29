package Classes.PokemonTrainer;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Set<Trainer> trainerSet = new LinkedHashSet<>();
        String input = sc.nextLine();
        while (!"Tournament".equals(input)) {
            String[] data = input.split("\\s+");
            String trainerName = data[0];
            String pokemonName = data[1];
            String pokemonElement = data[2];
            int pokemonHealth = Integer.parseInt(data[3]);
            Pokemon newPokemon = new Pokemon(pokemonName, pokemonElement, pokemonHealth);
            Trainer newTrainer = new Trainer(trainerName);
            boolean trainerExists = false;
            for (Trainer trainer : trainerSet) {
                if(trainer.getName().equals(trainerName)) {
                    trainerExists = true;
                    break;
                }
            }
            if (trainerExists) {
                trainerSet.forEach(trainer -> {
                    if(trainer.getName().equals(trainerName)) {
                        trainer.getPokemonsCollected().add(newPokemon);
                    }
                });
            } else {
                newTrainer.getPokemonsCollected().add(newPokemon);
                trainerSet.add(newTrainer);
            }

                    input = sc.nextLine();
        }
        input = sc.nextLine();
        while(!"End".equals(input)) {
           switch (input) {
               case "Fire":

               case "Water":

               case "Electricity":
                   loopingThroughPokemons(trainerSet, input);
                   break;
           }
            input = sc.nextLine();
        }
        trainerSet.stream()
                .sorted((trainer1, trainer2) -> Integer.compare(trainer2.getNumberOfBadges(), trainer1.getNumberOfBadges()))
                .forEach(trainer -> System.out.printf("%s %d %d%n", trainer.getName(), trainer.getNumberOfBadges(), trainer.getPokemonsCollected().size()));
    }


    private static void loopingThroughPokemons(Set<Trainer> trainerSet, String input) {
        for (Trainer trainer : trainerSet) {
            if(trainer.hasSpecifiedPokemonElement(input)){
                trainer.setNumberOfBadges();
            } else {
                trainer.reducingPokemonHealth();
            }
        }
    }
}
