package org.example.database_lib.controller;

import org.example.database_lib.model.Library;
import org.example.database_lib.service.AbstractService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/libraries")
public class LibraryController extends AbstractController<Library> {
    protected LibraryController(AbstractService<Library> service) {
        super(service);
    }
}