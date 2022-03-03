package cg.service;

import cg.model.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> selectAll();
    Category selectById(int id);
    Category save(Category category);
    void delete(int id);
}
