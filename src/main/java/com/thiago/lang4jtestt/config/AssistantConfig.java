package com.thiago.lang4jtestt.config;

import com.thiago.lang4jtestt.Service.AssistantAiService;
import org.springframework.context.annotation.Configuration;

import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;
import dev.langchain4j.service.AiServices;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

@Configuration
public class AssistantConfig {

    @Value("${gemini.api-key}")
    private String geminiApiKey;

    @Value("${gemini.model}")
    private String geminiModel;

    @Bean
    public GoogleAiGeminiChatModel googleAiGeminiChatModel() {
        return GoogleAiGeminiChatModel.builder()
                .apiKey(geminiApiKey)
                .modelName(geminiModel)
                .build();
    }

    @Bean
    public AssistantAiService assistant(GoogleAiGeminiChatModel model, AssistantTools assistantTools) {
        return AiServices.builder(AssistantAiService.class)
                .chatModel(model)
                .tools(assistantTools) // registra a ferramenta no Serviço IA
                .build();
    }
}