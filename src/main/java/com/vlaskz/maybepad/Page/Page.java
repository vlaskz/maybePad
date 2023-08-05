package com.vlaskz.maybepad.Page;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Page {

    @Id
    private String path;

    private String parentId;

    @Column(length=1000000000)
    private String content;

    @CreationTimestamp
    @Column(name = "creationDateTime", nullable = false, updatable = false)
    private LocalDateTime creationDateTime;

    @UpdateTimestamp
    @Column(name = "lastUpdate", nullable = false)
    private LocalDateTime lastUpdate;

}