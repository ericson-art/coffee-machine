package er.art;

public interface CoffeDrinks {
    public String getNameDrink ();

    public StringBuilder getPrintMSG ();

    void setPrintMSG (int waterInMachine, int coffeeInMachine, int milkInMachine);

    public void calkOfIngredients ();

    public Boolean isCalculate ();
}