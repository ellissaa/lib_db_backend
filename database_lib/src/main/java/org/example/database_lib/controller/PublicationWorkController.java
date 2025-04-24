package org.example.database_lib.controller;

import org.example.database_lib.model.PublicationWork;
import org.example.database_lib.service.AbstractService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/publication_work")
public class PublicationWorkController extends AbstractController<PublicationWork> {
    protected PublicationWorkController(AbstractService<PublicationWork> service) {
        super(service);
    }
}