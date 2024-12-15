package hcm.helper;

import hcm.db.HelicopterDAOImpl;
import hcm.db.PartDAOImpl;
import hcm.entities.Helicopter;
import hcm.entities.Part;

import java.util.Scanner;

public class PartHelper {

    PartDAOImpl partDAO = new PartDAOImpl();

    public int addPart(Part part) {
        return partDAO.insert(part);
    }

    public int deletePart(Part part) {
        return partDAO.delete(part);
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
