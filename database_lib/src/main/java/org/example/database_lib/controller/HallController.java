package org.example.database_lib.controller;

import org.example.database_lib.model.Hall;
import org.example.database_lib.service.AbstractService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/halls")
public class HallController extends AbstractController<Hall> {
    protected HallController(AbstractService<Hall> service) {
        super(service);
    }
}