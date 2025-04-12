package eif.viko.lt.predictionappclient.Dto;

public class StudentRequest {
    private final double attendance;
    private final double assignments;
    private final double midterm;
    private final double finalExam;

    public StudentRequest(double attendance, double assignments, double midterm, double finalExam) {
        this.attendance = attendance;
        this.assignments = assignments;
        this.midterm = midterm;
        this.finalExam = finalExam;
    }

    public double getAttendance() {
        return attendance;
    }

    public double getAssignments() {
        return assignments;
    }

    public double getMidterm() {
        return midterm;
    }

    public double getFinalExam() {
        return finalExam;
    }
}
