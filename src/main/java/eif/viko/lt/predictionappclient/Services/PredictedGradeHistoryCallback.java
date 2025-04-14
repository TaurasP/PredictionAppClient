package eif.viko.lt.predictionappclient.Services;

import eif.viko.lt.predictionappclient.Entities.PredictedGradeHistoryResponse;

import java.util.List;

public interface PredictedGradeHistoryCallback {
    void onSuccess(List<PredictedGradeHistoryResponse> list);
    void onFailure(String errorMessage);
}
