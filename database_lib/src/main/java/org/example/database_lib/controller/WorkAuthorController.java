package org.example.database_lib.controller;

import org.example.database_lib.model.WorkAuthor;
import org.example.database_lib.service.AbstractService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/work_author")
public class WorkAuthorController extends AbstractController<WorkAuthor> {
    protected WorkAuthorController(AbstractService<WorkAuthor> service) {
        super(service);
    }
}