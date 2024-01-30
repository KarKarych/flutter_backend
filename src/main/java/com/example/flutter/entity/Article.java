package com.example.flutter.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "article", schema = "public")
@Entity
public class Article {

    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "text", nullable = false)
    private String text;

    @NotNull
    @Column(name = "picture_url", nullable = false)
    private String pictureUrl;

    @NotNull
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @OneToMany(mappedBy = "article")
    private Set<ArticleCategory> articleCategories = new LinkedHashSet<>();
}