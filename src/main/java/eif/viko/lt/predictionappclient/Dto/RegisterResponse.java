package eif.viko.lt.predictionappclient.Dto;

public class RegisterResponse {
    private Long id;
    private String email;
    private String role;
    private boolean enabled;

    public RegisterResponse() {
    }

    public RegisterResponse(Long id, String email, String role, boolean enabled) {
        this.id = id;
        this.email = email;
        this.role = role;
        this.enabled = enabled;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
