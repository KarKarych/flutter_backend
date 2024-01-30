package com.example.flutter.mapper;

import com.example.flutter.entity.Bucket;
import com.example.flutter.entity.Order;
import com.example.flutter.entity.OrderProduct;
import com.example.flutter.model.get.OrderProductModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(uses = {
        ProductMapper.class
})
public interface OrderProductMapper {

    @Mapping(target = "size", source = "order.id.size")
    OrderProductModel toModel(OrderProduct order);

    default List<OrderProduct> toEntities(Order order, List<Bucket> buckets) {
        return buckets.stream()
                .map(bucket -> OrderProduct.of(order, bucket))
                .toList();
    }
}
