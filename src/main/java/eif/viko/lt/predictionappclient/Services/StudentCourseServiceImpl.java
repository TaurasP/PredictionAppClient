package eif.viko.lt.predictionappclient.Services;

import eif.viko.lt.predictionappclient.Entities.StudentCourseRequest;
import eif.viko.lt.predictionappclient.Entities.StudentCourseResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.util.List;

public class StudentCourseServiceImpl {

    private final StudentCourseService studentCourseService;

    public StudentCourseServiceImpl() {
        Retrofit client = ApiClientWithAuth.getClient();
        studentCourseService = client.create(StudentCourseService.class);
    }

    public void getStudentCourses(StudentCourseCallback callback) {
        Call<List<StudentCourseResponse>> call = studentCourseService.getStudentCourses();
        call.enqueue(new retrofit2.Callback<List<StudentCourseResponse>>() {
            @Override
            public void onResponse(Call<List<StudentCourseResponse>> call, Response<List<StudentCourseResponse>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onStudentCourseSuccess(response.body());
                } else {
                    callback.onStudentCourseFailure("Failed to fetch courses. Response code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<StudentCourseResponse>> call, Throwable throwable) {
                callback.onStudentCourseFailure("Error fetching courses: " + throwable.getMessage());
                throwable.printStackTrace();
            }
        });
    }

    public void saveStudentCourse(StudentCourseRequest request, RegisterCallback callback) {
        Call<String> call = studentCourseService.saveStudentCourse(request);
        call.enqueue(new retrofit2.Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onRegisterSuccess(response.body());
                } else {
                    callback.onRegisterFailure("Failed to save course. Response code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable throwable) {
                callback.onRegisterFailure("Error saving course: " + throwable.getMessage());
            }
        });
    }

    public void updateStudentCourse(StudentCourseRequest request, StudentCourseUpdateCallback callback) {
        Call<StudentCourseResponse> call = studentCourseService.updateStudentCourse(request.getId(), request);
        call.enqueue(new retrofit2.Callback<StudentCourseResponse>() {
            @Override
            public void onResponse(Call<StudentCourseResponse> call, Response<StudentCourseResponse> response) {
                callback.onStudentCourseUpdateSuccess(response.body());
            }

            @Override
            public void onFailure(Call<StudentCourseResponse> call, Throwable throwable) {
                callback.onStudentCourseUpdateFailure("Failed to update courses. Response code: " + throwable.getMessage());
            }
        });
    }

}
