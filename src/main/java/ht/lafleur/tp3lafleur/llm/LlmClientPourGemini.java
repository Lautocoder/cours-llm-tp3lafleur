package ht.lafleur.tp3lafleur.llm;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.Result;
import jakarta.enterprise.context.Dependent;

import java.io.Serializable;

@Dependent
public class LlmClientPourGemini implements Serializable {


    private String systemRole;

    private GuideTouristique guideTouristique;

    private ChatMemory chatMemory;


    public LlmClientPourGemini() {
        String geminiKey = System.getenv("GEMINI_KEY");
        if (geminiKey == null || geminiKey.isBlank()) {
            System.err.println("Environment variable GEMINI_KEY is missing.");
            return;
        }
        ChatModel model = GoogleAiGeminiChatModel.builder()
                .apiKey(geminiKey)
                .modelName("gemini-2.5-flash")
                .temperature(0.3)
                .build();

        this.chatMemory = MessageWindowChatMemory.withMaxMessages(20);
        this.guideTouristique = AiServices.builder(GuideTouristique.class)
                .chatModel(model)
                .chatMemory(chatMemory)
                .build();
    }

    public Result<InfosTouristiques> envoyerRequete(String question, int nb){
        return guideTouristique.chat(question, nb);
    }

    public void addReponse(AiMessage message) {
        this.chatMemory.add(message);
    }

}
