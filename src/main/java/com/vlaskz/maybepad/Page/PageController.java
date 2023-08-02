package com.vlaskz.maybepad.Page;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PageController {

    private final PageRepository pageRepository;

    public PageController(PageRepository pageRepository) {
        this.pageRepository = pageRepository;
    }

    @GetMapping("/")
    public ResponseEntity<Page> getHelpPage(){
        return new ResponseEntity<>(pageRepository.findByPath("/831914af-86f1-4442-8ef3-9e3f04698845"), HttpStatus.OK);
    }
    @GetMapping("/{path}/**")
    public ResponseEntity<Page> getPage(@PathVariable String path) {
        Page page = pageRepository.findByPath("/" + path);
        if(page == null) {
            page = pageRepository.findByPath("/831914af-86f1-4442-8ef3-9e3f04698845");
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