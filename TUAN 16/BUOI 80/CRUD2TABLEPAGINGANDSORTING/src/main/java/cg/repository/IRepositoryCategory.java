package cg.repository;

import cg.model.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepositoryCategory extends CrudRepository<Category, Integer> {

}
