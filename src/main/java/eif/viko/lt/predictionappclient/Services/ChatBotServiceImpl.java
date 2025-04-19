package eif.viko.lt.predictionappclient.Services;

import eif.viko.lt.predictionappclient.Dto.ChatBotResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ChatBotServiceImpl {

    private final ChatBotService chatBotService;

    public ChatBotServiceImpl() {
        Retrofit client = ApiClientWithAuth.getClient();
        chatBotService = client.create(ChatBotService.class);
    }

    public void sendMessage(String message, ChatBotCallback callback) {

        Call<ChatBotResponse> call = chatBotService.ask(message);

        call.enqueue(new Callback<ChatBotResponse>() {

            @Override
            public void onResponse(Call<ChatBotResponse> call, Response<ChatBotResponse> response) {
                if (response.isSuccessful()) {
                    ChatBotResponse chatBotResponse = response.body();
                    if (chatBotResponse != null) {
                        callback.onLoginSuccess(getAnswerByCategory(chatBotResponse.getBestCategory()));
                        System.out.println("Best Category: " + chatBotResponse.getBestCategory());
                        System.out.println("All Categories: " + chatBotResponse.getAllCategories());
                    }else {
                        System.out.println("Request failed: " + response.code());
                    }
                }
            }
            @Override
            public void onFailure(Call<ChatBotResponse> call, Throwable throwable) {
                System.out.println("Error: " + throwable.getMessage());
                callback.onLoginFailure(throwable.getMessage());
            }
        });
    }

    private String getAnswerByCategory(String category) {
        return switch (category) {
            case "__label__variables" -> "https://www.w3schools.com/java/java_variables.asp";
            case "__label__inheritance" -> "https://www.w3schools.com/java/java_inheritance.asp";
            case "__label__exception" -> "https://www.w3schools.com/java/java_try_catch.asp";
            case "__label__rest" -> "https://www.tutorialspoint.com/spring_boot/spring_boot_building_restful_web_services.htm";
            case "__label__spring" -> "https://www.tutorialspoint.com/spring_boot/index.htm";
            case "__label__method" -> "https://www.w3schools.com/java/java_methods.asp";
            case "__label__constructor" -> "https://www.w3schools.com/java/java_constructors.asp";
            case "__label__polymorphism" -> "https://www.w3schools.com/java/java_polymorphism.asp";
            case "__label__abstract" -> "https://www.w3schools.com/java/java_abstract.asp";
            case "__label__lambda" -> "https://www.w3schools.com/java/java_lambda.asp";
            case "__label__comment" -> "https://www.w3schools.com/java/java_comments.asp";
            case "__label__types" -> "https://www.w3schools.com/java/java_data_types.asp";
            case "__label__arrays" -> "https://www.w3schools.com/java/java_arrays.asp";
            case "__label__threads" -> "https://www.w3schools.com/java/java_threads.asp";
            case "__label__while" -> "https://www.w3schools.com/java/java_while_loop.asp";
            case "__label__for" -> "https://www.w3schools.com/java/java_for_loop.asp";
            case "__label__switch" -> "https://www.w3schools.com/java/java_switch.asp";
            case "__label__if" -> "https://www.w3schools.com/java/java_conditions.asp";
            default -> "No category found";
        };
    }

}
