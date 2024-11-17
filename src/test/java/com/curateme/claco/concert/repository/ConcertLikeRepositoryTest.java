package com.curateme.claco.concert.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import org.springframework.data.domain.Pageable;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ConcertLikeRepositoryTest {

    @Autowired
    private ConcertLikeRepository concertLikeRepository;

    @Test
    void testExistsByConcertId() {
        // Given
        Long concertId = 1L;

        // When
        boolean exists = concertLikeRepository.existsByConcertId(concertId);

        // Then
        assertThat(exists).isTrue();
    }

    @Test
    void testFindMostRecentLikedConcert() {
        // Given
        Long userId = 1L;

        // When
        Long mostRecentLikedConcert = concertLikeRepository.findMostRecentLikedConcert(userId);

        // Then
        assertThat(mostRecentLikedConcert).isNotNull();
    }

    @Test
    void testFindConcertIdsByMemberId() {
        // Given
        Long userId = 1L;

        // When
        List<Long> concertIds = concertLikeRepository.findConcertIdsByMemberId(userId);

        // Then
        assertThat(concertIds).isNotNull();
        assertThat(concertIds).isNotEmpty();
    }

    @Test
    void testFindTopConcertIdsByLikeCount() {
        // Given
        int topCount = 5;

        // When
        List<Long> topConcertIds = concertLikeRepository.findTopConcertIdsByLikeCount(Pageable.ofSize(topCount));

        // Then
        assertThat(topConcertIds).isNotNull();
        assertThat(topConcertIds).hasSizeLessThanOrEqualTo(topCount);
    }
}
