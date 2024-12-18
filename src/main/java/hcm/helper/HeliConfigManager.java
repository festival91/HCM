package hcm.helper;

import hcm.db.HelicopterDAOImpl;
import hcm.entities.Helicopter;
import hcm.entities.Part;
import hcm.util.Constants;
import hcm.util.HCMUtility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class HeliConfigManager {

    private HeliConfigManager() {
    }

    public static void createConfiguration(Scanner scanner, HelicopterDAOImpl helicopterDAO) {

        List<Part> partList = new ArrayList<>();
        System.out.println("Enter Model Name:");
        String modelName = HCMUtility.getValidatedInput(scanner);
        boolean isConfigurationCreated = false;
        while (!isConfigurationCreated) {

            System.out.println("Enter Part Name:");
            String partName = HCMUtility.getValidatedInput(scanner);
            System.out.println("Enter Part Type:");
            String partType = HCMUtility.getValidatedInput(scanner);
            partList.add(new Part(partName, partType, 0));
            System.out.println("Do you want to add more parts? (Y/N)");
            String value = HCMUtility.getValidatedYesNoInput(scanner);
            if (Arrays.asList("n","no").contains(value.toLowerCase())) {

                int inserted = helicopterDAO.insert(new Helicopter(modelName));
                System.out.println("Inserted value : " + inserted);
                if (inserted >= 0) {
                    Helicopter helicopter = helicopterDAO.getObjectById(modelName);
                    for (Part part : partList) {
                        part.setHelicopterId(helicopter.getId());
                        PartHelper.createNewPart(part);
                    }
                }
                System.out.print("Helicopter configuration created successfully.\n");
                isConfigurationCreated = true;
            } else if (Arrays.asList("y","yes").contains(value.toLowerCase())) {
                // no valid action
            } else {
                isConfigurationCreated = true;
            }
        }
    }

    public static void deleteConfiguration(Scanner scanner, HelicopterDAOImpl helicopterDAO) {
        System.out.println("Enter Model Name to delete:");
        String modelName = HCMUtility.getValidatedInput(scanner);
        Helicopter helicopter = helicopterDAO.getObjectById(modelName);
        if (helicopter != null) {
            int deleted = PartHelper.deletePart(helicopter.getId());
            if (deleted >= 0) {
                helicopterDAO.delete(helicopter);
                System.out.println("Helicopter configuration deleted!");
            }
        }
    }

    public static String readConfiguration(Scanner scanner, HelicopterDAOImpl helicopterDAO) {
        System.out.println("Enter Model Name to show:");
        StringBuilder builder = new StringBuilder();
        String modelName = HCMUtility.getValidatedInput(scanner);
        Helicopter helicopter = helicopterDAO.getObjectById(modelName);
        if (helicopter != null) {
            builder.append(helicopter);
            builder.append(Constants.LINE_SEPARATOR);
            List<Part> associatedParts = PartHelper.getAssociatedParts(helicopter.getId());
            for (Part part : associatedParts) {
                builder.append(part);
                builder.append(Constants.LINE_SEPARATOR);
            }
        }
        return builder.toString();
    }
}
