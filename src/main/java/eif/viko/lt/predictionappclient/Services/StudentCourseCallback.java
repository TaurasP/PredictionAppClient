package eif.viko.lt.predictionappclient.Services;

import eif.viko.lt.predictionappclient.Entities.StudentCourseResponse;

import java.util.List;

public interface StudentCourseCallback {
    void onStudentCourseSuccess(List<StudentCourseResponse> list);
    void onStudentCourseFailure(String errorMessage);
}
