package er.art;

import er.art.coffeeDrinks.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

public class Main {

    public static void main (String[] args) throws IOException {

        CoffeeMachine coffeeMachine = new CoffeeMachine ( args[0], args[1], args[2] );
        System.out.println ( "\n\n\n" + coffeeMachine.getStartMsg () );
        System.out.println ( "\n\n" + coffeeMachine.getMsgValueAddError () + "\n" );

        BufferedReader reader = new BufferedReader ( new InputStreamReader ( System.in ) );
        var drinkList = coffeeMachine.getDrinkList ();
        var functionList = coffeeMachine.getFunctionList ();

        while (true) {

            String input = reader.readLine ().toLowerCase ( Locale.ROOT ).trim ().replaceAll ( "\\s+", " " );

            if (input.equals ( "" )) {
                System.out.println ( "Ошибка!\n • Отсутствует команда." );
                continue;
            }
            if (input.equals ( "turn off" )) System.exit ( 0 );


            String[] inputArray = input.split ( " " );

            String command;
            int size = 0;

            StringBuilder errMsg;
            errMsg = new StringBuilder ();


            if (inputArray.length == 2) {
                command = inputArray[0];

                try {
                    if (!(functionList.contains ( command )) && !(drinkList.contains ( command ))) {
                        errMsg.append ( "Ошибка!\n • Команда не найдена." );

                        size = Integer.parseInt ( inputArray[1] );

                        if (size < 10) {
                            errMsg.append ( "\n • Введенное значение объема не соответствует требованиям (N ≥ 10)." );
                            System.out.println ( errMsg );
                        }
                        else {
                            System.out.println ( errMsg );
                            continue;
                        }
                    }
                    else if (functionList.contains ( command ) | drinkList.contains ( command )) {
                        if (functionList.contains ( command )) {
                            try {
                                size = Integer.parseInt ( inputArray[1] );
                            } catch (NumberFormatException e) {
                                errMsg.append ( "Ошибка!\n • Второй параметр должен быть числом." );
                                System.out.println ( errMsg );
                                continue;
                            }
                        }
                        else {
                            try {
                                size = Integer.parseInt ( inputArray[1] );
                                if (size < 10) {
                                    errMsg.append ( "Ошибка!" );
                                    errMsg.append ( "\n • Введенное значение объема не соответствует требованиям (N ≥ 10)." );
                                    System.out.println ( errMsg );
                                    continue;
                                }
                            } catch (NumberFormatException e) {
                                errMsg.append ( "Ошибка!\n • Второй параметр должен быть числом. (Условие N ≥ 10)" );
                                System.out.println ( errMsg );
                                continue;
                            }
                        }
                    }
                } catch (NumberFormatException e) {
                    errMsg.append ( "\n • Второй параметр должен быть числом. (Условие N ≥ 10)" );
                    System.out.println ( errMsg );
                    continue;
                }
            }
            else {
                System.out.println ( "Ошибка!\n• Недопустимое колличество аргументов команды!" );
                continue;
            }

            switch ( command ) {
                case "ristretto":
                    var ristretto = new Ristretto ( coffeeMachine, size );
                    coffeeMachine.makeDrink ( ristretto );
                    break;
                case "cappuccino":
                    var cappuccino = new Cappuccino ( coffeeMachine, size );
                    coffeeMachine.makeDrink ( cappuccino );
                    break;
                case "lungo":
                    var lungo = new Lungo ( coffeeMachine, size );
                    coffeeMachine.makeDrink ( lungo );
                    break;
                case "espresso":
                    var espresso = new Espresso ( coffeeMachine, size );
                    coffeeMachine.makeDrink ( espresso );
                    break;
                case "latte":
                    var latte = new Latte ( coffeeMachine, size );
                    coffeeMachine.makeDrink ( latte );
                    break;

                case "water":
                    coffeeMachine.setWater ( coffeeMachine.getWater () + size );
                    coffeeMachine.printValueAdd ( "воды" );
                    break;
                case "milk":
                    coffeeMachine.setMilk ( coffeeMachine.getMilk () + size );
                    coffeeMachine.printValueAdd ( "молока" );
                    break;
                case "coffee":
                    coffeeMachine.setCoffee ( coffeeMachine.getCoffee () + size );
                    coffeeMachine.printValueAdd ( "кофе" );
                    break;
            }
        }
    }
}