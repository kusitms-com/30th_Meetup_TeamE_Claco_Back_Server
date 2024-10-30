package com.curateme.claco.concert.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "concert_category")
public class ConcertCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category")
    private String category;

    @Column(name = "score")
    private Double score;

    @ManyToOne
    @JoinColumn(name = "concertId", nullable = false)
    private Concert concert;

    public ConcertCategory(String category, Double score, Concert concert) {
        this.category = category;
        this.score = score;
        this.concert = concert;
    }
}
