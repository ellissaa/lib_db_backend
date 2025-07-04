package org.example.database_lib.controller;

import org.example.database_lib.model.Library;
import org.example.database_lib.service.AbstractService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin()
@RequestMapping("/api/libraries")
public class LibraryController extends AbstractController<Library, Long> {
    protected LibraryController(AbstractService<Library, Long> service) {
        super(service);
    }
}