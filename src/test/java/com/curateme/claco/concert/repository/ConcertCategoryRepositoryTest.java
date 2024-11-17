package com.curateme.claco.concert.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@Slf4j
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ConcertCategoryRepositoryTest {

    @Autowired
    private ConcertCategoryRepository concertCategoryRepository;

    @Test
    void testFindCategoryIdsByCategoryName() {
        // Given
        Long concertId = 1L;

        // When
        List<Long> categoryIds = concertCategoryRepository.findCategoryIdsByCategoryName(concertId);

        // Then
        assertThat(categoryIds).isNotNull();
        assertThat(categoryIds).containsExactlyInAnyOrder(Long.valueOf("1"), Long.valueOf("2"));
    }
}
