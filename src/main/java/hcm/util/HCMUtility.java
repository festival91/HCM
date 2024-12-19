package hcm.util;

import org.apache.commons.lang3.StringUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class HCMUtility {

    protected static final List<String> ALLOWED_Y_N_CHARACTERS = Arrays.asList("y", "n", "yes", "no");

    private HCMUtility() {}

    public static String getValidatedInput(Scanner scanner) {
        String nextLine = scanner.nextLine();
        while(StringUtils.isEmpty(nextLine)) {
            System.out.println("Enter valid input...");
            nextLine = scanner.nextLine();
        }
        return nextLine;
    }

    public static String getValidatedYesNoInput(Scanner scanner) {
        String nextLine = scanner.nextLine();
        while(!ALLOWED_Y_N_CHARACTERS.contains(nextLine.toLowerCase())) {
            System.out.println("Enter valid input...");
            nextLine = scanner.nextLine();
        }
        return nextLine;
    }

    public static String getValidatedDataSourceInput(Scanner scanner) {
        String nextLine = scanner.nextLine();
        while(!Arrays.asList("db","xml").contains(nextLine.toLowerCase())) {
            System.out.println("Enter valid input...");
            nextLine = scanner.nextLine();
        }
        return nextLine;
    }

    public static Properties readPropertyFile(String file) {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream(file)) {
            props.load(fis);
            return props;
        } catch (IOException e) {
            System.out.println("Unable to load database properties file.");
            System.exit(1);
        }
        return null;
    }

    public static void updateSwitchProperties(String key, String value) {

        Properties props = readPropertyFile(Constants.SWITCH_PROPERTIES_FILE);
        props.setProperty(key, value);
        try (FileOutputStream fos = new FileOutputStream(Constants.SWITCH_PROPERTIES_FILE)) {

            // Save the update properties
            props.store(fos, "Updated switch Properties");
            System.out.println("Property updated successfully.");
        } catch (IOException e) {
            System.err.println("Error saving properties file: " + e.getMessage());
        }
    }

}
