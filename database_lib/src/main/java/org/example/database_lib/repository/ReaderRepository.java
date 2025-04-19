package org.example.database_lib.repository;

import org.example.database_lib.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReaderRepository extends JpaRepository<Reader, Long> {
    @Query("SELECT s FROM Student s WHERE s.id = :id")
    Optional<Student> findStudentById(@Param("id") Long id);

    @Query("SELECT s FROM Student s")
    List<Student> findAllStudents();

    @Query("SELECT s FROM Student s WHERE s.university = :university")
    List<Student> findStudentsByUniversity(@Param("university") String university);

    @Query("SELECT s FROM Scientist s WHERE s.id = :id")
    Optional<Scientist> findScientistById(@Param("id") Long id);

    @Query("SELECT s FROM Scientist s")
    List<Scientist> findAllScientists();

    @Query("SELECT s FROM Scientist s WHERE s.academicDegree = :degree")
    List<Scientist> findScientistsByAcademicDegree(@Param("degree") String degree);

    @Query("SELECT r FROM Resident r WHERE r.id = :id")
    Optional<Resident> findResidentById(@Param("id") Long id);

    @Query("SELECT r FROM Resident r")
    List<Resident> findAllResidents();

    @Query("SELECT r FROM Retiree r WHERE r.id = :id")
    Optional<Retiree> findRetireeById(@Param("id") Long id);

    @Query("SELECT r FROM Retiree r")
    List<Retiree> findAllRetirees();

    @Query("SELECT r FROM Retiree r WHERE r.has_benefits = :hasBenefits")
    List<Retiree> findRetireesByHasBenefits(@Param("hasBenefits") boolean hasBenefits);

    @Query("SELECT s FROM Schoolchild s WHERE s.id = :id")
    Optional<Schoolchild> findSchoolchildById(@Param("id") Long id);

    @Query("SELECT s FROM Schoolchild s")
    List<Schoolchild> findAllSchoolchildren();

    @Query("SELECT s FROM Schoolchild s WHERE s.school = :school AND s.grade = :grade")
    List<Schoolchild> findSchoolchildrenBySchoolAndGrade(
            @Param("school") String school,
            @Param("grade") String grade);

    @Query("SELECT w FROM Worker w WHERE w.id = :id")
    Optional<Worker> findWorkerById(@Param("id") Long id);

    @Query("SELECT w FROM Worker w")
    List<Worker> findAllWorkers();

    @Query("SELECT w FROM Worker w WHERE w.profession = :profession")
    List<Worker> findWorkersByProfession(@Param("profession") String profession);

    @Query("SELECT w FROM Worker w WHERE w.organization = :organization")
    List<Worker> findWorkersByOrganization(@Param("organization") String organization);

    @Query("SELECT p FROM Professor p WHERE p.university = :university")
    List<Professor> findProfessorsByUniversity(String university);

    @Query("SELECT p FROM Professor p WHERE p.department = :department")
    List<Professor> findProfessorsByDepartment(String department);

    @Query("SELECT p FROM Professor p WHERE p.id = :id")
    Optional<Object> findProfessorById(Long id);

    @Query("SELECT p FROM Professor p")
    List<Professor> findAllProfessors();
}
