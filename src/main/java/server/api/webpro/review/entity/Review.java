package server.api.webpro.review.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "Review")
public class Review {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Review_id")
    private Long id;
    private String content;
    private Float starRating;

    @Builder
    public Review(String content, Float starRating){
        this.content = content;
        this.starRating = starRating;
    }
}