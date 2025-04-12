package eif.viko.lt.predictionappclient.Services;

public interface GradePredictionCallback {
    void onPredictionSuccess(String predictedGrade);
    void onPredictionFailure(String errorMessage);
}

