package com.vlaskz.maybepad.core.Note;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;

    public Note save(Note note) {
        return noteRepository.save(note);
    }

    public Optional<Note> findById(Long id) {
        return noteRepository.findById(id);
    }

    // Outros métodos conforme necessário...
}
