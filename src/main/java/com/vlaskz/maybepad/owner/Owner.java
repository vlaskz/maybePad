package com.vlaskz.maybepad.owner;

import com.vlaskz.maybepad.page.Page;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @NotEmpty
    @Column(name="email", unique = true, nullable = false)
    private String email;

    @NotEmpty
    @Column(name="password", nullable = false)
    private String password;

    @CreationTimestamp
    @Column(name = "creationDateTime", updatable = false)
    private LocalDateTime creationDateTime;

    @UpdateTimestamp
    @Column(name = "lastUpdate")
    private LocalDateTime lastUpdate;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Page> pages = new ArrayList<>();

}