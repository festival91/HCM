package hcm.helper;

import hcm.db.HelicopterDAOImpl;
import hcm.db.PartDAOImpl;
import hcm.entities.Helicopter;
import hcm.entities.Part;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PartHelper {


    PartDAOImpl partDAO = new PartDAOImpl();


    public int addPart(Part part) {
        return partDAO.insert(part);
    }

    public int deletePart(Part part) {
        return partDAO.delete(part);
    }

    public List<Part> getAllAssociatedPart(int id) {
        return partDAO.getAllRelatedParts(id);
    }


    public static void createNewPart(Part part) {

        PartHelper partHelper = new PartHelper();
        partHelper.addPart(part);

    }

    public static int deletePart(int helicopterID) {

        PartHelper partHelper = new PartHelper();
        Part part = new Part("", "", helicopterID);
        return partHelper.deletePart(part);
    }

    public static List<Part> getAssociatedParts(int helicopterID) {

        PartHelper partHelper = new PartHelper();
        return partHelper.getAllAssociatedPart(helicopterID);
    }

}
