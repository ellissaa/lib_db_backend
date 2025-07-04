package org.example.database_lib.controller;

import org.example.database_lib.model.AcquisitionJournal;
import org.example.database_lib.service.AbstractService;
import org.example.database_lib.service.AcquisitionJournalService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin()
@RequestMapping("/api/acquisition_journal")
public class AcquisitionJournalController extends AbstractController<AcquisitionJournal, Long> {
    private final AcquisitionJournalService service;

    protected AcquisitionJournalController(AcquisitionJournalService service) {
        super(service);
        this.service = service;
    }

    @GetMapping("/period")
    public List<AcquisitionJournal> findByPeriod(
            @RequestParam String start, @RequestParam String end) {
        return service.findByPeriod(start, end);
    }
}
