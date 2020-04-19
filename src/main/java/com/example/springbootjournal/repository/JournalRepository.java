package com.example.springbootjournal.repository;

import com.example.springbootjournal.domain.Journal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JournalRepository extends JpaRepository<Journal, Long> {
}
