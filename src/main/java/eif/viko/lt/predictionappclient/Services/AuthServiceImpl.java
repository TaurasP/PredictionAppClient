package eif.viko.lt.predictionappclient.Services;

import eif.viko.lt.predictionappclient.Dto.LoginRequest;
import eif.viko.lt.predictionappclient.Dto.LoginResponse;
import eif.viko.lt.predictionappclient.Dto.RegisterRequest;
import eif.viko.lt.predictionappclient.Dto.RegisterResponse;
import eif.viko.lt.predictionappclient.SecureStorage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AuthServiceImpl {
    private final AuthService authService;

    public AuthServiceImpl() {
        Retrofit client = ApiClient.getClient();
        authService = client.create(AuthService.class);
    }

    public void login(String username, String password, LoginCallback callback) {

        LoginRequest request = new LoginRequest(username, password);
        Call<LoginResponse> call = authService.login(request);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.isSuccessful() && response.body() != null) {
                    String token = response.body().getToken();
                    System.out.println("JWT TOKENAS: " + token);
                    SecureStorage.saveToken(token);
                    callback.onLoginSuccess(token);
                }else{
                    System.out.println("LOGIN FAILED: " + response.message());
                }
            }
            @Override
            public void onFailure(Call<LoginResponse> call, Throwable throwable) {
                System.out.println("LOGIN FAILED: " + throwable.getMessage());
                callback.onLoginFailure(throwable.getMessage());
            }
        });
    }

    public void register(String email, String username, String password) {
        RegisterRequest request = new RegisterRequest(email, password, username);
        Call<RegisterResponse> call = authService.register(request);

        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.isSuccessful()) {
                    System.out.println("REGISTER SUCCESS: " + response.body());
                } else {
                    System.out.println("REGISTER FAILED: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable throwable) {
                System.out.println("REGISTER FAILED: " + throwable.getMessage());
            }
        });
    }

}
