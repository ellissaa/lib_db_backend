package org.example.database_lib.controller;

import org.example.database_lib.model.PublicationType;
import org.example.database_lib.service.AbstractService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin()
@RequestMapping("/api/publication_types")
public class PublicationTypeController extends AbstractController<PublicationType, Long> {
    protected PublicationTypeController(AbstractService<PublicationType, Long> service) {
        super(service);
    }
}