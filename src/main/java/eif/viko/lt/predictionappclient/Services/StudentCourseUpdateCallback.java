package eif.viko.lt.predictionappclient.Services;

public interface StudentCourseUpdateCallback {
    void onStudentCourseUpdateSuccess(String response);
    void onStudentCourseUpdateFailure(String errorMessage);
}
