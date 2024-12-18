package hcm;

import com.mysql.cj.util.StringUtils;
import hcm.db.HelicopterDAOImpl;
import hcm.db.PartDAOImpl;
import hcm.helper.HeliConfigManager;
import hcm.util.Constants;

import java.util.Scanner;

public class HCMApp {

    public static final int counter = 5;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        while (true) {
            System.out.println(Constants.HCM_APP_NAME);
            for (String option : Constants.MAIN_APP_OPTIONS) {
                System.out.println(option);
            }

            int choice = getValidInputValue(scanner);
            if(choice == 0) {
                System.exit(0);
            }
            try {
                switch (choice) {
                    case 1:
                        helicopterConfigurations(scanner);
                        break;
                    case 2:
                        partConfigurations(scanner);
                        break;
                    case 3:
                        System.exit(0);
                        break;
                    default:
                        break;
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static int getValidInputValue(Scanner scanner) {
        int iterationCount = 0;
        int choice = 0;
        while (iterationCount <= 5) {
            String input = scanner.nextLine();
            choice = validateInput(input, Constants.MAIN_APP_OPTIONS.size());
            if(choice > 0) {
                break;
            } else {
                System.out.println("Please enter a valid Choice:");
            }
            iterationCount++;
        }
        return choice;

    }

    private static int validateInput(String input, int maxLimit) {
        if(!StringUtils.isNullOrEmpty(input) && input.matches("[1-" + maxLimit + "]")) {
            return Integer.parseInt(input);
        }
        return 0;
    }

    private static void helicopterConfigurations(Scanner scanner) {

        while (true) {
            System.out.println("Please select Operation:");
            for (String option : Constants.HELI_CONFIG_OPTIONS) {
                System.out.println(option);
            }
            while (!scanner.hasNextInt()) {
                System.out.println("Please enter a valid Choice:");
                scanner.nextLine();
            }
            int configurationChoice = scanner.nextInt();
            while (Constants.HELI_CONFIG_OPTIONS.size() < configurationChoice) {
                System.out.println("Please enter a valid Choice:");
                scanner.nextLine();
                configurationChoice = scanner.nextInt();
            }

            scanner.nextLine();
            HelicopterDAOImpl helicopterDAO = new HelicopterDAOImpl();
            switch (configurationChoice) {
                case 1:
                    HeliConfigManager.createConfiguration(scanner, helicopterDAO);
                    break;
                case 3:
                    String configuration = HeliConfigManager.readConfiguration(scanner, helicopterDAO);
                    System.out.println(configuration);
                    System.out.println(Constants.OPERATION_SEPARATOR);
                    break;
                case 4:
                    HeliConfigManager.deleteConfiguration(scanner, helicopterDAO);
                    break;
                case 5:
                    return;
                default:
                    break;
            }
        }

    }

    private static void partConfigurations(Scanner scanner) {

        while (true) {
            System.out.println("Please select Operation:");
            System.out.println("1. Create Part Configuration");
            System.out.println("2. Update Part Configuration");
            System.out.println("3. Read Part Configuration");
            System.out.println("4. Delete Part Configuration");
            System.out.println("5. Exit");
            int configurationChoice = scanner.nextInt();
            scanner.nextLine();
            PartDAOImpl partDAO = new PartDAOImpl();
            switch (configurationChoice) {
                case 1:
                    // createConfiguration(scanner, partDAO);
                    break;
                case 4:
                    // deleteConfiguration(scanner, partDAO);
                    break;
                case 5:
                    return;
                default:
                    break;
            }
        }

    }


}
