package edu.nyu.cs9053.homework5;


public class RoastedSweetPotato extends AbstractRecipe implements Recipe {

    private static final int DEFAULT_VOLUME_CUBIC_INCHES = 6000;

    public RoastedSweetPotato() {
        super(DEFAULT_VOLUME_CUBIC_INCHES);
    }

    @Override public void initializeFromOven(Oven oven) {
        this.ovenTemperature = oven.getSetTemperature();
        this.remainingSecondsUntilDone = this.ovenTemperature / 10 * 60.0;
    }
}