package er.art.coffeeDrinks;

import er.art.CoffeDrinks;
import er.art.CoffeeMachine;

public class Lungo implements CoffeDrinks {
    private final int size;
    private final StringBuilder printMSG = new StringBuilder ();
    private final CoffeeMachine coffeeMachine;
    boolean isCalculate = true;
    private int water;
    private int coffee;

    public Lungo (CoffeeMachine coffeeMachine, int size) {
        this.size = size;
        this.coffeeMachine = coffeeMachine;
        water = 0;
        coffee = 0;
    }

    @Override
    public String getNameDrink () {
        return "Lungo";
    }

    @Override
    public StringBuilder getPrintMSG () {
        return printMSG;
    }

    @Override
    public void setPrintMSG (int waterInMachine, int coffeeInMachine, int milkInMachine) {
        int tempCoffee;
        int tempWater;

        printMSG.append ( "Не хватает ингредиентов. Нужно пополнить запасы!\n" ).
                append ( String.format ( " • Для приготовления хотя бы одной порции %s ( %s мл. )\n" +
                                         "       Необходимо добавить:\n", this.getNameDrink (), size ) );

        if ((waterInMachine < water) && (coffeeInMachine < coffee)) {
            tempCoffee = coffee - coffeeInMachine;
            tempWater = water - waterInMachine;

            printMSG.append ( "        • " ).append ( tempCoffee ).append ( " мл. " ).append ( "кофе\n" ).
                    append ( "        • " ).append ( tempWater ).append ( " мл. воды\n" );

        }
        else {


            if (coffeeInMachine < coffee) {
                tempCoffee = coffee - coffeeInMachine;
                printMSG.append ( "      • " + tempCoffee ).append ( " мл." ).append ( " кофе\n" );
            }
            if (waterInMachine < water) {
                tempWater = water - waterInMachine;
                printMSG.append ( "      • " + tempWater ).append ( " мл." ).append ( " воды\n" );
            }

        }

    }

    @Override
    public void calkOfIngredients () {

        water = (int) Math.round ( size * 0.85d );
        coffee = (int) Math.round ( size * 0.15d );

        int waterInMachine = coffeeMachine.getWater ();
        int coffeeInMachine = coffeeMachine.getCoffee ();
        int milkInMachine = coffeeMachine.getMilk ();

        if (waterInMachine < water | coffeeInMachine < coffee) {
            isCalculate = false;
            setPrintMSG ( waterInMachine, coffeeInMachine, milkInMachine );
        }
        else {
            coffeeMachine.setWater ( waterInMachine -= water );
            coffeeMachine.setCoffee ( coffeeInMachine -= coffee );
            printMSG.append ( String.format ( "%s %s %s", coffeeInMachine, waterInMachine, milkInMachine ) );

        }
    }

    @Override
    public Boolean isCalculate () {
        return this.isCalculate;
    }
}