package eif.viko.lt.predictionappclient.Entities;

public class CourseRequest {

    private String courseName;
    private String teacherName;
    private String teacherSurname;

    public CourseRequest() {
    }

    public CourseRequest(String courseName, String teacherName, String teacherSurname) {
        this.courseName = courseName;
        this.teacherName = teacherName;
        this.teacherSurname = teacherSurname;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherSurname() {
        return teacherSurname;
    }

    public void setTeacherSurname(String teacherSurname) {
        this.teacherSurname = teacherSurname;
    }
}
