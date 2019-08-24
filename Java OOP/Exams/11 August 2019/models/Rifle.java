package viceCity.models;

public class Rifle extends BaseGun {

    private static final int DEFAULT_BULLETS_PER_BARREL = 50;
    private static final int DEFAULT_TOTAL_BULLETS = 500;
    private static final int DEFAULT_NUMBER_OF_BULLETS_FIRED = 5;

    private int currentBulletsInBarrel = DEFAULT_BULLETS_PER_BARREL;

    public Rifle(String name) {
        super(name, DEFAULT_BULLETS_PER_BARREL, DEFAULT_TOTAL_BULLETS);
    }

    @Override
    public int fire() {
        this.currentBulletsInBarrel -= DEFAULT_NUMBER_OF_BULLETS_FIRED;
        if (this.currentBulletsInBarrel == 0) {
            this.setTotalBullets(this.getTotalBullets() - DEFAULT_BULLETS_PER_BARREL);
            this.currentBulletsInBarrel = DEFAULT_BULLETS_PER_BARREL;
        }
        return DEFAULT_NUMBER_OF_BULLETS_FIRED;
    }
}
