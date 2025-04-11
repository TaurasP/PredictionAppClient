package eif.viko.lt.predictionappclient.Services;

import eif.viko.lt.predictionappclient.Dto.LoginRequest;
import eif.viko.lt.predictionappclient.Dto.LoginResponse;
import eif.viko.lt.predictionappclient.Dto.RegisterRequest;
import eif.viko.lt.predictionappclient.Dto.RegisterResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthService {

    @POST("api/auth/signup")
    Call<RegisterResponse> register(@Body RegisterRequest registerRequest);

    @POST("api/auth/login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);
}
