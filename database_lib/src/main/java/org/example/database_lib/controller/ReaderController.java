package org.example.database_lib.controller;

import org.example.database_lib.model.Reader;
import org.example.database_lib.model.ReaderPublication;
import org.example.database_lib.model.Resident;
import org.example.database_lib.service.AbstractService;
import org.example.database_lib.service.ReaderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin()
@RequestMapping("/api/readers")
public class ReaderController extends AbstractController<Reader, Long> {
    private final ReaderService service;

    protected ReaderController(ReaderService service) {
        super(service);
        this.service = service;
    }

    @GetMapping("/has-work/{work}")
    public List<Reader> findByWork(@PathVariable String work) {
        return service.findByWork(work);
    }

    @GetMapping("/has-publication/{publication}")
    public List<Reader> findByPublication(@PathVariable String publication) {
        return service.findByPublication(publication);
    }

    @GetMapping("/got-work")
    public List<ReaderPublication> findGotWork(
            @RequestParam String start, @RequestParam String end, @RequestParam String work) {
        return service.findGotWork(start, end, work);
    }

    @GetMapping("/librarian")
    public List<Reader> findByLibrarian(
            @RequestParam Long librarian, @RequestParam String start, @RequestParam String end) {
        return service.findByLibrarian(librarian, start, end);
    }

    @GetMapping("/overdue")
    public List<Reader> findOverdue() {
        return service.findOverdue();
    }

    @GetMapping("/not-visited")
    public List<Reader> findNotVisited(@RequestParam String start, @RequestParam String end) {
        return service.findNotVisited(start, end);
    }
}
