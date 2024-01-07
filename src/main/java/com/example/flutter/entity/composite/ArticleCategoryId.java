package com.example.flutter.entity.composite;

import com.example.flutter.entity.enumeration.ArticleCategoryType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class ArticleCategoryId implements Serializable {

    @NotNull
    @Column(name = "article_id", nullable = false)
    private UUID articleId;

    @NotNull
    @Column(name = "category_id", nullable = false)
    private ArticleCategoryType category;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ArticleCategoryId entity = (ArticleCategoryId) o;
        return Objects.equals(this.articleId, entity.articleId) &&
                Objects.equals(this.category, entity.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(articleId, category);
    }
}