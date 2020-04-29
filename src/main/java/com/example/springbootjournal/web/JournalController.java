package com.example.springbootjournal.web;

import com.example.springbootjournal.domain.JournalEntry;
import com.example.springbootjournal.repository.JournalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@RestController
public class JournalController {
    private static final String VIEW_INDEX = "index";

    @Autowired
    JournalRepository repo;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index(ModelAndView modelAndView) {
        modelAndView.setViewName(VIEW_INDEX);
        modelAndView.addObject("journal", repo.findAll());
        return modelAndView;
    }

    @GetMapping(path = "/all")
//    @PreAuthorize("hasAuthority('journal:read'")
    public List<JournalEntry> getAllJournals() {
        return repo.findAll();
    }

    @PostMapping(path = "/add")
//    @PreAuthorize("hasAuthority('journal:write'")
    public void addJournal(@RequestBody JournalEntry journalEntry) {
        repo.save(journalEntry);
    }
}
