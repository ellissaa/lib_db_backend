package org.example.database_lib.service;

import org.example.database_lib.model.Student;
import org.example.database_lib.repository.DaoInterface;
import org.springframework.stereotype.Service;

@Service
public class StudentService extends AbstractService<Student> {
    public StudentService(DaoInterface<Student> dao) {
        super(dao);
    }
}