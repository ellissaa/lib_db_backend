package org.example.database_lib.controller;

import org.example.database_lib.model.LoanJournal;
import org.example.database_lib.service.AbstractService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/loan_journal")
public class LoanJournalController extends AbstractController<LoanJournal> {
    protected LoanJournalController(AbstractService<LoanJournal> service) {
        super(service);
    }
}