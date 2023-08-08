package com.vlaskz.maybepad.core.Note;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    // Os métodos CRUD básicos, como save, findAll, findById, delete, etc., são fornecidos automaticamente

    // Caso precise de consultas personalizadas, você pode defini-las aqui. Exemplo:
    // List<Note> findByTitleContaining(String keyword);
}