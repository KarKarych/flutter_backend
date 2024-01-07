package com.example.flutter.repository;

import com.example.flutter.entity.Article;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ArticleRepository extends JpaRepository<Article, UUID> {

    @EntityGraph(attributePaths = {"articleCategories"})
    @Query("SELECT a FROM Article a")
    List<Article> findAllWithCategories();

    @EntityGraph(attributePaths = {"articleCategories"})
    @Query("""
            SELECT  a
             FROM   Article a
             WHERE  a.name ILIKE :searchQuery OR
                    a.text ILIKE :searchQuery
             ORDER BY a.createdAt DESC
            """)
    List<Article> findBySearchQuery(String searchQuery);
}