package org.example.database_lib.controller;

import org.example.database_lib.model.Resident;
import org.example.database_lib.model.Scientist;
import org.example.database_lib.service.AbstractService;
import org.example.database_lib.service.ResidentService;
import org.example.database_lib.service.ScientistService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin()
@RequestMapping("/api/scientists")
public class ScientistController extends AbstractController<Scientist, Long> {
    private final ScientistService service;

    protected ScientistController(ScientistService service) {
        super(service);
        this.service = service;
    }

    @GetMapping("/{workplace}")
    public List<Scientist> findByWorkplace(@PathVariable String workplace) {
        return service.findByWorkplace(workplace);
    }
}
