package eif.viko.lt.predictionappclient.Services;

import eif.viko.lt.predictionappclient.Dto.StudentRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class GradePredictionServiceImpl {

    private final GradePredictionService gradePredictionService;

    public GradePredictionServiceImpl() {
        Retrofit client = RetrofitClient.getClient();
        gradePredictionService = client.create(GradePredictionService.class);
    }

    public void predict(StudentRequest request, GradePredictionCallback callback) {
        Call<String> call = gradePredictionService.predict(request);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onPredictionSuccess(response.body());
                } else {
                    String errorMessage = "Failed to predict grade. Response code: " + response.code();
                    callback.onPredictionFailure(errorMessage);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable throwable) {
                String errorMessage = "Prediction failed: " + throwable.getMessage();
                callback.onPredictionFailure(errorMessage);
                throwable.printStackTrace();
            }
        });
    }
}

