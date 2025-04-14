package eif.viko.lt.predictionappclient.Services;

import eif.viko.lt.predictionappclient.Entities.ChatUser;

import java.util.List;

public interface ChaUserCallback {
    void onChaUserSuccess(List<ChatUser> list);
    void onChaUserFailure(String errorMessage);
}
