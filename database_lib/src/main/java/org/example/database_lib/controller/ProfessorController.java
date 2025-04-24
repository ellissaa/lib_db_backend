package org.example.database_lib.controller;

import org.example.database_lib.model.Professor;
import org.example.database_lib.service.AbstractService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/professors")
public class ProfessorController extends AbstractController<Professor> {
    protected ProfessorController(AbstractService<Professor> service) {
        super(service);
    }
}