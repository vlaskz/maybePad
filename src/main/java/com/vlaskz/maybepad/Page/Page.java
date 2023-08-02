package com.vlaskz.maybepad.Page;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
public class Page {

    @Id
    private String path;

    private String parentId;

    @Column(length = 100000)
    @Lob
    private String content;

    public Page() {
    }

    public Page(String path, String content) {
        this.path = path;
        this.content = content;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}