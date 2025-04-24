package org.example.database_lib.controller;

import org.example.database_lib.model.PublicationType;
import org.example.database_lib.service.AbstractService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/publication_types")
public class PublicationTypeController extends AbstractController<PublicationType> {
    protected PublicationTypeController(AbstractService<PublicationType> service) {
        super(service);
    }
}