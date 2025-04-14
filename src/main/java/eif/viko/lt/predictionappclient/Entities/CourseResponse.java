package eif.viko.lt.predictionappclient.Entities;

public class CourseResponse {

    private int rowId;
    private String courseName;
    private String teacher;

    public CourseResponse() {
    }

    public CourseResponse(int rowId, String courseName, String teacher) {
        this.rowId = rowId;
        this.courseName = courseName;
        this.teacher = teacher;
    }

    public int getRowId() {
        return rowId;
    }

    public void setRowId(int rowId) {
        this.rowId = rowId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
}
