package org.example.database_lib.controller;

import org.example.database_lib.model.Resident;
import org.example.database_lib.model.Schoolchild;
import org.example.database_lib.service.AbstractService;
import org.example.database_lib.service.SchoolchildService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin()
@RequestMapping("/api/schoolchildren")
public class SchoolchildController extends AbstractController<Schoolchild, Long> {
    private final SchoolchildService service;

    protected SchoolchildController(SchoolchildService service) {
        super(service);
        this.service = service;
    }

    @GetMapping("/{school}")
    public List<Schoolchild> findBySchool(@PathVariable String school) {
        return service.findBySchool(school);
    }
}
