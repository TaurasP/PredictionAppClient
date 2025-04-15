package eif.viko.lt.predictionappclient.Services;

import eif.viko.lt.predictionappclient.Entities.ChatUserResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

public interface UserService {

    @GET("api/users/role/{role}")
    Call<List<ChatUserResponse>> getUsersByRole(@Path("role") String userRole);
}
