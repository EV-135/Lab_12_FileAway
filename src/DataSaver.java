import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DataSaver {

    public static void main(String[] args) {
        ArrayList<String> records = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        boolean moreRecords = true;

        // Loop to gather data from the user
        while (moreRecords) {
            // Gather input using SafeInput methods
            String firstName = SafeInput.getNonZeroLenString(scanner, "Enter First Name");
            String lastName = SafeInput.getNonZeroLenString(scanner, "Enter Last Name");

            int id = SafeInput.getInt(scanner, "Enter ID Number (numeric)");
            scanner.nextLine();  // Clear buffer after integer input to avoid newline issues
            String idNumber = String.format("%06d", id); // Format ID to six digits (e.g., 000001)

            String email = SafeInput.getNonZeroLenString(scanner, "Enter Email");
            int yearOfBirth = SafeInput.getRangedInt(scanner, "Enter Year of Birth", 1900, 2023);
            scanner.nextLine();  // Clear buffer after integer input to avoid newline issues

            // Create CSV record and add it to the ArrayList
            String record = String.join(", ", firstName, lastName, idNumber, email, String.valueOf(yearOfBirth));
            records.add(record);

            // Ask if the user wants to enter another record
            moreRecords = !SafeInput.getYNConfirm(scanner, "Do you want to enter another record? (Y/N)");
        }

        // Ask for file name to save the data
        String fileName = SafeInput.getNonZeroLenString(scanner, "Enter the file name to save the records (without extension)") + ".csv";

        // Save records to the specified file
        File file = new File("src/" + fileName);
        try (FileWriter writer = new FileWriter(file)) {
            for (String record : records) {
                writer.write(record + "\n");
            }
            System.out.println("Records saved to " + fileName + " successfully.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
