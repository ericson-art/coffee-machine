package er.art.coffeeDrinks;

import er.art.CoffeDrinks;
import er.art.CoffeeMachine;

public class Latte implements CoffeDrinks {
    private final int size;
    private final StringBuilder printMSG = new StringBuilder ();
    private final CoffeeMachine coffeeMachine;
    boolean isCalculate = true;
    private int water;
    private int coffee;
    private int milk;

    public Latte (CoffeeMachine coffeeMachine, int size) {
        this.size = size;
        this.coffeeMachine = coffeeMachine;
        water = 0;
        coffee = 0;
        milk = 0;
    }

    @Override
    public String getNameDrink () {
        return "Latte";
    }

    @Override
    public StringBuilder getPrintMSG () {
        return printMSG;
    }

    @Override
    public void setPrintMSG (int waterInMachine, int coffeeInMachine, int milkInMachine) {
        int tempCoffee;
        int tempWater;
        int tempMilk;

        printMSG.append ( "Не хватает ингредиентов. Нужно пополнить запасы!\n" ).
                append ( String.format ( " • Для приготовления хотя бы одной порции %s ( %s мл. )\n" +
                                         "       Необходимо добавить:\n", this.getNameDrink (), size ) );

        if ((waterInMachine < water) && (coffeeInMachine < coffee) && (milkInMachine < milk)) {
            tempCoffee = coffee - coffeeInMachine;
            tempWater = water - waterInMachine;
            tempMilk = milk - milkInMachine;

            printMSG.append ( "        • " + tempCoffee ).append ( " мл. " ).append ( "кофе\n" ).
                    append ( "        • " + tempWater ).append ( " мл. воды\n" ).
                    append ( "        • " + tempMilk ).append ( " мл. молока\n" );

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
            if (milkInMachine < milk) {
                tempMilk = milk - milkInMachine;
                printMSG.append ( "      • " + tempMilk ).append ( " мл." ).append ( " молока\n" );
            }


        }

    }

    @Override
    public void calkOfIngredients () {
        water = (int) Math.round ( size * 0.3d );
        coffee = (int) Math.round ( size * 0.1d );
        milk = (int) Math.round ( size * 0.6d );

        int waterInMachine = coffeeMachine.getWater ();
        int coffeeInMachine = coffeeMachine.getCoffee ();
        int milkInMachine = coffeeMachine.getMilk ();

        if (waterInMachine < water | coffeeInMachine < coffee | milkInMachine < milk) {
            isCalculate = false;
            setPrintMSG ( waterInMachine, coffeeInMachine, milkInMachine );
        }
        else {
            coffeeMachine.setWater ( waterInMachine -= water );
            coffeeMachine.setCoffee ( coffeeInMachine -= coffee );
            coffeeMachine.setMilk ( milkInMachine -= milk );
            printMSG.append ( String.format ( "%s %s %s", coffeeInMachine, waterInMachine, milkInMachine ) );

        }
    }

    @Override
    public Boolean isCalculate () {
        return this.isCalculate;
    }
}