package eif.viko.lt.predictionappclient.Services;

import eif.viko.lt.predictionappclient.Entities.StudentCourseResponse;

public interface StudentCourseUpdateCallback {
    void onStudentCourseUpdateSuccess(StudentCourseResponse response);
    void onStudentCourseUpdateFailure(String errorMessage);
}
