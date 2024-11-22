package fr.univrouen.server.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.univrouen.server.entity.Category;
import fr.univrouen.server.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<String> createCategory(@RequestBody Category category) {
        try {
            categoryService.createCategory(category);
            return ResponseEntity.status(201).body("Catégorie créée avec succès.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Données invalides pour la catégorie : " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur est survenue lors de la création de la catégorie.");
        }
    }
    @PostMapping("/{parentId}/children")
    public ResponseEntity<String> addChildToParent(
            @PathVariable Long parentId,
            @RequestBody Category childCategory
    ) {
        try {

            if (!categoryService.parentExists(parentId)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La catégorie parente avec l'ID " + parentId + " n'existe pas.");
            }
            categoryService.addChildToParent(parentId, childCategory);
            return ResponseEntity.ok("Catégorie enfant ajoutée avec succès.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Données invalides : " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur est survenue lors de l'ajout de la catégorie enfant.");
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateCategory(
            @PathVariable Long id,
            @RequestBody Category request) {
        try {
        Category updatedCategory = categoryService.updateCategory(id, request.getName(), request.getParentId());
        return ResponseEntity.ok("Catégorie mise à jour avec succès : ");

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Données invalides : " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur est survenue lors de la mise à jour de la catégorie.");
        }
    }
}
