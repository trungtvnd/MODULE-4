package com.codegym.vn.democrud2tableangularsecurity.repository.product;


import com.codegym.vn.democrud2tableangularsecurity.model.product.Category;
import org.springframework.data.repository.CrudRepository;

public interface ICategoryRepository extends CrudRepository<Category, Long> {

}
