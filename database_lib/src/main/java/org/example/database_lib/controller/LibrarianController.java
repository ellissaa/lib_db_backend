package org.example.database_lib.controller;

import org.example.database_lib.model.Librarian;
import org.example.database_lib.service.AbstractService;
import org.example.database_lib.service.LibrarianService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin()
@RequestMapping("/api/librarians")
public class LibrarianController extends AbstractController<Librarian, Long> {
    private final LibrarianService service;

    protected LibrarianController(LibrarianService service) {
        super(service);
        this.service = service;
    }

    @GetMapping("/served")
    public Long countServed(
            @RequestParam Long id, @RequestParam String start, @RequestParam String end) {
        return service.countServed(id, start, end);
    }

    @GetMapping("/hall/{hall}")
    public List<Librarian> findByHall(@PathVariable Long hall) {
        return service.findByHall(hall);
    }
}
