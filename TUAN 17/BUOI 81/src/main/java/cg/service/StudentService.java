package cg.service;

import cg.model.Student;
import cg.repository.IStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class StudentService implements IStudentService{

    @Autowired
    private IStudentRepository repository;

    @Override
    public Page<Student> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Page<Student> findAllByName(String name, Pageable pageable) {
        return repository.findAllByNameContaining(name, pageable);
    }

    @Override
    public Student save(Student student) {
        return repository.save(student);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

    @Override
    public Student findOne(int id) {
        if(repository.findById(id).isPresent()){
            return repository.findById(id).get();
        }return null;
    }
}
