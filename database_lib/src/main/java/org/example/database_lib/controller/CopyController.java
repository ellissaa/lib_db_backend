package org.example.database_lib.controller;

import org.example.database_lib.model.Copy;
import org.example.database_lib.service.AbstractService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/copies")
public class CopyController extends AbstractController<Copy> {
    protected CopyController(AbstractService<Copy> service) {
        super(service);
    }
}