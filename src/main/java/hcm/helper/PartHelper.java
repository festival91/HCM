package hcm.helper;

import hcm.db.PartDAOImpl;
import hcm.entities.Part;

public class PartHelper {

    PartDAOImpl partDAO = new PartDAOImpl();

    public int addPart(Part part) {
        return partDAO.insert(part);
    }

    public int deletePart(Part part) {
        return partDAO.delete(part);
    }

}
