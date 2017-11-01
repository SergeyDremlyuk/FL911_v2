package com.innopo.websockets;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import javax.json.Json;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class ChatMessageEncoder implements Encoder.Text<ChatMessage>{
    @Override
    public String encode(ChatMessage chatMessage) throws EncodeException {
//        com.fasterxml.jackson.databind.ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json().build();
//        objectMapper.
        ObjectMapper mapper = new ObjectMapper();

String jsonInString = "";
        try {
             jsonInString = mapper.writeValueAsString(chatMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


//        return Json.createObjectBuilder()
//                .add("message", chatMessage.getMessage())
//                .add("sender", chatMessage.getSender())
//                .add("received", chatMessage.getReceived().toString()).build()
//                .toString();
        return jsonInString;
    }

    @Override
    public void init(EndpointConfig config) {

    }

    @Override
    public void destroy() {

    }
}
