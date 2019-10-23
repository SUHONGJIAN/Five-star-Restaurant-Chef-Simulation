package edu.nyu.cs9053.homework5;

/**
 * User: blangel
 */
public class Chef {

    private final SousChef sousChef;

    private final Oven oven;

    public Chef(SousChef sousChef, Oven oven) {
        this.sousChef = sousChef;
        this.oven = oven;
    }

    public static void main(String[] args) {
        Oven oven = new Oven(300);
        Chef hongjian = new Chef(new SousChef(oven), oven);

        Recipe firstRecipe = new PotRoast();
        Recipe secondRecipe = new Baguette(0.5);
        Recipe thirdRecipe = new RoastedSweetPotato();
        Recipe fourthRecipe = new Baguette(0.2);

        boolean initialFinishRecipe1 = false;
        boolean initialFinishRecipe2 = false;
        boolean initialFinishRecipe3 = false;
        boolean initialFinishRecipe4 = false;

        while (true) {

            if (firstRecipe.getRemainingSecondsUntilDone() == -60.0) {   //Equaling default value means the recipe has not been put on oven by souschef.
                hongjian.prepareToCook(firstRecipe);
            }
            else if (firstRecipe.getRemainingSecondsUntilDone() != -60.0 && secondRecipe.getRemainingSecondsUntilDone() == -60.0) {
                hongjian.prepareToCook(secondRecipe);
            }
            else if (secondRecipe.getRemainingSecondsUntilDone() != -60.0 && thirdRecipe.getRemainingSecondsUntilDone() == -60.0) {
                hongjian.prepareToCook(thirdRecipe);
            }
            else if (thirdRecipe.getRemainingSecondsUntilDone() != -60.0 && fourthRecipe.getRemainingSecondsUntilDone() == -60.0){
                hongjian.prepareToCook(fourthRecipe);
            }

            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {}

            if (firstRecipe.getRemainingSecondsUntilDone() != -60.0 && !firstRecipe.isRecipeDone()) {
                hongjian.cookInOven(firstRecipe, false);
            }
            if (secondRecipe.getRemainingSecondsUntilDone() != -60.0 && !secondRecipe.isRecipeDone()) {
                hongjian.cookInOven(secondRecipe, false);
            }
            if (thirdRecipe.getRemainingSecondsUntilDone() != -60.0 && !thirdRecipe.isRecipeDone()) {
                hongjian.cookInOven(thirdRecipe, false);
            }
            if (fourthRecipe.getRemainingSecondsUntilDone() != -60.0 && !fourthRecipe.isRecipeDone()) {
                hongjian.cookInOven(fourthRecipe, false);
            }

            if (firstRecipe.isRecipeDone() && !initialFinishRecipe1) {
                oven.takeOut(firstRecipe);
                initialFinishRecipe1 = true;
            }
            if (secondRecipe.isRecipeDone() && !initialFinishRecipe2) {
                oven.takeOut(secondRecipe);
                initialFinishRecipe2 = true;
            }
            if (thirdRecipe.isRecipeDone() && !initialFinishRecipe3) {
                oven.takeOut(thirdRecipe);
                initialFinishRecipe3 = true;
            }
            if (fourthRecipe.isRecipeDone() && !initialFinishRecipe4) {
                oven.takeOut(fourthRecipe);
                initialFinishRecipe4 = true;
            }

            if (firstRecipe.isRecipeDone() && secondRecipe.isRecipeDone() && thirdRecipe.isRecipeDone() && fourthRecipe.isRecipeDone()) break;
        }

    }

    private void prepareToCook(Recipe recipe) {
        sousChef.prepare(recipe, new RecipeReadyCallback() {
            @Override public void recipeReadyToCook(Recipe recipe) {
                System.out.printf(":) Recipe %s is ready to cook!%n", recipe.getClass().getSimpleName());
                recipe.initializeFromOven(oven);
                cookInOven(recipe, true);
            }
        });
    }

    private void cookInOven(Recipe recipe, boolean initialPutInOven) {
        oven.cook(recipe, new Timer() {
            @Override public void update(Time unit, int value, int ovenTemperature) {
                if (unit == null || (unit != Time.Minutes && unit != Time.Seconds)) {
                    throw new IllegalArgumentException();
                }
                recipe.adjust(unit, value, ovenTemperature);
            }
        }, initialPutInOven);
    }
}
