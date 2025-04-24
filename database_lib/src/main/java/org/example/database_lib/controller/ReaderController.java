package org.example.database_lib.controller;

import org.example.database_lib.model.Reader;
import org.example.database_lib.service.AbstractService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/readers")
public class ReaderController extends AbstractController<Reader> {
    protected ReaderController(AbstractService<Reader> service) {
        super(service);
    }
}