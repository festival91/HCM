package hcm.util;

public class Constants {

    public static final String HCM_APP_NAME = "Welcome to Helicopter Configuration Management";

    public static final String HELICOPTER_GET_ALL_QUERY = "SELECT * FROM Helicopters;";
    public static final String HELICOPTER_ADD_QUERY = "INSERT INTO helicopters (model) VALUES (?);";
    public static final String HELICOPTER_DELETE_QUERY = "DELETE FROM Helicopters where model = (?);";
    public static final String HELICOPTER_GET_QUERY = "SELECT model, id FROM Helicopters where model = (?);";

    public static final String PART_GET_ALL_QUERY = "SELECT * FROM parts;";
    public static final String PART_ADD_QUERY = "INSERT INTO parts (helicopter_id, part_name, part_type) VALUES (?, ?, ?);";
    public static final String PART_DELETE_QUERY = "DELETE FROM parts where helicopter_id = (?);";
    public static final String PART_GET_QUERY = "SELECT id FROM parts where helicopter_id = (?);";

}
