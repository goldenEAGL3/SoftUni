package aquarium.core;

import aquarium.models.aquariums.Aquarium;
import aquarium.models.aquariums.FreshwaterAquarium;
import aquarium.models.aquariums.SaltwaterAquarium;
import aquarium.models.decorations.Decoration;
import aquarium.models.decorations.Ornament;
import aquarium.models.decorations.Plant;
import aquarium.models.fish.Fish;
import aquarium.models.fish.FreshwaterFish;
import aquarium.models.fish.SaltwaterFish;
import aquarium.repositories.DecorationRepository;
import aquarium.repositories.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Stream;

import static aquarium.common.ConstantMessages.*;
import static aquarium.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {
    private Repository decorations;
    private Collection<Aquarium> aquariums;

    public ControllerImpl() {
        this.decorations = new DecorationRepository();
        this.aquariums = new ArrayList<>();
    }

    @Override
    public String addAquarium(String aquariumType, String aquariumName) {
        Aquarium aquarium;
        switch (aquariumType) {
            case "FreshwaterAquarium":
                aquarium = new FreshwaterAquarium(aquariumName);
                break;

            case "SaltwaterAquarium":
                aquarium = new SaltwaterAquarium(aquariumName);
                break;
            default:
                throw new IllegalArgumentException(INVALID_AQUARIUM_TYPE);

        }
        this.aquariums.add(aquarium);
        return String.format(SUCCESSFULLY_ADDED_AQUARIUM_TYPE, aquariumType);
    }

    @Override
    public String addDecoration(String type) {
        Decoration decoration;
        switch (type) {
            case "Ornament":
                decoration = new Ornament();
                break;
            case "Plant":
                decoration = new Plant();
                break;
            default:
                throw new IllegalArgumentException(INVALID_DECORATION_TYPE);
        }
        this.decorations.add(decoration);
        return String.format(SUCCESSFULLY_ADDED_DECORATION_TYPE, type);
    }

    @Override
    public String insertDecoration(String aquariumName, String decorationType) {
        Decoration decorationByType = this.decorations.findByType(decorationType);

        if (decorationByType == null) {
            throw new IllegalArgumentException(String.format(NO_DECORATION_FOUND, decorationType));
        }

        for (Aquarium aquarium : aquariums) {
            if (aquarium.getName().equals(aquariumName)) {
                aquarium.addDecoration(decorationByType);
                this.decorations.remove(decorationByType);
            }
        }
        return String.format(SUCCESSFULLY_ADDED_DECORATION_IN_AQUARIUM, decorationType, aquariumName);
    }

    @Override
    public String addFish(String aquariumName, String fishType, String fishName, String fishSpecies, double price) {
        Fish fish;
        switch (fishType) {
            case "FreshwaterFish":
                fish = new FreshwaterFish(fishName, fishSpecies, price);
                break;

            case "SaltwaterFish":
                fish = new SaltwaterFish(fishName, fishSpecies, price);
                break;

            default:
                throw new IllegalArgumentException(INVALID_FISH_TYPE);

        }

        for (Aquarium aquarium : aquariums) {
            if (aquarium.getName().equals(aquariumName)) {
                String aquariumType = aquarium.getClass().getSimpleName().split("Aquarium")[0];
                String typeOfFish = fish.getClass().getSimpleName().split("Fish")[0];
                if (!aquariumType.equals(typeOfFish)) {
                    return WATER_NOT_SUITABLE;
                }
                aquarium.addFish(fish);
                break;
            }
        }
        return String.format(SUCCESSFULLY_ADDED_FISH_IN_AQUARIUM, fishType, aquariumName);
    }

    @Override
    public String feedFish(String aquariumName) {
        int fishFed = 0;
        for (Aquarium aquarium : aquariums) {
            if (aquarium.getName().equals(aquariumName)) {
                Collection<Fish> fish = aquarium.getFish();
                for (Fish fish1 : fish) {
                    fish1.eat();
                    fishFed++;
                }
            }
        }
        return String.format(FISH_FED, fishFed);
    }

    @Override
    public String calculateValue(String aquariumName) {
        double value = 0.0;
        for (Aquarium aquarium : aquariums) {
            if (aquarium.getName().equals(aquariumName)) ;
            double fishPrice = aquarium.getFish()
                    .stream()
                    .map(Fish::getPrice)
                    .mapToDouble(Double::doubleValue)
                    .sum();

            double decorationsPrice = aquarium.getDecorations()
                    .stream()
                    .map(Decoration::getPrice)
                    .mapToDouble(Double::doubleValue)
                    .sum();

            value = fishPrice + decorationsPrice;
            break;
        }
        return String.format(VALUE_AQUARIUM, aquariumName, value);
    }

    @Override
    public String report() {
        StringBuilder sb = new StringBuilder();
        for (Aquarium aquarium : aquariums) {
            sb.append(aquarium.getInfo());
        }
        return sb.toString().trim();
    }
}
