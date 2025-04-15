package eif.viko.lt.predictionappclient.Entities;

public class StudentCourseRequest {
    private Long id;
    private double attendance;
    private double assignments;
    private double midterm;
    private double finalExam;
    private String grade;
    private String predictedGrade;
    private String courseName;
    private String studentName;
    private String teacherName;

    public StudentCourseRequest() {
    }

    public StudentCourseRequest(Long id, double attendance, double assignments, double midterm, double finalExam, String grade, String predictedGrade, String courseName, String studentName, String teacherName) {
        this.id = id;
        this.attendance = attendance;
        this.assignments = assignments;
        this.midterm = midterm;
        this.finalExam = finalExam;
        this.grade = grade;
        this.predictedGrade = predictedGrade;
        this.courseName = courseName;
        this.studentName = studentName;
        this.teacherName = teacherName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getAttendance() {
        return attendance;
    }

    public void setAttendance(double attendance) {
        this.attendance = attendance;
    }

    public double getAssignments() {
        return assignments;
    }

    public void setAssignments(double assignments) {
        this.assignments = assignments;
    }

    public double getMidterm() {
        return midterm;
    }

    public void setMidterm(double midterm) {
        this.midterm = midterm;
    }

    public double getFinalExam() {
        return finalExam;
    }

    public void setFinalExam(double finalExam) {
        this.finalExam = finalExam;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getPredictedGrade() {
        return predictedGrade;
    }

    public void setPredictedGrade(String predictedGrade) {
        this.predictedGrade = predictedGrade;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
}
