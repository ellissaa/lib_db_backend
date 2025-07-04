package org.example.database_lib.controller;

import org.example.database_lib.model.Resident;
import org.example.database_lib.service.AbstractService;
import org.example.database_lib.service.ResidentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin()
@RequestMapping("/api/residents")
public class ResidentController extends AbstractController<Resident, Long> {
    private final ResidentService service;

    protected ResidentController(ResidentService service) {
        super(service);
        this.service = service;
    }

    @GetMapping("/{occupation}")
    public List<Resident> findByOccupation(@PathVariable String occupation) {
        return service.findByOccupation(occupation);
    }
}
