package com.example.flutter.mapper;

import com.example.flutter.entity.Bucket;
import com.example.flutter.entity.FlutterUser;
import com.example.flutter.entity.Product;
import com.example.flutter.model.get.OrderProductModel;
import com.example.flutter.model.update.BucketProductModel;
import org.mapstruct.Mapper;

import java.util.Optional;

import static com.example.flutter.util.exception.NotFoundException.Code.PRODUCT_NOT_FOUND;

@Mapper(uses = {
        ProductMapper.class
})
public interface BucketMapper {

    OrderProductModel toModel(Bucket bucket);

    default Bucket toEntity(BucketProductModel model, Optional<Product> productOptional, FlutterUser user) {
        var product = productOptional
                .orElseThrow(() -> PRODUCT_NOT_FOUND.get(user.getLogin(), "productId = %s".formatted(model.productId())));
        return new Bucket(product, user, model.size(), model.amount());
    }
}
