package eif.viko.lt.predictionappclient.Utils;

public class EmailToNameConverter {

    public static String getFullNameFromEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Invalid email provided.");
        }

        // Split the email at the '@' symbol
        String[] parts = email.split("@");
        if (parts.length < 2) {
            throw new IllegalArgumentException("Invalid email format.");
        }

        // Take the part before '@' and split it into name and surname
        String[] nameParts = parts[0].split("\\.");
        if (nameParts.length != 2) {
            throw new IllegalArgumentException("Email does not contain a proper name format.");
        }

        // Capitalize the first letter of each part and concatenate them
        String name = capitalizeFirstLetter(nameParts[0]);
        String surname = capitalizeFirstLetter(nameParts[1]);

        return surname + " " + name;
    }

    private static String capitalizeFirstLetter(String word) {
        if (word == null || word.isEmpty()) {
            return word;
        }
        return word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
    }

    public static String extractName(String fullName) {
        if (fullName == null || fullName.isBlank()) {
            throw new IllegalArgumentException("Input fullName cannot be null or blank");
        }
        String[] parts = fullName.trim().split("\\s+");
        if (parts.length < 2) {
            throw new IllegalArgumentException("Input fullName must contain at least two words");
        }
        return parts[1];
    }

    public static String extractSurname(String fullName) {
        if (fullName == null || fullName.isBlank()) {
            throw new IllegalArgumentException("Input fullName cannot be null or blank");
        }
        String[] parts = fullName.trim().split("\\s+");
        if (parts.length < 2) {
            throw new IllegalArgumentException("Input fullName must contain at least two words");
        }
        return parts[0];
    }

    public static String createEmail(String fullName) {
        if (fullName == null || fullName.isBlank()) {
            throw new IllegalArgumentException("Full name cannot be null or blank");
        }

        // Split the full name into parts
        String[] nameParts = fullName.trim().toLowerCase().split("\\s+");

        if (nameParts.length != 2) {
            throw new IllegalArgumentException("Full name must contain exactly two parts: Surname and Name");
        }

        // Reverse the order and combine with a dot, then append the domain
        return nameParts[1] + "." + nameParts[0] + "@viko.lt";
    }

}
