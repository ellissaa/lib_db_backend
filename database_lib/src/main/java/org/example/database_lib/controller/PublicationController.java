package org.example.database_lib.controller;

import org.example.database_lib.model.Publication;
import org.example.database_lib.service.AbstractService;
import org.example.database_lib.service.PublicationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin()
@RequestMapping("/api/publications")
public class PublicationController extends AbstractController<Publication, Long> {
    private final PublicationService service;

    protected PublicationController(PublicationService service) {
        super(service);
        this.service = service;
    }

    @GetMapping("/reader-reg")
    public List<Publication> findByReaderReg(
            @RequestParam String start, @RequestParam String end, @RequestParam Long readerId) {
        return service.findByReaderReg(start, end, readerId);
    }

    @GetMapping("/reader-not-reg")
    public List<Publication> findByReaderNotReg(
            @RequestParam String start, @RequestParam String end, @RequestParam Long readerId) {
        return service.findByReaderNotReg(start, end, readerId);
    }

    @GetMapping("/loaned")
    public List<Publication> findByShelf(
            @RequestParam Long lib, @RequestParam Long hall,
            @RequestParam Long rack, @RequestParam Long shelf) {
        return service.findByShelf(lib, hall, rack, shelf);
    }

    @GetMapping("/work/{work}")
    public List<Publication> findByWork(@PathVariable String work) {
        return service.findByWork(work);
    }

    @GetMapping("/author/{author}")
    public List<Publication> findByAuthor(@PathVariable Long author) {
        return service.findByAuthor(author);
    }
}
