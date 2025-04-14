package eif.viko.lt.predictionappclient.Services;

import eif.viko.lt.predictionappclient.Entities.ChatUser;
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

    public void getUsersByRole(ChaUserCallback callback) {
        Call<List<ChatUser>> call = userService.getUsersByRole(Role.TEACHER.name());
        call.enqueue(new retrofit2.Callback<List<ChatUser>>() {
            @Override
            public void onResponse(Call<List<ChatUser>> call, Response<List<ChatUser>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    System.out.println(response.body());
                    callback.onChaUserSuccess(response.body());
                } else {
                    callback.onChaUserFailure("Failed to fetch users. Response code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<ChatUser>> call, Throwable throwable) {
                callback.onChaUserFailure("Error fetching users: " + throwable.getMessage());
                throwable.printStackTrace();
            }
        });
    }

}
