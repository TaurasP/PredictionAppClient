package eif.viko.lt.predictionappclient.Dto;

public class LoginResponse {
    private long expiration;
    private String token;
    private String email;
    private String role;

    public long getExpiration() {
        return expiration;
    }

    public String getToken() {
        return token;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }
}
