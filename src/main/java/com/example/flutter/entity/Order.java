package com.example.flutter.entity;

import com.example.flutter.entity.enumeration.OrderType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static jakarta.persistence.CascadeType.PERSIST;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "\"order\"", schema = "public")
@Entity
public class Order {

    @Id
    @UuidGenerator
    @Column(name = "id", nullable = false)
    private UUID id;

    @NotNull
    @Column(name = "status", nullable = false)
    private OrderType status;

    @NotNull
    @CreatedDate
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @OneToMany(mappedBy = "order", cascade = PERSIST)
    private Set<OrderProduct> products;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private FlutterUser user;

    public void addProducts(List<OrderProduct> orderProductsTransient) {
        if (products == null) {
            products = new LinkedHashSet<>();
        }
        products.addAll(orderProductsTransient);
    }
}