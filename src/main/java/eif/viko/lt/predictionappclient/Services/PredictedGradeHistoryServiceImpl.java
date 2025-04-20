package eif.viko.lt.predictionappclient.Services;

import eif.viko.lt.predictionappclient.Entities.PredictedGradeHistoryResponse;
import retrofit2.Retrofit;
import retrofit2.Call;
import retrofit2.Response;

import java.util.List;

public class PredictedGradeHistoryServiceImpl {

    private final PredictedGradeHistoryService predictedGradeHistoryService;

    public PredictedGradeHistoryServiceImpl() {
        Retrofit client = RetrofitClient.getClient();
        predictedGradeHistoryService = client.create(PredictedGradeHistoryService.class);
    }

    public void getPredictedGradeHistory(PredictedGradeHistoryCallback callback) {
        Call<List<PredictedGradeHistoryResponse>> call = predictedGradeHistoryService.getPredictedGradeHistory();
        call.enqueue(new retrofit2.Callback<List<PredictedGradeHistoryResponse>>() {
            @Override
            public void onResponse(Call<List<PredictedGradeHistoryResponse>> call, Response<List<PredictedGradeHistoryResponse>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure("Failed to fetch grade history. Response code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<PredictedGradeHistoryResponse>> call, Throwable throwable) {
                callback.onFailure("Error fetching grade history: " + throwable.getMessage());
                throwable.printStackTrace();
            }
        });
    }
}
