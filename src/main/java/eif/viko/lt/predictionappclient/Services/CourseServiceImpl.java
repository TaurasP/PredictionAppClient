package eif.viko.lt.predictionappclient.Services;

import eif.viko.lt.predictionappclient.Entities.CourseRequest;
import eif.viko.lt.predictionappclient.Entities.CourseResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.util.List;

import static eif.viko.lt.predictionappclient.Utils.EmailToNameConverter.extractName;
import static eif.viko.lt.predictionappclient.Utils.EmailToNameConverter.extractSurname;

public class CourseServiceImpl {

    private final CourseService courseService;

    public CourseServiceImpl() {
        Retrofit client = RetrofitClient.getClient();
        courseService = client.create(CourseService.class);
    }

    public void saveCourse(String courseName, String teacher, CourseCallback callback) {
        Call<String> call = courseService.saveCourse(new CourseRequest(courseName, extractName(teacher), extractSurname(teacher)));
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onCourseSuccess(response.body());
                } else {
                    callback.onCourseFailure("Failed to save course. Response code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable throwable) {
                callback.onCourseFailure("Error saving course: " + throwable.getMessage());
                throwable.printStackTrace();
            }
        });
    }

    public void getCourses(CourseCallback callback) {
        Call<List<CourseResponse>> call = courseService.getCourses();
        call.enqueue(new Callback<List<CourseResponse>>() {
            @Override
            public void onResponse(Call<List<CourseResponse>> call, Response<List<CourseResponse>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onAllCourseSuccess(response.body());
                } else {
                    callback.onCourseFailure("Failed to fetch courses. Response code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<CourseResponse>> call, Throwable throwable) {
                callback.onCourseFailure("Error fetching courses: " + throwable.getMessage());
                throwable.printStackTrace();
            }
        });
    }
}
