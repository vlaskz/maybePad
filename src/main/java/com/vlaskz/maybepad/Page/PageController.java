package com.vlaskz.maybepad.Page;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PageController {
    @Value("${page.helpId}")
    private String helpId;
    @Value("${page.homeId}")
    private String homeId;

    private final PageRepository pageRepository;

    public PageController(PageRepository pageRepository) {
        this.pageRepository = pageRepository;
    }

    @GetMapping("/")
    public String getHomePage(){
        return "home";
    }
    @GetMapping("/{path}/**")
    public ResponseEntity<Page> getPage(@PathVariable String path) {
        Page page = pageRepository.findByPath("/" + path);
        if(page == null) {
            page = pageRepository.findByPath(helpId);
            if (page == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @PostMapping("/{path}/**")
    public ResponseEntity<Page> savePage(@PathVariable String path, @RequestBody Page page) {
        pageRepository.save(page);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }
}