package com.example.flutter.entity;

import com.example.flutter.entity.composite.WishlistId;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)

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

    @NotNull
    @CreatedDate
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    public Wishlist(Product product, FlutterUser user) {
        this.id = new WishlistId(product.getId(), user.getId());
        this.product = product;
        this.user = user;
    }
}