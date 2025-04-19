package org.example.database_lib.controller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/libraries")
public class LibraryController {
    @Autowired
    private LibraryService libraryService;

    // CRUD endpoints
}