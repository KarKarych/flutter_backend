package com.example.flutter.mapper;

import com.example.flutter.entity.FlutterUser;
import com.example.flutter.entity.Product;
import com.example.flutter.entity.Wishlist;
import org.mapstruct.Mapper;

@Mapper
public interface WishlistMapper {

    default Wishlist toEntity(Product product, FlutterUser user) {
        return new Wishlist(product, user);
    }
}
