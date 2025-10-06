package com.ktz.equalization.qa.websocket;//package com.ktz.equalization.qa.websocket;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//import org.springframework.web.socket.config.annotation.EnableWebSocket;
//import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
//import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
//
//import javax.servlet.http.HttpSession;
//
//@Component
//@EnableWebSocket
//@Controller
//public class NzWebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {
//
//    /**
//     * 当WebSocketHandler类被加载时就会创建该Integer，随类而生
//     * 测试用的，用于分辨
//     */
//    public static Integer count = 0;
//
//    /**
//     * 跳转到聊天View
//     * @param session
//     * @return
//     */
//    @RequestMapping("conSocket")
//    public String conSocket(HttpSession session){
//
//        count++;
//
//        session.setAttribute("user", new User("NZ" + count));
//
//        return "nz/webSocketJsp";
//    }
//
//    @Autowired
//    NzWebSocketHandler handler;
//
//    @Override
//    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
//
//        //添加websocket处理器，添加握手拦截器
//        webSocketHandlerRegistry.addHandler(handler, "/websocket").addInterceptors(new NzHandShakeInterceptor());
//
//    }
//
//}
