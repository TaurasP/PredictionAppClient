package eif.viko.lt.predictionappclient.Entities;

public class CourseRequest {

    private String name;
    private String teacher;

    public CourseRequest() {
    }

    public CourseRequest(String name, String teacher) {
        this.name = name;
        this.teacher = teacher;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
}
