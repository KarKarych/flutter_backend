package com.example.flutter.repository.specification;

import com.example.flutter.entity.Product;
import com.example.flutter.entity.enumeration.ProductCategoryType;
import com.example.flutter.model.filter.ProductFilter;
import jakarta.persistence.criteria.*;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;

import static com.example.flutter.util.Constants.SEARCH_PATTERN;


@AllArgsConstructor
public class ProductSpecification implements Specification<Product> {

    private final ProductFilter filterRequest;

    @Override
    public Predicate toPredicate(
            @NonNull Root<Product> product,
            @NonNull CriteriaQuery<?> query,
            @NonNull CriteriaBuilder builder
    ) {
        query.orderBy(getSortingConditions(product, builder));

        List<Predicate> filteringConditions = getFilteringConditions(product, builder);
        if (filteringConditions.isEmpty()) return null;

        return builder.and(filteringConditions.toArray(new Predicate[0]));
    }

    private List<Order> getSortingConditions(Root<Product> product, CriteriaBuilder builder) {
        List<Order> orders = new ArrayList<>();

        Path<List<String>> name = product.get("name");

        orders.add(builder.desc(getPictureUrlsLength(product, builder)));
        orders.add(builder.asc(name));

        return orders;
    }

    private List<Predicate> getFilteringConditions(Root<Product> product, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();

        String searchQuery = filterRequest.searchQuery();
        if (searchQuery != null) {
            Path<String> name = product.get("name");
            Path<String> description = product.get("description");
            predicates.add(builder.or(
                    ilike(name, searchQuery, builder),
                    ilike(description, searchQuery, builder)
            ));
        }

        List<ProductCategoryType> categories = filterRequest.categories();
        if (categories != null && !categories.isEmpty()) {
            Path<ProductCategoryType> category = product.get("category");
            predicates.add(category.in(categories));
        }

        return predicates;
    }

    private Predicate ilike(Path<String> column, String searchQuery, CriteriaBuilder builder) {
        Expression<Boolean> ilikeFunction = builder.function(
                "texticlike",
                Boolean.class,
                column,
                builder.literal(SEARCH_PATTERN.formatted(searchQuery))
        );
        return builder.isTrue(ilikeFunction);
    }

    private Expression<Integer> getPictureUrlsLength(Root<Product> product, CriteriaBuilder builder) {
        return builder.function(
                "array_length",
                Integer.class,
                product.get("pictureUrls"),
                builder.literal(1)
        );
    }
}