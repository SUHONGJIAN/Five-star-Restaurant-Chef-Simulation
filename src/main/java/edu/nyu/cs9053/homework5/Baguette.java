package edu.nyu.cs9053.homework5;


public class Baguette extends AbstractRecipe implements Recipe {

    private static final int DEFAULT_VOLUME_CUBIC_INCHES = 2000;

    private final double rate;

    public Baguette(double rate) {
        super(DEFAULT_VOLUME_CUBIC_INCHES);
        this.rate = rate;
    }

    @Override public void initializeFromOven(Oven oven) {
        this.ovenTemperature = oven.getSetTemperature();
        remainingSecondsUntilDone = this.ovenTemperature * 3.0;   //这里的指数衰减函数如何使用？？？
        //remainingSecondsUntilDone = oven.getSetTemperature();
    }
}