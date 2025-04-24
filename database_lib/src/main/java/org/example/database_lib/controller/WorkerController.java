package org.example.database_lib.controller;

import org.example.database_lib.model.Worker;
import org.example.database_lib.service.AbstractService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/workers")
public class WorkerController extends AbstractController<Worker> {
    protected WorkerController(AbstractService<Worker> service) {
        super(service);
    }
}