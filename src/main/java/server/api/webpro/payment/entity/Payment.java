package server.api.webpro.payment.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "Payment")
public class Payment {
    @Id
    @Column(name = "payment_id")
    private Long id;

    @Column(name = "amount", nullable = false)
    private Integer amount;
    @Column(name = "imp_uid", nullable = false)
    private String imp_uid;
}
