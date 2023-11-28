package server.api.webpro.review.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "Review")
public class Review {
    @Id
    @Column(name = "Review_id")
    private Long id;
    private String content;
    @Column(nullable = false)
    private Float starRating;
}