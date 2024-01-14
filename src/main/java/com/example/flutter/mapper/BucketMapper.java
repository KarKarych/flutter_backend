package com.example.flutter.mapper;

import com.example.flutter.entity.Bucket;
import com.example.flutter.entity.FlutterUser;
import com.example.flutter.entity.Product;
import com.example.flutter.model.get.OrderProductModel;
import com.example.flutter.model.update.BucketProductModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Optional;

import static com.example.flutter.util.exception.NotFoundException.Code.PRODUCT_NOT_FOUND;
import static com.example.flutter.util.exception.NotFoundException.Code.SIZE_IN_PRODUCT_NOT_FOUND;

@Mapper(uses = {
        ProductMapper.class
})
public interface BucketMapper {

    @Mapping(target = "size", source = "id.size")
    OrderProductModel toModel(Bucket bucket);

    default Bucket toEntity(BucketProductModel model, Optional<Product> productOptional, FlutterUser user) {
        var sizeRequest = model.size();
        var productIdRequest = model.productId();

        var product = productOptional
                .orElseThrow(() -> PRODUCT_NOT_FOUND.get(user.getLogin(), "productId = %s".formatted(productIdRequest)));

        if (!product.getSizes().contains(sizeRequest.getIntId())) {
            throw SIZE_IN_PRODUCT_NOT_FOUND.get(
                    user.getLogin(),
                    "productId = %s; size = %s".formatted(productIdRequest, sizeRequest)
            );
        }

        return new Bucket(product, user, sizeRequest, model.amount());
    }
}
