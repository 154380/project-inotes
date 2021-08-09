package com.nmt.controller;

import com.nmt.model.Note;
import com.nmt.model.NoteType;
import com.nmt.service.note.INoteService;
import com.nmt.service.noteType.INoteTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class NoteController {

    @Autowired
    private INoteService noteService;

    @Autowired
    private INoteTypeService noteTypeService;

    @ModelAttribute("noteTypes")
    public Iterable<NoteType> noteTypes(){
        return noteTypeService.findAll();
    }

    @GetMapping("/notes")
    public ModelAndView listNotes(@RequestParam("search") Optional<String> search,@PageableDefault(value = 3) Pageable pageable) {
        Page<Note> notes;
        if(search.isPresent()) notes = noteService.findAllByTitleContaining(search.get(), pageable);
        else notes = noteService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("/note/list");
        modelAndView.addObject("notes", notes);
        modelAndView.addObject("search", search);
        return modelAndView;
    }

    @GetMapping("/create-note")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/note/create");
        modelAndView.addObject("note", new Note());
        return modelAndView;
    }

    @PostMapping("/create-note")
    public ModelAndView saveNote(@ModelAttribute("note") Note note) {
        noteService.save(note);
        ModelAndView modelAndView = new ModelAndView("/note/create");
        modelAndView.addObject("note", new Note());
        return modelAndView;
    }

    @GetMapping("/edit-note/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Optional<Note> note = noteService.findById(id);
        if (note.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/note/edit");
            modelAndView.addObject("note", note.get());
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/edit-note")
    public ModelAndView updateNote(@ModelAttribute("note") Note note) {
        noteService.save(note);
        ModelAndView modelAndView = new ModelAndView("/note/edit");
        modelAndView.addObject("note", note);
        return modelAndView;
    }

    @GetMapping("/show-note/{id}")
    public ModelAndView showNoteForm(@PathVariable Long id) {
        Optional<Note> note = noteService.findById(id);
        if (note.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/note/note");
            modelAndView.addObject("note", note.get());
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @GetMapping("/delete-note/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        Optional<Note> note = noteService.findById(id);
        if (note.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/note/delete");
            modelAndView.addObject("note", note.get());
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/delete-note")
    public String deleteNote(@ModelAttribute("note") Note note) {
        noteService.remove(note.getId());
        return "redirect:notes";
    }

}
