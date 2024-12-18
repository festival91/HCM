package hcm.db;

import hcm.entities.Helicopter;
import hcm.entities.Part;
import hcm.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PartDAOImpl implements DAO<Part> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PartDAOImpl.class);

    @Override
    public Part getObjectById(String helicopterID) {
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
    public int delete(Part part) {
        int resultInt = -1;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(Constants.PART_DELETE_QUERY)) {
            stmt.setInt(1, part.getHelicopterId());
            resultInt = stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Error deleting part : ", e);
        }
        return resultInt;
    }

    @Override
    public int insert(Part part) {
        int resultInt = -1;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(Constants.PART_ADD_QUERY)) {
            stmt.setInt(1, part.getHelicopterId());
            stmt.setString(2, part.getPartName());
            stmt.setString(3, part.getPartType());
            resultInt = stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Error inserting part : ", e);
        }
        return resultInt;
    }

    public List<Part> getAllRelatedParts(int helicopterID) {
        List<Part> associatedParts = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(Constants.PART_GET_QUERY);
            stmt.setInt(1, helicopterID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Part part = new Part(rs.getString("part_name"), rs.getString("part_type"),
                        rs.getInt("helicopter_id"));
                associatedParts.add(part);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return associatedParts;
    }
}
