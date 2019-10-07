package edu.nyu.cs9053.homework5;


public class Baguette extends AbstractRecipe implements Recipe {

    private static final int DEFAULT_VOLUME_CUBIC_INCHES = 2000;

    private final double rate;

    public Baguette(double rate) {
        super(DEFAULT_VOLUME_CUBIC_INCHES);
        this.rate = rate;
    }

    /**
     * {@code this.remainingSecondsUntilDone} obey exponential decay. There is no exact time so take 0.01(~0) as the purpose. Accoring to
     * the formula from wiki, the time would be ln(0.01/temperature)/(-constant), that is [ln(temperature) +ln(100)]/constant.
     */
    @Override public void initializeFromOven(Oven oven) {
        this.ovenTemperature = oven.getSetTemperature();
        this.remainingSecondsUntilDone = (Math.log(this.ovenTemperature) + Math.log(100)) / this.rate * 60.0;
    }
}