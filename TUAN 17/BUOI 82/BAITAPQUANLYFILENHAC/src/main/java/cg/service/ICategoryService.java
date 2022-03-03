package cg.service;

import cg.model.Category;
import cg.model.Song;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Iterator;
import java.util.Optional;

public interface ICategoryService {
    Iterable<Category> findAll();
//    Iterable<Category> findAllByName(String name);
    void deleteById(int id);
    Optional<Category> findById(int id);
    void save(Category category);
}
