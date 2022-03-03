package cg.service;

import cg.model.Category;
import cg.repository.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CategoryService implements ICategoryService{
    @Autowired
    private ICategoryRepository repository;
    @Override
    public Iterable<Category> findAll() {
        return repository.findAll();
    }

//    @Override
//    public Iterable<Category> findAllByName(String name) {
//        return null;
//    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Category> findById(int id) {
       return repository.findById(id);
    }

    @Override
    public void save(Category category) {
        repository.save(category);
    }
}
