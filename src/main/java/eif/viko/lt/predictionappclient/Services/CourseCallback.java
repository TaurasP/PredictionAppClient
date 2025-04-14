package eif.viko.lt.predictionappclient.Services;

public interface CourseCallback {
    void onCourseSuccess(String message);
    void onCourseFailure(String errorMessage);
}
