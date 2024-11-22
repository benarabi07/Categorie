package fr.univrouen.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.univrouen.server.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}