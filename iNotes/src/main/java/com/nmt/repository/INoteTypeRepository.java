package com.nmt.repository;


import com.nmt.model.Note;
import com.nmt.model.NoteType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface INoteTypeRepository extends PagingAndSortingRepository<NoteType, Long> {
}
