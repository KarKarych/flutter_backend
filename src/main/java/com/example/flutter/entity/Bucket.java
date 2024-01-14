package com.example.flutter.entity;

import com.example.flutter.entity.composite.BucketId;
import com.example.flutter.entity.enumeration.SizeType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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

    @NotNull
    @Column(name = "amount", nullable = false)
    private Integer amount;

    public Bucket(Product product, FlutterUser user, SizeType size, Integer amount) {
        this.id = new BucketId(product.getId(), user.getId(), size);
        this.product = product;
        this.user = user;
        this.amount = amount;
    }
}