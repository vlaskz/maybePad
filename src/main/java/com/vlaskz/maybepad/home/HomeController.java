package com.vlaskz.maybepad.home;

import com.vlaskz.maybepad.interceptors.annotations.IgnoreInterceptor;
import com.vlaskz.maybepad.utils.Utils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
@Slf4j(topic = "HomeController")
public class HomeController {

    @GetMapping("/")
    @ResponseBody
    @IgnoreInterceptor
    public Resource home() {
        log.info("serving home");
        return new ClassPathResource("static/home.html");
    }

    @GetMapping("/assets/{filename:.+}")
    @ResponseBody
    @IgnoreInterceptor
    public ResponseEntity<Resource> serveAsset(@PathVariable String filename) {
        log.info("serving assets for resource {}", filename);
        Resource asset = new ClassPathResource("static/assets/" + filename);
        return ResponseEntity.ok().body(asset);
    }
    @RequestMapping("/**")
    public ResponseEntity<Resource> handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("serving dynamic route for request {}", request);
        String originalPath = request.getRequestURI();
        System.out.println("called root route");
        String normalizedPath = Utils.normalizePath(originalPath);

        if (!originalPath.equals(normalizedPath)) {
            response.sendRedirect(normalizedPath);
            return null;
        }

        Resource indexFile = new ClassPathResource("static/defaultPage.html");
        return ResponseEntity.ok().body(indexFile);
    }
}