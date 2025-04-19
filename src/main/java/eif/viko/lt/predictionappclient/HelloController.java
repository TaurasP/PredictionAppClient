package eif.viko.lt.predictionappclient;

import eif.viko.lt.predictionappclient.Entities.ChatUserResponse;
import eif.viko.lt.predictionappclient.Entities.CourseResponse;
import eif.viko.lt.predictionappclient.Entities.PredictedGradeHistoryResponse;
import eif.viko.lt.predictionappclient.Entities.StudentCourseRequest;
import eif.viko.lt.predictionappclient.Entities.StudentCourseResponse;
import eif.viko.lt.predictionappclient.Dto.StudentRequest;
import eif.viko.lt.predictionappclient.Entities.Role;
import eif.viko.lt.predictionappclient.Services.*;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
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

    // Login
    @FXML
    private Tab loginTab;
    @FXML
    private VBox authPanelBox;
    @FXML
    private Text mainTabLabel;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button loginBtn;
    @FXML
    private Button logoutBtn;


    // Register
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
    private PasswordField passwordRegField;
    @FXML
    private Button regBtn;


    // Courses
    @FXML
    private Tab coursesTab;
    @FXML
    private TextField coursesTabCourseNameInput;
    @FXML
    private ComboBox<String> coursesTabTeacherComboBox;
    @FXML
    private Button coursesTabSaveBtn;
    @FXML
    private TextField coursesTabSearchInput;
    @FXML
    private TableView<CourseResponse> coursesTabCoursesTable;
    @FXML
    private TableColumn<CourseResponse, Integer> coursesTabTableRowIdCol;
    @FXML
    private TableColumn<CourseResponse, String> coursesTabTableCourseCol;
    @FXML
    private TableColumn<CourseResponse, String> coursesTabTableTeacherCol;
    private ObservableList<CourseResponse> allCourses = FXCollections.observableArrayList();


    // Students assignment
    @FXML
    private Tab studentsAssignmentTab;
    @FXML
    private ComboBox<String> studentsAssignmentTabCoursesComboBox;
    @FXML
    private TextField studentsAssignmentTabSearchInput;
    @FXML
    private TextField studentsAssignmentTabTeacherInput;
    @FXML
    private Button studentsAssignmentTabSaveBtn;
    @FXML
    private TableView<ChatUserResponse> studentsAssignmentTabTable;
    @FXML
    private TableColumn<ChatUserResponse, Integer> studentsAssignmentTabTableRowIdCol;
    @FXML
    private TableColumn<ChatUserResponse, String> studentsAssignmentTabTableStudentCol;
    private ObservableList<ChatUserResponse> allStudentsAssignments = FXCollections.observableArrayList();


    // Students
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



    // Predicted grades
    @FXML
    private Tab perdictedGradesTab;
    @FXML
    private Label predictedGradesTableSearchLabel;
    @FXML
    private TextField predictedGradesTableSearchInput;
    @FXML
    private TableView<PredictedGradeHistoryResponse> predictedGradesTable;
    @FXML
    private TableColumn<PredictedGradeHistoryResponse, String> predictedGradesDateCol;
    @FXML
    private TableColumn<PredictedGradeHistoryResponse, String> predictedGradesStudentCol;
    @FXML
    private TableColumn<PredictedGradeHistoryResponse, String> predictedGradesCourseCol;
    @FXML
    private TableColumn<PredictedGradeHistoryResponse, String> predictedGradesPredictedGradeCol;
    @FXML
    private TableColumn<PredictedGradeHistoryResponse, String> predictedGradesTeacherCol;
    @FXML
    private TableColumn<PredictedGradeHistoryResponse, Double> predictedGradesAttendanceCol;
    @FXML
    private TableColumn<PredictedGradeHistoryResponse, Double> predictedGradesAssignmentsCol;
    @FXML
    private TableColumn<PredictedGradeHistoryResponse, Double> predictedGradesMidTermCol;
    @FXML
    private TableColumn<PredictedGradeHistoryResponse, Double> predictedGradesFinalExamCol;
    private ObservableList<PredictedGradeHistoryResponse> allPredictedGrades = FXCollections.observableArrayList();


    // Chat
    @FXML
    private Tab chatTab;
    @FXML
    private TextArea chatBotAnswerTextArea;
    @FXML
    private TextField chatBotMessageInput;


    // Profile
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
    private final PredictedGradeHistoryServiceImpl predictedGradeHistoryService = new PredictedGradeHistoryServiceImpl();
    private boolean isAdmin = false;
    private boolean isTeacher = false;
    private boolean isStudent = false;
    private final BooleanProperty isPredictedGradeSearchVisible = new SimpleBooleanProperty(false);


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
        studentsAssignmentTab.setDisable(isAuthenticated);
        roleComboBox.getItems().addAll(Role.ADMIN.getDisplayName(), Role.TEACHER.getDisplayName(), Role.STUDENT.getDisplayName());
        roleComboBox.setVisible(false);
        mainTabLabel.setText(SecureStorage.getToken());
        chatBotAnswerTextArea.setText("Sveiki! Užduokite klausimą iš Java programavimo kalbos.\n");

        chatBotMessageInput.setOnKeyPressed(this::handleChatKeyPress);
        roleComboBox.setOnKeyPressed(this::handleRegisterKeyPress);
        password.setOnKeyPressed(this::handleLoginKeyPress);

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

        predictedGradesDateCol.setCellValueFactory(new PropertyValueFactory<PredictedGradeHistoryResponse, String>("date"));
        predictedGradesStudentCol.setCellValueFactory(new PropertyValueFactory<PredictedGradeHistoryResponse, String>("studentName"));
        predictedGradesCourseCol.setCellValueFactory(new PropertyValueFactory<PredictedGradeHistoryResponse, String>("courseName"));
        predictedGradesPredictedGradeCol.setCellValueFactory(new PropertyValueFactory<PredictedGradeHistoryResponse, String>("predictedGrade"));
        predictedGradesTeacherCol.setCellValueFactory(new PropertyValueFactory<PredictedGradeHistoryResponse, String>("teacherName"));
        predictedGradesAttendanceCol.setCellValueFactory(new PropertyValueFactory<PredictedGradeHistoryResponse, Double>("attendance"));
        predictedGradesAssignmentsCol.setCellValueFactory(new PropertyValueFactory<PredictedGradeHistoryResponse, Double>("assignments"));
        predictedGradesMidTermCol.setCellValueFactory(new PropertyValueFactory<PredictedGradeHistoryResponse, Double>("midterm"));
        predictedGradesFinalExamCol.setCellValueFactory(new PropertyValueFactory<PredictedGradeHistoryResponse, Double>("finalExam"));

        studentsAssignmentTabTableRowIdCol.setCellValueFactory(new PropertyValueFactory<ChatUserResponse, Integer>("rowId"));
        studentsAssignmentTabTableStudentCol.setCellValueFactory(new PropertyValueFactory<ChatUserResponse, String>("fullName"));
    }



    // Login
    @FXML
    void login(ActionEvent event) {
        String user = username.getText();
        String pass = password.getText();

        if (user != null && pass != null) {
            authService.login(user, pass, new LoginCallback() {
                @Override
                public void onLoginSuccess(String token) {
                    Platform.runLater(() -> {
                        isAdmin = SecureStorage.getRole() != null && SecureStorage.getRole().equals(Role.ADMIN.name());
                        isTeacher = SecureStorage.getRole() != null && SecureStorage.getRole().equals(Role.TEACHER.name());
                        isStudent = SecureStorage.getRole() != null && SecureStorage.getRole().equals(Role.STUDENT.name());

                        authPanelBox.setVisible(false);
                        username.setText("");
                        password.setText("");
                        logoutBtn.setVisible(true);
                        regTab.setDisable(!isAdmin);
                        loginTab.setDisable(true);
                        roleComboBox.setVisible(isAdmin);
                        chatTab.setDisable(false);
                        studentsTab.setDisable(isStudent);
                        studentsAssignmentTab.setDisable(isStudent);
                        profileTab.setDisable(false);
                        coursesTab.setDisable(!isAdmin);
                        perdictedGradesTab.setDisable(false);
                        profileNameText.setText(extractName(getFullNameFromEmail(SecureStorage.getEmail())));
                        profileSurnameText.setText(extractSurname(getFullNameFromEmail(SecureStorage.getEmail())));
                        profileEmailText.setText(SecureStorage.getEmail());
                        profileRoleText.setText(getRoleDisplayName(SecureStorage.getRole()));
                        getStudentCourses(null);
                        getTeachers(null);
                        getCourses(null);
                        getStudents(null);
                        getPredictedGradesHistory(null);
                        studentSearchInput.textProperty().addListener((observable, oldValue, newValue) -> {
                            filterStudentsTable(newValue);
                        });
                        coursesTabSearchInput.textProperty().addListener((observable, oldValue, newValue) -> {
                            filterCoursesTable(newValue);
                        });
                        studentsAssignmentTabSearchInput.textProperty().addListener((observable, oldValue, newValue) -> {
                            filterStudentsAssignmentTable(newValue);
                        });
                        predictedGradesTableSearchInput.textProperty().addListener((observable, oldValue, newValue) -> {
                            filterPredictedGradesTable(newValue);
                        });
                        predictedGradesTableSearchLabel.setVisible(!isStudent);
                        predictedGradesTableSearchInput.setVisible(!isStudent);

                        predictedGradesStudentCol.visibleProperty().bind(isPredictedGradeSearchVisible);
                        isPredictedGradeSearchVisible.set(!isStudent);

                        redirectToTab(profileTab);
//                    studentsAssignmentTabCoursesComboBox.setItems(FXCollections.observableArrayList(allCourses.stream().map(CourseResponse::getCourseName).toList()));
                    });
                    }

                @Override
                public void onLoginFailure(String errorMessage) {
                    Platform.runLater(() -> {
                        chatTab.setDisable(true);
                        profileTab.setDisable(true);
                    });
                }
            });
        }
    }

    private void handleLoginKeyPress(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            login(new ActionEvent());
        }
    }



    // Register
    @FXML
    void register(ActionEvent event) {
        String email = emailRegField.getText();
        String password = passwordRegField.getText();
        String role = roleComboBox.getValue() != null ? getRoleNameFromDisplayName(roleComboBox.getValue()) : Role.STUDENT.name();

        if (email != null && password != null) {
            authService.register(email, password, role, new RegisterCallback() {
                @Override
                public void onRegisterSuccess(String message) {
                    Platform.runLater(() -> {
                        emailRegField.setText("");
                        passwordRegField.setText("");
                        roleComboBox.setValue(null);
                        regLabel.setText("");
                    });
                }

                @Override
                public void onRegisterFailure(String errorMessage) {

                }
            });
            regLabel.setText("User " + email + " created successfully!");
        }
    }

    private String getRoleNameFromDisplayName(String displayName) {
        for (Role role : Role.values()) {
            if (role.getDisplayName().equalsIgnoreCase(displayName)) {
                return role.name();
            }
        }
        throw new IllegalArgumentException("Invalid display name: " + displayName);
    }

    private void handleRegisterKeyPress(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            register(new ActionEvent());
        }
    }



    // Courses
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

    private void filterCoursesTable(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            coursesTabCoursesTable.setItems(allCourses);
            return;
        }

        ObservableList<CourseResponse> filteredCourses = FXCollections.observableArrayList();

        for (CourseResponse course : allCourses) {
            if (course.getCourseName().toLowerCase().contains(keyword.toLowerCase()) ||
                    course.getTeacher().toLowerCase().contains(keyword.toLowerCase())) {
                filteredCourses.add(course);
            }
        }

        coursesTabCoursesTable.setItems(filteredCourses);
        coursesTabCoursesTable.refresh();
    }

    @FXML
    void saveCourse(ActionEvent event) {
        String courseName = coursesTabCourseNameInput.getText();
        String teacher = coursesTabTeacherComboBox.getValue();

        if (courseName != null && !teacher.isEmpty()) {
            courseService.saveCourse(courseName, teacher, new CourseCallback() {
                @Override
                public void onCourseSuccess(String message) {

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



    // Students assignments
    @FXML
    void getStudents(ActionEvent event) {
        userService.getStudents(new ChaUserCallback() {
            @Override
            public void onChaUserSuccess(List<ChatUserResponse> list) {
                Platform.runLater(() -> {
                    studentsAssignmentTabTable.setItems(FXCollections.observableArrayList(list));
                    allStudentsAssignments.clear();
                    allStudentsAssignments.setAll(list);
                });
            }

            @Override
            public void onChaUserFailure(String errorMessage) {

            }
        });
    }

    @FXML
    void studentAssignmentRowClicked(MouseEvent event) {
        ChatUserResponse clickedStudent = studentsAssignmentTabTable.getSelectionModel().getSelectedItem();
        List<String> courses = allCourses.stream().map(CourseResponse::getCourseName).toList();
        List<String> coursesStudentIsIn = allStudents.stream()
                .filter(s -> s.getStudentName().equals(clickedStudent.getFullName()))
                .map(StudentCourseResponse::getCourseName)
                .toList();
        List<String> filteredCourses = courses.stream().filter(c -> !coursesStudentIsIn.contains(c)).toList();
        studentsAssignmentTabCoursesComboBox.setItems(FXCollections.observableArrayList(filteredCourses));
        studentsAssignmentTabCoursesComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                String teacher = allCourses.stream()
                        .filter(c -> c.getCourseName().equals(newValue))
                        .findFirst()
                        .map(CourseResponse::getTeacher)
                        .orElse("");
                studentsAssignmentTabTeacherInput.setText(teacher);
            }
        });
        String teacher = allCourses.stream()
                .filter(c -> c.getCourseName().equals(studentsAssignmentTabCoursesComboBox.getValue()))
                .findFirst()
                .map(CourseResponse::getTeacher)
                .orElse("");
        studentsAssignmentTabTeacherInput.setText(teacher);
    }

    private void filterStudentsAssignmentTable(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            studentsAssignmentTabTable.setItems(allStudentsAssignments);
            return;
        }

        ObservableList<ChatUserResponse> filteredStudents = FXCollections.observableArrayList();

        for (ChatUserResponse student : allStudentsAssignments) {
            if (student != null && student.getFullName() != null &&
                    student.getFullName().toLowerCase().contains(keyword.toLowerCase())) {
                filteredStudents.add(student);
            }
        }

        studentsAssignmentTabTable.setItems(filteredStudents);
        studentsAssignmentTabTable.refresh();
    }

    @FXML
    void saveStudentCourse(ActionEvent event) {
        String selectedStudent = studentsAssignmentTabTable.getSelectionModel().getSelectedItem().getFullName();
        String selectedCourse = studentsAssignmentTabCoursesComboBox.getValue();
        String selectedTeacher = studentsAssignmentTabTeacherInput.getText();

        if (selectedStudent != null && selectedCourse != null && selectedTeacher != null) {
            studentCourseService.saveStudentCourse(
                    new StudentCourseRequest(
                            null,
                            0,
                            0,
                            0,
                            0,
                            null,
                            null,
                            selectedCourse,
                            selectedStudent,
                            selectedTeacher
                    ),
                    new RegisterCallback() {
                        @Override
                        public void onRegisterSuccess(String message) {

                        }

                        @Override
                        public void onRegisterFailure(String errorMessage) {

                        }
                    }
            );
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Incomplete Input");
            alert.setContentText("Please select a student, course, and teacher before saving.");
            alert.showAndWait();
        }
    }



    // Students
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
    void studentRowClicked(MouseEvent event) {
        StudentCourseResponse clickedStudent = studentCoursesTable.getSelectionModel().getSelectedItem();
        attendanceInput.setText(String.valueOf(clickedStudent.getAttendance()));
        assignmentsInput.setText(String.valueOf(clickedStudent.getAssignments()));
        midTermInput.setText(String.valueOf(clickedStudent.getMidterm()));
        finalExamInput.setText(String.valueOf(clickedStudent.getFinalExam()));
        gradeInput.setText(String.valueOf(clickedStudent.getGrade()));
        predictedGradeInput.setText(String.valueOf(clickedStudent.getPredictedGrade()));
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
    void predictGrade(ActionEvent event) {
        StudentRequest request = new StudentRequest(
                Double.parseDouble(attendanceInput.getText()),
                Double.parseDouble(assignmentsInput.getText()),
                Double.parseDouble(midTermInput.getText()),
                Double.parseDouble(finalExamInput.getText()));
        gradePredictionService.predict(request, new GradePredictionCallback() {
            @Override
            public void onPredictionSuccess(String predictedGrade) {
                Platform.runLater(() -> {
                    predictedGradeInput.setText(predictedGrade);
                });
            }

            @Override
            public void onPredictionFailure(String errorMessage) {
                Platform.runLater(() -> {
                    predictedGradeInput.setText("Prediction failed!");
                    System.err.println("Error: " + errorMessage);
                });
            }
        });
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
        updateStudentCourse(null, selectedStudent);
    }

    @FXML
    void updateStudentCourse(ActionEvent event, StudentCourseResponse selectedStudent) {
        Long id = studentCoursesTable.getSelectionModel().getSelectedItem().getId();

        studentCourseService.updateStudentCourse(new StudentCourseRequest(
                id,
                selectedStudent.getAttendance(),
                selectedStudent.getAssignments(),
                selectedStudent.getMidterm(),
                selectedStudent.getFinalExam(),
                selectedStudent.getGrade(),
                selectedStudent.getPredictedGrade(),
                null,
                null,
                null
        ), new StudentCourseUpdateCallback() {

            @Override
            public void onStudentCourseUpdateSuccess(StudentCourseResponse response) {
                // todo update predicted grades history table
            }

            @Override
            public void onStudentCourseUpdateFailure(String errorMessage) {

            }
        });
    }



    // Predicted grades history
    @FXML
    void getPredictedGradesHistory(ActionEvent event) {
        predictedGradeHistoryService.getPredictedGradeHistory(new PredictedGradeHistoryCallback() {
            @Override
            public void onSuccess(List<PredictedGradeHistoryResponse> list) {
                Platform.runLater(() -> {
                    list.sort((a, b) -> b.getDate().compareTo(a.getDate()));
                    if (isStudent) {
                        String studentEmail = SecureStorage.getEmail();
                        List<PredictedGradeHistoryResponse> filteredList = list.stream()
                                .filter(it -> it.getStudentName().equals(getFullNameFromEmail(studentEmail)))
                                .toList();
                        predictedGradesTable.setItems(FXCollections.observableArrayList(filteredList));
                        allPredictedGrades.clear();
                        allPredictedGrades.setAll(filteredList);
                    } else {
                        predictedGradesTable.setItems(FXCollections.observableArrayList(list));
                        allPredictedGrades.clear();
                        allPredictedGrades.setAll(list);
                    }
                });
            }

            @Override
            public void onFailure(String errorMessage) {
                Platform.runLater(() -> {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Failed to Load Predicted Grades History");
                    alert.setContentText(errorMessage);
                    alert.showAndWait();
                });
            }
        });
    }

    private void filterPredictedGradesTable(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            predictedGradesTable.setItems(allPredictedGrades);
            return;
        }

        ObservableList<PredictedGradeHistoryResponse> filteredPredictedGrades = FXCollections.observableArrayList();

        for (PredictedGradeHistoryResponse predictedGrade : allPredictedGrades) {
            if (predictedGrade.getDate().contains(keyword.toLowerCase())
                    || predictedGrade.getStudentName().toLowerCase().contains(keyword.toLowerCase()) ||
                    predictedGrade.getCourseName().toLowerCase().contains(keyword.toLowerCase()) ||
                    predictedGrade.getTeacherName().toLowerCase().contains(keyword.toLowerCase())) {
                filteredPredictedGrades.add(predictedGrade);
            }
        }

        predictedGradesTable.setItems(filteredPredictedGrades);
        predictedGradesTable.refresh();
    }



    // Chat
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
                    Platform.runLater(() -> {
                        System.out.println(errorMessage);
                    });
                }
            });
        }
    }

    @FXML
    void getTeachers(ActionEvent event) {
        userService.getTeachers(new ChaUserCallback() {
            @Override
            public void onChaUserSuccess(List<ChatUserResponse> list) {
                Platform.runLater(() -> {
                    List<String> emailList = list.stream()
                            .map(user -> getFullNameFromEmail(user.getEmail()))
                            .toList();

                    coursesTabTeacherComboBox.setItems(FXCollections.observableArrayList(emailList));
                });
            }

            @Override
            public void onChaUserFailure(String errorMessage) {

            }
        });
    }

    private void handleChatKeyPress(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            askChatBot(new ActionEvent());
            chatBotMessageInput.clear();
        }
    }



    // Profile
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
        studentsAssignmentTab.setDisable(true);
        coursesTab.setDisable(true);
        perdictedGradesTab.setDisable(true);
        redirectToTab(loginTab);
    }

    private void clearSecureStorage() {
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

    private String getRoleDisplayName(String roleName) {
        for (Role role : Role.values()) {
            if (role.name().equalsIgnoreCase(roleName)) {
                return role.getDisplayName();
            }
        }
        throw new IllegalArgumentException("Invalid role: " + roleName);
    }
}
