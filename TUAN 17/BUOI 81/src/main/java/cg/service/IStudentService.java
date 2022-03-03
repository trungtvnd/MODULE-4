package cg.service;

import cg.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IStudentService {
    Page<Student> findAll(Pageable pageable);
    Page<Student> findAllByName(String name, Pageable pageable);
    Student save(Student student);
    void delete(int id);
    Student findOne(int id);
}
