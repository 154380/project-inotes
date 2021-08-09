package com.nmt.service.noteType;

import com.nmt.model.Note;
import com.nmt.model.NoteType;
import com.nmt.repository.INoteTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NoteTypeService implements INoteTypeService {
    @Autowired
    private INoteTypeRepository noteTypeRepository;

    @Override
    public Iterable<NoteType> findAll() {
        return noteTypeRepository.findAll();
    }

    @Override
    public Optional<NoteType> findById(Long id) {
        return noteTypeRepository.findById(id);
    }

    @Override
    public void save(NoteType noteType) {
        noteTypeRepository.save(noteType);
    }

    @Override
    public void remove(Long id) {
        noteTypeRepository.deleteById(id);
    }

}
