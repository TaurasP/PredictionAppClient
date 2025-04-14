package eif.viko.lt.predictionappclient.Services;

import eif.viko.lt.predictionappclient.Entities.StudentCourseRequest;
import eif.viko.lt.predictionappclient.Entities.StudentCourseResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

import java.util.List;

public interface StudentCourseService {

    @GET("api/student-courses")
    Call<List<StudentCourseResponse>> getStudentCourses();

    @PUT("api/student-courses/{id}")
    Call<StudentCourseResponse> updateStudentCourse(@Path("id") Long id, @Body StudentCourseRequest studentCourseRequest);
}
