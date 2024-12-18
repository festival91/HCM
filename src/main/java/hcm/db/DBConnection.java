package hcm.db;


import hcm.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

    private static final Logger LOGGER = LoggerFactory.getLogger(DBConnection.class);

    private static final String PROPERTIES_FILE = "resources/db.properties";


    private DBConnection() {

    }

    public static Connection getConnection() throws SQLException {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream(PROPERTIES_FILE)) {
            props.load(fis);
        } catch (IOException e) {
            LOGGER.error("Unable to load database properties file.");
            System.exit(1);
        }

        String url = props.getProperty(Constants.DB_URL);
        String user = props.getProperty(Constants.DB_USER);
        String password = props.getProperty(Constants.DB_PASSWORD);

        if (url == null || user == null || password == null) {
            throw new IllegalStateException("Database credentials are missing. Please correct it to proceed");
        }

        return DriverManager.getConnection(url, user, password);
    }
}
