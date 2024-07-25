package io.jum8.e_commerce_cart.domain;

import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.Set;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(
            name = "primary_sequence",
            sequenceName = "customer_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "customer_sequence"
    )
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String email;

    @Column
    private Boolean vip;

    @OneToMany(mappedBy = "customer")
    private Set<Cart> carts;

    @OneToMany(mappedBy = "customer")
    private Set<Order> orders;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

    @Column(nullable = false)
    private OffsetDateTime vipStatusChangedAt;

    @Transient
    private boolean previousVipStatus;

    @PrePersist
    public void createVipStatusTimestamp() {
       this.vipStatusChangedAt = OffsetDateTime.now();
       this.previousVipStatus = this.isVip();
    }

    @PreUpdate
    public void updateVipStatusTimestamp() {
        if(this.isVip() != this.previousVipStatus) {
            this.vipStatusChangedAt = OffsetDateTime.now();
            this.previousVipStatus = this.isVip();
        }
    }

    @PostLoad
    public void storePreviousVipStatus() {
        this.previousVipStatus = this.isVip();
    }

    public boolean isVip() {
        return vip;
    }
}
