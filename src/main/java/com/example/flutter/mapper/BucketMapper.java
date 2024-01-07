package com.example.flutter.mapper;

import com.example.flutter.entity.Bucket;
import com.example.flutter.entity.FlutterUser;
import com.example.flutter.entity.Product;
import org.mapstruct.Mapper;

@Mapper
public interface BucketMapper {

    default Bucket toEntity(Product product, FlutterUser user) {
        return new Bucket(product, user);
    }
}
