package edu.nyu.cs9053.homework5;


public class PotRoast extends AbstractRecipe implements Recipe {

    private static final int DEFAULT_VOLUME_CUBIC_INCHES = 10000;

    public PotRoast() {
        super(DEFAULT_VOLUME_CUBIC_INCHES);
    }

    @Override public void initializeFromOven(Oven oven) {
        this.ovenTemperature = oven.getSetTemperature();
        remainingSecondsUntilDone = this.ovenTemperature/5 * 60.0;
    }
}