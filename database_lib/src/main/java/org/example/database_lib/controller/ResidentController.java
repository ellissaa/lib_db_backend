package org.example.database_lib.controller;

import org.example.database_lib.model.Resident;
import org.example.database_lib.service.AbstractService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/residents")
public class ResidentController extends AbstractController<Resident> {
    protected ResidentController(AbstractService<Resident> service) {
        super(service);
    }
}