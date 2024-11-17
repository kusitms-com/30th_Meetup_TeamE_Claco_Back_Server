package com.curateme.claco.concert.repository;

import com.curateme.claco.concert.domain.entity.Concert;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ConcertRepositoryTest {

    @Autowired
    private ConcertRepository concertRepository;

    @Test
    void testFindByIdIn() {
        // Given
        List<Long> ids = Stream.of("440", "441", "443")
            .map(Long::valueOf)
            .toList();

        PageRequest pageable = PageRequest.of(0, 10);

        // When
        Page<Concert> result = concertRepository.findByIdIn(ids, pageable);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getContent()).isNotEmpty();
    }

    @Test
    void testFindConcertIdsByFilters() {
        // Given
        String area = "서울특별시";
        LocalDate startDate = LocalDate.now().minusDays(30);
        LocalDate endDate = LocalDate.now().plusDays(30);
        List<String> categories = Arrays.asList("웅장한", "현대적인");

        // When
        List<Long> concertIds = concertRepository.findConcertIdsByFilters(area, startDate, endDate, categories);

        // Then
        assertThat(concertIds).isNotNull();
        assertThat(concertIds).isNotEmpty();
    }

    @Test
    void testFindConcertIdsBySearchQuery() {
        // Given
        String query = "연극";

        // When
        List<Long> concertIds = concertRepository.findConcertIdsBySearchQuery(query);

        // Then
        assertThat(concertIds).isNotNull();
        assertThat(concertIds).isNotEmpty();
    }

    @Test
    void testFindConcertById() {
        // Given
        Long concertId = Long.valueOf("445");

        // When
        Concert concert = concertRepository.findConcertById(concertId);

        // Then
        assertThat(concert).isNotNull();
        assertThat(concert.getId()).isEqualTo(concertId);
    }

    @Test
    void testFindConcertIdsByGenre() {
        // Given
        String genre = "연극";

        // When
        List<Long> concertIds = concertRepository.findConcertIdsByGenre(genre);

        // Then
        assertThat(concertIds).isNotNull();
        assertThat(concertIds).isNotEmpty();
    }
}
