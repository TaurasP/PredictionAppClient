package eif.viko.lt.predictionappclient.Services;

import eif.viko.lt.predictionappclient.Entities.ChatUserResponse;

import java.util.List;

public interface ChaUserCallback {
    void onChatUserSuccess(List<ChatUserResponse> list);
    void onChaUserFailure(String errorMessage);
}
