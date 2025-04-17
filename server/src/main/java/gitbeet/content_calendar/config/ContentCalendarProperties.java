package gitbeet.content_calendar.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(value = "cc")
public record ContentCalendarProperties(String welcomeMessageString, String about) {

} 


