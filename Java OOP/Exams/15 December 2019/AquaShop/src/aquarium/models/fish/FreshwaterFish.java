package aquarium.models.fish;

public class FreshwaterFish extends BaseFish {
    private static final int DEFAULT_SIZE = 3;

    public FreshwaterFish(String name, String species, double price) {
        super(name, species, price);
    }

    @Override
    public int getSize() {
        return DEFAULT_SIZE;
    }

    @Override
    public void eat() {
        super.size += 3;
    }
}
