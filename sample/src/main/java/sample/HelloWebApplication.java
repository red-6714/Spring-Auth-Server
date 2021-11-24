package sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Rob Winch
 * @since 5.0
 */

@SpringBootApplication
public class HelloWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloWebApplication.class, args);
    }
//
//    @Bean
//    public HandlerMapping webSocketHandlerMapping() {
//        Map<String, WebSocketHandler> map = new HashMap<>();
//        map.put("/chats", handler());
//        SimpleUrlHandlerMapping handlerMapping = new SimpleUrlHandlerMapping();
//        handlerMapping.setOrder(Ordered.HIGHEST_PRECEDENCE);
//        handlerMapping.setUrlMap(map);
//        return handlerMapping;
//    }
//
//    @Bean
//    public WebSocketHandler handler() {
//        return (session) -> {
//            System.out.println(session.getHandshakeInfo().getPrincipal().doOnNext(p -> System.out.println(p.getName())));
//            Flux<WebSocketMessage> message = session.receive()
//                    .map(WebSocketMessage::getPayloadAsText)
//                    .map(session::textMessage);
//            return session.send(message);
//        };
//    }

}