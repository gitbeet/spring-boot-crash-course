package gitbeet.content_calendar.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gitbeet.content_calendar.model.Content;
import gitbeet.content_calendar.repository.ContentCollectionRepository;


@RestController
@RequestMapping("/api/content")
public class ContentController {
    
    private final ContentCollectionRepository repository;
    
    public ContentController(ContentCollectionRepository repository){
        this.repository = repository;
    }

    // make a  request and find all the pieces of content in the system
    //handle GET requests , empty path means it gets the class path "/api/content"
    @GetMapping("")
    public List<Content> findAll() {
        return repository.findAll();
    }

}
