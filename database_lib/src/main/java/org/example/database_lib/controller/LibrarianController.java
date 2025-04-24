package org.example.database_lib.controller;

import org.example.database_lib.model.Librarian;
import org.example.database_lib.service.AbstractService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/librarians")
public class LibrarianController extends AbstractController<Librarian> {
    protected LibrarianController(AbstractService<Librarian> service) {
        super(service);
    }
}