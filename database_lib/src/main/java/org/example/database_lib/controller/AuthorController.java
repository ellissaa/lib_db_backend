package org.example.database_lib.controller;

import org.example.database_lib.model.Author;
import org.example.database_lib.service.AbstractService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin()
@RequestMapping("/api/authors")
public class AuthorController extends AbstractController<Author, Long> {
    protected AuthorController(AbstractService<Author, Long> service) {
        super(service);
    }
}