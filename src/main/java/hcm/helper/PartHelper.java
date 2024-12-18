package hcm.helper;

import hcm.db.PartDAOImpl;
import hcm.entities.Part;

import java.util.List;

public class PartHelper {


    PartDAOImpl partDAO = new PartDAOImpl();


    public void addPart(Part part) {
        partDAO.insert(part);
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
