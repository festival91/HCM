package hcm.db;

import hcm.entities.Part;

import java.util.Collections;
import java.util.List;

public class XMLPartDAOImpl implements DAO<Part> {
    @Override
    public Part getObjectById(String objectID) {
        return null;
    }

    @Override
    public List<Part> getAllObjects() {
        return Collections.emptyList();
    }

    @Override
    public int save(Part object) {
        return 0;
    }

    @Override
    public int update(Part object) {
        return 0;
    }

    @Override
    public int delete(Part object) {
        return 0;
    }

    @Override
    public int insert(Part object) {
        return 0;
    }
}
