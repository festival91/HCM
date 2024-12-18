package hcm.db;

import hcm.entities.Helicopter;
import hcm.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HelicopterDAOImpl implements DAO<Helicopter> {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelicopterDAOImpl.class);

    @Override
    public Helicopter getObjectById(String objectID) {
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(Constants.HELICOPTER_GET_QUERY);
            stmt.setString(1, objectID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Helicopter helicopter = new Helicopter(rs.getString("model"));
                helicopter.setId(rs.getInt("id"));
                helicopter.setCreatedOn((rs.getDate("created_at")).toLocalDate());
                return helicopter;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<Helicopter> getAllObjects() {
        List<Helicopter> helicopterList = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(Constants.HELICOPTER_GET_ALL_QUERY);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Helicopter helicopter = new Helicopter(rs.getString("model"));
                helicopter.setId(rs.getInt("id"));
                helicopterList.add(helicopter);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return helicopterList;
    }

    @Override
    public int save(Helicopter object) {
        return 0;
    }

    @Override
    public int update(Helicopter object) {
        return 0;
    }

    @Override
    public int delete(Helicopter helicopter) {
        int resultInt = 0;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(Constants.HELICOPTER_DELETE_QUERY)) {
            stmt.setString(1, helicopter.getModelName());
            resultInt = stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultInt;
    }

    @Override
    public int insert(Helicopter helicopter) {
        int resultInt = -1;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(Constants.HELICOPTER_ADD_QUERY)) {
            stmt.setString(1, helicopter.getModelName());
            resultInt = stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Error inserting object : ", e);
        }
        return resultInt;

    }
}
