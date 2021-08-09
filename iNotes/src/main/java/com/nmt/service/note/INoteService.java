package com.nmt.service.note;

import com.nmt.model.Note;
import com.nmt.model.NoteType;
import com.nmt.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface INoteService extends IGeneralService<Note> {
    Iterable<Note> findAllByNoteType(NoteType noteType);
    Page<Note> findAll(Pageable pageable);
    Page<Note> findAllByTitleContaining(String title, Pageable pageable);
}
