package eif.viko.lt.predictionappclient;

import eif.viko.lt.predictionappclient.Dto.StudentRequest;
import eif.viko.lt.predictionappclient.Entities.Animal;
import eif.viko.lt.predictionappclient.Entities.Role;
import eif.viko.lt.predictionappclient.Services.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
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
    private ComboBox<String> roleComboBox;
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
    private Button predictedGradeBtn;
    @FXML
    private Text predictedGradeText;


    @FXML
    private Tab profileTab;
    @FXML
    private Text profileEmailText;
    @FXML
    private Text profileRoleText;


    @FXML
    private Tab gradesTab;
    @FXML
    private TableView<Animal> animals;
    @FXML
    private TableColumn<Animal, Integer> idCol;
    @FXML
    private TableColumn<Animal, String> typeCol;
    @FXML
    private TableColumn<Animal, String> nameCol;
    @FXML
    private TextField inputId;
    @FXML
    private TextField inputType;
    @FXML
    private TextField inputName;
    @FXML
    private TextField searchInput;
    private ObservableList<Animal> allAnimals = FXCollections.observableArrayList();


    private final AuthServiceImpl authService = new AuthServiceImpl();

    private final ChatBotServiceImpl chatBotService = new ChatBotServiceImpl();

    private final GradePredictionServiceImpl gradePredictionService = new GradePredictionServiceImpl();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        clearSecureStorage();
        boolean isAuthenticated = SecureStorage.getToken() == null;
        authPanelBox.setVisible(isAuthenticated);
        logoutBtn.setVisible(false);
        chatTab.setDisable(isAuthenticated);
        predictionTab.setDisable(isAuthenticated);
        profileTab.setDisable(isAuthenticated);
        regPanelBox.setVisible(isAuthenticated);
        roleComboBox.getItems().addAll(Role.ADMIN.getDisplayName(), Role.TEACHER.getDisplayName(), Role.STUDENT.getDisplayName());
        roleComboBox.setVisible(false);
        mainTabLabel.setText(SecureStorage.getToken());
        chatBotAnswerTextArea.setText("Sveiki! Užduokite klausimą iš Java programavimo kalbos.\n");

        //Enter simbolio paspaudimas
        chatBotMessageInput.setOnKeyPressed(this::handleKeyPress);

        // Test
        idCol.setCellValueFactory(new PropertyValueFactory<Animal, Integer>("id"));
        typeCol.setCellValueFactory(new PropertyValueFactory<Animal, String>("type"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Animal, String>("name"));
        initializeAnimals();
        // Adding listener for dynamic search functionality
        searchInput.textProperty().addListener((observable, oldValue, newValue) -> {
            filterTable(newValue);
        });
    }

    @FXML
    void submit(ActionEvent event) {
        int currentAnimalId = Integer.parseInt(inputId.getText());

        for (Animal animal : allAnimals) {
            if (animal.getId() == currentAnimalId) {
                animal.setType(inputType.getText());
                animal.setName(inputName.getText());
                break;
            }
        }

        animals.setItems(allAnimals); // Refresh the TableView with updated data
        animals.refresh();
    }

    @FXML
    void rowClicked(MouseEvent event) {
        Animal clickedAnimal = animals.getSelectionModel().getSelectedItem();
        inputId.setText(String.valueOf(clickedAnimal.getId()));
        inputType.setText(String.valueOf(clickedAnimal.getType()));
        inputName.setText(String.valueOf(clickedAnimal.getName()));
    }

    private void initializeAnimals() {
        allAnimals.clear(); // Clear the list to avoid duplicates

        allAnimals.add(new Animal(1, "Dog", "Buddy"));
        allAnimals.add(new Animal(2, "Cat", "Bella"));
        allAnimals.add(new Animal(3, "Bear", "Bob"));
        allAnimals.add(new Animal(4, "Squid", "Laila"));
        allAnimals.add(new Animal(5, "Horse", "Max"));
        allAnimals.add(new Animal(6, "Rabbit", "Lily"));
        allAnimals.add(new Animal(7, "Tiger", "Rocky"));
        allAnimals.add(new Animal(8, "Dolphin", "Blue"));
        allAnimals.add(new Animal(9, "Penguin", "Snowy"));

        animals.setItems(allAnimals); // Set the initial data for TableView
    }

    private void filterTable(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            animals.setItems(allAnimals); // Show all records when the search input is empty
            return;
        }

        ObservableList<Animal> filteredAnimals = FXCollections.observableArrayList();

        for (Animal animal : allAnimals) {
            if (animal.getType().toLowerCase().contains(keyword.toLowerCase()) ||
                    animal.getName().toLowerCase().contains(keyword.toLowerCase())) {
                filteredAnimals.add(animal);
            }
        }

        animals.setItems(filteredAnimals);
        animals.refresh();
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
        String password = passwordRegField.getText();
        String role = roleComboBox.getValue() != null ? getRoleNameFromDisplayName(roleComboBox.getValue()) : Role.STUDENT.name();

        if (email != null && password != null) {
            authService.register(email, password, role, new RegisterCallback() {
                @Override
                public void onRegisterSuccess(String message) {

                }

                @Override
                public void onRegisterFailure(String errorMessage) {

                }
            });
            regLabel.setText("User " + email + " created successfully!");
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
                    regTab.setDisable(!isAdmin);
                    roleComboBox.setVisible(isAdmin);
                    chatTab.setDisable(false);
                    predictionTab.setDisable(false);
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
    }

    public String getRoleDisplayName(String roleName) {
        for (Role role : Role.values()) {
            if (role.name().equalsIgnoreCase(roleName)) {
                return role.getDisplayName();
            }
        }
        throw new IllegalArgumentException("Invalid role: " + roleName);
    }

    public String getRoleNameFromDisplayName(String displayName) {
        for (Role role : Role.values()) {
            if (role.getDisplayName().equalsIgnoreCase(displayName)) {
                return role.name();
            }
        }
        throw new IllegalArgumentException("Invalid display name: " + displayName);
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

    @FXML
    void predict(ActionEvent event) {
        StudentRequest request = new StudentRequest(85, 80, 75, 85);
        gradePredictionService.predict(request, new GradePredictionCallback() {
            @Override
            public void onPredictionSuccess(String predictedGrade) {
                // Update the UI and print the result
                predictedGradeText.setText(predictedGrade);
                System.out.println("Predicted grade (HelloController): " + predictedGrade);
            }

            @Override
            public void onPredictionFailure(String errorMessage) {
                // Handle the error case
                predictedGradeText.setText("Prediction failed!");
                System.err.println("Error: " + errorMessage);
            }
        });
    }


}