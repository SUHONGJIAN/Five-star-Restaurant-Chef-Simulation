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
	// TODO - create an instance of Chef and cook the proper recipes according to the instructions.
        Oven oven = new Oven(300);
        Chef hongjian = new Chef(new SousChef(oven), oven);

        Recipe firstRecipe = new PotRoast();
        Recipe secondRecipe = new Baguette(0.6);
        Recipe thirdRecipe = new RoastedSweetPotato();
        Recipe fourthRecipe = new Baguette(0.8);

        boolean preparedRecipe1 = false;
        boolean preparedRecipe2 = false;
        boolean preparedRecipe3 = false;
        boolean preparedRecipe4 = false;

        boolean firstDoneRecipe1 = true;
        boolean firstDoneRecipe2 = true;
        boolean firstDoneRecipe3 = true;
        boolean firstDoneRecipe4 = true;


        while (true) {

            if (firstRecipe.getRemainingSecondsUntilDone() == -60.0 && !preparedRecipe1) {
                hongjian.prepareToCook(firstRecipe);
                preparedRecipe1 = true;
            }
            else if (firstRecipe.getRemainingSecondsUntilDone() != -60.0 && secondRecipe.getRemainingSecondsUntilDone() == -60.0 && !preparedRecipe2) {
                hongjian.prepareToCook(secondRecipe);
                preparedRecipe2 = true;
            }
            else if (secondRecipe.getRemainingSecondsUntilDone() != -60.0 && thirdRecipe.getRemainingSecondsUntilDone() == -60.0 && !preparedRecipe3) {
                hongjian.prepareToCook(thirdRecipe);
                preparedRecipe3 = true;
            }
            else if (thirdRecipe.getRemainingSecondsUntilDone() != -60.0 && fourthRecipe.getRemainingSecondsUntilDone() == -60.0 && !preparedRecipe4){
                hongjian.prepareToCook(fourthRecipe);
                preparedRecipe4 = true;
            }

            try {
                Thread.sleep(500L);   //avoid too many outputs.
            } catch (InterruptedException e) {
                throw new AssertionError(e);
            }

            if (firstRecipe.getRemainingSecondsUntilDone() != -60.0 && !firstRecipe.isRecipeDone()) {   //Not equaling default value means the recipe has been put on oven by souschef.
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

            if (firstRecipe.isRecipeDone() && firstDoneRecipe1) {
                oven.takeOut(firstRecipe);
                firstDoneRecipe1 = false;
            }
            if (secondRecipe.isRecipeDone() && firstDoneRecipe2) {
                oven.takeOut(secondRecipe);
                firstDoneRecipe2 = false;
            }
            if (thirdRecipe.isRecipeDone() && firstDoneRecipe3) {
                oven.takeOut(thirdRecipe);
                firstDoneRecipe3 = false;
            }
            if (fourthRecipe.isRecipeDone() && firstDoneRecipe4) {
                oven.takeOut(fourthRecipe);
                firstDoneRecipe4 = false;
            }

            if (firstRecipe.isRecipeDone() && secondRecipe.isRecipeDone() && thirdRecipe.isRecipeDone() && fourthRecipe.isRecipeDone()) break;

        }

    }

    private void prepareToCook(Recipe recipe) {
	// TODO
        //RecipeReadyCallback callback = new RecipeReadyCallbackImplementation();
        sousChef.prepare(recipe, new RecipeReadyCallback() {
            @Override public void recipeReadyToCook(Recipe recipe) {
                System.out.printf(":) Recipe %s is ready to cook!%n", recipe.getClass().getSimpleName());
                recipe.initializeFromOven(oven);
                cookInOven(recipe, true);
            }
        });
    }

    private void cookInOven(Recipe recipe, boolean initialPutInOven) {
	// TODO
        oven.cook(recipe, new Timer() {
            @Override public void update(Time unit, int value, int ovenTemperature) {
                recipe.adjust(unit, value, ovenTemperature);
            }
        }, initialPutInOven);
    }
}
