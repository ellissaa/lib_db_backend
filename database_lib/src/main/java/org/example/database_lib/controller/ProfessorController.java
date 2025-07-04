package org.example.database_lib.controller;

import org.example.database_lib.model.Professor;
import org.example.database_lib.model.Resident;
import org.example.database_lib.service.AbstractService;
import org.example.database_lib.service.ProfessorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin()
@RequestMapping("/api/professors")
public class ProfessorController extends AbstractController<Professor, Long> {
    private final ProfessorService service;

    protected ProfessorController(ProfessorService service) {
        super(service);
        this.service = service;
    }

    @GetMapping("/{university}")
    public List<Professor> findByUniversity(@PathVariable String university) {
        return service.findByUniversity(university);
    }
}
