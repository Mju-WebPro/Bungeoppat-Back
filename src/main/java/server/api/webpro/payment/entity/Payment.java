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
    @Column(name = "paid_amount", nullable = false)
    private Long paidAmount;
    @Column(name = "pay_method", nullable = false)
    private String payMethod;
    @Column(name = "status", nullable = false)
    private String status;
    @Column(name = "imp_uid", nullable = false)
    private String impUid;

    @Builder
    public Payment(Long paidAmount, String payMethod, String status, String impUid){
        this.paidAmount = paidAmount;
        this.payMethod = payMethod;
        this.status = status;
        this.impUid = impUid;
    }
}
