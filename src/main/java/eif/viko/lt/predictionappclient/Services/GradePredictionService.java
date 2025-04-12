package eif.viko.lt.predictionappclient.Services;

import eif.viko.lt.predictionappclient.Dto.StudentRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface GradePredictionService {

    @POST("/api/predict/grade")
    Call<String> predict(@Body StudentRequest studentRequest);
}
