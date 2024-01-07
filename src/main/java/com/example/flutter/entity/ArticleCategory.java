package com.example.flutter.entity;

import com.example.flutter.entity.composite.ArticleCategoryId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "article_category", schema = "public")
@Entity
public class ArticleCategory {

    @EmbeddedId
    private ArticleCategoryId id;

    @MapsId("articleId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "article_id", nullable = false)
    private Article article;
}