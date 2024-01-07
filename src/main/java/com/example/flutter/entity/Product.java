package com.example.flutter.entity;

import com.example.flutter.entity.enumeration.ProductCategoryType;
import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "product", schema = "public")
@Entity
public class Product {

    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "description", nullable = false)
    private String description;

    @NotNull
    @Type(value = ListArrayType.class)
    @Column(name = "picture_urls", nullable = false)
    private List<String> pictureUrls;

    @NotNull
    @Column(name = "price", nullable = false)
    private Integer price;

    @NotNull
    @Column(name = "rating_percent", nullable = false)
    private Integer ratingPercent;

    @NotNull
    @Column(name = "rating_number", nullable = false)
    private Integer ratingNumber;

    @NotNull
    @Column(name = "category", nullable = false)
    private ProductCategoryType category;

    @NotNull
    @Type(value = ListArrayType.class)
    @Column(name = "sizes", nullable = false)
    private List<Integer> sizes;

    public Product(UUID id) {
        this.id = id;
    }
}