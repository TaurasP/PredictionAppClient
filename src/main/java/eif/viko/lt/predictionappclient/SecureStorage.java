package eif.viko.lt.predictionappclient;

import java.util.prefs.Preferences;

public class SecureStorage {
    private static final Preferences prefs = Preferences.userNodeForPackage(SecureStorage.class);

    public static void saveToken(String token) {
        prefs.put("token", token);
    }
    public static String getToken() {
        return prefs.get("token", null);
    }
    public static void clearToken() {
        prefs.remove("token");
    }


    public static void saveEmail(String email) {
        prefs.put("email", email);
    }
    public static String getEmail() {
        return prefs.get("email", null);
    }
    public static void clearEmail() {
        prefs.remove("email");
    }


    public static void saveRole(String role) {
        prefs.put("role", role);
    }
    public static String getRole() {
        return prefs.get("role", null);
    }
    public static void clearRole() {
        prefs.remove("role");
    }
}
