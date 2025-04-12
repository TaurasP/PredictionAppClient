package eif.viko.lt.predictionappclient.Services;

public interface RegisterCallback {
    void onRegisterSuccess(String message);
    void onRegisterFailure(String errorMessage);
}
