package Exams.February24th2019.heroRepository;

import java.util.ArrayList;
import java.util.List;

public class HeroRepository {
    private List<Hero> myHeroes;

    public HeroRepository() {
        this.myHeroes = new ArrayList<>();
    }

    public List<Hero> getMyHeroes() {
        return this.myHeroes;
    }

    public void add(Hero hero) {
        this.myHeroes.add(hero);
    }

    public void remove(String heroName) {
        List<Hero> removeList = new ArrayList<>();
        for (Hero myHero : this.myHeroes) {
            if (heroName.equals(myHero.getName())) {
                removeList.add(myHero);
            }
        }
        this.myHeroes.removeAll(removeList);
    }

    public Hero getHeroWithHighestStrength() {
        int highestStrength = 0;
        Hero bestHero = new Hero();
        for (Hero myHero : this.myHeroes) {
            if (myHero.getItem().getStrength() > highestStrength) {
                highestStrength = myHero.getItem().getStrength();
                bestHero = myHero;
            }
        }
        return bestHero;
    }

    public Hero getHeroWithHighestAgility(){
        int highestAgility = 0;
        Hero bestHero = new Hero();
        for (Hero myHero : this.myHeroes) {
            if (myHero.getItem().getAgility() > highestAgility) {
                highestAgility = myHero.getItem().getAgility();
                bestHero = myHero;
            }
        }
        return bestHero;
    }

    public Hero getHeroWithHighestIntelligence(){
        int highestIntelligence = 0;
        Hero bestHero = new Hero();
        for (Hero myHero : this.myHeroes) {
            if (myHero.getItem().getIntelligence() > highestIntelligence) {
                highestIntelligence = myHero.getItem().getIntelligence();
                bestHero = myHero;
            }
        }
        return bestHero;
    }

    public int getCount(){
        return this.myHeroes.size();
    }

    @Override
    public String toString() {
        String result = "";
        for (Hero myHero : this.myHeroes) {
            result += myHero.toString();

        }
        return result;
    }
}
