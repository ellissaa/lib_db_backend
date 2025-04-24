package org.example.database_lib.controller;

import org.example.database_lib.model.Retiree;
import org.example.database_lib.service.AbstractService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/retirees")
public class RetireeController extends AbstractController<Retiree> {
    protected RetireeController(AbstractService<Retiree> service) {
        super(service);
    }
}