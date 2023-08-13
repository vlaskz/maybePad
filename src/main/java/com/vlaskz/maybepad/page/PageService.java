package com.vlaskz.maybepad.page;

import org.springframework.stereotype.Service;

@Service
public class PageService {


    public Page process(String path) {
        return Page.builder().path(path).content("content").build();
    }
}
