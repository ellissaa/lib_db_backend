package org.example.database_lib.controller;

import org.example.database_lib.model.Publication;
import org.example.database_lib.service.AbstractService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/publications")
public class PublicationController extends AbstractController<Publication> {
    protected PublicationController(AbstractService<Publication> service) {
        super(service);
    }
}