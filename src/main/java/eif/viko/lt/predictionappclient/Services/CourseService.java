package eif.viko.lt.predictionappclient.Services;

import eif.viko.lt.predictionappclient.Entities.CourseRequest;
import eif.viko.lt.predictionappclient.Entities.CourseResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

import java.util.List;

public interface CourseService {

    @POST("api/courses/teacher")
    Call<String> saveCourse(@Body CourseRequest courseRequest);

    @GET("api/courses")
    Call<List<CourseResponse>> getCourses();
}
