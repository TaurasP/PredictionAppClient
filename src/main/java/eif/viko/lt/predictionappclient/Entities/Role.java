package eif.viko.lt.predictionappclient.Entities;

public enum Role {
    STUDENT("Studentas"),
    TEACHER("Mokytojas"),
    ADMIN("Administratorius");

    private final String displayName; // This holds the text value

    Role(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
