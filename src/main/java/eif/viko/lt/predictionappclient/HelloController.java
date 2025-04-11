package eif.viko.lt.predictionappclient;

import eif.viko.lt.predictionappclient.Entities.Role;
import eif.viko.lt.predictionappclient.Services.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private Tab regTab;
    @FXML
    private VBox regPanelBox;
    @FXML
    private Label regLabel;
    @FXML
    private TextField emailRegField;
    @FXML
    private TextField nameRegField;
    @FXML
    private TextField passwordRegField;
    @FXML
    private Button regBtn;


    @FXML
    private Tab loginTab;
    @FXML
    private VBox authPanelBox;
    @FXML
    private Text mainTabLabel;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private Button loginBtn;
    @FXML
    private Button logoutBtn;


    @FXML
    private Tab chatTab;
    @FXML
    private TextArea chatBotAnswerTextArea;
    @FXML
    private TextField chatBotMessageInput;


    @FXML
    private Tab predictionTab;


    @FXML
    private Tab profileTab;
    @FXML
    private Text profileEmailText;
    @FXML
    private Text profileRoleText;


    private final AuthServiceImpl authService = new AuthServiceImpl();

    private final ChatBotServiceImpl chatBotService = new ChatBotServiceImpl();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        clearSecureStorage();
        logoutBtn.setVisible(false);
        boolean isAuthenticated = SecureStorage.getToken() == null;
        authPanelBox.setVisible(isAuthenticated);
        chatTab.setDisable(isAuthenticated);
        predictionTab.setDisable(isAuthenticated);
        profileTab.setDisable(isAuthenticated);
        regPanelBox.setVisible(isAuthenticated);
        mainTabLabel.setText(SecureStorage.getToken());
        chatBotAnswerTextArea.setText("Sveiki! Užduokite klausimą iš Java programavimo kalbos.\n");

        //Enter simbolio paspaudimas
        chatBotMessageInput.setOnKeyPressed(this::handleKeyPress);

    }

    private void handleKeyPress(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            // Trigger the askChatBot method
            askChatBot(new ActionEvent());
            // Clear the input field after sending the message
            chatBotMessageInput.clear();
        }
    }

    @FXML
    void askChatBot(ActionEvent event) {

        var question = chatBotMessageInput.getText();

        if (!question.isEmpty()) {

            chatBotAnswerTextArea.appendText(
                    """
                    Jūsų klausimas
                    """);
            chatBotAnswerTextArea.appendText("\t"+question + "\n");


            chatBotService.sendMessage(question, new ChatBotCallback() {

                @Override
                public void onLoginSuccess(String message) {
                    System.out.println(message);
                    chatBotAnswerTextArea.appendText("""
                            Pokalbių roboto atsakymas
                            """);
                    chatBotAnswerTextArea.appendText("\t"+message+"\n");
                }

                @Override
                public void onLoginFailure(String errorMessage) {
                    System.out.println(errorMessage);
                }
            });
        }
    }

    @FXML
    void register(ActionEvent event) {
        String email = emailRegField.getText();
        String username = nameRegField.getText();
        String password = passwordRegField.getText();

        if (email != null && username != null && password != null) {
            authService.register(email, username, password);
            regLabel.setText("User " + nameRegField.getText() + " created successfully!");
        }
    }

    @FXML
    void login(ActionEvent event) {
        String user = username.getText();
        String pass = password.getText();

        if (user != null && pass != null) {
            authService.login(user, pass, new LoginCallback() {
                @Override
                public void onLoginSuccess(String token) {
                    boolean isAdmin = SecureStorage.getRole() != null && SecureStorage.getRole().equals(Role.ADMIN.name());

                    authPanelBox.setVisible(false);
                    mainTabLabel.setText("Sveiki prisijungę");
                    logoutBtn.setVisible(true);
                    regTab.setDisable(true);
                    chatTab.setDisable(false);
                    predictionTab.setDisable(!isAdmin);
                    profileTab.setDisable(false);
                    profileEmailText.setText(SecureStorage.getEmail());
                    profileRoleText.setText(getRoleDisplayName(SecureStorage.getRole()));
                }

                @Override
                public void onLoginFailure(String errorMessage) {
                    chatTab.setDisable(true);
                    predictionTab.setDisable(true);
                    profileTab.setDisable(true);
                }
            });
        }

//        AuthServiceImpl authService = new AuthServiceImpl();
//        if (username != null && password != null)
//            authService.login(username.getText(), password.getText());
    }

    public String getRoleDisplayName(String roleName) {
        for (Role role : Role.values()) {
            if (role.name().equalsIgnoreCase(roleName)) {
                return role.getDisplayName();
            }
        }
        throw new IllegalArgumentException("Invalid role: " + roleName);
    }

    public void clearSecureStorage() {
        SecureStorage.clearToken();
        SecureStorage.clearEmail();
        SecureStorage.clearRole();
    }

    @FXML
    void logout(ActionEvent event) {
        clearSecureStorage();
        authPanelBox.setVisible(true);
        mainTabLabel.setText("");
        logoutBtn.setVisible(false);
        regTab.setDisable(false);
        chatTab.setDisable(true);
        predictionTab.setDisable(true);
        profileTab.setDisable(true);
    }

}