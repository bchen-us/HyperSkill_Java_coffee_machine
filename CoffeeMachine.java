package machine;

import java.util.Scanner;

public class CoffeeMachine {

    private int coffeeBeans;
    private int cups;
    private int water;
    private int milk;
    private int money;
    private final Scanner scan = new Scanner(System.in);
    private enum MachineStates{
        BUYING, TAKING, TELLING, FILLING, EXITING, WAITING;
    }
    MachineStates currentState;

    CoffeeMachine() {
        water = 0;
        milk = 0;
        coffeeBeans = 0;
        cups = 0;
        currentState = MachineStates.WAITING;
    }

    CoffeeMachine(int waterArg, int milkArg, int beanArg, int cupsArg, int moneyArg) {
        water = waterArg;
        milk = milkArg;
        coffeeBeans = beanArg;
        cups = cupsArg;
        money = moneyArg;
        currentState = MachineStates.WAITING;
    }

    public int getWater() {
        return water;
    }

    public void setWater(int water) {
        this.water = water;
    }

    public int getMilk() {
        return milk;
    }

    public void setMilk(int milk) {
        this.milk = milk;
    }

    public int getCoffeeBeans() {
        return coffeeBeans;
    }

    public void setCoffeeBeans(int coffeeBeans) {
        this.coffeeBeans = coffeeBeans;
    }

    public int getCups() {
        return cups;
    }

    public void setCups(int cups) {
        this.cups = cups;
    }

    public int getMoney() { return money; }

    public void setMoney(int money) { this.money = money; }

    public void putInIngredients() {

        System.out.println("Write how many ml of water the coffee machine has:");
        water = scan.nextInt();
        System.out.println("Write how many ml of milk the coffee machine has:");
        milk = scan.nextInt();
        System.out.println("Write how many grams of coffee beans the coffee machine has:");
        coffeeBeans = scan.nextInt();
        System.out.println("Write how many cups of coffee you will need:");
        cups = scan.nextInt();

    }

    public String coffeeMachineStatus() {

        String str = "";
        str += "The coffee machine has:\n";
        str += water + " of water\n";
        str += milk + " of milk\n";
        str += coffeeBeans + " of coffee beans\n";
        str += cups + " of disposable cups\n";
        str += money + " of money\n";

        return str;

    }
    public void fill(){

        System.out.println("Write how many ml of water do you want to add:\n");
        water += scan.nextInt();
        System.out.println("Write how many ml of milk do you want to add:\n");
        milk += scan.nextInt();
        System.out.println("Write how many grams of coffee beans do you want to add:\n");
        coffeeBeans += scan.nextInt();
        System.out.println("Write how many disposable cups of coffee do you want to add:n\n");
        cups += scan.nextInt();

    }

    public void buy(){

        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:\n");
        switch (scan.next()) {
            case "1":
                if (enoughSuppliesBeforeMakingCoffee(1)) {
                    water -= 250;
                    coffeeBeans -= 16;
                    cups--;
                    money += 4;
                    System.out.println("I have enough resources, making you a coffee!\n");
                } else {
                    System.out.println("Not enough to make coffee\n");
                }
                break;
            case "2":
                if (enoughSuppliesBeforeMakingCoffee(2)) {
                    water -= 350;
                    milk -= 75;
                    coffeeBeans -= 20;
                    cups--;
                    money += 7;
                    System.out.println("I have enough resources, making you a coffee!");
                } else {
                    System.out.println("Not enough to make coffee\n");
                }
                break;
            case "3":
                if (enoughSuppliesBeforeMakingCoffee(3)) {
                    water -= 200;
                    milk -= 100;
                    coffeeBeans -= 12;
                    cups--;
                    money += 6;
                    System.out.println("I have enough resources, making you a coffee!");
                } else {
                    System.out.println("Not enough to make coffee\n");
                }
                break;
            default:
                break;
        }

    }

    public String take() {
        String str = "I gave you $" + money;
        money = 0;
        return str;
    }


    public String tellUser() {

        int maximumCups = water / (200);
        maximumCups = Math.min(maximumCups, milk / (50));
        maximumCups = Math.min(maximumCups, coffeeBeans / (15));

        if ( cups < maximumCups ) {
            return "Yes, I can make that amount of coffee (and even " + (maximumCups - cups) +" more than that)";
        } else if ( cups == maximumCups ) {
            return "Yes, I can make that amount of coffee";
        } else {
            return "No, I can make only " + maximumCups + " cup(s) of coffee";
        }

    }

    public boolean enoughSuppliesBeforeMakingCoffee(int coffeeSelection) {
        switch (coffeeSelection) {
            case 1:
                return (water - 250 >= 0) && (coffeeBeans - 16 >= 0) && (cups - 1 >= 0);
            case 2:
                return (water - 350 >= 0) && (milk - 75 >= 0) && (coffeeBeans - 20 >= 0) && (cups - 1 >= 0);
            case 3:
                return (water - 200 >= 0) && (milk - 100 >= 0) && (coffeeBeans - 12 >= 0) && (cups - 1 >= 0);
            default:
                return false;
        }
    }

    /*
    public boolean userAction(String action) {

        switch (currentState) {
            case BUYING:

            case TAKING:
            case FILLING:
            case TELLING:
            case WAITING:
                switch (action) {
                    case "buy":
                        currentState = MachineStates.BUYING;
                        break;
                    case "fill":
                        currentState = MachineStates.FILLING;
                        break;
                    case "take":
                        currentState = MachineStates.TAKING;
                        break;
                    case "remaining":
                        currentState = MachineStates.TELLING;
                        break;
                    case "exit":
                        currentState = MachineStates.EXITING;
                        break;
                    default:
                        System.out.println("Invalid selection");
                        break;
                }
                break;
            case EXITING:
            default:
        }

        return true;
    }
    */
    public static void main(String[] args) {

        CoffeeMachine cm = new CoffeeMachine(400,540,120,9,550);
        String selection = "";
        Scanner scanner = new Scanner(System.in);
        boolean endProgram = false;

        while (!endProgram) {
            System.out.println("Write action (buy, fill, take, remaining, exit):\n");
            selection = scanner.next();
            switch (selection) {
                case "buy":
                    cm.buy();
                    System.out.println();
                    break;
                case "fill":
                    cm.fill();
                    System.out.println();
                    break;
                case "take":
                    cm.take();
                    System.out.println();
                    break;
                case "remaining":
                    System.out.println(cm.coffeeMachineStatus() + "\n");
                    break;
                case "exit":
                    endProgram = true;
                    break;
                default:
                    System.out.println("Invalid selection");
            }
        }


    }

}

