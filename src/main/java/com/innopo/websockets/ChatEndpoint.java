package com.innopo.websockets;


import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ServerEndpoint(value = "/chat/{room}", decoders = ChatMessageDecoder.class, encoders = ChatMessageEncoder.class)
public class ChatEndpoint{

    static Set<Session> sessionSet = Collections.synchronizedSet(new HashSet<Session>());

    @OnOpen
    public void open(final Session session, @PathParam("room") final String room) {
        session.getUserProperties().put("room", room);
        sessionSet.add(session);
    }

    @OnMessage
    public void onMessage(final Session session, final ChatMessage chatMessage) {
        String room = (String) session.getUserProperties().get("room");
        try {
            for (Session s : sessionSet) {
                if (s.isOpen()
                        && room.equals(s.getUserProperties().get("room"))) {
                    s.getBasicRemote().sendObject(chatMessage);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (EncodeException e) {
            e.printStackTrace();
        }
    }

    @OnClose
    public void onClose(Session session) {
        sessionSet.remove(session);
    }

}
