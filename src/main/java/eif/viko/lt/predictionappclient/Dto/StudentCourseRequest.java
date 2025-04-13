package eif.viko.lt.predictionappclient.Dto;

import eif.viko.lt.predictionappclient.Entities.ChatUser;
import eif.viko.lt.predictionappclient.Entities.Course;
import javafx.beans.property.StringProperty;

import java.time.LocalDateTime;

public class StudentCourseRequest {

    private Long id;
    private double attendance;
    private double assignments;
    private double midterm;
    private double finalExam;
    private String grade;
    private String predictedGrade;
    private LocalDateTime date;
    private Course course;
    private ChatUser student;
    private ChatUser teacher;
    private String email;

    public StudentCourseRequest() {
    }

    public StudentCourseRequest(Long id, String email, double attendance, double assignments, double midterm, double finalExam, String grade) {
        this.id = id;
        this.attendance = attendance;
        this.assignments = assignments;
        this.midterm = midterm;
        this.finalExam = finalExam;
        this.grade = grade;
        this.email = email;
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public ChatUser getStudent() {
        return student;
    }

    public void setStudent(ChatUser student) {
        this.student = student;
    }

    public ChatUser getTeacher() {
        return teacher;
    }

    public void setTeacher(ChatUser teacher) {
        this.teacher = teacher;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
