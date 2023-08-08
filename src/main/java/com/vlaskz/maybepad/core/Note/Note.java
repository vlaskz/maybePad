package com.vlaskz.maybepad.core.Note;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data  // Gera getters, setters, equals, hashCode e toString automaticamente
@NoArgsConstructor  // Gera um construtor sem argumentos
@AllArgsConstructor  // Gera um construtor com todos os argumentos
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Note parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Note> children = new ArrayList<>();
}
