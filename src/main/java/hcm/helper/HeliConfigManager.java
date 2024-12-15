package hcm.helper;

import hcm.db.HelicopterDAOImpl;
import hcm.entities.Helicopter;

import java.util.Scanner;

public class HeliConfigManager {

    public static void createConfiguration(Scanner scanner, HelicopterDAOImpl helicopterDAO) {
        System.out.println("Enter Model Name:");
        String modelName = scanner.nextLine();
        System.out.println("Enter Part Name:");
        String partName = scanner.nextLine();
        System.out.println("Enter Part Type:");
        String partType = scanner.nextLine();

        int inserted = helicopterDAO.insert(new Helicopter(modelName));

        if(inserted >= 0) {
            Helicopter helicopter = helicopterDAO.getObjectById(modelName);
            PartHelper.createNewPart(partName, partType, helicopter.getId());
        }

        System.out.println("New Helicopter configuration created!");
    }

    public static void deleteConfiguration(Scanner scanner, HelicopterDAOImpl helicopterDAO) {
        System.out.println("Enter Model Name to delete:");
        String modelName = scanner.nextLine();
        Helicopter helicopter = helicopterDAO.getObjectById(modelName);
        if(helicopter != null) {
            int deleted = PartHelper.deletePart(helicopter.getId());
            if(deleted >= 0) {
                helicopterDAO.delete(helicopter);
                System.out.println("Helicopter configuration deleted!");
            }
        }


    }
}
