package org.example.database_lib.controller;

import org.example.database_lib.model.Schoolchild;
import org.example.database_lib.service.AbstractService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/schoolchildren")
public class SchoolchildController extends AbstractController<Schoolchild> {
    protected SchoolchildController(AbstractService<Schoolchild> service) {
        super(service);
    }
}