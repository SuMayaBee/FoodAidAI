package com.redy.blogbackend.services;


import org.springframework.ai.chat.messages.Media;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

import java.io.IOException;
import java.util.Base64;
import java.util.List;


@Service
public class ChatService {

    @Autowired
    ChatModel chatClient;




    public String getImageChatReader(String query, String imageBase64) {
        // Decode the base64 image to byte array

        byte[] imageData = Base64.getDecoder().decode(imageBase64);

        UserMessage userMessage = new UserMessage(query,
                List.of(new Media(MimeTypeUtils.IMAGE_PNG, imageData)));

        ChatResponse response = chatClient.call(new Prompt(userMessage));
        return response.getResult().getOutput().getContent();
    }

}
