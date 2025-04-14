package eif.viko.lt.predictionappclient.Entities;

public class StudentCourseRequest {
    private Long id;
    private double attendance;
    private double assignments;
    private double midterm;
    private double finalExam;
    private String grade;
    private String predictedGrade;

    public StudentCourseRequest() {
    }

    public StudentCourseRequest(Long id, double attendance, double assignments, double midterm, double finalExam, String grade, String predictedGrade) {
        this.id = id;
        this.attendance = attendance;
        this.assignments = assignments;
        this.midterm = midterm;
        this.finalExam = finalExam;
        this.grade = grade;
        this.predictedGrade = predictedGrade;
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
}
