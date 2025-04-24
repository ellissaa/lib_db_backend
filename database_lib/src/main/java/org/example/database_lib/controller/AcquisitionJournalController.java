package org.example.database_lib.controller;

import org.example.database_lib.model.AcquisitionJournal;
import org.example.database_lib.service.AbstractService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/acquisition_journal")
public class AcquisitionJournalController extends AbstractController<AcquisitionJournal> {
    protected AcquisitionJournalController(AbstractService<AcquisitionJournal> service) {
        super(service);
    }
}