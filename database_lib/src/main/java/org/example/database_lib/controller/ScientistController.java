package org.example.database_lib.controller;

import org.example.database_lib.model.Scientist;
import org.example.database_lib.service.AbstractService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/scientists")
public class ScientistController extends AbstractController<Scientist> {
    protected ScientistController(AbstractService<Scientist> service) {
        super(service);
    }
}