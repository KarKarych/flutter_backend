package com.example.flutter.mapper;

import com.example.flutter.entity.FlutterUser;
import com.example.flutter.entity.Order;
import com.example.flutter.entity.OrderProduct;
import com.example.flutter.entity.enumeration.OrderType;
import com.example.flutter.model.get.OrderModel;
import com.example.flutter.model.get.OrderProductModel;
import org.mapstruct.*;

import java.util.Comparator;
import java.util.Set;

@Mapper(uses = {
        OrderProductMapper.class
})
public interface OrderMapper {

    @Mapping(target = "amount", source = "products", qualifiedByName = "amountToModel")
    @Mapping(target = "status", source = "status.displayMessage")
    OrderModel toModel(Order order);

    @Named("amountToModel")
    default Integer generateAmountToModel(Set<OrderProduct> products) {
        return products.stream()
                .map(OrderProduct::getFullPrice)
                .reduce(0, Integer::sum);
    }

    @AfterMapping
    default void sortProductsByName(@MappingTarget OrderModel orderModel) {
        orderModel.products().sort(Comparator.comparing(OrderProductModel::productName));
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", source = "status")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "products", ignore = true)
    @Mapping(target = "user", source = "user")
    Order toEntity(OrderType status, FlutterUser user);
}
