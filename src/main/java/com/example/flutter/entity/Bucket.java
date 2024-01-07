package com.example.flutter.entity;

import com.example.flutter.entity.composite.BucketId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "bucket", schema = "public")
@Entity
public class Bucket {

    @EmbeddedId
    private BucketId id;

    @MapsId("productId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private FlutterUser user;

    public Bucket(Product product, FlutterUser user) {
        this.id = new BucketId(product.getId(), user.getId());
        this.product = product;
        this.user = user;
    }
}