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

    @GetMapping("/{path}/**")
    public ResponseEntity<Page> getPage(@PathVariable String path) {
        System.out.println("path:"+ path);
        Page page = pageRepository.findByPath("/"+path);
        System.out.println("content:"+ page.toString());
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @PostMapping("/{path}/**")
    public ResponseEntity<Page> savePage(@PathVariable String path, @RequestBody Page page) {
        System.out.println(page.getContent());
        System.out.println(page.getPath());
        pageRepository.save(page);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }
}