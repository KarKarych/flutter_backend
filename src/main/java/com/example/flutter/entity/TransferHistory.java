package com.example.flutter.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "transfer_history", schema = "public")
@Entity
public class TransferHistory {

    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private FlutterUser user;

    @NotNull
    @Column(name = "amount", nullable = false)
    private Integer amount;

    @NotNull
    @Column(name = "datetime", nullable = false)
    private OffsetDateTime datetime;

    @NotNull
    @Column(name = "operation_type", nullable = false)
    private Short operationType;

    @NotNull
    @Column(name = "target", nullable = false)
    private String target;
}