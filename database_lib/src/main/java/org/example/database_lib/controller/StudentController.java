package org.example.database_lib.controller;

import org.example.database_lib.model.Student;
import org.example.database_lib.service.AbstractService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
public class StudentController extends AbstractController<Student> {
    protected StudentController(AbstractService<Student> service) {
        super(service);
    }
}