package org.example.database_lib.controller;

import org.example.database_lib.model.Author;
import org.example.database_lib.service.AbstractService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/authors")
public class AuthorController extends AbstractController<Author> {
    protected AuthorController(AbstractService<Author> service) {
        super(service);
    }
}