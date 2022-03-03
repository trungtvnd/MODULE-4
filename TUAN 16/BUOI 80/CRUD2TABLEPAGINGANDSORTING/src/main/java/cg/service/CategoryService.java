package cg.service;

import cg.model.Category;
import cg.repository.IRepositoryCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CategoryService implements ICategoryService{
    @Autowired
    private IRepositoryCategory repository;

    @Override
    public Iterable<Category> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Category> findOne(int id) {
        return repository.findById(id);
    }
}
