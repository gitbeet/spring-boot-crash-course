package gitbeet.content_calendar.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @Value("${cc.welcomeMessage: Default Welcome Message}")    
    private String welcomeMessage;

    @Value("${cc.about}")    
    private String about;

    @GetMapping("/")
        public String Home(){
               return welcomeMessage;
        }    
};
