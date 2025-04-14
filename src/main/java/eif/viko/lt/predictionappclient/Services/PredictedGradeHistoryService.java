package eif.viko.lt.predictionappclient.Services;

import eif.viko.lt.predictionappclient.Entities.PredictedGradeHistoryResponse;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface PredictedGradeHistoryService {

    @GET("api/predicted-grades")
    Call<List<PredictedGradeHistoryResponse>> getPredictedGradeHistory();

}
