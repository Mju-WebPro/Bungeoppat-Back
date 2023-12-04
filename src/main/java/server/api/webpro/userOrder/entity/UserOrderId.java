package server.api.webpro.userOrder.entity;

import java.io.Serializable;

public class UserOrderId implements Serializable {
    private Long storeId;
    private Long userId;
    private Long reviewId;
    private Long paymentId;

    @Override
    public int hashCode(){
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj){
        return super.equals(obj);
    }
}
