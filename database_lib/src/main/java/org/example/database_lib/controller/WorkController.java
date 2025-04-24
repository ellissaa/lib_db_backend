package org.example.database_lib.controller;

import org.example.database_lib.model.Work;
import org.example.database_lib.service.AbstractService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/works")
public class WorkController extends AbstractController<Work> {
    protected WorkController(AbstractService<Work> service) {
        super(service);
    }
}