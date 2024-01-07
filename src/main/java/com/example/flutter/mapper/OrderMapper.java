package com.example.flutter.mapper;

import com.example.flutter.entity.Order;
import com.example.flutter.entity.Product;
import com.example.flutter.model.get.OrderModel;
import com.example.flutter.model.get.ProductModel;
import org.mapstruct.*;

import java.util.Comparator;
import java.util.Set;

@Mapper(uses = {
        ProductMapper.class
})
public interface OrderMapper {

    @Mapping(target = "amount", source = "products", qualifiedByName = "amountToModel")
    @Mapping(target = "status", source = "status.displayMessage")
    OrderModel toModel(Order order);

    @Named("amountToModel")
    default Integer generateAmountToModel(Set<Product> products) {
        return products.stream()
                .map(Product::getPrice)
                .reduce(0, Integer::sum);
    }

    @AfterMapping
    default void sortProductsByName(@MappingTarget OrderModel orderModel) {
        orderModel.products().sort(Comparator.comparing(ProductModel::name));
    }
}
