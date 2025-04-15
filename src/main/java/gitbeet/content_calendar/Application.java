package gitbeet.content_calendar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		// SpringApplication.run(Application.class, args);
		ConfigurableApplicationContext context = SpringApplication.run(Application.class ,args);
        RestTemplate restTemplate = context.getBean(RestTemplate.class);
		System.out.println("*****rest template below*****");
		System.out.println(restTemplate);
	}

}
