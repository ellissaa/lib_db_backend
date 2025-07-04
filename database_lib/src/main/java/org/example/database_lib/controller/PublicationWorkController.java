package org.example.database_lib.controller;

import org.example.database_lib.model.PublicationWork;
import org.example.database_lib.model.PublicationWorkId;
import org.example.database_lib.service.AbstractService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin()
@RequestMapping("/api/publication_work")
public class PublicationWorkController extends AbstractController<PublicationWork, PublicationWorkId> {
    protected PublicationWorkController(AbstractService<PublicationWork, PublicationWorkId> service) {
        super(service);
    }

    @DeleteMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public int delete(@RequestParam Long publicationId, @RequestParam Long workId) {
        return service.delete(new PublicationWorkId(publicationId, workId));
    }
}
