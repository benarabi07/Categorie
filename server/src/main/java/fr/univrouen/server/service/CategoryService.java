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


}
