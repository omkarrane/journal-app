package com.example.springbootjournal.repository;

import com.example.springbootjournal.domain.JournalEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JournalRepository extends JpaRepository<JournalEntry, Long> {
}
