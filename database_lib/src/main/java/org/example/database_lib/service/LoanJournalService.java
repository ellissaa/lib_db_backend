package org.example.database_lib.service;

import jakarta.transaction.Transactional;
import org.example.database_lib.exception.ResourceNotFoundException;
import org.example.database_lib.model.*;
import org.example.database_lib.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class LoanJournalService {
    private final LoanJournalRepository loanJournalRepository;
    private final CopyRepository copyRepository;
    private final ReaderRepository readerRepository;
    private final LibrarianRepository librarianRepository;

    public LoanJournalService(LoanJournalRepository loanJournalRepository,
                              CopyRepository copyRepository,
                              ReaderRepository readerRepository,
                              LibrarianRepository librarianRepository) {
        this.loanJournalRepository = loanJournalRepository;
        this.copyRepository = copyRepository;
        this.readerRepository = readerRepository;
        this.librarianRepository = librarianRepository;
    }

    public LoanJournal create(Long copyId, Long readerId, Long librarianId, LocalDate expectedReturnDate) {
        Copy copy = copyRepository.findById(copyId)
                .orElseThrow(() -> new ResourceNotFoundException("Copy not found"));
        Reader reader = readerRepository.findById(readerId)
                .orElseThrow(() -> new ResourceNotFoundException("Reader not found"));
        Librarian librarian = librarianRepository.findById(librarianId)
                .orElseThrow(() -> new ResourceNotFoundException("Librarian not found"));

        LoanJournal record = new LoanJournal();
        record.setLoanDate(LocalDate.now());
        record.setExpectedReturnDate(expectedReturnDate);
        record.setCopy(copy);
        record.setReader(reader);
        record.setLibrarian(librarian);
        return loanJournalRepository.save(record);
    }

    public LoanJournal getById(Long id) {
        return loanJournalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Loan record not found"));
    }

    public List<LoanJournal> getAll() {
        return loanJournalRepository.findAll();
    }

    public LoanJournal markAsReturned(Long id) {
        LoanJournal record = getById(id);
        record.setActualReturnDate(LocalDate.now());
        return loanJournalRepository.save(record);
    }

    public List<LoanJournal> getActiveLoans() {
        return loanJournalRepository.findByActualReturnDateIsNull();
    }

    public void delete(Long id) {
        LoanJournal record = getById(id);
        loanJournalRepository.delete(record);
    }
}