package org.example.database_lib.service;

import org.example.database_lib.model.Student;
import org.example.database_lib.repository.DaoInterface;
import org.example.database_lib.repository.StudentDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService extends AbstractService<Student, Long> {
    private final StudentDao dao;

    public StudentService(StudentDao dao) {
        super(dao);
        this.dao = dao;
    }

    public List<Student> findByUniversity(String university) {
        return dao.findByUniversity(university);
    }
}
