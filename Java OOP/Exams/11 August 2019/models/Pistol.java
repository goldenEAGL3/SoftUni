package viceCity.models;

public class Pistol extends BaseGun {
    private static final int DEFAULT_BULLETS_PER_BARREL = 10;
    private static final int DEFAULT_TOTAL_BULLETS = 100;
    private static final int DEFAULT_NUMBER_OF_BULLETS_FIRED = 1;

    private int currentBulletsInBarrel = DEFAULT_BULLETS_PER_BARREL;

    public Pistol(String name) {
        super(name, DEFAULT_BULLETS_PER_BARREL, DEFAULT_TOTAL_BULLETS);
    }

    @Override
    public int fire() {
        this.currentBulletsInBarrel -= DEFAULT_NUMBER_OF_BULLETS_FIRED;
        if (this.currentBulletsInBarrel == 0) {
            this.setTotalBullets(this.getTotalBullets() - this.getBulletsPerBarrel());
            this.currentBulletsInBarrel = this.getBulletsPerBarrel();
        }
        return DEFAULT_NUMBER_OF_BULLETS_FIRED;
    }
}
