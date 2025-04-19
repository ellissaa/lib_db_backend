package org.example.database_lib.service;

import jakarta.transaction.Transactional;
import org.example.database_lib.exception.ResourceNotFoundException;
import org.example.database_lib.model.*;
import org.example.database_lib.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class StudentService extends ReaderService {
    public StudentService(ReaderRepository readerRepository,
                          LibraryRepository libraryRepository) {
        super(readerRepository, libraryRepository);
    }

    public Student create(Student student, Long libraryId) {
        return (Student) createBaseReader(student, libraryId);
    }

    public Student getById(Long id) {
        return (Student) readerRepository.findStudentById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
    }

    public List<Student> getAll() {
        return readerRepository.findAllStudents();
    }

    public Student update(Long id, Student studentDetails) {
        Student student = getById(id);
        student.setName(studentDetails.getName());
        student.setSurname(studentDetails.getSurname());
        student.setPatronymic(studentDetails.getPatronymic());
        student.setBirthDate(studentDetails.getBirthDate());
        student.setAddress(studentDetails.getAddress());
        student.setPhone(studentDetails.getPhone());
        student.setUniversity(studentDetails.getUniversity());
        student.setFaculty(studentDetails.getFaculty());
        student.setStudentGroup(studentDetails.getStudentGroup());
        return (Student) readerRepository.save(student);
    }

    public List<Student> getByUniversity(String university) {
        return readerRepository.findStudentsByUniversity(university);
    }
}
