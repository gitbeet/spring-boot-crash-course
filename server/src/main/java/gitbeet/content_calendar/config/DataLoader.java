package gitbeet.content_calendar.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

// @Profile("!dev") // means do not run this in dev
// @Component
public class DataLoader implements CommandLineRunner {
  @Override
  public void run (String...args) throws Exception{
    System.out.println("Hello from data loader");
  }
}
