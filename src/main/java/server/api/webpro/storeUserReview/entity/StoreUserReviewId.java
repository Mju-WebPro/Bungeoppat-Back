package server.api.webpro.storeUserReview.entity;

import java.io.Serializable;

public class StoreUserReviewId implements Serializable {
    private Long storeId;
    private Long userId;
    private Long reviewId;

    @Override
    public int hashCode(){
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj){
        return super.equals(obj);
    }
}
