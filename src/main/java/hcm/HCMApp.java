package hcm;

import hcm.db.HelicopterDAOImpl;
import hcm.entities.Helicopter;
import hcm.entities.Part;
import hcm.helper.PartHelper;
import hcm.util.Constants;

import java.util.Scanner;

public class HCMApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HelicopterDAOImpl helicopterDAO = new HelicopterDAOImpl();
        System.out.println(Constants.HCM_APP_NAME);
        while (true) {
            System.out.println("1. Helicopters");
            System.out.println("2. Parts");
            System.out.println("3. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            try {
                switch (choice) {
                    case 1:
                        System.out.println("Please select Operation:");
                        System.out.println("1. Create Helicopter Configuration");
                        System.out.println("2. Update Helicopter Configuration");
                        System.out.println("3. Read Helicopter Configuration");
                        System.out.println("4. Delete Helicopter Configuration");
                        System.out.println("5. Exit");
                        int configurationChoice = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        switch (configurationChoice) {
                            case 1:
                                createConfiguration(scanner, helicopterDAO);
                                break;
                            case 4:
                                deleteConfiguration(scanner, helicopterDAO);
                                break;
                            case 5:
                                System.exit(0);
                                break;
                            default:
                                break;
                        }

                        break;
                    case 2:
                        System.out.println("Viewing all parts:");
                        break;
                    case 3:
                        System.exit(0);
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void createConfiguration(Scanner scanner, HelicopterDAOImpl helicopterDAO) {
        System.out.println("Enter Model Name:");
        String modelName = scanner.nextLine();
        System.out.println("Enter Part Name:");
        String partName = scanner.nextLine();
        System.out.println("Enter Part Type:");
        String partType = scanner.nextLine();

        int inserted = helicopterDAO.insert(new Helicopter(modelName));

        if(inserted >= 0) {
            Helicopter helicopter = helicopterDAO.getObjectById(modelName);
            createNewPart(partName, partType, helicopter.getId());
        }

        System.out.println("New Helicopter configuration created!");
    }

    private static void deleteConfiguration(Scanner scanner, HelicopterDAOImpl helicopterDAO) {
        System.out.println("Enter Model Name to delete:");
        String modelName = scanner.nextLine();
        Helicopter helicopter = helicopterDAO.getObjectById(modelName);
        if(helicopter != null) {
            int deleted = deletePart(helicopter.getId());
            if(deleted >= 0) {
                helicopterDAO.delete(helicopter);
                System.out.println("Helicopter configuration deleted!");
            }
        }


    }

    public static void createNewPart(String partName, String partType, int helicopterID) {

        PartHelper partHelper = new PartHelper();
        Part part = new Part(partName, partType, helicopterID);
        partHelper.addPart(part);

    }

    public static int deletePart(int helicopterID) {

        PartHelper partHelper = new PartHelper();
        Part part = new Part("", "", helicopterID);
        return partHelper.deletePart(part);
    }
}
