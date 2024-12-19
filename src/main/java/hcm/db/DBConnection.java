package hcm.db;


import hcm.util.Constants;
import hcm.util.HCMUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

    private static final Logger LOGGER = LoggerFactory.getLogger(DBConnection.class);

    private DBConnection() {

    }

    public static Connection getConnection() throws SQLException {
        Properties props = HCMUtility.readPropertyFile(Constants.DB_PROPERTIES_FILE);

        String url = props.getProperty(Constants.DB_URL);
        String user = props.getProperty(Constants.DB_USER);
        String password = props.getProperty(Constants.DB_PASSWORD);

        if (url == null || user == null || password == null) {
            throw new IllegalStateException("Database credentials are missing. Please correct it to proceed");
        }

        return DriverManager.getConnection(url, user, password);
    }
}
