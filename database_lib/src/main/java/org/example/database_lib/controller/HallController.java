package org.example.database_lib.controller;

import org.example.database_lib.model.Hall;
import org.example.database_lib.service.AbstractService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin()
@RequestMapping("/api/halls")
public class HallController extends AbstractController<Hall, Long> {
    protected HallController(AbstractService<Hall, Long> service) {
        super(service);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public int delete(@PathVariable Long id) {
//        System.out.println(id);
        int ret = super.delete(id);
        System.out.println(ret);
        return ret;
    }
}