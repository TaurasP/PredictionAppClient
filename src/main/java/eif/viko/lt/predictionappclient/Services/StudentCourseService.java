package eif.viko.lt.predictionappclient.Services;

import eif.viko.lt.predictionappclient.Entities.StudentCourseResponse;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface StudentCourseService {

    @GET("api/student-courses")
    Call<List<StudentCourseResponse>> getStudentCourses();
}
