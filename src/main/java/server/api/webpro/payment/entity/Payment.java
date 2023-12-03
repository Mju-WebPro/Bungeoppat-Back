package server.api.webpro.payment.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "Payment")
public class Payment {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "payment_id")
    private Long id;

    @Column(name = "amount", nullable = false)
    private Integer amount;
    @Column(name = "imp_uid", nullable = false)
    private String imp_uid;
}
