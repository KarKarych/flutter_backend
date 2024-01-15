package com.example.flutter.entity;

import com.example.flutter.entity.composite.OrderProductId;
import com.example.flutter.entity.enumeration.SizeType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "order_product", schema = "public")
@Entity
public class OrderProduct {

    @EmbeddedId
    private OrderProductId id;

    @MapsId("orderId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @MapsId("productId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @NotNull
    @Column(name = "size", nullable = false)
    private SizeType size;

    @NotNull
    @Column(name = "amount", nullable = false)
    private Integer amount;

    @NotNull
    @CreatedDate
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    public static OrderProduct fromBucket(Order order, Bucket bucket) {
        return new OrderProduct(order, bucket.getProduct(), bucket.getId().getSize(), bucket.getAmount());
    }

    public OrderProduct(Order order, Product product, SizeType size, Integer amount) {
        this.id = new OrderProductId(order.getId(), product.getId());
        this.order = order;
        this.product = product;
        this.size = size;
        this.amount = amount;
    }

    public Integer getFullPrice() {
        return amount * product.getPrice();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderProduct that = (OrderProduct) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}