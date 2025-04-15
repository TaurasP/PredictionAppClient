package eif.viko.lt.predictionappclient.Services;

import eif.viko.lt.predictionappclient.Entities.ChatUser;
import eif.viko.lt.predictionappclient.Entities.ChatUserResponse;
import eif.viko.lt.predictionappclient.Entities.CourseResponse;
import eif.viko.lt.predictionappclient.Entities.Role;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.util.List;

public class UserServiceImpl {

    private final UserService userService;

    public UserServiceImpl() {
        Retrofit client = RetrofitClient.getClient();
        userService = client.create(UserService.class);
    }

    public void getTeachers(ChaUserCallback callback) {
        Call<List<ChatUserResponse>> call = userService.getUsersByRole(Role.TEACHER.name());
        call.enqueue(new retrofit2.Callback<List<ChatUserResponse>>() {
            @Override
            public void onResponse(Call<List<ChatUserResponse>> call, Response<List<ChatUserResponse>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onChaUserSuccess(response.body());
                } else {
                    callback.onChaUserFailure("Failed to fetch teachers. Response code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<ChatUserResponse>> call, Throwable throwable) {
                callback.onChaUserFailure("Error fetching teachers: " + throwable.getMessage());
                throwable.printStackTrace();
            }
        });
    }

    public void getStudents(ChaUserCallback callback) {
        Call<List<ChatUserResponse>> call = userService.getUsersByRole(Role.STUDENT.name());
        call.enqueue(new retrofit2.Callback<List<ChatUserResponse>>() {
            @Override
            public void onResponse(Call<List<ChatUserResponse>> call, Response<List<ChatUserResponse>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onChaUserSuccess(response.body());
                } else {
                    callback.onChaUserFailure("Failed to fetch students. Response code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<ChatUserResponse>> call, Throwable throwable) {
                callback.onChaUserFailure("Error fetching students: " + throwable.getMessage());
                throwable.printStackTrace();
            }
        });
    }

}
