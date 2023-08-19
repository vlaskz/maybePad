package com.vlaskz.maybepad.page;


import com.vlaskz.maybepad.owner.Owner;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Page {

    @Id
    private String path;

    @Builder.Default
    @Column(name = "isLocked", nullable = false, columnDefinition = "boolean default false")
    private Boolean isLocked = false;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @Column(length = 100000000)
    private String content;

    @CreationTimestamp
    @Column(name = "creationDateTime", updatable = false)
    private LocalDateTime creationDateTime;

    @UpdateTimestamp
    @Column(name = "lastUpdate")
    private LocalDateTime lastUpdate;

}
