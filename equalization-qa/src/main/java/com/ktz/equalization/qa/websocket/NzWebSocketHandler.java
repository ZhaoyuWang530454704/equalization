package com.ktz.equalization.qa.websocket;//package com.ktz.equalization.qa.websocket;
//
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.socket.*;
//
//import java.io.IOException;
//import java.sql.Timestamp;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.Map;
//
//@Component
//public class NzWebSocketHandler implements WebSocketHandler {
//
////    /**
////     * 数据访问层
////     */
////    @Autowired
////    private SendMsgService sendMsgService;
//
//    /**
//     * 当WebSocketHandler类被加载时就会创建该Map，随类而生
//     */
//    public static final Map<String, WebSocketSession> userSocketSessionMap;
//
//    static {
//        userSocketSessionMap = new HashMap<>();
//    }
//
//    /**
//     * 握手实现连接后的处理
//     * @param webSocketSession
//     */
//    @Override
//    public void afterConnectionEstablished(WebSocketSession webSocketSession) {
//        String userId = (String) webSocketSession.getAttributes().get("userId");
//        if (userSocketSessionMap.get(userId) == null) {
//            userSocketSessionMap.put(userId, webSocketSession);
//        }
//    }
//
//    /**
//     * 发送信息前的处理
//     * @param webSocketSession
//     * @param webSocketMessage
//     * @throws Exception
//     */
//    @Override
//    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
//
//        if (webSocketMessage.getPayloadLength() == 0) {
//            return;
//        }
//
//        //得到Socket通道中的数据并转化为Message对象
//        Message msg = new Gson().fromJson(webSocketMessage.getPayload().toString(), Message.class);
//
//        Timestamp now = new Timestamp(System.currentTimeMillis());
//        msg.setMessageDate(now);
//
////        System.err.println("size = " + userSocketSessionMap.size());
////        for (Map.Entry map : userSocketSessionMap.entrySet()) {
////            System.err.println(map.getKey() + "  val= " + map.getValue());
////        }
////        System.err.println("---------------------------------------------------");
//
//        //将信息保存至数据库
//        //sndMsgService.addMessage(msg.getFromId(), msg.getFromName(), msg.getToId(), msg.getMessageText(), msg.getMessageDate());
//
//        //发送Socket信息
//        sendMessageToUser(msg.getToId() + "", new TextMessage(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(msg)));
//
//    }
//
//    /**
//     * 握手传输错误后的处理
//     * @param webSocketSession
//     * @param throwable
//     * @throws Exception
//     */
//    @Override
//    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {
//
//    }
//
//    /**
//     * 连接关闭前的处理
//     * 在此刷新页面就相当于断开WebSocket连接,原本在静态变量userSocketSessionMap中的
//     * WebSocketSession会变成关闭状态(close)，但是刷新后的第二次连接服务器创建的
//     * 新WebSocketSession(open状态)又不会加入到userSocketSessionMap中,所以这样就无法发送消息
//     * 因此应当在关闭连接这个切面增加去除userSocketSessionMap中当前处于close状态的WebSocketSession，
//     * 让新创建的WebSocketSession(open状态)可以加入到userSocketSessionMap中
//     *
//     * @param webSocketSession
//     * @param closeStatus
//     * @throws Exception
//     */
//    @Override
//    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
//        System.out.println("WebSocket:" + webSocketSession.getAttributes().get("userId") + " close connection");
//        Iterator<Map.Entry<String, WebSocketSession>> iterator = userSocketSessionMap.entrySet().iterator();
//        while (iterator.hasNext()) {
//            Map.Entry<String, WebSocketSession> entry = iterator.next();
//            if (entry.getValue().getAttributes().get("userId") == webSocketSession.getAttributes().get("userId")) {
//                userSocketSessionMap.remove(webSocketSession.getAttributes().get("userId"));
//                System.out.println("WebSocket in staticMap:" + webSocketSession.getAttributes().get("userId") + " removed");
//            }
//        }
//    }
//
//    /**
//     * 是否支持局部的消息
//     * @return
//     */
//    @Override
//    public boolean supportsPartialMessages() {
//        return false;
//    }
//
//    /**
//     * 发送信息的实现
//     * @param userId, message
//     * @throws IOException
//     */
//    public void sendMessageToUser(String userId, TextMessage message) throws IOException {
//        WebSocketSession session = userSocketSessionMap.get(userId);
//        if (session != null && session.isOpen()) {
//            session.sendMessage(message);
//        }
//    }
//
//}
//
