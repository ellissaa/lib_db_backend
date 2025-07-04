package org.example.database_lib.controller;

import org.example.database_lib.model.Resident;
import org.example.database_lib.model.Worker;
import org.example.database_lib.service.AbstractService;
import org.example.database_lib.service.WorkerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin()
@RequestMapping("/api/workers")
public class WorkerController extends AbstractController<Worker, Long> {
    private final WorkerService service;

    protected WorkerController(WorkerService service) {
        super(service);
        this.service = service;
    }

    @GetMapping("/{organization}")
    public List<Worker> findByOrganization(@PathVariable String organization) {
        return service.findByOrganization(organization);
    }
}
