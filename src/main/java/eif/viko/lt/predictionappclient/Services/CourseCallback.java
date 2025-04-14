package eif.viko.lt.predictionappclient.Services;

import eif.viko.lt.predictionappclient.Entities.CourseResponse;

import java.util.List;

public interface CourseCallback {
    void onCourseSuccess(String message);
    void onAllCourseSuccess(List<CourseResponse> courses);
    void onCourseFailure(String errorMessage);
}
