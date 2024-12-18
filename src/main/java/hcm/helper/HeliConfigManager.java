package hcm.helper;

import com.mysql.cj.util.StringUtils;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import hcm.db.HelicopterDAOImpl;
import hcm.entities.Helicopter;
import hcm.entities.Part;
import hcm.util.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HeliConfigManager {

    public static void createConfiguration(Scanner scanner, HelicopterDAOImpl helicopterDAO) {

        List<Part> partList = new ArrayList<>();
        System.out.println("Enter Model Name:");
        String modelName = scanner.nextLine();
        String value = "Y";
        while(true) {
            System.out.println("Enter Part Name:");
            String partName = scanner.nextLine();
            System.out.println("Enter Part Type:");
            String partType = scanner.nextLine();
            if(!StringUtils.isNullOrEmpty(partName) && !StringUtils.isNullOrEmpty(partType)) {
                partList.add(new Part(partName, partType, 0));
            }
            System.out.println("Do you want to add more parts? (Y/N)");
            value = scanner.nextLine();
            if("N".equals(value)) {
                break;
            } else if("Y".equals(value)) {
                // valid input
            } else {
                System.out.println("Enter valid input!");
                scanner.nextLine();
            }
        }

        int inserted = helicopterDAO.insert(new Helicopter(modelName));
        if(inserted >= 0) {
            Helicopter helicopter = helicopterDAO.getObjectById(modelName);
            for(Part part : partList) {
                part.setHelicopterId(helicopter.getId());
                PartHelper.createNewPart(part);
            }

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

    public static String readConfiguration(Scanner scanner, HelicopterDAOImpl helicopterDAO) {
        System.out.println("Enter Model Name to show:");
        StringBuilder builder = new StringBuilder();
        String modelName = scanner.nextLine();
        Helicopter helicopter = helicopterDAO.getObjectById(modelName);
        if(helicopter != null) {
            builder.append(helicopter);
            builder.append(Constants.LINE_SEPARATOR);
            List<Part> associatedParts = PartHelper.getAssociatedParts(helicopter.getId());
            for(Part part : associatedParts) {
                builder.append(part);
                builder.append(Constants.LINE_SEPARATOR);
            }
        }
        return builder.toString();
    }
}
