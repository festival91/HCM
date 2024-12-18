package hcm.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
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
}
