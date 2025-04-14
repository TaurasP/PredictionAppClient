package eif.viko.lt.predictionappclient;

import eif.viko.lt.predictionappclient.Entities.ChatUser;
import eif.viko.lt.predictionappclient.Entities.CourseResponse;
import eif.viko.lt.predictionappclient.Entities.StudentCourseResponse;
import eif.viko.lt.predictionappclient.Dto.StudentRequest;
import eif.viko.lt.predictionappclient.Entities.Role;
import eif.viko.lt.predictionappclient.Services.*;
import javafx.application.Platform;
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
import java.util.List;
import java.util.ResourceBundle;

import static eif.viko.lt.predictionappclient.Utils.EmailToNameConverter.*;

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
    private Tab studentsTab;
    @FXML
    private TableView<StudentCourseResponse> studentCoursesTable;
    @FXML
    private TableColumn<StudentCourseResponse, Integer> studentRowIdCol;
    @FXML
    private TableColumn<StudentCourseResponse, String> studentNameCol;
    @FXML
    private TableColumn<StudentCourseResponse, String> teacherNameCol;
    @FXML
    private TableColumn<StudentCourseResponse, String> courseNameCol;
    @FXML
    private TableColumn<StudentCourseResponse, Double> attendanceCol;
    @FXML
    private TableColumn<StudentCourseResponse, Double> assignmentsCol;
    @FXML
    private TableColumn<StudentCourseResponse, Double> midTermCol;
    @FXML
    private TableColumn<StudentCourseResponse, Double> finalExamCol;
    @FXML
    private TableColumn<StudentCourseResponse, String> gradeCol;
    @FXML
    private TableColumn<StudentCourseResponse, String> predictedGradeCol;
    @FXML
    private TableColumn<StudentCourseResponse, String> dateCol;
    private ObservableList<StudentCourseResponse> allStudents = FXCollections.observableArrayList();
    @FXML
    private TextField studentSearchInput;
    @FXML
    private TextField attendanceInput;
    @FXML
    private TextField assignmentsInput;
    @FXML
    private TextField midTermInput;
    @FXML
    private TextField finalExamInput;
    @FXML
    private TextField gradeInput;
    @FXML
    private TextField predictedGradeInput;
    @FXML
    private Button updateStudentBtn;
    @FXML
    private Button predictGradeBtn;


    @FXML
    private Tab coursesTab;
    @FXML
    private TextField coursesTabCourseNameInput;
    @FXML
    private ComboBox<String> coursesTabTeacherComboBox;
    @FXML
    private Button coursesTabSaveBtn;
    @FXML
    private TableView<CourseResponse> coursesTabCoursesTable;
    @FXML
    private TableColumn<CourseResponse, Integer> coursesTabTableRowIdCol;
    @FXML
    private TableColumn<CourseResponse, String> coursesTabTableCourseCol;
    @FXML
    private TableColumn<CourseResponse, String> coursesTabTableTeacherCol;
    private ObservableList<CourseResponse> allCourses = FXCollections.observableArrayList();


    @FXML
    private Tab perdictedGradesTab;


    @FXML
    private Tab profileTab;
    @FXML
    private Text profileNameText;
    @FXML
    private Text profileSurnameText;
    @FXML
    private Text profileEmailText;
    @FXML
    private Text profileRoleText;


    private final AuthServiceImpl authService = new AuthServiceImpl();
    private final ChatBotServiceImpl chatBotService = new ChatBotServiceImpl();
    private final GradePredictionServiceImpl gradePredictionService = new GradePredictionServiceImpl();
    private final StudentCourseServiceImpl studentCourseService = new StudentCourseServiceImpl();
    private final UserServiceImpl userService = new UserServiceImpl();
    private final CourseServiceImpl courseService = new CourseServiceImpl();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        clearSecureStorage();
        boolean isAuthenticated = SecureStorage.getToken() == null;
        authPanelBox.setVisible(isAuthenticated);
        logoutBtn.setVisible(false);
        chatTab.setDisable(isAuthenticated);
        profileTab.setDisable(isAuthenticated);
        studentsTab.setDisable(isAuthenticated);
        regPanelBox.setVisible(isAuthenticated);
        coursesTab.setDisable(isAuthenticated);
        perdictedGradesTab.setDisable(isAuthenticated);
        roleComboBox.getItems().addAll(Role.ADMIN.getDisplayName(), Role.TEACHER.getDisplayName(), Role.STUDENT.getDisplayName());
        roleComboBox.setVisible(false);
        mainTabLabel.setText(SecureStorage.getToken());
        chatBotAnswerTextArea.setText("Sveiki! Užduokite klausimą iš Java programavimo kalbos.\n");

        //Enter simbolio paspaudimas
        chatBotMessageInput.setOnKeyPressed(this::handleKeyPress);

        studentRowIdCol.setCellValueFactory(new PropertyValueFactory<StudentCourseResponse, Integer>("rowId"));
        studentNameCol.setCellValueFactory(new PropertyValueFactory<StudentCourseResponse, String>("studentName"));
        teacherNameCol.setCellValueFactory(new PropertyValueFactory<StudentCourseResponse, String>("teacherName"));
        courseNameCol.setCellValueFactory(new PropertyValueFactory<StudentCourseResponse, String>("courseName"));
        attendanceCol.setCellValueFactory(new PropertyValueFactory<StudentCourseResponse, Double>("attendance"));
        assignmentsCol.setCellValueFactory(new PropertyValueFactory<StudentCourseResponse, Double>("assignments"));
        midTermCol.setCellValueFactory(new PropertyValueFactory<StudentCourseResponse, Double>("midterm"));
        finalExamCol.setCellValueFactory(new PropertyValueFactory<StudentCourseResponse, Double>("finalExam"));
        gradeCol.setCellValueFactory(new PropertyValueFactory<StudentCourseResponse, String>("grade"));
        predictedGradeCol.setCellValueFactory(new PropertyValueFactory<StudentCourseResponse, String>("predictedGrade"));
        dateCol.setCellValueFactory(new PropertyValueFactory<StudentCourseResponse, String>("date"));

        coursesTabTableRowIdCol.setCellValueFactory(new PropertyValueFactory<CourseResponse, Integer>("rowId"));
        coursesTabTableCourseCol.setCellValueFactory(new PropertyValueFactory<CourseResponse, String>("courseName"));
        coursesTabTableTeacherCol.setCellValueFactory(new PropertyValueFactory<CourseResponse, String>("teacher"));
    }

    private void filterStudentsTable(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            studentCoursesTable.setItems(allStudents);
            return;
        }

        ObservableList<StudentCourseResponse> filteredStudents = FXCollections.observableArrayList();

        for (StudentCourseResponse student : allStudents) {
            if (student.getStudentName().toLowerCase().contains(keyword.toLowerCase()) ||
                    student.getTeacherName().toLowerCase().contains(keyword.toLowerCase()) ||
                    student.getCourseName().toLowerCase().contains(keyword.toLowerCase())) {
                filteredStudents.add(student);
            }
        }

        studentCoursesTable.setItems(filteredStudents);
        studentCoursesTable.refresh();
    }

    @FXML
    void updateStudent(ActionEvent event) {
        StudentCourseResponse selectedStudent = studentCoursesTable.getSelectionModel().getSelectedItem();
        if (selectedStudent != null) {
            selectedStudent.setAttendance(Double.parseDouble(attendanceInput.getText()));
            selectedStudent.setAssignments(Double.parseDouble(assignmentsInput.getText()));
            selectedStudent.setMidterm(Double.parseDouble(midTermInput.getText()));
            selectedStudent.setFinalExam(Double.parseDouble(finalExamInput.getText()));
            selectedStudent.setGrade(gradeInput.getText());
            selectedStudent.setPredictedGrade(predictedGradeInput.getText());
        }

        studentCoursesTable.setItems(allStudents); // Refresh the TableView with updated data
        studentCoursesTable.refresh();
        // todo REST updateStudent call
    }

    @FXML
    void studentRowClicked(MouseEvent event) {
        StudentCourseResponse clickedStudent = studentCoursesTable.getSelectionModel().getSelectedItem();
        attendanceInput.setText(String.valueOf(clickedStudent.getAttendance()));
        assignmentsInput.setText(String.valueOf(clickedStudent.getAssignments()));
        midTermInput.setText(String.valueOf(clickedStudent.getMidterm()));
        finalExamInput.setText(String.valueOf(clickedStudent.getFinalExam()));
        gradeInput.setText(String.valueOf(clickedStudent.getGrade()));
        predictedGradeInput.setText(String.valueOf(clickedStudent.getPredictedGrade()));
    }

    @FXML
    void predictGrade(ActionEvent event) {
        StudentRequest request = new StudentRequest(
                Double.parseDouble(attendanceInput.getText()),
                Double.parseDouble(assignmentsInput.getText()),
                Double.parseDouble(midTermInput.getText()),
                Double.parseDouble(finalExamInput.getText()));
        gradePredictionService.predict(request, new GradePredictionCallback() {
            @Override
            public void onPredictionSuccess(String predictedGrade) {
                predictedGradeInput.setText(predictedGrade);
                // post request to save data to StudentCourse and PredictedGradeHistory
            }

            @Override
            public void onPredictionFailure(String errorMessage) {
                predictedGradeInput.setText("Prediction failed!");
                System.err.println("Error: " + errorMessage);
            }
        });
    }

    @FXML
    void getUsersByRole(ActionEvent event) {
        userService.getUsersByRole(new ChaUserCallback() {
            @Override
            public void onChaUserSuccess(List<ChatUser> list) {
                List<String> emailList = list.stream()
                        .map(user -> getFullNameFromEmail(user.getEmail()))
                        .toList();

                coursesTabTeacherComboBox.setItems(FXCollections.observableArrayList(emailList));
            }

            @Override
            public void onChaUserFailure(String errorMessage) {

            }
        });
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
                    boolean isTeacher = SecureStorage.getRole() != null && SecureStorage.getRole().equals(Role.TEACHER.name());
                    boolean isStudent = SecureStorage.getRole() != null && SecureStorage.getRole().equals(Role.STUDENT.name());

                    authPanelBox.setVisible(false);
                    mainTabLabel.setText("Sveiki prisijungę");
                    logoutBtn.setVisible(true);
                    regTab.setDisable(!isAdmin);
                    loginTab.setDisable(true);
                    roleComboBox.setVisible(isAdmin);
                    chatTab.setDisable(false);
                    studentsTab.setDisable(isStudent);
                    profileTab.setDisable(false);
                    coursesTab.setDisable(!isAdmin);
                    perdictedGradesTab.setDisable(false);
                    profileNameText.setText(extractName(getFullNameFromEmail(SecureStorage.getEmail())));
                    profileSurnameText.setText(extractSurname(getFullNameFromEmail(SecureStorage.getEmail())));
                    profileEmailText.setText(SecureStorage.getEmail());
                    profileRoleText.setText(getRoleDisplayName(SecureStorage.getRole()));
                    getStudentCourses(null);
                    getUsersByRole(null);
                    getCourses(null);
                    studentSearchInput.textProperty().addListener((observable, oldValue, newValue) -> {
                        filterStudentsTable(newValue);
                    });
                    redirectToTab(profileTab);
                }

                @Override
                public void onLoginFailure(String errorMessage) {
                    chatTab.setDisable(true);
                    profileTab.setDisable(true);
                }
            });
        }
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

    @FXML
    void getStudentCourses(ActionEvent event) {
        studentCourseService.getStudentCourses(new StudentCourseCallback() {
            @Override
            public void onStudentCourseSuccess(List<StudentCourseResponse> studentCourses) {
                Platform.runLater(() -> {
                    ObservableList<StudentCourseResponse> items = studentCoursesTable.getItems();
                    items.clear();
                    items.addAll(studentCourses);
                    allStudents.clear();
                    allStudents.setAll(studentCourses);
                });
            }
            @Override
            public void onStudentCourseFailure(String errorMessage) {
                Platform.runLater(() -> {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Failed to load student courses");
                    alert.setContentText(errorMessage);
                    alert.showAndWait();
                });
            }
        });
    }

    @FXML
    void saveCourse(ActionEvent event) {
        String courseName = coursesTabCourseNameInput.getText();
        String teacher = coursesTabTeacherComboBox.getValue();

        if (courseName != null && !teacher.isEmpty()) {
            courseService.saveCourse(courseName, teacher, new CourseCallback() {
                @Override
                public void onCourseSuccess(String message) {
                    System.out.println(message);
                }

                @Override
                public void onAllCourseSuccess(List<CourseResponse> courses) {

                }

                @Override
                public void onCourseFailure(String errorMessage) {

                }
            });
        }
    }

    @FXML
    void getCourses(ActionEvent event) {
        courseService.getCourses(new CourseCallback() {
            @Override
            public void onCourseSuccess(String message) {

            }

            @Override
            public void onAllCourseSuccess(List<CourseResponse> courses) {
                Platform.runLater(() -> {
                    ObservableList<CourseResponse> items = coursesTabCoursesTable.getItems();
                    items.clear();
                    items.addAll(courses);
                    allCourses.clear();
                    allCourses.setAll(courses);
                });
            }

            @Override
            public void onCourseFailure(String errorMessage) {

            }
        });
    }

    @FXML
    void logout(ActionEvent event) {
        clearSecureStorage();
        authPanelBox.setVisible(true);
        mainTabLabel.setText("");
        logoutBtn.setVisible(false);
        regTab.setDisable(false);
        loginTab.setDisable(false);
        chatTab.setDisable(true);
        profileTab.setDisable(true);
        studentsTab.setDisable(true);
        coursesTab.setDisable(true);
        perdictedGradesTab.setDisable(true);
        redirectToTab(loginTab);
    }

    public void clearSecureStorage() {
        SecureStorage.clearToken();
        SecureStorage.clearEmail();
        SecureStorage.clearRole();
    }

    private void redirectToTab(Tab tab) {
        TabPane tabPane = tab.getTabPane();
        if (tabPane != null) {
            tabPane.getSelectionModel().select(tab);
        }
    }

}
