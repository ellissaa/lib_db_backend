package org.example.database_lib.controller;

import org.example.database_lib.model.Work;
import org.example.database_lib.service.AbstractService;
import org.example.database_lib.service.WorkService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin()
@RequestMapping("/api/works")
public class WorkController extends AbstractController<Work, Long> {
    private final WorkService service;

    protected WorkController(WorkService service) {
        super(service);
        this.service = service;
    }

    @GetMapping("popular")
    public List<Work> findMostPopular() {
        return service.findMostPopular();
    }
}
