package gitbeet.content_calendar;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import gitbeet.content_calendar.model.Content;
import gitbeet.content_calendar.model.Status;
import gitbeet.content_calendar.model.Type;
import gitbeet.content_calendar.repository.ContentRepository;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class ,args);
	}

	@Bean
	CommandLineRunner commandLineRunner (ContentRepository repository){
		return args ->{
			Content gptContent = new Content(
				null,
				"GPT Video content",
				"Video content about AI",
				Status.IDEA,
				Type.VIDEO,
				LocalDateTime.now(),
				null,
				"");

				Content javaContent = new Content(
				null,
				"Java article",
				"Article content about java",
				Status.IN_PROGRESS,
				Type.ARTICLE,
				LocalDateTime.now(),
				null,
				"");

			repository.save(gptContent);
			repository.save(javaContent);
			};
	}

}
