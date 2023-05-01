import java.util.Scanner;

public class CoffeeProgram {

    public static void main(String[] args) {
        String[] items = new String[]{"espresso", "latte", "cappuccino"};
        int[] items_water = new int[]{50, 200, 250};
        int[] items_cream = new int[]{0, 150, 100};
        int[] items_coffee = new int[]{25, 20, 15};
        double[] items_prices = new double[]{1.5, 2.5, 3};

        String[] resource = new String[]{"water", "cream", "coffee", "money"};
        double[] resource_levels = new double[]{300, 200, 100, 0};
        String[] resource_measures = new String[]{"oz", "oz", "g", "USD"};

        boolean machineOn = true;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("What would you like? (espresso/latte/cappuccino)");
            String input = scanner.nextLine().trim();

            switch (input.toUpperCase()) {
                case "OFF":
                    machineOn = false;
                    System.out.println("Shutting down... Press [Enter]");
                    scanner.nextLine();
                    break;
                case "REPORT":
                    System.out.println("Current Status:");
                    for (int index = 0; index < resource.length; index++) {
                        System.out.printf("\t%s = %.2f%s%n", resource[index], resource_levels[index], resource_measures[index]);
                    }
                    System.out.println("Press [Enter]");
                    scanner.nextLine();
                    break;
                case "MENU":
                    System.out.println("Current Menu:");
                    for (int index = 0; index < items.length; index++) {
                        System.out.printf("%s %.2f%s%n", items[index], items_prices[index], resource_measures[3]);
                    }
                    System.out.println("Press [Enter]");
                    scanner.nextLine();
                    break;
                case "ESPRESSO":
                case "LATTE":
                case "CAPPUCCINO":

                    // find menu item index
                    int item = 0;
                    for (int index = 0; index < items.length; index++) {
                        if (items[index].equalsIgnoreCase(input)) {
                            item = index;
                        }
                    }

                    // check resources
                    if (resource_levels[0] < items_water[item]) {
                        System.out.println("Sorry not enough water...");
                    } else if (resource_levels[1] < items_cream[item]) {
                        System.out.println("Sorry not enough cream...");
                    } else if (resource_levels[2] < items_coffee[item]) {
                        System.out.println("Sorry not enough coffee...");
                    } else {
                        double amountPaid = 0;
                        int coins;

                        System.out.printf("Please pay %.2f%s:%n", items_prices[item], resource_measures[3]);
                        System.out.println("How many quarters?");
                        coins = scanner.nextInt();
                        amountPaid += coins * 0.25;

                        System.out.println("How many dimes?");
                        coins = scanner.nextInt();
                        amountPaid += coins * 0.10;

                        System.out.println("How many nickels?");
                        coins = scanner.nextInt();
                        amountPaid += coins * 0.05;

                        System.out.println("How many pennies?");
                        coins = scanner.nextInt();
                        amountPaid += coins * 0.01;
                        
                        scanner.nextLine(); // Consume newline left-over

                        if (amountPaid < items_prices[item]) {
                            System.out.println("Sorry not enough money...");
                        } else {
                            resource_levels[0] -= items_water[item];
                            resource_levels[1] -= items_cream[item];
                            resource_levels[2] -= items_coffee[item];
                            resource_levels[3] += items_prices[item];
                            System.out.printf("Here is your change %.2f%s%n", amountPaid - items_prices[item], resource_measures[3]);
                            System.out.printf("Here is your %s. Enjoy!%n", items[item]);
                        }
                    }
    
                    System.out.println("To continue, press [Enter]");
                    scanner.nextLine();
                    break;
                default:
                    System.out.println("Invalid selection, please try again. Press [Enter]");
                    scanner.nextLine();
                    break;
            }
        } while (machineOn);
    
        scanner.close();
    }
}    
