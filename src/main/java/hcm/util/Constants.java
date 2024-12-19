package hcm.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Constants {

    private Constants() {}

    public static final String HCM_APP_NAME = "Welcome to Helicopter Configuration Management";

    public static final String PROPERTIES_FILE = "resources/db.properties";
    public static final String SWITCH_PROPERTIES_FILE = "resources/switch.properties";

    // DB Properties
    public static final String DB_URL = "db.url";
    public static final String DB_USER = "db.user";
    public static final String DB_PASSWORD = "db.password";
    public static final String DB_SWITCH = "datasource";

    public static final String HELICOPTER_GET_ALL_QUERY = "SELECT * FROM Helicopters;";
    public static final String HELICOPTER_ADD_QUERY = "INSERT INTO helicopters (model) VALUES (?);";
    public static final String HELICOPTER_DELETE_QUERY = "DELETE FROM Helicopters where model = (?);";
    public static final String HELICOPTER_GET_QUERY = "SELECT model, id, created_at FROM Helicopters where model = (?);";

    public static final String PART_GET_ALL_QUERY = "SELECT * FROM parts;";
    public static final String PART_ADD_QUERY = "INSERT INTO parts (helicopter_id, part_name, part_type) VALUES (?, ?, ?);";
    public static final String PART_DELETE_QUERY = "DELETE FROM parts where helicopter_id = (?);";
    public static final String PART_GET_QUERY = "SELECT helicopter_id, part_name, part_type FROM parts where helicopter_id = (?);";

    public static final List<String> MAIN_APP_OPTIONS = Arrays.asList("1. Helicopters", "2. Parts", "3. Data Source", "4. Exit");

    public static final List<String> HELI_CONFIG_OPTIONS = new ArrayList<>();

    public static final String PLEASE_ENTER_A_VALID_CHOICE = "Please enter a valid Choice:";

    static {
        HELI_CONFIG_OPTIONS.add("1. Create Helicopter Configuration");
        HELI_CONFIG_OPTIONS.add("2. Update Helicopter Configuration");
        HELI_CONFIG_OPTIONS.add("3. Read Helicopter Configuration");
        HELI_CONFIG_OPTIONS.add("4. Delete Helicopter Configuration");
        HELI_CONFIG_OPTIONS.add("5. Exit");
    }

    public static final String LINE_SEPARATOR = "\n--------------------------------------------------------------\n";
    public static final String OPERATION_SEPARATOR = "\n==============================================================\n";

}
