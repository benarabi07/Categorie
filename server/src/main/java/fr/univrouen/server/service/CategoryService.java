package fr.univrouen.server.service;

import fr.univrouen.server.entity.Category;
import fr.univrouen.server.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
     @Transactional
     public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public boolean parentExists(Long parentId) {
        return categoryRepository.existsById(parentId);
    }
    @Transactional
    public Category addChildToParent(Long parentId, Category childCategory) {
        Category parentCategory = categoryRepository.findById(parentId)
                .orElseThrow(() -> new IllegalArgumentException("Parent category not found"));
        parentCategory.getChildren().add(childCategory);
        childCategory.setParent(parentCategory);
        categoryRepository.save(childCategory);
        return categoryRepository.save(parentCategory);
    }
}
