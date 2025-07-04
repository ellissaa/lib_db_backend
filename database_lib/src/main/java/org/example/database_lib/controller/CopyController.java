package org.example.database_lib.controller;

import org.example.database_lib.model.Copy;
import org.example.database_lib.service.AbstractService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin()
@RequestMapping("/api/copies")
public class CopyController extends AbstractController<Copy, Long> {
    protected CopyController(AbstractService<Copy, Long> service) {
        super(service);
    }
}