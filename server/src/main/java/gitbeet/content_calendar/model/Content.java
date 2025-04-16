package gitbeet.content_calendar.model;

import java.time.LocalDateTime;
import jakarta.validation.constraints.NotBlank;

public record Content(
    Integer id,
    @NotBlank(message = "Title cannot be blank") 
    String title,
    String description,
    Status status,
    Type contentType,
    LocalDateTime dateCreated,
    LocalDateTime dateUpdated,
    String url
) {}