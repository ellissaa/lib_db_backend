package org.example.database_lib.controller;

import org.example.database_lib.model.Resident;
import org.example.database_lib.model.Retiree;
import org.example.database_lib.service.AbstractService;
import org.example.database_lib.service.RetireeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin()
@RequestMapping("/api/retirees")
public class RetireeController extends AbstractController<Retiree, Long> {
    private final RetireeService service;

    protected RetireeController(RetireeService service) {
        super(service);
        this.service = service;
    }

    @GetMapping("/{benefits}")
    public List<Retiree> findByBenefits(@PathVariable Boolean benefits) {
        return service.findByBenefits(benefits);
    }
}
