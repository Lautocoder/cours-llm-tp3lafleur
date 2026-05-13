package ht.lafleur.tp3lafleur.llm;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.anthropic.AnthropicChatModel;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.service.AiServices;
import jakarta.enterprise.context.Dependent;

import java.io.Serializable;

@Dependent
public class LlmClientPourClaude implements Serializable {


    private String systemRole;

    private GuideTouristique guideTouristique;

    private ChatMemory chatMemory;


    public LlmClientPourClaude() {
        String claudeKey = System.getenv("CLAUDE_KEY");

        if (claudeKey == null || claudeKey.isBlank()) {
            System.err.println("Environment variable GEMINI_KEY is missing.");
            return;
        }
        ChatModel model = AnthropicChatModel.builder()
                .apiKey(claudeKey)
                .modelName("claude-sonnet-4-6")
                .temperature(0.3)
                .build();

        this.chatMemory = MessageWindowChatMemory.withMaxMessages(20);
        this.guideTouristique = AiServices.builder(GuideTouristique.class)
                .chatModel(model)
                .chatMemory(chatMemory)
                .build();
    }

    public InfosTouristiques envoyerRequete(String question, int nb){
        return guideTouristique.chat(question,  nb);
    }

    public void addReponse(AiMessage message) {
        this.chatMemory.add(message);
    }

}
