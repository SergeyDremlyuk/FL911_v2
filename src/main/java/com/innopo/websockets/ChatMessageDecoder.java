package com.innopo.websockets;




import com.fasterxml.jackson.databind.ObjectMapper;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import java.io.IOException;
import java.io.StringReader;
import java.util.Date;

public class ChatMessageDecoder implements Decoder.Text<ChatMessage>{


    @Override
    public ChatMessage decode(String s) throws DecodeException {
//     JsonObjectBuilder jsb =  Json.createObjectBuilder()
//                .add("message", "dasdas")
//                .add("sender", "dasdasdas")
//                .add("received", "dasdasdsa");

        ObjectMapper mapper = new ObjectMapper();
        int A =0;
        String[] arr = s.split(":");

       ChatMessage chatMessage = new ChatMessage();
//       chatMessage.setMessage(arr[0]);
//       chatMessage.setSender(arr[1]);
//       chatMessage.setReceived(new Date());
//        JsonReader red = Json.createReader(new StringReader(s));
//         JsonObject obj =        red.readObject();
//        chatMessage.setMessage(obj.getString("message"));
//        chatMessage.setSender(obj.getString("sender"));
//        chatMessage.setReceived(new Date());
    //    ChatMessage chatMessage = null;
        try {
            chatMessage = mapper.readValue(s, ChatMessage.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        chatMessage.setReceived(new Date());
        return chatMessage;
    }

    @Override
    public boolean willDecode(String s) {
        return true;
    }

    @Override
    public void init(EndpointConfig config) {

    }

    @Override
    public void destroy() {

    }

}
