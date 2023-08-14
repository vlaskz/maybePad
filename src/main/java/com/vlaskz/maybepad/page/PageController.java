package com.vlaskz.maybepad.page;


import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")

public class PageController {

    @Autowired
    private PageRepository pageRepository;

    @Autowired
    private PageService pageService;


    @GetMapping("/**")
    public ResponseEntity<Page> getPage(HttpServletRequest request) {
     return new ResponseEntity<>(pageRepository.findByPath(request.getRequestURI().replace("/api","")),HttpStatus.OK);
    }

    @PostMapping("/**")
    public ResponseEntity<Page> savePage(HttpServletRequest request, @RequestBody Page page) {
        page.setPath(request.getRequestURI().replace("/api",""));
        pageRepository.save(page);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @GetMapping("/subitems")
    public ResponseEntity<List<String>> getSubItems(@RequestParam("path") String path) {

        List<String> subitems = pageRepository.findPathsStartingWith(path);

        return ResponseEntity.ok(subitems);
    }
}