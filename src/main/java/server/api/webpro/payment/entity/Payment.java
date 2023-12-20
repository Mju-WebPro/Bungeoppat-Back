package server.api.webpro.payment.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "Payment")
public class Payment {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "payment_id")
    private Long id;

    @Column(name = "amount", nullable = false)
    private Integer amount;

    @Builder
    public Payment(Integer amount){
        this.amount = amount;
    }
}
