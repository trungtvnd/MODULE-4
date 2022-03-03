package cg.formatter;

import cg.model.Category;
import cg.repository.ICategoryRepository;
import cg.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class CategoryFormatter implements Formatter<Category> {
    private final ICategoryService iCategoryService;
    @Autowired
    public CategoryFormatter (ICategoryService iCategoryService){
        this.iCategoryService = iCategoryService;
    }

    @Override
    public Category parse(String text, Locale locale) throws ParseException {
        return iCategoryService.selectById(Integer.parseInt(text));
    }

    @Override
    public String print(Category object, Locale locale) {
        return"[" + object.getId() + ", " +object.getName() + "]";

    }
}
