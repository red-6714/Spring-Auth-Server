package sample.controller;
//
//import org.springframework.boot.autoconfigure.security.oauth2.resource.reactive.ReactiveOAuth2ResourceServerAutoConfiguration;
//import org.springframework.security.core.userdetails.UserDetailsService;
////import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
//import org.springframework.web.bind.annotation.*;
//import reactor.core.publisher.Mono;
//import sample.model.LoginRequest;
//
//import java.security.Principal;
//import java.util.Collections;
//import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@CrossOrigin
public class HelloUserController {
//
//    //@Autowired
//    UserDetailsService userDetailsService;

    @GetMapping("/hello")
    public Principal hell(Principal principal) {
        return principal;
    }
//
//    @PostMapping(path = "/login")
//    public Mono<?> validateLogin(Mono<Principal> p, @RequestBody LoginRequest loginRequest) {
//        return p;//userDetailsService.findByUsername(loginRequest);
//    }
//
//    @GetMapping("/login")
//    public Mono login(Mono<Principal> principal) {
//        return principal;
//    }
//
//    private Map<String, String> helloMessage(String username) {
//        System.out.println("edfnjiof fef " + username);
//        return Collections.singletonMap("message", "Hello " + username + "!");
//    }
}