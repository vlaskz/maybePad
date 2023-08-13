package com.vlaskz.maybepad.home;

import com.vlaskz.maybepad.utils.Utils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
public class HomeController {

    @Autowired
    private ResourceLoader resourceLoader;

    @GetMapping("/")
    @ResponseBody
    public Resource home() {
        return new ClassPathResource("static/home.html");
    }

    @GetMapping("/assets/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveAsset(@PathVariable String filename) {
        Resource asset = new ClassPathResource("static/assets/" + filename);
        return ResponseEntity.ok().body(asset);
    }
    @RequestMapping("/**")
    public ResponseEntity<Resource> handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String originalPath = request.getRequestURI();
        String normalizedPath = Utils.normalizePath(originalPath);

        // Se o caminho normalizado é diferente do original, reescreva a URL.
        if (!originalPath.equals(normalizedPath)) {
            request.getRequestDispatcher(normalizedPath).forward(request, response);
            return null;  // Termina a execução do método após o redirecionamento.
        }

        // Se o caminho é o mesmo (ou já foi reescrito anteriormente),
        // servimos o conteúdo de index.html mantendo a URL.
        Resource indexFile = new ClassPathResource("static/defaultPage.html");
        return ResponseEntity.ok().body(indexFile);
    }
}