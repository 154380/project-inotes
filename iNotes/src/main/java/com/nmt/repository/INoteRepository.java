package com.nmt.repository;

import com.nmt.model.Note;
import com.nmt.model.NoteType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface INoteRepository extends PagingAndSortingRepository<Note, Long> {
    Iterable<Note> findAllByNoteType(NoteType noteType);
    Page<Note> findAllByTitleContaining(String title, Pageable pageable);
}
