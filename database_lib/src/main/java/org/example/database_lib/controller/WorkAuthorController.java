package org.example.database_lib.controller;

import org.example.database_lib.model.WorkAuthor;
import org.example.database_lib.model.WorkAuthorId;
import org.example.database_lib.service.AbstractService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin()
@RequestMapping("/api/work_author")
public class WorkAuthorController extends AbstractController<WorkAuthor, WorkAuthorId> {
    protected WorkAuthorController(AbstractService<WorkAuthor, WorkAuthorId> service) {
        super(service);
    }

    @DeleteMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public int delete(@RequestParam Long workId, @RequestParam Long authorId) {
        return service.delete(new WorkAuthorId(workId, authorId));
    }
}
