package com.example.flutter.entity;

import com.example.flutter.entity.composite.WishlistId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "wishlist", schema = "public")
@Entity
public class Wishlist {

    @EmbeddedId
    private WishlistId id;

    @MapsId("productId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private FlutterUser user;

    public Wishlist(Product product, FlutterUser user) {
        this.id = new WishlistId(product.getId(), user.getId());
        this.product = product;
        this.user = user;
    }
}