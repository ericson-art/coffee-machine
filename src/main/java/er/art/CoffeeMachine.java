package er.art;

import java.util.List;

public class CoffeeMachine {

    private final List<String> drinkList = List.of ( "ristretto", "espresso", "lungo", "cappuccino", "latte" );
    private final List<String> functionList = List.of ( "water", "milk", "coffee" );

    private int coffee;
    private int water;
    private int milk;

    public CoffeeMachine (String coffee, String water, String milk) {
        this.coffee = Integer.parseInt ( coffee );
        this.water = Integer.parseInt ( water );
        this.milk = Integer.parseInt ( milk );
    }

    public int getCoffee () {
        return coffee;
    }

    public void setCoffee (int value) {
        this.coffee = value;
    }

    public int getWater () {
        return water;
    }

    public void setWater (int value) {
        this.water = value;
    }

    public int getMilk () {
        return milk;
    }

    public void setMilk (int value) {
        this.milk = value;
    }

    public List<String> getDrinkList () {
        return drinkList;
    }

    public List<String> getFunctionList () {
        return functionList;
    }

    public void printValueAdd (String msg) {
        String out = String.format ( "%s %s %s  ---> ( Добавили %s )", coffee, water, milk, msg );
        System.out.println ( out );
    }

    public String getMsgValueAddError () {
        return String.format ( "В машине:\n" +
                               " • %d мл. кофе\n" +
                               " • %d мл. воды\n" +
                               " • %d мл. молока\n\n", coffee, water, milk ).trim ();
    }

    private String resultString (CoffeDrinks drink) {
        if (drink.isCalculate ())
            return drink.getPrintMSG ().append ( String.format ( "    -->    Ваш %s готов!", drink.getNameDrink () ) ).toString ();

        else
            return drink.getPrintMSG ().
                    append ( "\n" ).
                    append ( this.getMsgValueAddError () ).
                    append ( "\n" ).toString ();
    }

    public String getStartMsg () {
        String msg = "\nПример команд:\n" +
                     " • water N - залить N мл воды." +
                     "\n" +
                     " • milk N - залить N мл молока." +
                     "\n" +
                     " • coffee N - засыпать N мл кофе." +
                     "\n" +
                     " • latte N - приготовить латте на N мл. Приготовление происходит только при N ≥ 10." +
                     "\n" +
                     " • ristretto N - приготовить ристретто на N мл. Приготовление происходит только при N ≥ 10." +
                     "\n" +
                     " • cappuccino N - приготовить капучино на N мл. Приготовление происходит только при N ≥ 10." +
                     "\n" +
                     " • lungo N - приготовить лунго на N мл. Приготовление происходит только при N ≥ 10." +
                     "\n" +
                     " • espresso N - приготовить эспрессо на N мл. Приготовление происходит только при N ≥ 10." +
                     "\n\n" +
                     " • turn off - отключение. После получения команды отключения кофемашина должна завершить свою работу.";
        return msg.trim ();
    }

    public void makeDrink (CoffeDrinks drink) {
        drink.calkOfIngredients ();
        String output = resultString ( drink ).trim ();
        System.out.println ( output );
    }
}