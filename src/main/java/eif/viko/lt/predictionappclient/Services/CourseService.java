package eif.viko.lt.predictionappclient.Services;

import eif.viko.lt.predictionappclient.Entities.CourseRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface CourseService {

    @POST("api/courses/teacher")
    Call<String> saveCourse(@Body CourseRequest courseRequest);
}
