package cg.repository;

import cg.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;



public interface ICategoryRepository extends CrudRepository<Category,Integer> {

 }
