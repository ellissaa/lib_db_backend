package org.example.database_lib.controller;

import org.example.database_lib.model.Resident;
import org.example.database_lib.model.Student;
import org.example.database_lib.service.AbstractService;
import org.example.database_lib.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin()
@RequestMapping("/api/students")
public class StudentController extends AbstractController<Student, Long> {
    private final StudentService service;

    protected StudentController(StudentService service) {
        super(service);
        this.service = service;
    }

    @GetMapping("/{university}")
    public List<Student> findByUniversity(@PathVariable String university) {
        return service.findByUniversity(university);
    }
}
