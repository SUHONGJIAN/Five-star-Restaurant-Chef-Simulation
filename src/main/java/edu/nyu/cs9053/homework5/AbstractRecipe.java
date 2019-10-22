package edu.nyu.cs9053.homework5;


public abstract class AbstractRecipe implements Recipe {

    private final int volumeCubicInches;

    private static final Double DEFAULT_REMAINING_SECONDS_UNTIL_DONE = -60.0;   //set a non-0 default value to avoid the wrong response of isRecipeDone().

    protected AtomicReference<Double> remainingSecondsUntilDone;

    protected int ovenTemperature;

    protected AbstractRecipe(int volumeCubicInches) {
        this.volumeCubicInches = volumeCubicInches;
        this.remainingSecondsUntilDone = DEFAULT_REMAINING_SECONDS_UNTIL_DONE;
    }

    @Override
    public int getVolumeCubicInches() {
        return volumeCubicInches;
    }

    @Override
    public Double getRemainingSecondsUntilDone() {
        return remainingSecondsUntilDone;
    }

    @Override
    public boolean isRecipeDone() {
        if (remainingSecondsUntilDone <= 0.0) return true;
        else return false;
    }

    @Override
    public void adjust(Time unit, int amount, int ovenTemperature) {     //Because different temperature needs different time, so it's possible the remaining time is larger than before after adjust() run.
        if (unit == Time.Minutes) {
            amount *= 60;
        }
        if (remainingSecondsUntilDone - amount < 0) {
            remainingSecondsUntilDone = 0.0;
        } else {
            remainingSecondsUntilDone = (remainingSecondsUntilDone - amount) / this.ovenTemperature * ovenTemperature;   //Here is the reason of exist of larger remaining time after adjust() run.
            this.ovenTemperature = ovenTemperature;
        }
    }
}