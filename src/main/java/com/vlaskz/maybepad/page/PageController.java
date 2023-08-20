package com.vlaskz.maybepad.page;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pages")
@Slf4j(topic = "PageController")
public class PageController {

    String removeLastBar(String url){
        log.info("#removeLastBar");
        if(url.endsWith("/")){
            return url.substring(0, url.length()-1);
        }
        return url;
    }

    @Autowired
    private PageRepository pageRepository;

    // Usando @RequestParam para obter o 'path' da queryString
    @GetMapping("/get_page")
    public ResponseEntity<Page> getPage(@RequestParam("path") String path) {
        log.info("serving getPage with path{}",path);
        return new ResponseEntity<>(pageRepository.findByPath(removeLastBar(path)), HttpStatus.OK);
    }

    // Aqui usamos @RequestParam para obter o 'path' da queryString
    @PostMapping("/save_page")
    public ResponseEntity<Page> savePage(@RequestBody Page page, @RequestParam("path") String path) {
        log.info("serving save_page with page {} and path {}",page, path);
        page.setPath(removeLastBar(path));
        pageRepository.save(page);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @GetMapping("/subitems")
    public ResponseEntity<List<String>> getSubItems(@RequestParam("path") String path) {
        log.info("serving /subitems with path {}",path);
        List<String> subitems = pageRepository.findPathsStartingWith(removeLastBar(path));
        return ResponseEntity.ok(subitems);
    }
}