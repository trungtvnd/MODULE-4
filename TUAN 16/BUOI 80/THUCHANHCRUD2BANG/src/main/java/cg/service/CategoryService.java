package cg.service;

import cg.model.Category;
import cg.repository.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryService implements ICategoryService{
    @Autowired
    private ICategoryRepository repository;
    @Override
    public List<Category> selectAll() {
        return (List<Category>) repository.findAll();
    }

    @Override
    public Category selectById(int id) {
        if(repository.findById(id).isPresent()){
            return  repository.findById(id).get();
        }return null;
    }

    @Override
    public Category save(Category category) {
        return repository.save(category);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }
}
