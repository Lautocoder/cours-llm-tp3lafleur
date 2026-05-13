package ht.lafleur.tp3lafleur.llm;

import dev.langchain4j.model.output.TokenUsage;

public record TokenUsageDto(
        int inputTokenCount,
        int outputTokenCount,
        int totalTokenCount
) {
    // Méthode de conversion de TokenUsage à TokenUsageDto
    public static TokenUsageDto from(TokenUsage usage) {
        return new TokenUsageDto(
                usage.inputTokenCount(),
                usage.outputTokenCount(),
                usage.totalTokenCount()
        );
    }
}
